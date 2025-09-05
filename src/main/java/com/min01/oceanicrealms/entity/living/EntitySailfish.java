package com.min01.oceanicrealms.entity.living;

import java.util.List;

import com.min01.oceanicrealms.entity.AbstractOceanicCreature;
import com.min01.oceanicrealms.entity.AgeableWaterAnimal;
import com.min01.oceanicrealms.entity.IAvoid;
import com.min01.oceanicrealms.entity.OceanicEntities;
import com.min01.oceanicrealms.entity.ai.goal.SailfishEatingGoal;
import com.min01.oceanicrealms.item.OceanicItems;
import com.min01.oceanicrealms.misc.SmoothAnimationState;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class EntitySailfish extends AbstractOceanicCreature implements Bucketable, IAvoid
{
	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(EntitySailfish.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> HUNGER_COOLDOWN = SynchedEntityData.defineId(EntitySailfish.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> IS_EAT = SynchedEntityData.defineId(EntitySailfish.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(EntitySailfish.class, EntityDataSerializers.BOOLEAN);
	
	public final AnimationState dryAnimationState = new AnimationState();
	public final SmoothAnimationState eatingAnimationState = new SmoothAnimationState();
	
	public EntitySailfish(EntityType<? extends AgeableWaterAnimal> p_33002_, Level p_33003_)
	{
		super(p_33002_, p_33003_);
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMonsterAttributes()
        		.add(Attributes.MAX_HEALTH, 40.0F)
        		.add(Attributes.FOLLOW_RANGE, 30.0F)
        		.add(Attributes.MOVEMENT_SPEED, 0.55F);
    }
    
    @Override
    protected void registerGoals() 
    {
    	super.registerGoals();
    	this.goalSelector.addGoal(4, new SailfishEatingGoal(this));
    	this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    	this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, WaterAnimal.class, false, OceanicUtil.TARGET_PREDICATE2)
    	{
    		@Override
    		public boolean canUse() 
    		{
    			return super.canUse() && EntitySailfish.this.isHungry();
    		}
    	});
    }
    
    @Override
    protected void defineSynchedData()
    {
    	super.defineSynchedData();
    	this.entityData.define(FROM_BUCKET, false);
    	this.entityData.define(VARIANT, this.random.nextInt(1, 3));
    	this.entityData.define(HUNGER_COOLDOWN, 0);
    	this.entityData.define(IS_EAT, false);
    }
    
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_)
    {
    	return OceanicEntities.SAILFISH.get().create(p_146743_);
    }
    
    @Override
    public boolean isFood(ItemStack p_27600_) 
    {
    	return p_27600_.is(ItemTags.FISHES);
    }
    
    @Override
    public EntityDimensions getDimensions(Pose p_21047_) 
    {
    	return this.isBaby() ? EntityDimensions.scalable(0.625F, 0.4375F) : super.getDimensions(p_21047_);
    }
    
    @Override
    public void tick() 
    {
    	super.tick();
		if(this.level.isClientSide)
		{
			this.dryAnimationState.animateWhen(!this.isInWater(), this.tickCount);
			this.eatingAnimationState.updateWhen(this.isUsingSkill(1), this.tickCount);
		}
		if(!this.isUsingSkill())
		{
			float size = 0.5F;
			Vec3 lookPos = OceanicUtil.getLookPos(this.getRotationVector(), this.position(), 0.0F, 0.0F, 1.2F);
			AABB aabb = new AABB(-size, -size, -size, size, 2.0F, size).move(lookPos);
			List<ItemEntity> list = this.level.getEntitiesOfClass(ItemEntity.class, aabb, item -> 
			{
				boolean flag = item.getItem().getFoodProperties(this) != null && item.getItem().getFoodProperties(this).isMeat();
				return item.getItem().is(ItemTags.FISHES) || flag;
			});
			if(!list.isEmpty())
			{
				ItemEntity item = list.get(0);
				this.setAnimationState(1);
				this.setAnimationTick(10);
				this.playSound(SoundEvents.GENERIC_EAT);
				item.discard();
			}
		}
		if(this.getAnimationTick() <= 0)
		{
			if(this.getAnimationState() == 1)
			{
				this.setAnimationState(0);
			}
		}
		if(this.getTarget() != null)
		{
			if(this.canMove())
			{
				this.getNavigation().moveTo(this.getTarget(), 0.7F);
				if(this.getFirstPassenger() != this.getTarget())
				{
					Vec3 lookPos = OceanicUtil.getLookPos(this.getRotationVector(), this.position(), 0.0F, 0.4F, 1.5F);
					if(lookPos.distanceTo(this.getTarget().position()) <= 1.5F)
					{
						this.getTarget().startRiding(this);
					}
				}
				else
				{
					if(this.getTarget().hurt(this.damageSources().mobAttack(this), 4.0F))
					{
						if(!this.getTarget().isAlive())
						{
							this.setHungerCooldown(3600);
							this.getNavigation().stop();
						}
					}
				}
			}
			if(this.canLook())
			{
				this.lookAt(Anchor.EYES, this.getTarget().getEyePosition());
			}
		}
		if(!this.isHungry())
		{
			this.setHungerCooldown(this.getHungerCooldown() - 1);
		}
    }
    
    @Override
    protected void positionRider(Entity p_19957_, MoveFunction p_19958_) 
    {
		Vec3 lookPos = OceanicUtil.getLookPos(this.getRotationVector(), this.position(), 0.0F, 0.4F, 1.5F);
    	p_19958_.accept(p_19957_, lookPos.x, lookPos.y, lookPos.z);
    }
    
    @Override
    public void addAdditionalSaveData(CompoundTag p_21484_)
    {
    	super.addAdditionalSaveData(p_21484_);
    	p_21484_.putInt("Variant", this.getVariant());
		p_21484_.putInt("Hunger", this.getHungerCooldown());
    }
    
    @Override
    public void readAdditionalSaveData(CompoundTag p_21450_) 
    {
    	super.readAdditionalSaveData(p_21450_);
    	if(p_21450_.contains("Variant"))
    	{
    		this.setVariant(p_21450_.getInt("Variant"));
    	}
		if(p_21450_.contains("Hunger"))
		{
			this.setHungerCooldown(p_21450_.getInt("Hunger"));
		}
    }
    
    @Override
    protected Component getTypeName()
    {
    	return this.isBaby() ? Component.translatable("entity.oceanicrealms.baby_sailfish") : super.getTypeName();
    }
    
	@Override
	public InteractionResult mobInteract(Player p_27477_, InteractionHand p_27478_)
    {
		if(this.isBaby())
		{
	    	return Bucketable.bucketMobPickup(p_27477_, p_27478_, this).orElse(super.mobInteract(p_27477_, p_27478_));
		}
		return super.mobInteract(p_27477_, p_27478_);
    }

    @SuppressWarnings("deprecation")
	@Override
	public void saveToBucketTag(ItemStack p_27494_)
    {
    	Bucketable.saveDefaultDataToBucketTag(this, p_27494_);
    	this.addAdditionalSaveData(p_27494_.getOrCreateTag());
    }

    @SuppressWarnings("deprecation")
	@Override
    public void loadFromBucketTag(CompoundTag p_148708_)
    {
    	Bucketable.loadDefaultDataFromBucketTag(this, p_148708_);
    	this.readAdditionalSaveData(p_148708_);
    }
    
	@Override
	public boolean fromBucket() 
	{
		return this.entityData.get(FROM_BUCKET);
	}

	@Override
	public void setFromBucket(boolean p_148834_)
	{
		if(p_148834_ && !this.isBaby())
		{
			this.setBaby(true);
		}
		this.entityData.set(FROM_BUCKET, p_148834_);
	}

	@Override
	public ItemStack getBucketItemStack() 
	{
		return new ItemStack(OceanicItems.BABY_SAILFISH_BUCKET.get());
	}

	@Override
	public SoundEvent getPickupSound()
	{
		return SoundEvents.BUCKET_FILL_FISH;
	}
    
	@Override
	public boolean canRandomSwim()
	{
		return super.canRandomSwim() && !this.isEat();
	}
	
	public boolean isHungry()
	{
		return this.getHungerCooldown() <= 0;
	}
	
	public void setEat(boolean value)
	{
		this.entityData.set(IS_EAT, value);
	}
	
	public boolean isEat()
	{
		return this.entityData.get(IS_EAT);
	}
	
	public void setHungerCooldown(int value)
	{
		this.entityData.set(HUNGER_COOLDOWN, value);
	}
	
	public int getHungerCooldown()
	{
		return this.entityData.get(HUNGER_COOLDOWN);
	}
    
    public void setVariant(int value)
    {
    	this.entityData.set(VARIANT, value);
    }
    
    public int getVariant()
    {
    	return this.entityData.get(VARIANT);
    }
}

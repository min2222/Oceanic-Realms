package com.min01.oceanicrealms.entity.living;

import com.min01.oceanicrealms.entity.AbstractOceanicCreature;
import com.min01.oceanicrealms.entity.AgeableWaterAnimal;
import com.min01.oceanicrealms.entity.IBoid;
import com.min01.oceanicrealms.entity.OceanicEntities;
import com.min01.oceanicrealms.entity.ai.goal.FishTemptGoal;
import com.min01.oceanicrealms.item.OceanicItems;
import com.min01.oceanicrealms.util.OceanicUtil;

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
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

public class EntityDolphinfish extends AbstractOceanicCreature implements Bucketable, IBoid
{
	public static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(EntityDolphinfish.class, EntityDataSerializers.BOOLEAN);
	
	public final AnimationState dryAnimationState = new AnimationState();
	
	public EntityDolphinfish(EntityType<? extends AgeableWaterAnimal> p_33002_, Level p_33003_)
	{
		super(p_33002_, p_33003_);
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMonsterAttributes()
        		.add(Attributes.MAX_HEALTH, 15.0F)
        		.add(Attributes.MOVEMENT_SPEED, 0.5F);
    }

    @Override
    protected void defineSynchedData()
    {
    	super.defineSynchedData();
    	this.entityData.define(FROM_BUCKET, false);
    }

    @Override
    protected void registerGoals()
    {
    	super.registerGoals();
    	this.goalSelector.addGoal(0, new FishTemptGoal(this, 1.25F, Ingredient.of(ItemTags.FISHES), false));
    }
    
    @Override
    public AgeableMob getBreedOffspring(ServerLevel p_146743_, AgeableMob p_146744_)
    {
    	return OceanicEntities.DOLPHINFISH.get().create(p_146743_);
    }
    
    @Override
    public boolean isFood(ItemStack p_27600_) 
    {
    	return p_27600_.is(ItemTags.FISHES);
    }
    
    @Override
    public EntityDimensions getDimensions(Pose p_21047_) 
    {
    	return this.isBaby() ? EntityDimensions.scalable(0.6875F, 0.3125F) : super.getDimensions(p_21047_);
    }
    
	@Override
	public void tick() 
	{
		super.tick();
		if(this.level.isClientSide)
		{
			this.dryAnimationState.animateWhen(!this.isInWater(), this.tickCount);
		}
		OceanicUtil.fishFlopping(this);
	}
	
    @Override
    protected Component getTypeName()
    {
    	return this.isBaby() ? Component.translatable("entity.oceanicrealms.baby_dolphinfish") : super.getTypeName();
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
		return new ItemStack(OceanicItems.BABY_DOLPHINFISH_BUCKET.get());
	}

	@Override
	public SoundEvent getPickupSound()
	{
		return SoundEvents.BUCKET_FILL_FISH;
	}
}

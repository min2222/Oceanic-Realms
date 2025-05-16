package com.min01.oceanicrealms.entity.living;

import com.min01.oceanicrealms.entity.AbstractOceanicCreature;
import com.min01.oceanicrealms.entity.ai.goal.BoidGoal;
import com.min01.oceanicrealms.entity.ai.goal.LimitSpeedAndLookInVelocityDirectionGoal;
import com.min01.oceanicrealms.entity.ai.goal.StayInWaterGoal;
import com.min01.oceanicrealms.item.OceanicItems;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EntitySilverPomfretFish extends AbstractOceanicCreature implements Bucketable
{	
	public static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(EntitySilverPomfretFish.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(EntitySilverPomfretFish.class, EntityDataSerializers.INT);
	
	public final AnimationState dryAnimationState = new AnimationState();
	
	public EntitySilverPomfretFish(EntityType<? extends WaterAnimal> p_33002_, Level p_33003_)
	{
		super(p_33002_, p_33003_);
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
        		.add(Attributes.MAX_HEALTH, 3.0F)
        		.add(Attributes.MOVEMENT_SPEED, 0.5F);
    }
    
    @Override
    protected void registerGoals() 
    {
    	super.registerGoals();
        this.goalSelector.addGoal(5, new BoidGoal(this, 0.5F, 0.9F, 8 / 20.0F, 1 / 20.0F));
        this.goalSelector.addGoal(3, new StayInWaterGoal(this));
        this.goalSelector.addGoal(2, new LimitSpeedAndLookInVelocityDirectionGoal(this, 0.3F, 0.5F));
    }
    
    @Override
    protected void defineSynchedData()
    {
    	super.defineSynchedData();
    	this.entityData.define(FROM_BUCKET, false);
    	this.entityData.define(VARIANT, this.random.nextInt(1, 3));
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
    public void addAdditionalSaveData(CompoundTag p_21484_)
    {
    	super.addAdditionalSaveData(p_21484_);
    	p_21484_.putInt("Variant", this.getVariant());
    }
    
    @Override
    public void readAdditionalSaveData(CompoundTag p_21450_) 
    {
    	super.readAdditionalSaveData(p_21450_);
    	if(p_21450_.contains("Variant"))
    	{
    		this.setVariant(p_21450_.getInt("Variant"));
    	}
    }

	@Override
    protected InteractionResult mobInteract(Player p_27477_, InteractionHand p_27478_)
    {
    	return Bucketable.bucketMobPickup(p_27477_, p_27478_, this).orElse(super.mobInteract(p_27477_, p_27478_));
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
		this.entityData.set(FROM_BUCKET, p_148834_);
	}

	@Override
	public ItemStack getBucketItemStack() 
	{
		return new ItemStack(OceanicItems.SILVER_POMFRET_FISH_BUCKET.get());
	}

	@Override
	public SoundEvent getPickupSound()
	{
		return SoundEvents.BUCKET_FILL_FISH;
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

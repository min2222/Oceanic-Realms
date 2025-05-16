package com.min01.oceanicrealms.entity.living;

import com.min01.oceanicrealms.entity.AbstractOceanicCreature;
import com.min01.oceanicrealms.entity.ai.goal.BoidGoal;
import com.min01.oceanicrealms.entity.ai.goal.LimitSpeedAndLookInVelocityDirectionGoal;
import com.min01.oceanicrealms.entity.ai.goal.StayInWaterGoal;
import com.min01.oceanicrealms.misc.OceanicTags;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class EntityTuna extends AbstractOceanicCreature
{
	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(EntityTuna.class, EntityDataSerializers.INT);
	
	public final AnimationState dryAnimationState = new AnimationState();
	
	public EntityTuna(EntityType<? extends WaterAnimal> p_33002_, Level p_33003_)
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
    protected void registerGoals() 
    {
        this.goalSelector.addGoal(5, new BoidGoal(this, 0.5F, 0.9F, 8 / 20.0F, 1 / 20.0F));
        this.goalSelector.addGoal(3, new StayInWaterGoal(this));
        this.goalSelector.addGoal(2, new LimitSpeedAndLookInVelocityDirectionGoal(this, 0.3F, 0.8F));
    }
    
    @Override
    protected void defineSynchedData()
    {
    	super.defineSynchedData();
    	this.entityData.define(VARIANT, 1);
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
	
	@SuppressWarnings("deprecation")
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, SpawnGroupData p_21437_, CompoundTag p_21438_) 
	{
    	if(p_21434_.getBiome(this.blockPosition()).is(OceanicTags.OceanicBiomes.COLD_OCEANS))
    	{
    		this.setVariant(2);
    	}
		return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
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
    
    public void setVariant(int value)
    {
    	this.entityData.set(VARIANT, value);
    }
    
    public int getVariant()
    {
    	return this.entityData.get(VARIANT);
    }
}

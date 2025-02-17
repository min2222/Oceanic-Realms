package com.min01.oceanicrealms.entity.living;

import javax.annotation.Nullable;

import com.min01.oceanicrealms.entity.AbstractAnimatableCreature;
import com.min01.oceanicrealms.network.OceanicNetwork;
import com.min01.oceanicrealms.network.UpdateCrabDancePacket;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;

public class EntityCrab extends AbstractAnimatableCreature
{
	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(EntityCrab.class, EntityDataSerializers.INT);
	
	public final AnimationState idleAnimationState = new AnimationState();
	public final AnimationState danceAnimationState = new AnimationState();
	
	public boolean dance;
	public boolean isDance;
	
	@Nullable
	public BlockPos jukebox;
	
	public EntityCrab(EntityType<? extends PathfinderMob> p_33002_, Level p_33003_) 
	{
		super(p_33002_, p_33003_);
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMonsterAttributes()
        		.add(Attributes.MAX_HEALTH, 5.0F)
        		.add(Attributes.MOVEMENT_SPEED, 0.45F);
    }
    
    @Override
    protected void defineSynchedData()
    {
    	super.defineSynchedData();
    	this.entityData.define(VARIANT, this.random.nextInt(1, 5));
    }
    
    @Override
    protected void registerGoals()
    {
    	this.goalSelector.addGoal(4, new RandomStrollGoal(this, 0.45F, 60)
    	{
    		@Override
    		public boolean canUse()
    		{
    			return super.canUse() && !EntityCrab.this.isDance;
    		}
    	});
    }
    
    @Override
    public void tick() 
    {
    	super.tick();
    	
    	if(this.level.isClientSide)
    	{
    		this.idleAnimationState.animateWhen(!this.walkAnimation.isMoving() && !this.dance, this.tickCount);
    		this.danceAnimationState.animateWhen(this.dance, this.tickCount);
    	}
    	
        if(this.jukebox == null || !this.jukebox.closerToCenterThan(this.position(), 3.46D) || !this.level.getBlockState(this.jukebox).is(Blocks.JUKEBOX))
        {
            this.dance = false;
            this.jukebox = null;
            if(this.level.isClientSide)
            {
                OceanicNetwork.sendToServer(new UpdateCrabDancePacket(this.getUUID(), this.dance));
            }
        }
        
        if(this.isDance)
        {
        	this.getNavigation().stop();
        }
    }
    
	public static boolean checkCrabSpawnRules(EntityType<EntityCrab> type, ServerLevelAccessor pServerLevel, MobSpawnType pMobSpawnType, BlockPos pPos, RandomSource pRandom) 
    {
		return pServerLevel.getBlockState(pPos.below()).is(Blocks.SAND);
    }
    
    @Override
    public void setRecordPlayingNearby(BlockPos p_29395_, boolean p_29396_) 
    {
        this.jukebox = p_29395_;
        this.dance = p_29396_;
        OceanicNetwork.sendToServer(new UpdateCrabDancePacket(this.getUUID(), this.dance));
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

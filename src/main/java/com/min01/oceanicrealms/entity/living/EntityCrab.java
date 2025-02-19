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
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;

public class EntityCrab extends AbstractAnimatableCreature
{
	public static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(EntityCrab.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Float> SIZE = SynchedEntityData.defineId(EntityCrab.class, EntityDataSerializers.FLOAT);
	
	public final AnimationState idleAnimationState = new AnimationState();
	public final AnimationState danceAnimationState = new AnimationState();
	public final AnimationState eatingAnimationState = new AnimationState();
	public final AnimationState digAnimationState = new AnimationState();
	public final AnimationState digOutAnimationState = new AnimationState();
	
	public boolean dance;
	public boolean isDance;
	
	@Nullable
	public BlockPos jukebox;
	
	public EntityCrab(EntityType<? extends PathfinderMob> p_33002_, Level p_33003_) 
	{
		super(p_33002_, p_33003_);
		this.setMaxUpStep(1.5F);
		this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
	}
	
    @Override
    public boolean removeWhenFarAway(double p_27598_)
    {
        return false;
    }
	
	@Override
	public MobType getMobType() 
	{
		return MobType.WATER;
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
    	this.entityData.define(SIZE, 0.7F + (float) Math.random() * (1.4F - 0.7F));
    }
    
    @Override
    protected void registerGoals()
    {
    	this.goalSelector.addGoal(4, new RandomStrollGoal(this, 0.45F, 60)
    	{
    		@Override
    		public boolean canUse()
    		{
    			return super.canUse() && !EntityCrab.this.isDance && EntityCrab.this.getAnimationState() == 0;
    		}
    	});
    	this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 10.0F, 0.8D, 0.8D)
    	{
    		@Override
    		public void start() 
    		{
    			EntityCrab.this.isDance = false;
    			super.start();
    		}
    	});
    }
    
	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> p_219422_) 
	{
        if(ANIMATION_STATE.equals(p_219422_) && this.level.isClientSide) 
        {
            switch(this.getAnimationState()) 
            {
        		case 0: 
        		{
        			this.stopAllAnimationStates();
        			break;
        		}
        		case 1: 
        		{
        			this.stopAllAnimationStates();
        			this.eatingAnimationState.start(this.tickCount);
        			break;
        		}
        		case 2: 
        		{
        			this.stopAllAnimationStates();
        			this.digAnimationState.start(this.tickCount);
        			break;
        		}
        		case 3: 
        		{
        			this.stopAllAnimationStates();
        			this.digOutAnimationState.start(this.tickCount);
        			break;
        		}
            }
        }
	}
	
	@Override
	public void stopAllAnimationStates() 
	{
		this.eatingAnimationState.stop();
		this.digAnimationState.stop();
		this.digOutAnimationState.stop();
	}
    
    @Override
    public void tick() 
    {
    	super.tick();
    	
    	this.refreshDimensions();
    	
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

        Player player = this.level.getNearestPlayer(this.getX(), this.getY(), this.getZ(), 5.0F, true);
        if(this.getAnimationState() == 0)
        {
            if(this.random.nextInt(500) == 0 && player == null)
            {
            	this.setAnimationState(1);
            	this.setAnimationTick(30);
            	this.getNavigation().stop();
            }
            if(player != null)
            {
            	this.setAnimationState(2);
            	this.setAnimationTick(20);
            }
        }
        
        if(this.getAnimationState() == 2)
        {
        	if(player == null)
        	{
        		this.moveTo(this.position().add(0.0F, 0.3F, 0.0F));
        		this.setAnimationState(3);
        		this.setAnimationTick(35);
        	}
        	else if(!this.isInWall())
        	{
            	this.setDeltaMovement(Vec3.ZERO);
            	this.moveTo(this.position().subtract(0.0F, (20 - this.getAnimationTick()) * 0.003F, 0.0F));
        	}
        }
        
        if(this.getAnimationState() == 3)
        {
        	if(!this.level.getBlockState(this.blockPosition()).isAir() && this.level.getFluidState(this.blockPosition()).isEmpty())
        	{
            	this.setDeltaMovement(Vec3.ZERO);
            	this.moveTo(this.position().add(0.0F, (35 - this.getAnimationTick()) * 0.003F, 0.0F));
        	}
        	else if(this.getAnimationTick() <= 0)
        	{
        		this.setAnimationState(0);
        	}
        }
        
        if(this.getAnimationTick() <= 0)
        {
        	if(this.getAnimationState() == 1)
        	{
        		this.setAnimationState(0);
        	}
        }
        
        if(this.isDance || this.getAnimationState() != 0)
        {
        	this.getNavigation().stop();
        }
    }
    
	public static boolean checkCrabSpawnRules(EntityType<EntityCrab> type, ServerLevelAccessor pServerLevel, MobSpawnType pMobSpawnType, BlockPos pPos, RandomSource pRandom) 
    {
		boolean isWater = pServerLevel.getBlockState(pPos).is(Blocks.WATER);
		boolean isLand = pServerLevel.getBlockState(pPos.below()).isCollisionShapeFullBlock(pServerLevel, pPos.below()) && pServerLevel.getBlockState(pPos.above()).isAir();
		return isWater || isLand;
    }
	
	@Override
	public boolean hurt(DamageSource p_21016_, float p_21017_) 
	{
		if(p_21016_.is(DamageTypes.IN_WALL))
		{
			return false;
		}
		return super.hurt(p_21016_, p_21017_);
	}
	
	@Override
	public boolean canBreatheUnderwater() 
	{
		return true;
	}
	
	@Override
	public boolean isPushedByFluid() 
	{
		return false;
	}
	
	@Override
	protected boolean isAffectedByFluids()
	{
		return false;
	}
    
    @Override
    public void setRecordPlayingNearby(BlockPos p_29395_, boolean p_29396_) 
    {
    	if(this.getAnimationState() == 0)
    	{
            this.jukebox = p_29395_;
            this.dance = p_29396_;
            OceanicNetwork.sendToServer(new UpdateCrabDancePacket(this.getUUID(), this.dance));
    	}
    }
    
    @Override
    public float getScale() 
    {
    	return this.getSize();
    }
    
    @Override
    public void addAdditionalSaveData(CompoundTag p_21484_)
    {
    	super.addAdditionalSaveData(p_21484_);
    	p_21484_.putInt("Variant", this.getVariant());
    	p_21484_.putFloat("Size", this.getSize());
    }
    
    @Override
    public void readAdditionalSaveData(CompoundTag p_21450_) 
    {
    	super.readAdditionalSaveData(p_21450_);
    	if(p_21450_.contains("Variant"))
    	{
    		this.setVariant(p_21450_.getInt("Variant"));
    	}
    	if(p_21450_.contains("Size"))
    	{
    		this.setSize(p_21450_.getFloat("Size"));
    	}
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, SpawnGroupData p_21437_, CompoundTag p_21438_) 
    {
    	if(p_21434_.getBiome(this.blockPosition()).is(Tags.Biomes.IS_SWAMP))
    	{
    		this.setVariant(5);
    	}
    	return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
    }
    
    public void setSize(float value)
    {
    	this.entityData.set(SIZE, value);
    }
    
    public float getSize()
    {
    	return this.entityData.get(SIZE);
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

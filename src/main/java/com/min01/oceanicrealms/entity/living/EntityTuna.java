package com.min01.oceanicrealms.entity.living;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.min01.oceanicrealms.entity.AbstractOceanicCreature;
import com.min01.oceanicrealms.entity.OceanicEntities;
import com.min01.oceanicrealms.misc.OceanicTags;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.pathfinder.Path;

public class EntityTuna extends AbstractOceanicCreature
{
	public static final EntityDataAccessor<Optional<UUID>> LEADER_UUID = SynchedEntityData.defineId(EntityTuna.class, EntityDataSerializers.OPTIONAL_UUID);
	public static final EntityDataAccessor<Boolean> IS_LEADER = SynchedEntityData.defineId(EntityTuna.class, EntityDataSerializers.BOOLEAN);
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
        		.add(Attributes.MOVEMENT_SPEED, 0.58F);
    }
    
    @Override
    protected void defineSynchedData()
    {
    	super.defineSynchedData();
    	this.entityData.define(LEADER_UUID, Optional.empty());
    	this.entityData.define(IS_LEADER, false);
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
		
		if(this.isLeader())
		{
			if(this.tickCount % 20 == 0)
			{
				List<EntityTuna> list = this.level.getEntitiesOfClass(EntityTuna.class, this.getBoundingBox().inflate(5.0F), t -> !t.isLeader() && t.getLeader() == null);
				list.forEach(t -> 
				{
					t.setLeader(this);
				});
			}
		}
		else if(this.getLeader() != null)
		{
			EntityTuna leader = this.getLeader();
			if(this.distanceTo(leader) > 2.5F)
			{
				this.getNavigation().moveTo(leader, 0.58F);
			}
			else
			{
				if(leader.getNavigation().getPath() != null)
				{
					BlockPos pos = leader.getNavigation().getPath().getTarget();
					Path path = this.getNavigation().createPath(pos, 1);
					this.getNavigation().moveTo(path, 0.58F);
				}
				if(leader.getTarget() != null)
				{
					this.setTarget(leader.getTarget());
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, SpawnGroupData p_21437_, CompoundTag p_21438_) 
	{
		if(p_21436_ == MobSpawnType.NATURAL)
		{
			this.setLeader(true);
			int schoolSize = this.random.nextInt(3, 7);
			for(int i = 0; i < schoolSize; i++)
			{
				EntityTuna tuna = new EntityTuna(OceanicEntities.TUNA.get(), this.level);
				tuna.setPos(this.position());
				tuna.setLeader(this);
		    	if(p_21434_.getBiome(tuna.blockPosition()).is(OceanicTags.OceanicBiomes.COLD_OCEANS))
		    	{
		    		tuna.setVariant(2);
		    	}
				this.level.addFreshEntity(tuna);
			}
		}
    	if(p_21434_.getBiome(this.blockPosition()).is(OceanicTags.OceanicBiomes.COLD_OCEANS))
    	{
    		this.setVariant(2);
    	}
		return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
	}
    
	@Override
	public int getBodyRotationSpeed() 
	{
		return 6;
	}
	
    @Override
    public void addAdditionalSaveData(CompoundTag p_21484_)
    {
    	super.addAdditionalSaveData(p_21484_);
    	p_21484_.putInt("Variant", this.getVariant());
		if(this.entityData.get(LEADER_UUID).isPresent())
		{
			p_21484_.putUUID("Leader", this.entityData.get(LEADER_UUID).get());
		}
		p_21484_.putBoolean("isLeader", this.isLeader());
    }
    
    @Override
    public void readAdditionalSaveData(CompoundTag p_21450_) 
    {
    	super.readAdditionalSaveData(p_21450_);
    	if(p_21450_.contains("Variant"))
    	{
    		this.setVariant(p_21450_.getInt("Variant"));
    	}
		if(p_21450_.hasUUID("Leader")) 
		{
			this.entityData.set(LEADER_UUID, Optional.of(p_21450_.getUUID("Leader")));
		}
		if(p_21450_.contains("isLeader")) 
		{
			this.setLeader(p_21450_.getBoolean("isLeader"));
		}
    }
    
    @Override
    public boolean canRandomSwim() 
    {
    	return super.canRandomSwim() && this.getLeader() == null;
    }
    
    public void setVariant(int value)
    {
    	this.entityData.set(VARIANT, value);
    }
    
    public int getVariant()
    {
    	return this.entityData.get(VARIANT);
    }
    
    public void setLeader(boolean value)
    {
    	this.entityData.set(IS_LEADER, value);
    }
    
    public boolean isLeader()
    {
    	return this.entityData.get(IS_LEADER);
    }
	
	public void setLeader(EntityTuna leader)
	{
		this.entityData.set(LEADER_UUID, Optional.of(leader.getUUID()));
	}
	
	@Nullable
	public EntityTuna getLeader() 
	{
		if(this.entityData.get(LEADER_UUID).isPresent()) 
		{
			return (EntityTuna) OceanicUtil.getEntityByUUID(this.level, this.entityData.get(LEADER_UUID).get());
		}
		return null;
	}
}

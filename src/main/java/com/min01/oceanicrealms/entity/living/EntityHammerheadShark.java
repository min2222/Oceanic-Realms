package com.min01.oceanicrealms.entity.living;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.min01.oceanicrealms.entity.AbstractOceanicShark;
import com.min01.oceanicrealms.entity.IAvoid;
import com.min01.oceanicrealms.entity.OceanicEntities;
import com.min01.oceanicrealms.misc.Boid;
import com.min01.oceanicrealms.misc.Boid.Bounds;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class EntityHammerheadShark extends AbstractOceanicShark
{	
	public static final EntityDataAccessor<Optional<UUID>> LEADER_UUID = SynchedEntityData.defineId(EntityHammerheadShark.class, EntityDataSerializers.OPTIONAL_UUID);
	public static final EntityDataAccessor<Boolean> IS_LEADER = SynchedEntityData.defineId(EntityHammerheadShark.class, EntityDataSerializers.BOOLEAN);

	public static final Vec3 BOUND_SIZE = new Vec3(4, 4, 4);
	
	public Bounds bounds;
	public final Collection<Boid.Obstacle> obstacles = new ArrayList<Boid.Obstacle>();
	public final Map<EntityHammerheadShark, Boid> boids = new HashMap<EntityHammerheadShark, Boid>();
	
	public final AnimationState attackAnimationState = new AnimationState();
	public final AnimationState eatingAnimationState = new AnimationState();
	
	public EntityHammerheadShark(EntityType<? extends WaterAnimal> p_33002_, Level p_33003_) 
	{
		super(p_33002_, p_33003_);
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMonsterAttributes()
        		.add(Attributes.MAX_HEALTH, 25.0F)
        		.add(Attributes.MOVEMENT_SPEED, 0.45F)
        		.add(Attributes.ATTACK_DAMAGE, 4.0F);
    }
    
    @Override
    protected void registerGoals() 
    {
    	super.registerGoals();
    	this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, WaterAnimal.class, false, t -> t.isInWater() && !(t instanceof Dolphin) && !(t instanceof AbstractOceanicShark) && !(t instanceof IAvoid))
    	{
    		@Override
    		public boolean canUse() 
    		{
    			return super.canUse() && EntityHammerheadShark.this.isHungry() && EntityHammerheadShark.this.getLeader() == null;
    		}
    	});
    }
    
    @Override
    protected void defineSynchedData()
    {
    	super.defineSynchedData();
    	this.entityData.define(LEADER_UUID, Optional.empty());
    	this.entityData.define(IS_LEADER, false);
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
        			this.attackAnimationState.start(this.tickCount);
        			break;
        		}
        		case 2: 
        		{
        			this.stopAllAnimationStates();
        			this.eatingAnimationState.start(this.tickCount);
        			break;
        		}
            }
        }
	}
	
	@Override
	public void stopAllAnimationStates() 
	{
		this.attackAnimationState.stop();
		this.eatingAnimationState.stop();
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		if(this.isLeader() && this.isInWater())
		{
			if(this.tickCount % 60 == 0)
			{
				this.recreateBounds();
			}
			
			for(Entry<EntityHammerheadShark, Boid> entry : this.boids.entrySet())
			{
				EntityHammerheadShark shark = entry.getKey();
				Boid boid = entry.getValue();
				Vec3 direction = boid.direction;
				BlockPos pos = BlockPos.containing(shark.position().add(direction));
				boid.update(this.boids.values(), this.obstacles, true, true, true, 2.5F, 0.25F);
				if(this.bounds != null)
				{
					boid.bounds = this.bounds;
				}
				while(shark.level.getBlockState(pos.above()).isAir())
				{
					direction = direction.subtract(0.0F, 0.5F, 0.0F);
					pos = BlockPos.containing(shark.position().add(direction));
				}
				shark.setDeltaMovement(direction);
				float yRot = -(float)(Mth.atan2(direction.x, direction.z) * (double)(180.0F / (float)Math.PI));
				float xRot = -(float)(Mth.atan2(direction.y, direction.horizontalDistance()) * (double)(180.0F / (float)Math.PI));
				shark.setYRot(OceanicUtil.rotlerp(this.getYRot(), yRot, (float)this.getBodyRotationSpeed()));
				shark.setYHeadRot(shark.getYRot());
				shark.setYBodyRot(shark.getYRot());
				shark.setXRot(OceanicUtil.rotlerp(this.getXRot(), xRot, (float)this.getBodyRotationSpeed()));
			}
			
			for(int x = -1; x < 1; x++) 
			{
				for(int y = -1; y < 1; y++)
				{
					for(int z = -1; z < 1; z++)
					{
						BlockPos pos = this.blockPosition().offset(x, y, z);
						if(this.level.getBlockState(pos).isCollisionShapeFullBlock(this.level, pos) || this.level.getBlockState(pos).isAir()) 
						{
							this.obstacles.add(new Boid.Obstacle(Vec3.atCenterOf(pos), 12, 0.1F));
						}
					}
				}
			}
		}
		
		if(this.bounds != null)
		{
			List<EntityGreatWhiteShark> list = this.level.getEntitiesOfClass(EntityGreatWhiteShark.class, this.getBoundingBox().inflate(2.0F));
			list.forEach(t -> 
			{
				if(this.bounds.contains(t.position()))
				{
					this.obstacles.add(new Boid.Obstacle(t.position(), 12, 0.1F));
				}
			});
		}
		
		if(!this.level.isClientSide)
		{
			if(this.getLeader() != null)
			{
				EntityHammerheadShark leader = this.getLeader();
				if(!leader.boids.containsKey(this))
				{
					Bounds bounds = Bounds.fromCenter(leader.position(), BOUND_SIZE);
					Vec3 pos = new Vec3(bounds.minX() + Math.random() * bounds.size.x, bounds.minY() + Math.random() * bounds.size.y, bounds.minZ() + Math.random() * bounds.size.z);
					leader.boids.put(this, new Boid(pos, bounds));
				}
			}
			if(this.isLeader())
			{
				if(!this.boids.containsKey(this))
				{
					Bounds bounds = Bounds.fromCenter(this.position(), BOUND_SIZE);
					Vec3 pos = new Vec3(bounds.minX() + Math.random() * bounds.size.x, bounds.minY() + Math.random() * bounds.size.y, bounds.minZ() + Math.random() * bounds.size.z);
					this.bounds = bounds;
					this.boids.put(this, new Boid(pos, bounds));
				}
			}
		}
	}
	
    public void recreateBounds() 
    {
        Level world = this.level;
        if(!this.hasTarget())
        {
            int radius = 8;
            
            for(int i = 0; i < 10; i++)
            {
            	Vec3 pos = OceanicUtil.getRandomPosition(this, radius);
            	HitResult hitResult = this.level.clip(new ClipContext(this.position(), pos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
            	if(hitResult instanceof BlockHitResult blockHit)
            	{
                    BlockPos targetPos = blockHit.getBlockPos();
                    BlockState blockState = world.getBlockState(targetPos);
                    BlockState blockState2 = world.getBlockState(targetPos.above());
                    
                    if(blockState.is(Blocks.WATER) && blockState2.is(Blocks.WATER))
                    {
        				this.bounds = Bounds.fromCenter(pos, BOUND_SIZE);
                    	break;
                    }
            	}
            }
        }
        else
        {
        	if(this.getTarget() != null)
        	{
    			this.bounds = Bounds.fromCenter(this.getTarget().position(), BOUND_SIZE);
        	}
        }
    }
    
    @Override
    public void die(DamageSource p_21014_) 
    {
    	super.die(p_21014_);
    	if(this.isLeader())
    	{
    		
    	}
    }
	
	@SuppressWarnings("deprecation")
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, SpawnGroupData p_21437_, CompoundTag p_21438_) 
	{
		this.setLeader(true);
		Bounds bounds = Bounds.fromCenter(this.position(), BOUND_SIZE);
		Vec3 pos = new Vec3(bounds.minX() + Math.random() * bounds.size.x, bounds.minY() + Math.random() * bounds.size.y, bounds.minZ() + Math.random() * bounds.size.z);
		this.boids.put(this, new Boid(pos, bounds));
		this.bounds = bounds;
		this.createBoid(pos, bounds);
		return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
	}
	
	public void createBoid(Vec3 pos, Bounds bounds)
	{
		int schoolSize = this.random.nextInt(1, 4);
		for(int i = 0; i < schoolSize; i++)
		{
			EntityHammerheadShark shark = new EntityHammerheadShark(OceanicEntities.HAMMERHEAD_SHARK.get(), this.level);
			shark.setPos(this.position());
			shark.setLeader(this);
			this.boids.put(shark, new Boid(pos, bounds));
			this.level.addFreshEntity(shark);
		}
	}
	
    @Override
    public void addAdditionalSaveData(CompoundTag p_21484_)
    {
    	super.addAdditionalSaveData(p_21484_);
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
    	return false;
    }
    
    @Override
    public float getHeadDistance() 
    {
    	return 0.8F;
    }
    
    public void setLeader(boolean value)
    {
    	this.entityData.set(IS_LEADER, value);
    }
    
    public boolean isLeader()
    {
    	return this.entityData.get(IS_LEADER);
    }
	
	public void setLeader(EntityHammerheadShark leader)
	{
		this.entityData.set(LEADER_UUID, Optional.of(leader.getUUID()));
	}
	
	@Nullable
	public EntityHammerheadShark getLeader() 
	{
		if(this.entityData.get(LEADER_UUID).isPresent()) 
		{
			return (EntityHammerheadShark) OceanicUtil.getEntityByUUID(this.level, this.entityData.get(LEADER_UUID).get());
		}
		return null;
	}
}

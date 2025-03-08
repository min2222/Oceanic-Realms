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

import com.min01.oceanicrealms.entity.AbstractOceanicCreature;
import com.min01.oceanicrealms.entity.AbstractOceanicShark;
import com.min01.oceanicrealms.entity.IAvoid;
import com.min01.oceanicrealms.entity.OceanicEntities;
import com.min01.oceanicrealms.item.OceanicItems;
import com.min01.oceanicrealms.misc.Boid;
import com.min01.oceanicrealms.misc.Boid.Bounds;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class EntityMackerelFish extends AbstractOceanicCreature implements Bucketable
{	
	public static final EntityDataAccessor<Optional<UUID>> LEADER_UUID = SynchedEntityData.defineId(EntityMackerelFish.class, EntityDataSerializers.OPTIONAL_UUID);
	public static final EntityDataAccessor<Boolean> IS_LEADER = SynchedEntityData.defineId(EntityMackerelFish.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(EntityMackerelFish.class, EntityDataSerializers.BOOLEAN);

	public static final Vec3 BOUND_SIZE = new Vec3(8, 8, 8);
	
	public Bounds bounds;
	public final Collection<Boid.Obstacle> obstacles = new ArrayList<Boid.Obstacle>();
	public final Map<EntityMackerelFish, Boid> boids = new HashMap<EntityMackerelFish, Boid>();
	
	public final AnimationState dryAnimationState = new AnimationState();
	
	public EntityMackerelFish(EntityType<? extends WaterAnimal> p_33002_, Level p_33003_)
	{
		super(p_33002_, p_33003_);
	}
    
    @Override
    protected void defineSynchedData()
    {
    	super.defineSynchedData();
    	this.entityData.define(LEADER_UUID, Optional.empty());
    	this.entityData.define(IS_LEADER, false);
    	this.entityData.define(FROM_BUCKET, false);
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
		
		if(this.isLeader() && this.isInWater())
		{
			if(this.tickCount % 60 == 0)
			{
				this.recreateBounds();
			}
			
			for(Entry<EntityMackerelFish, Boid> entry : this.boids.entrySet())
			{
				EntityMackerelFish fish = entry.getKey();
				Boid boid = entry.getValue();
				Vec3 direction = boid.direction;
				BlockPos blockPos = BlockPos.containing(fish.position().add(direction));
				boid.update(this.boids.values(), fish.obstacles, true, true, true, 2.5F, 0.25F);
				if(this.bounds != null)
				{
					boid.bounds = this.bounds;
				}
				while(fish.level.getBlockState(blockPos.above()).isAir())
				{
					direction = direction.subtract(0.0F, 0.5F, 0.0F);
					blockPos = BlockPos.containing(fish.position().add(direction));
				}
				fish.setDeltaMovement(direction);
				fish.setYRot(-(float)(Mth.atan2(direction.x, direction.z) * (double)(180.0F / (float)Math.PI)));
				fish.setYHeadRot(fish.getYRot());
				fish.setYBodyRot(fish.getYRot());
				fish.setXRot(-(float)(Mth.atan2(direction.y, direction.horizontalDistance()) * (double)(180.0F / (float)Math.PI)));
				
				for(int x = -1; x < 1; x++) 
				{
					for(int y = -1; y < 1; y++)
					{
						for(int z = -1; z < 1; z++)
						{
							BlockPos pos = fish.blockPosition().offset(x, y, z);
							if(this.level.getBlockState(pos).isCollisionShapeFullBlock(this.level, pos) || this.level.getBlockState(pos).isAir()) 
							{
								fish.obstacles.add(new Boid.Obstacle(Vec3.atCenterOf(pos), 5, 0.1F));
							}
						}
					}
				}
			}
		}
		
		if(this.bounds != null)
		{	
			List<AbstractOceanicCreature> list = this.level.getEntitiesOfClass(AbstractOceanicCreature.class, this.getBoundingBox().inflate(3.0F), t -> t instanceof AbstractOceanicShark || t instanceof IAvoid);
			list.forEach(t -> 
			{
				if(this.bounds.contains(t.position()))
				{
					this.obstacles.add(new Boid.Obstacle(t.position(), 5, 0.1F));
				}
			});
		}
		
		if(!this.level.isClientSide)
		{
			if(this.getLeader() != null)
			{
				EntityMackerelFish leader = this.getLeader();
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
        int radius = 8;
        
        for(int i = 0; i < 10; i++)
        {
        	Vec3 pos = OceanicUtil.getRandomPosition(this, radius);
        	HitResult hitResult = this.level.clip(new ClipContext(this.position(), pos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
        	if(hitResult instanceof BlockHitResult blockHit)
        	{
                BlockPos targetPos = blockHit.getBlockPos();
                BlockState blockState = world.getBlockState(targetPos);
                
                if(blockState.is(Blocks.WATER))
                {
    				this.bounds = Bounds.fromCenter(pos, BOUND_SIZE);
                	break;
                }
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
		if(!this.fromBucket() && p_21436_ != MobSpawnType.BUCKET)
		{
			this.setLeader(true);
			Bounds bounds = Bounds.fromCenter(this.position(), BOUND_SIZE);
			Vec3 pos = new Vec3(bounds.minX() + Math.random() * bounds.size.x, bounds.minY() + Math.random() * bounds.size.y, bounds.minZ() + Math.random() * bounds.size.z);
			this.boids.put(this, new Boid(pos, bounds));
			this.bounds = bounds;
			this.createBoid(pos, bounds);
		}
		return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
	}
	
	public void createBoid(Vec3 pos, Bounds bounds)
	{
		int schoolSize = this.random.nextInt(6, 11);
		for(int i = 0; i < schoolSize; i++)
		{
			EntityMackerelFish fish = new EntityMackerelFish(OceanicEntities.MACKEREL_FISH.get(), this.level);
			fish.setPos(this.position());
			fish.setLeader(this);
			this.boids.put(fish, new Boid(pos, bounds));
			this.level.addFreshEntity(fish);
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
    protected InteractionResult mobInteract(Player p_27477_, InteractionHand p_27478_)
    {
    	return Bucketable.bucketMobPickup(p_27477_, p_27478_, this).orElse(super.mobInteract(p_27477_, p_27478_));
    }

    @SuppressWarnings("deprecation")
	@Override
	public void saveToBucketTag(ItemStack p_27494_)
    {
    	Bucketable.saveDefaultDataToBucketTag(this, p_27494_);
    }

    @SuppressWarnings("deprecation")
	@Override
    public void loadFromBucketTag(CompoundTag p_148708_)
    {
    	Bucketable.loadDefaultDataFromBucketTag(this, p_148708_);
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
		return new ItemStack(OceanicItems.MACKEREL_FISH_BUCKET.get());
	}

	@Override
	public SoundEvent getPickupSound()
	{
		return SoundEvents.BUCKET_FILL_FISH;
	}
    
    @Override
    public boolean canRandomSwim() 
    {
    	return false;
    }
    
    public void setLeader(boolean value)
    {
    	this.entityData.set(IS_LEADER, value);
    }
    
    public boolean isLeader()
    {
    	return this.entityData.get(IS_LEADER);
    }
	
	public void setLeader(EntityMackerelFish leader)
	{
		this.entityData.set(LEADER_UUID, Optional.of(leader.getUUID()));
	}
	
	@Nullable
	public EntityMackerelFish getLeader() 
	{
		if(this.entityData.get(LEADER_UUID).isPresent()) 
		{
			return (EntityMackerelFish) OceanicUtil.getEntityByUUID(this.level, this.entityData.get(LEADER_UUID).get());
		}
		return null;
	}
}

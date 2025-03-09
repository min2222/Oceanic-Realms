package com.min01.oceanicrealms.util;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.joml.Math;

import com.min01.oceanicrealms.entity.AbstractOceanicCreature;
import com.min01.oceanicrealms.entity.IBoid;
import com.min01.oceanicrealms.misc.Boid;
import com.min01.oceanicrealms.misc.Boid.Bounds;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.LogicalSidedProvider;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

public class OceanicUtil
{
	public static <T extends LivingEntity & IBoid<T>> void avoid(T entity, Bounds bounds, Collection<Boid.Obstacle> obstacles, float radius, Predicate<? super Entity> predicate)
	{
		if(bounds != null)
		{
			List<AbstractOceanicCreature> list = entity.level.getEntitiesOfClass(AbstractOceanicCreature.class, entity.getBoundingBox().inflate(radius), predicate);
			list.forEach(t -> 
			{
				if(bounds.contains(t.position()))
				{
					obstacles.add(new Boid.Obstacle(t.position(), 5, 0.1F));
				}
			});
		}
	}
	
	public static <T extends LivingEntity & IBoid<T>> void loadBoid(T entity) 
	{
		if(!entity.level.isClientSide)
		{
			if(entity.getLeader() != null)
			{
				T leader = entity.getLeader();
				if(!leader.getBoid().containsKey(entity))
				{
					Bounds bounds = Bounds.fromCenter(leader.position(), entity.getBoundSize());
					Vec3 pos = new Vec3(bounds.minX() + Math.random() * bounds.size.x, bounds.minY() + Math.random() * bounds.size.y, bounds.minZ() + Math.random() * bounds.size.z);
					leader.getBoid().put(entity, new Boid(pos, bounds));
				}
			}
			
			if(entity.isLeader())
			{
				if(!entity.getBoid().containsKey(entity))
				{
					Bounds bounds = Bounds.fromCenter(entity.position(), entity.getBoundSize());
					Vec3 pos = new Vec3(bounds.minX() + Math.random() * bounds.size.x, bounds.minY() + Math.random() * bounds.size.y, bounds.minZ() + Math.random() * bounds.size.z);
					entity.setBound(bounds);
					entity.getBoid().put(entity, new Boid(pos, bounds));
				}
			}
		}
	}
	
    public static <T extends AbstractOceanicCreature & IBoid<T>> void tickBoid(T entity, Bounds bounds, Map<T, Boid> boids) 
    {
		for(Entry<T, Boid> entry : boids.entrySet())
		{
			BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
			T fish = entry.getKey();
			Boid boid = entry.getValue();
			Vec3 direction = boid.direction;
			BlockPos blockPos = BlockPos.containing(fish.position().add(direction));
			boid.update(boids.values(), fish.getObstacle(), true, true, true, 2.5F, 0.25F);
			if(bounds != null)
			{
				boid.bounds = bounds;
			}
			while(fish.level.getBlockState(blockPos.above()).isAir())
			{
				direction = direction.subtract(0.0F, 0.5F, 0.0F);
				blockPos = BlockPos.containing(fish.position().add(direction));
			}
			if(fish.rotLerp())
			{
				float yRot = -(float)(Mth.atan2(direction.x, direction.z) * (double)(180.0F / (float)Math.PI));
				float xRot = -(float)(Mth.atan2(direction.y, direction.horizontalDistance()) * (double)(180.0F / (float)Math.PI));
				fish.setYRot(OceanicUtil.rotlerp(fish.getYRot(), yRot, (float)fish.getBodyRotationSpeed()));
				fish.setYHeadRot(fish.getYRot());
				fish.setYBodyRot(fish.getYRot());
				fish.setXRot(OceanicUtil.rotlerp(fish.getXRot(), xRot, 65));
				if(!mutable.equals(BlockPos.ZERO))
				{
					boolean lerpDone = Math.abs(fish.getYRot() - yRot) < 0.01F && Math.abs(fish.getXRot() - xRot) < 0.01F;
					if(lerpDone)
					{
						fish.setDeltaMovement(direction);
						mutable.set(BlockPos.ZERO);
					}
				}
				else
				{
					fish.setDeltaMovement(direction);
				}
			}
			else
			{
				fish.setDeltaMovement(direction);
				fish.setYRot(-(float)(Mth.atan2(direction.x, direction.z) * (double)(180.0F / (float)Math.PI)));
				fish.setYHeadRot(fish.getYRot());
				fish.setYBodyRot(fish.getYRot());
				fish.setXRot(-(float)(Mth.atan2(direction.y, direction.horizontalDistance()) * (double)(180.0F / (float)Math.PI)));
			}
			
			for(int x = -1; x < 1; x++) 
			{
				for(int y = -1; y < 1; y++)
				{
					for(int z = -1; z < 1; z++)
					{
						BlockPos pos = fish.blockPosition().offset(x, y, z);
						if(entity.level.getBlockState(pos).isCollisionShapeFullBlock(entity.level, pos) || entity.level.getBlockState(pos).isAir()) 
						{
							fish.getObstacle().add(new Boid.Obstacle(Vec3.atCenterOf(pos), 5, 0.1F));
							mutable.set(pos);
						}
					}
				}
			}
		}
    }
    
    public static <T extends LivingEntity & IBoid<T>> void recreateBounds(T entity, int radius) 
    {
        Level world = entity.level;
        
        for(int i = 0; i < 10; i++)
        {
        	Vec3 pos = OceanicUtil.getRandomPosition(entity, radius);
        	HitResult hitResult = entity.level.clip(new ClipContext(entity.position(), pos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity));
        	if(hitResult instanceof BlockHitResult blockHit)
        	{
                BlockPos targetPos = blockHit.getBlockPos();
                BlockState blockState = world.getBlockState(targetPos);
                
                if(blockState.is(Blocks.WATER))
                {
    				entity.setBound(Bounds.fromCenter(pos, entity.getBoundSize()));
                	break;
                }
        	}
        }
    }
    
	public static <T extends LivingEntity & IBoid<T>> void spawnWithBoid(T entity, int schoolSize)
	{
		entity.setLeader(true);
		Bounds bounds = Bounds.fromCenter(entity.position(), entity.getBoundSize());
		Vec3 pos = new Vec3(bounds.minX() + Math.random() * bounds.size.x, bounds.minY() + Math.random() * bounds.size.y, bounds.minZ() + Math.random() * bounds.size.z);
		entity.getBoid().put(entity, new Boid(pos, bounds));
		entity.setBound(bounds);
		createBoid(pos, bounds, schoolSize, entity);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends LivingEntity & IBoid<T>> void createBoid(Vec3 pos, Bounds bounds, int schoolSize, T entity)
	{
		for(int i = 0; i < schoolSize; i++)
		{
			T fish = (T) entity.getType().create(entity.level);
			fish.setPos(entity.position());
			fish.setLeader(entity);
			entity.getBoid().put(fish, new Boid(pos, bounds));
			entity.level.addFreshEntity(fish);
		}
	}
	
	public static Vec3 fromToVector(Vec3 from, Vec3 to, float scale)
	{
		Vec3 motion = to.subtract(from).normalize();
		return motion.scale(scale);
	}
	
	public static Vec3 fromToVector(Vec3 from, Vec3 to)
	{
		Vec3 motion = to.subtract(from).normalize();
		return motion;
	}
	
	public static void fishFlopping(LivingEntity entity)
	{
		fishFlopping(entity, SoundEvents.COD_FLOP, 1.0F, 0.5F);
	}
	
	public static void fishFlopping(LivingEntity entity, SoundEvent flopSound, float volume, float yMotion)
	{
        if(!entity.isInWater() && entity.onGround() && entity.verticalCollision) 
        {
        	entity.setDeltaMovement(entity.getDeltaMovement().add((double)((entity.getRandom().nextFloat() * 2.0F - 1.0F) * 0.05F), yMotion, (double)((entity.getRandom().nextFloat() * 2.0F - 1.0F) * 0.05F)));
        	entity.setOnGround(false);
        	entity.hasImpulse = true;
        	entity.playSound(flopSound, volume, entity.getVoicePitch());
        }
	}
	
	@SuppressWarnings("unchecked")
	public static Entity getEntityByUUID(Level level, UUID uuid)
	{
		Method m = ObfuscationReflectionHelper.findMethod(Level.class, "m_142646_");
		try 
		{
			LevelEntityGetter<Entity> entities = (LevelEntityGetter<Entity>) m.invoke(level);
			return entities.get(uuid);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static void getClientLevel(Consumer<Level> consumer)
	{
		LogicalSidedProvider.CLIENTWORLD.get(LogicalSide.CLIENT).filter(ClientLevel.class::isInstance).ifPresent(level -> 
		{
			consumer.accept(level);
		});
	}
	
	public static Vec3 getRandomPosition(Entity entity, int range)
	{
    	Vec3 vec3 = entity.position().add(Mth.randomBetweenInclusive(entity.level.random, -range, range), Mth.randomBetweenInclusive(entity.level.random, -range, range), Mth.randomBetweenInclusive(entity.level.random, -range, range));
        return vec3;
	}
	
	public static Vec3 getSpreadPosition(Entity entity, double range)
	{
        double x = (double) entity.getX() + (entity.level.random.nextDouble() - entity.level.random.nextDouble()) * (double)range + 0.5D;
        double y = (double) entity.getY() + (entity.level.random.nextDouble() - entity.level.random.nextDouble()) * (double)range + 0.5D;
        double z = (double) entity.getZ() + (entity.level.random.nextDouble() - entity.level.random.nextDouble()) * (double)range + 0.5D;
        return new Vec3(x, y, z);
	}
	
	public static Vec3 getLookPos(Vec2 rotation, Vec3 position, double left, double up, double forwards) 
	{
		Vec2 vec2 = rotation;
		Vec3 vec3 = position;
		float f = Mth.cos((vec2.y + 90.0F) * ((float)Math.PI / 180.0F));
		float f1 = Mth.sin((vec2.y + 90.0F) * ((float)Math.PI / 180.0F));
		float f2 = Mth.cos(-vec2.x * ((float)Math.PI / 180.0F));
		float f3 = Mth.sin(-vec2.x * ((float)Math.PI / 180.0F));
		float f4 = Mth.cos((-vec2.x + 90.0F) * ((float)Math.PI / 180.0F));
		float f5 = Mth.sin((-vec2.x + 90.0F) * ((float)Math.PI / 180.0F));
		Vec3 vec31 = new Vec3((double)(f * f2), (double)f3, (double)(f1 * f2));
		Vec3 vec32 = new Vec3((double)(f * f4), (double)f5, (double)(f1 * f4));
		Vec3 vec33 = vec31.cross(vec32).scale(-1.0D);
		double d0 = vec31.x * forwards + vec32.x * up + vec33.x * left;
		double d1 = vec31.y * forwards + vec32.y * up + vec33.y * left;
		double d2 = vec31.z * forwards + vec32.z * up + vec33.z * left;
		return new Vec3(vec3.x + d0, vec3.y + d1, vec3.z + d2);
	}
	
	public static float rotlerp(float p_24992_, float p_24993_, float p_24994_)
	{
		float f = Mth.wrapDegrees(p_24993_ - p_24992_);
		
		if(f > p_24994_) 
		{
			f = p_24994_;
		}

		if(f < -p_24994_) 
		{
			f = -p_24994_;
		}

		float f1 = p_24992_ + f;
		
		if(f1 < 0.0F)
		{
			f1 += 360.0F;
		}
		else if(f1 > 360.0F)
		{
			f1 -= 360.0F;
		}
		
		return f1;
	}
}

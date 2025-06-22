package com.min01.oceanicrealms.entity.living;

import com.min01.oceanicrealms.entity.OceanicEntities;
import com.min01.oceanicrealms.misc.WormChain;
import com.min01.oceanicrealms.misc.WormChain.Worm;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;

public class EntityOarfishHead extends AbstractOarfishPart
{
	public static final EntityDataAccessor<Integer> MAX_LENGTH = SynchedEntityData.defineId(EntityOarfishHead.class, EntityDataSerializers.INT);
	public Worm[] worms;
	public EntityOarfishHead(EntityType<? extends WaterAnimal> p_33002_, Level p_33003_) 
	{
		super(p_33002_, p_33003_);
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMonsterAttributes()
    			.add(Attributes.MAX_HEALTH, 50.0F)
    			.add(Attributes.MOVEMENT_SPEED, 0.35F);
    }
    
    @Override
    protected void defineSynchedData() 
    {
    	super.defineSynchedData();
    	this.entityData.define(MAX_LENGTH, 0);
    }
	
	@Override
	public boolean isHead() 
	{
		return true;
	}
	
	@Override
	public int getBodyRotationSpeed() 
	{
		return 6;
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		if(this.worms == null)
		{
			Worm[] worms = new Worm[this.getMaxLength()];
			for(int i = 0; i < worms.length; i++) 
			{
			    worms[i] = new Worm();
			}
			this.worms = worms;
		}
		else
		{
			for(int i = 0; i < this.worms.length; i++)
			{
				float speed = 0.08F;
				float distance = 1.35F;
				Worm worm = this.worms[i];
				if(worm != null)
				{
					worm.setOldPosAndRot();
					if(i == 0)
					{
						WormChain.tick(worm, this, 1.3F, speed);
					}
					else
					{
						Worm parent = this.worms[i - 1];
						if(parent != null)
						{
							WormChain.tick(worm, parent, distance, speed);
						}
					}
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, SpawnGroupData p_21437_, CompoundTag p_21438_)
	{
		int length = this.random.nextInt(8, 13);
		this.setMaxLength(length);
		AbstractOarfishPart prev = this;
		for(int i = 0; i < length; i++)
		{
			if(i < length - 1)
			{
				EntityOarfishBody body = new EntityOarfishBody(OceanicEntities.OARFISH_BODY.get(), this.level);
				body.setPos(this.position());
				body.setOwner(prev);
				body.setHead(this);
				body.setIndex(i);
				prev = body;
				this.level.addFreshEntity(body);
			}
			else
			{
				EntityOarfishTail tail = new EntityOarfishTail(OceanicEntities.OARFISH_TAIL.get(), this.level);
				tail.setPos(this.position());
				tail.setOwner(prev);
				tail.setHead(this);
				tail.setIndex(i);
				this.level.addFreshEntity(tail);
			}
		}
		return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
	}
	
	@Override
	public Vec3 getSwimRadius()
	{
		return new Vec3(15, 4, 15);
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag p_37265_)
	{
		super.addAdditionalSaveData(p_37265_);
		p_37265_.putInt("MaxLength", this.getMaxLength());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag p_37262_)
	{
		super.readAdditionalSaveData(p_37262_);
		if(p_37262_.contains("MaxLength"))
		{
			this.setMaxLength(p_37262_.getInt("MaxLength"));
		}
	}
	
	public void setMaxLength(int value)
	{
		this.entityData.set(MAX_LENGTH, value);
	}
	
	public int getMaxLength()
	{
		return this.entityData.get(MAX_LENGTH);
	}
}

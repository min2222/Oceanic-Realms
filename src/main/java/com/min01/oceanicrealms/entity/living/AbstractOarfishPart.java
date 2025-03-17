package com.min01.oceanicrealms.entity.living;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.min01.oceanicrealms.entity.AbstractOwnableOceanicCreature;
import com.min01.oceanicrealms.misc.WormChain.Worm;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractOarfishPart extends AbstractOwnableOceanicCreature<AbstractOarfishPart>
{
	public static final EntityDataAccessor<Optional<UUID>> HEAD_UUID = SynchedEntityData.defineId(AbstractOarfishPart.class, EntityDataSerializers.OPTIONAL_UUID);
	public static final EntityDataAccessor<Integer> INDEX = SynchedEntityData.defineId(AbstractOarfishPart.class, EntityDataSerializers.INT);
	
	public AbstractOarfishPart(EntityType<? extends WaterAnimal> p_33002_, Level p_33003_)
	{
		super(p_33002_, p_33003_);
	}
	
	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(HEAD_UUID, Optional.empty());
		this.entityData.define(INDEX, 0);
	}
	
	public boolean isHead()
	{
		return false;
	}
	
	@Override
	protected void registerGoals() 
	{
		if(this.isHead())
		{
			super.registerGoals();
		}
	}
	
	@Override
	protected void doPush(Entity p_21294_)
	{
		if(!(p_21294_ instanceof AbstractOarfishPart))
		{
			super.doPush(p_21294_);
		}
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		
		this.resetFallDistance();
		
		if(this.getOwner() != null)
		{
    		this.hurtTime = this.getOwner().hurtTime;
    		this.deathTime = this.getOwner().deathTime;
		}
		else if(!this.isHead())
		{
			this.discard();
		}
		
		if(this.getHead() != null)
		{
			EntityOarfishHead head = this.getHead();
			if(head.worms != null)
			{
				Worm worm = head.worms[this.getIndex()];
				if(worm != null)
				{
					Vec3 pos = head.position().add(worm.position());
					Vec2 rot = worm.getRot(1.0F);
					this.setPos(pos);
					this.setXRot(rot.x);
					this.setYRot(rot.y);
					this.setYHeadRot(rot.y);
					this.setYBodyRot(rot.y);
				}
			}
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag p_37265_) 
	{
		super.addAdditionalSaveData(p_37265_);
		if(this.entityData.get(HEAD_UUID).isPresent())
		{
			p_37265_.putUUID("Head", this.entityData.get(HEAD_UUID).get());
		}
		p_37265_.putInt("Index", this.getIndex());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag p_37262_) 
	{
		super.readAdditionalSaveData(p_37262_);
		if(p_37262_.hasUUID("Head")) 
		{
			this.entityData.set(HEAD_UUID, Optional.of(p_37262_.getUUID("Head")));
		}
		if(p_37262_.contains("Index"))
		{
			this.setIndex(p_37262_.getInt("Index"));
		}
	}
    
    @Override
    public boolean isInvulnerableTo(DamageSource p_20122_)
    {
    	return super.isInvulnerableTo(p_20122_) || p_20122_.is(DamageTypes.IN_WALL) || p_20122_.is(DamageTypeTags.IS_FALL);
    }
	
	public void setHead(EntityOarfishHead head)
	{
		this.entityData.set(HEAD_UUID, Optional.of(head.getUUID()));
	}
	
	@Nullable
	public EntityOarfishHead getHead() 
	{
		if(this.entityData.get(HEAD_UUID).isPresent()) 
		{
			return (EntityOarfishHead) OceanicUtil.getEntityByUUID(this.level, this.entityData.get(HEAD_UUID).get());
		}
		return null;
	}

	public void setIndex(int value)
	{
		this.entityData.set(INDEX, value);
	}
	
	public int getIndex()
	{
		return this.entityData.get(INDEX);
	}
}

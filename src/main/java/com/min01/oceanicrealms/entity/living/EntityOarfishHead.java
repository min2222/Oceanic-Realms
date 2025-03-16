package com.min01.oceanicrealms.entity.living;

import com.min01.oceanicrealms.entity.OceanicEntities;

import net.minecraft.nbt.CompoundTag;
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

public class EntityOarfishHead extends AbstractOarfishPart
{
	public EntityOarfishHead(EntityType<? extends WaterAnimal> p_33002_, Level p_33003_) 
	{
		super(p_33002_, p_33003_);
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMonsterAttributes()
    			.add(Attributes.MAX_HEALTH, 50.0F)
    			.add(Attributes.MOVEMENT_SPEED, 0.45F);
    }
	
	@Override
	public boolean isHead() 
	{
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_21434_, DifficultyInstance p_21435_, MobSpawnType p_21436_, SpawnGroupData p_21437_, CompoundTag p_21438_)
	{
		AbstractOarfishPart prev = this;
		int length = this.random.nextInt(8, 13);
		for(int i = 0; i < length; i++)
		{
			if(i < length - 1)
			{
				EntityOarfishBody body = new EntityOarfishBody(OceanicEntities.OARFISH_BODY.get(), this.level);
				body.setPos(this.position());
				body.setOwner(prev);
				prev = body;
				this.level.addFreshEntity(body);
			}
			else
			{
				EntityOarfishTail tail = new EntityOarfishTail(OceanicEntities.OARFISH_TAIL.get(), this.level);
				tail.setPos(this.position());
				tail.setOwner(prev);
				this.level.addFreshEntity(tail);
			}
		}
		return super.finalizeSpawn(p_21434_, p_21435_, p_21436_, p_21437_, p_21438_);
	}
}

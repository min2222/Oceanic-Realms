package com.min01.oceanicrealms.misc;

import com.min01.oceanicrealms.OceanicRealms;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OceanicEntityDataSerializers 
{
	public static final DeferredRegister<EntityDataSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.ENTITY_DATA_SERIALIZERS, OceanicRealms.MODID);
	
	public static final RegistryObject<EntityDataSerializer<Vec3>> VEC3 = SERIALIZERS.register("serializer_vec3", () -> EntityDataSerializer.simple(OceanicEntityDataSerializers::writeVec3, OceanicEntityDataSerializers::readVec3));

	public static CompoundTag writeVec3(CompoundTag tag, Vec3 vec)
	{
		tag.putDouble("X", vec.x);
		tag.putDouble("Y", vec.y);
		tag.putDouble("Z", vec.z);
		return tag;
	}
	
	public static Vec3 readVec3(CompoundTag tag)
	{
		return new Vec3(tag.getDouble("X"), tag.getDouble("Y"), tag.getDouble("Z"));
	}
	
	public static ByteBuf writeVec3(FriendlyByteBuf buf, Vec3 vec)
	{
		buf.writeDouble(vec.x);
		buf.writeDouble(vec.y);
		buf.writeDouble(vec.z);
		return buf;
	}
	
	public static Vec3 readVec3(ByteBuf buf)
	{
		return new Vec3(buf.readDouble(), buf.readDouble(), buf.readDouble());
	}
}

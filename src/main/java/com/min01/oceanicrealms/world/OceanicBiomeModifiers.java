package com.min01.oceanicrealms.world;

import com.min01.oceanicrealms.OceanicRealms;
import com.mojang.serialization.Codec;

import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OceanicBiomeModifiers
{
	public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, OceanicRealms.MODID);
	
	public static final RegistryObject<Codec<? extends BiomeModifier>> OCEANIC_MODIFIERS = BIOME_MODIFIERS.register("oceanic_modifiers", OceanicBiomeModifier::makeCodec);
}

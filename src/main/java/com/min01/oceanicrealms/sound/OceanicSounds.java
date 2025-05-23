package com.min01.oceanicrealms.sound;

import com.min01.oceanicrealms.OceanicRealms;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OceanicSounds
{
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, OceanicRealms.MODID);

	public static final RegistryObject<SoundEvent> SHARK_EATING = registerSound("shark_eating");
	
	private static RegistryObject<SoundEvent> registerSound(String name) 
	{
		return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(OceanicRealms.MODID, name)));
    }
}

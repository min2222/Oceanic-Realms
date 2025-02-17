package com.min01.oceanicrealms.misc;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.item.OceanicItems;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class OceanicCreativeTabs 
{
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OceanicRealms.MODID);

    public static final RegistryObject<CreativeModeTab> OCEANIC_REALMS = CREATIVE_MODE_TAB.register("oceanicrealms", () -> CreativeModeTab.builder()
    		.title(Component.translatable("itemGroup.oceanicrealms.oceanicrealms"))
    		.icon(() -> new ItemStack(OceanicItems.CRAB_CLAW.get()))
    		.displayItems((enabledFeatures, output) -> 
    		{
    			output.accept(OceanicItems.GREAT_WHITE_SHARK_SPAWN_EGG.get());
    			output.accept(OceanicItems.CRAB_SPAWN_EGG.get());
    			output.accept(OceanicItems.CRAB_CLAW.get());
    			output.accept(OceanicItems.COOKED_CRAB_CLAW.get());
    		}).build());
}

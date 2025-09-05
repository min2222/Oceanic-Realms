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
    		.title(Component.translatable("itemGroup.oceanicrealms"))
    		.icon(() -> new ItemStack(OceanicItems.CRAB_CLAW.get()))
    		.displayItems((enabledFeatures, output) -> 
    		{
    			output.accept(OceanicItems.GREAT_WHITE_SHARK_SPAWN_EGG.get());
    			output.accept(OceanicItems.CRAB_SPAWN_EGG.get());
    			output.accept(OceanicItems.PORBEAGLE_SHARK_SPAWN_EGG.get());
    			output.accept(OceanicItems.TUNA_SPAWN_EGG.get());
    			output.accept(OceanicItems.DOLPHINFISH_SPAWN_EGG.get());
    			output.accept(OceanicItems.HAMMERHEAD_SHARK_SPAWN_EGG.get());
    			output.accept(OceanicItems.MACKEREL_FISH_SPAWN_EGG.get());
    			output.accept(OceanicItems.SILVER_POMFRET_FISH_SPAWN_EGG.get());
    			output.accept(OceanicItems.LIONFISH_SPAWN_EGG.get());
    			output.accept(OceanicItems.WHALESHARK_SPAWN_EGG.get());
    			output.accept(OceanicItems.SAILFISH_SPAWN_EGG.get());
    			output.accept(OceanicItems.GREAT_HAMMERHEAD_SHARK_SPAWN_EGG.get());
    			output.accept(OceanicItems.OARFISH_SPAWN_EGG.get());
    			output.accept(OceanicItems.CRAB_CLAW.get());
    			output.accept(OceanicItems.COOKED_CRAB_CLAW.get());
    			output.accept(OceanicItems.RAW_TUNA.get());
    			output.accept(OceanicItems.RAW_DOLPHINFISH.get());
    			output.accept(OceanicItems.RAW_SAILFISH.get());
    			output.accept(OceanicItems.FISH_FILLET.get());
    			output.accept(OceanicItems.MACKEREL_FISH.get());
    			output.accept(OceanicItems.COOKED_MACKEREL_FISH.get());
    			output.accept(OceanicItems.SILVER_POMFRET_FISH.get());
    			output.accept(OceanicItems.COOKED_SILVER_POMFRET_FISH.get());
    			output.accept(OceanicItems.SEDIMENTARY_SANDSTONE.get());
    			output.accept(OceanicItems.HARD_SEDIMENTARY_SANDSTONE.get());
    			output.accept(OceanicItems.REEF_ROCK.get());
    			output.accept(OceanicItems.CLAM.get());
    			output.accept(OceanicItems.SEA_URCHIN.get());
    			output.accept(OceanicItems.SEA_ANEMONE.get());
    			output.accept(OceanicItems.STARFISH.get());
    			output.accept(OceanicItems.GIANT_KELP.get());
    			output.accept(OceanicItems.RED_ALGAE.get());
    			output.accept(OceanicItems.MACKEREL_FISH_BUCKET.get());
    			output.accept(OceanicItems.SILVER_POMFRET_FISH_BUCKET.get());
    			output.accept(OceanicItems.DOLPHINFISHSPAWN_BUCKET.get());
    			output.accept(OceanicItems.TUNASPAWN_BUCKET.get());
    			output.accept(OceanicItems.BABY_DOLPHINFISH_BUCKET.get());
    			output.accept(OceanicItems.BABY_TUNA_BUCKET.get());
    			output.accept(OceanicItems.DOLPHINFISHSPAWN.get());
    			output.accept(OceanicItems.TUNASPAWN.get());
    		}).build());
}

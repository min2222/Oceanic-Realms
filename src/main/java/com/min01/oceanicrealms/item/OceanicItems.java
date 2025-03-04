package com.min01.oceanicrealms.item;

import java.util.function.Supplier;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.block.OceanicBlocks;
import com.min01.oceanicrealms.entity.OceanicEntities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OceanicItems
{
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OceanicRealms.MODID);
	
	public static final RegistryObject<Item> GREAT_WHITE_SHARK_SPAWN_EGG = registerSpawnEgg("great_white_shark_spawn_egg", () -> OceanicEntities.GREAT_WHITE_SHARK.get(), 3760229, 14014421);
	public static final RegistryObject<Item> CRAB_SPAWN_EGG = registerSpawnEgg("crab_spawn_egg", () -> OceanicEntities.CRAB.get(), 8268809, 16774373);
	public static final RegistryObject<Item> PORBEAGLE_SHARK_SPAWN_EGG = registerSpawnEgg("porbeagle_shark_spawn_egg", () -> OceanicEntities.PORBEAGLE_SHARK.get(), 8347223, 14736324);
	public static final RegistryObject<Item> TUNA_SPAWN_EGG = registerSpawnEgg("tuna_spawn_egg", () -> OceanicEntities.TUNA.get(), 3957141, 12047834);
	public static final RegistryObject<Item> DOLPHINFISH_SPAWN_EGG = registerSpawnEgg("dolphinfish_spawn_egg", () -> OceanicEntities.DOLPHINFISH.get(), 4962601, 12824900);
	public static final RegistryObject<Item> HAMMERHEAD_SHARK_SPAWN_EGG = registerSpawnEgg("hammerhead_shark_spawn_egg", () -> OceanicEntities.HAMMERHEAD_SHARK.get(), 6654324, 12174013);
	public static final RegistryObject<Item> MACKEREL_FISH_SPAWN_EGG = registerSpawnEgg("mackerel_fish_spawn_egg", () -> OceanicEntities.MACKEREL_FISH.get(), 3830103, 13489859);
	public static final RegistryObject<Item> SILVER_POMFRET_FISH_SPAWN_EGG = registerSpawnEgg("silver_pomfret_fish_spawn_egg", () -> OceanicEntities.SILVER_POMFRET_FISH.get(), 13098194, 8247355);
	
	public static final RegistryObject<Item> CRAB_CLAW = ITEMS.register("crab_claw", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> COOKED_CRAB_CLAW = ITEMS.register("cooked_crab_claw", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationMod(2.5F).build())));
	
	public static final RegistryObject<Item> RAW_TUNA = ITEMS.register("raw_tuna", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationMod(3.0F).build())));
	public static final RegistryObject<Item> RAW_DOLPHINFISH = ITEMS.register("raw_dolphinfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationMod(3.0F).build())));
	public static final RegistryObject<Item> FISH_FILLET = ITEMS.register("fish_fillet", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationMod(4.0F).build())));
	public static final RegistryObject<Item> MACKEREL_FISH = ITEMS.register("mackerel_fish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).build())));
	public static final RegistryObject<Item> COOKED_MACKEREL_FISH = ITEMS.register("cooked_mackerel_fish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(0.8F).build())));
	public static final RegistryObject<Item> SILVER_POMFRET_FISH = ITEMS.register("silver_pomfret_fish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(2).saturationMod(0.1F).build())));
	public static final RegistryObject<Item> COOKED_SILVER_POMFRET_FISH = ITEMS.register("cooked_silver_pomfret_fish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().nutrition(6).saturationMod(0.8F).build())));
	
	public static final RegistryObject<Item> CRAB_HOLE = registerBlockItem("crab_hole", () -> OceanicBlocks.CRAB_HOLE.get(), new Item.Properties());
	public static final RegistryObject<Item> SEDIMENTARY_SANDSTONE = registerBlockItem("sedimentary_sandstone", () -> OceanicBlocks.SEDIMENTARY_SANDSTONE.get(), new Item.Properties());
	public static final RegistryObject<Item> HARD_SEDIMENTARY_SANDSTONE = registerBlockItem("hard_sedimentary_sandstone", () -> OceanicBlocks.HARD_SEDIMENTARY_SANDSTONE.get(), new Item.Properties());
	public static final RegistryObject<Item> CLAM = registerBlockItem("clam", () -> OceanicBlocks.CLAM.get(), new Item.Properties());
	public static final RegistryObject<Item> SEA_URCHIN = registerBlockItem("sea_urchin", () -> OceanicBlocks.SEA_URCHIN.get(), new Item.Properties());
	
	public static RegistryObject<Item> registerBlockItem(String name, Supplier<Block> block, Item.Properties properties)
	{
		return ITEMS.register(name, () -> new BlockItem(block.get(), properties));
	}
	
	public static <T extends Mob> RegistryObject<Item> registerSpawnEgg(String name, Supplier<EntityType<T>> entity, int color1, int color2) 
	{
		return ITEMS.register(name, () -> new ForgeSpawnEggItem(entity, color1, color2, new Item.Properties()));
	}
}

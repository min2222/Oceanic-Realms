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
	public static final RegistryObject<Item> BULL_SHARK_SPAWN_EGG = registerSpawnEgg("bull_shark_spawn_egg", () -> OceanicEntities.BULL_SHARK.get(), 8347223, 14736324);
	public static final RegistryObject<Item> TUNA_SPAWN_EGG = registerSpawnEgg("tuna_spawn_egg", () -> OceanicEntities.TUNA.get(), 3957141, 12047834);
	public static final RegistryObject<Item> DOLPHINFISH_SPAWN_EGG = registerSpawnEgg("dolphinfish_spawn_egg", () -> OceanicEntities.DOLPHINFISH.get(), 4962601, 12824900);
	public static final RegistryObject<Item> HAMMERHEAD_SHARK_SPAWN_EGG = registerSpawnEgg("hammerhead_shark_spawn_egg", () -> OceanicEntities.HAMMERHEAD_SHARK.get(), 6654324, 12174013);
	
	public static final RegistryObject<Item> CRAB_CLAW = ITEMS.register("crab_claw", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> COOKED_CRAB_CLAW = ITEMS.register("cooked_crab_claw", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationMod(2.5F).build())));
	
	public static final RegistryObject<Item> RAW_TUNA = ITEMS.register("raw_tuna", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationMod(3.0F).build())));
	public static final RegistryObject<Item> RAW_DOLPHINFISH = ITEMS.register("raw_dolphinfish", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationMod(3.0F).build())));
	public static final RegistryObject<Item> FISH_FILLET = ITEMS.register("fish_fillet", () -> new Item(new Item.Properties().food(new FoodProperties.Builder().saturationMod(4.0F).build())));
	
	public static final RegistryObject<Item> CRAB_HOLE = registerBlockItem("crab_hole", () -> OceanicBlocks.CRAB_HOLE.get(), new Item.Properties());
	public static final RegistryObject<Item> FLOAT_KELP = registerBlockItem("float_kelp", () -> OceanicBlocks.FLOAT_KELP.get(), new Item.Properties());
	
	public static RegistryObject<Item> registerBlockItem(String name, Supplier<Block> block, Item.Properties properties)
	{
		return ITEMS.register(name, () -> new BlockItem(block.get(), properties));
	}
	
	public static <T extends Mob> RegistryObject<Item> registerSpawnEgg(String name, Supplier<EntityType<T>> entity, int color1, int color2) 
	{
		return ITEMS.register(name, () -> new ForgeSpawnEggItem(entity, color1, color2, new Item.Properties()));
	}
}

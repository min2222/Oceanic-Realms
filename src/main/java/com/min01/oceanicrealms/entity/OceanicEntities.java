package com.min01.oceanicrealms.entity;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.entity.living.EntityPorbeagleShark;
import com.min01.oceanicrealms.entity.living.EntitySilverPomfretFish;
import com.min01.oceanicrealms.entity.living.EntityCrab;
import com.min01.oceanicrealms.entity.living.EntityDolphinfish;
import com.min01.oceanicrealms.entity.living.EntityGreatWhiteShark;
import com.min01.oceanicrealms.entity.living.EntityHammerheadShark;
import com.min01.oceanicrealms.entity.living.EntityMackerelFish;
import com.min01.oceanicrealms.entity.living.EntityTuna;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OceanicEntities
{
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, OceanicRealms.MODID);
	
	public static final RegistryObject<EntityType<EntityGreatWhiteShark>> GREAT_WHITE_SHARK = registerEntity("great_white_shark", createBuilder(EntityGreatWhiteShark::new, MobCategory.WATER_AMBIENT).sized(2.0F, 1.6F));
	public static final RegistryObject<EntityType<EntityCrab>> CRAB = registerEntity("crab", createBuilder(EntityCrab::new, MobCategory.WATER_AMBIENT).sized(0.4F, 0.3F));
	public static final RegistryObject<EntityType<EntityPorbeagleShark>> PORBEAGLE_SHARK = registerEntity("porbeagle_shark", createBuilder(EntityPorbeagleShark::new, MobCategory.WATER_AMBIENT).sized(1.125F, 0.875F));
	public static final RegistryObject<EntityType<EntityTuna>> TUNA = registerEntity("tuna", createBuilder(EntityTuna::new, MobCategory.WATER_AMBIENT).sized(1.0F, 1.0F));
	public static final RegistryObject<EntityType<EntityDolphinfish>> DOLPHINFISH = registerEntity("dolphinfish", createBuilder(EntityDolphinfish::new, MobCategory.WATER_AMBIENT).sized(1.0F, 0.6F));
	public static final RegistryObject<EntityType<EntityHammerheadShark>> HAMMERHEAD_SHARK = registerEntity("hammerhead_shark", createBuilder(EntityHammerheadShark::new, MobCategory.WATER_AMBIENT).sized(0.625F, 0.875F));
	public static final RegistryObject<EntityType<EntityMackerelFish>> MACKEREL_FISH = registerEntity("mackerel_fish", createBuilder(EntityMackerelFish::new, MobCategory.WATER_AMBIENT).sized(0.6F, 0.6F));
	public static final RegistryObject<EntityType<EntitySilverPomfretFish>> SILVER_POMFRET_FISH = registerEntity("silver_pomfret_fish", createBuilder(EntitySilverPomfretFish::new, MobCategory.WATER_AMBIENT).sized(0.3F, 0.3F));
	
	public static <T extends Entity> EntityType.Builder<T> createBuilder(EntityType.EntityFactory<T> factory, MobCategory category)
	{
		return EntityType.Builder.<T>of(factory, category);
	}
	
	public static <T extends Entity> RegistryObject<EntityType<T>> registerEntity(String name, EntityType.Builder<T> builder) 
	{
		return ENTITY_TYPES.register(name, () -> builder.build(new ResourceLocation(OceanicRealms.MODID, name).toString()));
	}
}

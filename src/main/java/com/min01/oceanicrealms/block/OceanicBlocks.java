package com.min01.oceanicrealms.block;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.blockentity.CrabHoleBlockEntity;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OceanicBlocks 
{
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, OceanicRealms.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, OceanicRealms.MODID);

    public static final RegistryObject<Block> CRAB_HOLE = BLOCKS.register("crab_hole", () -> new CrabHoleBlock());
    public static final RegistryObject<Block> FLOAT_KELP_PLANT = BLOCKS.register("float_kelp_plant", () -> new FloatKelpPlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WATER).noCollission().instabreak().sound(SoundType.WET_GRASS).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> FLOAT_KELP = BLOCKS.register("float_kelp", () -> new FloatKelpBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WATER).randomTicks().instabreak().sound(SoundType.WET_GRASS).pushReaction(PushReaction.DESTROY)));

    public static final RegistryObject<BlockEntityType<CrabHoleBlockEntity>> CRAB_HOLE_BLOCK_ENTITY = BLOCK_ENTITIES.register("crab_hole", () -> BlockEntityType.Builder.of(CrabHoleBlockEntity::new, OceanicBlocks.CRAB_HOLE.get()).build(null));
}

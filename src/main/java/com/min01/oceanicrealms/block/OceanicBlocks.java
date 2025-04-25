package com.min01.oceanicrealms.block;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.blockentity.AnimatableBlockEntity;
import com.min01.oceanicrealms.blockentity.NoRotationLimitBlockEntity;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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

    public static final RegistryObject<Block> SEDIMENTARY_SANDSTONE = BLOCKS.register("sedimentary_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));
    public static final RegistryObject<Block> HARD_SEDIMENTARY_SANDSTONE = BLOCKS.register("hard_sedimentary_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));
    public static final RegistryObject<Block> CLAM = BLOCKS.register("clam", () -> new ClamBlock());
    public static final RegistryObject<Block> SEA_URCHIN = BLOCKS.register("sea_urchin", () -> new SeaUrchinBlock());
    public static final RegistryObject<Block> SEA_ANEMONE = BLOCKS.register("sea_anemone", () -> new SeaAnemoneBlock());
    public static final RegistryObject<Block> STARFISH = BLOCKS.register("starfish", () -> new StarfishBlock());
    public static final RegistryObject<Block> GIANT_KELP_PLANT = BLOCKS.register("giant_kelp_plant", () -> new GiantKelpPlantBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WATER).noCollission().instabreak().noOcclusion().sound(SoundType.WET_GRASS).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> GIANT_KELP = BLOCKS.register("giant_kelp", () -> new GiantKelpBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WATER).noCollission().randomTicks().instabreak().noOcclusion().sound(SoundType.WET_GRASS).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> RED_ALGAE = BLOCKS.register("red_algae", () -> new RedAlgaeBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).noCollission().instabreak().sound(SoundType.WET_GRASS).pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> REEF_ROCK = BLOCKS.register("reef_rock", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    
    public static final RegistryObject<BlockEntityType<NoRotationLimitBlockEntity>> NO_ROTATION_LIMIT_BLOCK_ENTITY = BLOCK_ENTITIES.register("no_rotation_limit", () -> BlockEntityType.Builder.of(NoRotationLimitBlockEntity::new, 
    		OceanicBlocks.CLAM.get()).build(null));
    
    public static final RegistryObject<BlockEntityType<AnimatableBlockEntity>> ANIMATABLE_BLOCK_ENTITY = BLOCK_ENTITIES.register("animatable", () -> BlockEntityType.Builder.of(AnimatableBlockEntity::new, 
    		OceanicBlocks.SEA_ANEMONE.get(),
    		OceanicBlocks.STARFISH.get()).build(null));
}

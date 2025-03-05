package com.min01.oceanicrealms.block;

import com.min01.oceanicrealms.OceanicRealms;
import com.min01.oceanicrealms.blockentity.AnimatableBlockEntity;
import com.min01.oceanicrealms.blockentity.CrabHoleBlockEntity;
import com.min01.oceanicrealms.blockentity.NoRotationLimitBlockEntity;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OceanicBlocks 
{
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, OceanicRealms.MODID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, OceanicRealms.MODID);

    public static final RegistryObject<Block> CRAB_HOLE = BLOCKS.register("crab_hole", () -> new CrabHoleBlock());
    public static final RegistryObject<Block> SEDIMENTARY_SANDSTONE = BLOCKS.register("sedimentary_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));
    public static final RegistryObject<Block> HARD_SEDIMENTARY_SANDSTONE = BLOCKS.register("hard_sedimentary_sandstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.SANDSTONE)));
    public static final RegistryObject<Block> CLAM = BLOCKS.register("clam", () -> new ClamBlock());
    public static final RegistryObject<Block> SEA_URCHIN = BLOCKS.register("sea_urchin", () -> new SeaUrchinBlock());
    public static final RegistryObject<Block> SEA_ANEMONE = BLOCKS.register("sea_anemone", () -> new SeaAnemoneBlock());

    public static final RegistryObject<BlockEntityType<CrabHoleBlockEntity>> CRAB_HOLE_BLOCK_ENTITY = BLOCK_ENTITIES.register("crab_hole", () -> BlockEntityType.Builder.of(CrabHoleBlockEntity::new, OceanicBlocks.CRAB_HOLE.get()).build(null));
    
    public static final RegistryObject<BlockEntityType<NoRotationLimitBlockEntity>> NO_ROTATION_LIMIT_BLOCK_ENTITY = BLOCK_ENTITIES.register("no_rotation_limit", () -> BlockEntityType.Builder.of(NoRotationLimitBlockEntity::new, 
    		OceanicBlocks.CLAM.get()).build(null));
    
    public static final RegistryObject<BlockEntityType<AnimatableBlockEntity>> ANIMATABLE_BLOCK_ENTITY = BLOCK_ENTITIES.register("animatable", () -> BlockEntityType.Builder.of(AnimatableBlockEntity::new, 
    		OceanicBlocks.SEA_ANEMONE.get()).build(null));
}

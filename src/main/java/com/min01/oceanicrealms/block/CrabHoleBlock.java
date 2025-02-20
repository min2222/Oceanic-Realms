package com.min01.oceanicrealms.block;

import javax.annotation.Nullable;

import com.min01.oceanicrealms.blockentity.CrabHoleBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class CrabHoleBlock extends BaseEntityBlock
{
	public CrabHoleBlock() 
	{
		super(BlockBehaviour.Properties.copy(Blocks.SAND));
	}
	
	@Override
	public RenderShape getRenderShape(BlockState p_49232_) 
	{
		return RenderShape.MODEL;
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) 
	{
		return new CrabHoleBlockEntity(p_153215_, p_153216_);
	}
	
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level p_153273_, BlockState p_153274_, BlockEntityType<T> p_153275_)
    {
        return createTicker(p_153273_, p_153275_, OceanicBlocks.CRAB_HOLE_BLOCK_ENTITY.get());
    }

    @Nullable
    protected static <T extends BlockEntity> BlockEntityTicker<T> createTicker(Level p_151988_, BlockEntityType<T> p_151989_, BlockEntityType<CrabHoleBlockEntity> p_151990_)
    {
        return createTickerHelper(p_151989_, p_151990_, CrabHoleBlockEntity::update);
    }
}

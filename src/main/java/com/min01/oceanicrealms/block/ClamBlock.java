package com.min01.oceanicrealms.block;

import com.min01.oceanicrealms.blockentity.NoRotationLimitBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ClamBlock extends FaceAttachedHorizontalDirectionalBlock implements EntityBlock, SimpleWaterloggedBlock
{
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final IntegerProperty VARIANT = IntegerProperty.create("clam_variant", 1, 4);
	
	protected static final VoxelShape FLOOR_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.25D, 16.0D);
	protected static final VoxelShape CEILING_AABB = Block.box(0.0D, 13.75D, 0.0D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape EAST_AABB = Block.box(0.0D, 0.0D, 0.0D, 2.25D, 16.0D, 16.0D);
	protected static final VoxelShape NORTH_AABB = Block.box(0.0D, 0.0D, 13.75D, 16.0D, 16.0D, 16.0D);
	protected static final VoxelShape SOUTH_AABB = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 2.25D);
	protected static final VoxelShape WEST_AABB = Block.box(13.75D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
	
	public ClamBlock() 
	{
		super(BlockBehaviour.Properties.of().noOcclusion());
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(FACE, AttachFace.FLOOR).setValue(VARIANT, 1).setValue(WATERLOGGED, false));
	}
	
	@Override
	public RenderShape getRenderShape(BlockState p_49232_)
	{
		return RenderShape.ENTITYBLOCK_ANIMATED;
	}
	
	@Override
	public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) 
	{
		return new NoRotationLimitBlockEntity(p_153215_, p_153216_);
	}
	
	@Override
	public VoxelShape getShape(BlockState p_60555_, BlockGetter p_60556_, BlockPos p_60557_, CollisionContext p_60558_)
	{
		switch(p_60555_.getValue(FACE))
		{
		case CEILING:
			return CEILING_AABB;
		case FLOOR:
			return FLOOR_AABB;
		case WALL:
			switch(p_60555_.getValue(FACING))
			{
			case EAST:
				return EAST_AABB;
			case NORTH:
				return NORTH_AABB;
			case SOUTH:
				return SOUTH_AABB;
			case WEST:
				return WEST_AABB;
			default:
				return FLOOR_AABB;
			}
		default:
			return FLOOR_AABB;
		}
	}
	
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext p_54302_)
	{
		BlockState state = super.getStateForPlacement(p_54302_);
		FluidState fluidState = p_54302_.getLevel().getFluidState(p_54302_.getClickedPos());
		return state != null ? state.setValue(WATERLOGGED, fluidState.is(FluidTags.WATER)) : state;
	}
	
    @Override
    public FluidState getFluidState(BlockState p_152045_)
    {
    	return p_152045_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : Fluids.EMPTY.defaultFluidState();
    }
	
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_54663_) 
	{
		p_54663_.add(FACE, FACING, WATERLOGGED, VARIANT);
	}
}

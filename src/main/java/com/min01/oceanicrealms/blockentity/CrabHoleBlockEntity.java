package com.min01.oceanicrealms.blockentity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.min01.oceanicrealms.block.OceanicBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CrabHoleBlockEntity extends BlockEntity
{
	public static final int MAX_CRAB_SIZE = 5;
	public List<UUID> crabs = new ArrayList<>();
	
	public CrabHoleBlockEntity(BlockPos p_155229_, BlockState p_155230_) 
	{
		super(OceanicBlocks.CRAB_HOLE_BLOCK_ENTITY.get(), p_155229_, p_155230_);
	}
	
	public static void update(Level level, BlockPos pos, BlockState state, CrabHoleBlockEntity hole)
	{
		
	}
	
	@Override
	protected void saveAdditional(CompoundTag nbt)
	{
		super.saveAdditional(nbt);
		ListTag list = new ListTag();
		for(Iterator<UUID> itr = this.crabs.iterator(); itr.hasNext();)
		{
			UUID uuid = itr.next();
			CompoundTag tag = new CompoundTag();
			tag.putUUID("Crab", uuid);
			list.add(tag);
		}
		nbt.put("Crabs", list);
	}
	
	@Override
	public void load(CompoundTag nbt)
	{
		super.load(nbt);
		ListTag list = nbt.getList("Crabs", 10);
		for(int i = 0; i < list.size(); ++i)
		{
			CompoundTag tag = list.getCompound(i);
			this.crabs.add(tag.getUUID("Crab"));
		}
	}
}

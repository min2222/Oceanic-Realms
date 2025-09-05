package com.min01.oceanicrealms.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SolidBucketItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;

public class FishspawnBucketItem extends SolidBucketItem
{
	public FishspawnBucketItem(Block p_151187_, SoundEvent p_151188_, Properties p_151189_)
	{
		super(p_151187_, p_151188_, p_151189_);
	}
	
	@Override
	public InteractionResult useOn(UseOnContext p_220229_)
	{
		return InteractionResult.PASS;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level p_220231_, Player p_220232_, InteractionHand p_220233_)
	{
		BlockHitResult blockhitresult = getPlayerPOVHitResult(p_220231_, p_220232_, ClipContext.Fluid.SOURCE_ONLY);
		BlockHitResult blockhitresult1 = blockhitresult.withPosition(blockhitresult.getBlockPos().above());
		InteractionResult interactionresult = super.useOn(new UseOnContext(p_220232_, p_220233_, blockhitresult1));
		return new InteractionResultHolder<>(interactionresult, p_220232_.getItemInHand(p_220233_));
	}
}

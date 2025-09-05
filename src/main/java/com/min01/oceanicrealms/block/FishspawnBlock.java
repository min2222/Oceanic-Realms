package com.min01.oceanicrealms.block;

import java.util.Optional;
import java.util.function.Supplier;

import com.min01.oceanicrealms.entity.AbstractOceanicCreature;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.FrogspawnBlock;
import net.minecraft.world.level.block.state.BlockState;

public class FishspawnBlock extends FrogspawnBlock implements BucketPickup 
{
	public final Supplier<Item> bucketItem;
	public final Supplier<EntityType<? extends AbstractOceanicCreature>> fish;
	
	public FishspawnBlock(Properties p_221177_, Supplier<Item> bucketItem, Supplier<EntityType<? extends AbstractOceanicCreature>> fish) 
	{
		super(p_221177_);
		this.bucketItem = bucketItem;
		this.fish = fish;
	}
	
	@Override
	public void tick(BlockState p_221194_, ServerLevel p_221195_, BlockPos p_221196_, RandomSource p_221197_)
	{
		if(!this.canSurvive(p_221194_, p_221195_, p_221196_)) 
		{
			p_221195_.destroyBlock(p_221196_, false);
		} 
		else 
		{
			this.hatchFishspawn(p_221195_, p_221196_, p_221197_);
		}
	}
	
	private void hatchFishspawn(ServerLevel p_221182_, BlockPos p_221183_, RandomSource p_221184_) 
	{
		p_221182_.destroyBlock(p_221183_, false);
		p_221182_.playSound((Player)null, p_221183_, SoundEvents.FROGSPAWN_HATCH, SoundSource.BLOCKS, 1.0F, 1.0F);
		this.spawnFishes(p_221182_, p_221183_, p_221184_);
	}
	
	private void spawnFishes(ServerLevel p_221221_, BlockPos p_221222_, RandomSource p_221223_)
	{
		int i = p_221223_.nextInt(2, 6);
		for(int j = 1; j <= i; ++j) 
		{
			AbstractOceanicCreature fish = this.fish.get().create(p_221221_);
			if(fish != null)
			{
				double d0 = (double)p_221222_.getX() + this.getRandomPositionOffset(fish, p_221223_);
				double d1 = (double)p_221222_.getZ() + this.getRandomPositionOffset(fish, p_221223_);
				int k = p_221223_.nextInt(1, 361);
				fish.moveTo(d0, (double)p_221222_.getY() - 0.5D, d1, (float)k, 0.0F);
				fish.setPersistenceRequired();
				fish.setBaby(true);
				p_221221_.addFreshEntity(fish);
			}
		}
	}
	
	private double getRandomPositionOffset(Entity entity, RandomSource p_221225_) 
	{
		double d0 = (double)(entity.getBbWidth() / 2.0F);
		return Mth.clamp(p_221225_.nextDouble(), d0, 1.0D - d0);
	}
	
	@Override
	public ItemStack pickupBlock(LevelAccessor p_154281_, BlockPos p_154282_, BlockState p_154283_) 
	{
		p_154281_.setBlock(p_154282_, Blocks.AIR.defaultBlockState(), 11);
		if(!p_154281_.isClientSide()) 
		{
			p_154281_.levelEvent(2001, p_154282_, Block.getId(p_154283_));
		}
		return new ItemStack(this.bucketItem.get());
	}

	@Override
	public Optional<SoundEvent> getPickupSound()
	{
		return Optional.of(SoundEvents.FROGSPAWN_BREAK);
	}
}

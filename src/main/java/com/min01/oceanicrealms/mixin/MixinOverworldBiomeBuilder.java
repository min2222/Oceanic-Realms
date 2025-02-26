package com.min01.oceanicrealms.mixin;

import java.util.function.Consumer;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.min01.oceanicrealms.world.OceanicBiomes;
import com.mojang.datafixers.util.Pair;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;

@Mixin(OverworldBiomeBuilder.class)
public class MixinOverworldBiomeBuilder
{
	@Shadow
	@Final
	private Climate.Parameter FULL_RANGE;
	
	@Shadow
	@Final
	private Climate.Parameter[] temperatures;
	
	@Shadow
	@Final
	private Climate.Parameter deepOceanContinentalness;
	
    @Inject(method = "addOffCoastBiomes", at = @At("HEAD"), cancellable = true)
    private void addOffCoastBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> p_187196_, CallbackInfo ci)
    {
    	Climate.Parameter climate$parameter = this.temperatures[4];
    	this.addSurfaceBiome(p_187196_, climate$parameter, this.FULL_RANGE, this.deepOceanContinentalness, this.FULL_RANGE, this.FULL_RANGE, 0.0F, OceanicBiomes.SANDSTONE_OCEAN);
    }
    
    @Shadow
    private void addSurfaceBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> p_187181_, Climate.Parameter p_187182_, Climate.Parameter p_187183_, Climate.Parameter p_187184_, Climate.Parameter p_187185_, Climate.Parameter p_187186_, float p_187187_, ResourceKey<Biome> p_187188_)
    {
    	
    }
}

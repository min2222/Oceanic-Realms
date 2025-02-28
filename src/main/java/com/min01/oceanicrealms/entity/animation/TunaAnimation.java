package com.min01.oceanicrealms.entity.animation;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class TunaAnimation 
{
	public static final AnimationDefinition TUNA_SWIM = AnimationDefinition.Builder.withLength(1.2529F).looping()
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -1.97F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.0583F, KeyframeAnimations.degreeVec(0.0F, 1.03F, -1.97F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.1457F, KeyframeAnimations.degreeVec(0.0F, 1.93F, -1.73F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.204F, KeyframeAnimations.degreeVec(0.0F, 2.6F, -1.29F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.2914F, KeyframeAnimations.degreeVec(0.0F, 2.95F, -0.68F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.3497F, KeyframeAnimations.degreeVec(0.0F, 2.95F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.4079F, KeyframeAnimations.degreeVec(0.0F, 2.6F, 0.68F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.4953F, KeyframeAnimations.degreeVec(0.0F, 1.93F, 1.29F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.5827F, KeyframeAnimations.degreeVec(0.0F, 0.52F, 1.88F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.6702F, KeyframeAnimations.degreeVec(0.0F, -0.52F, 2.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.7284F, KeyframeAnimations.degreeVec(0.0F, -1.5F, 1.88F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.8158F, KeyframeAnimations.degreeVec(0.0F, -2.3F, 1.53F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.8741F, KeyframeAnimations.degreeVec(0.0F, -2.82F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.9324F, KeyframeAnimations.degreeVec(0.0F, -3.0F, 0.35F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.0198F, KeyframeAnimations.degreeVec(0.0F, -2.82F, -0.35F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.0781F, KeyframeAnimations.degreeVec(0.0F, -2.3F, -1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.1946F, KeyframeAnimations.degreeVec(0.0F, -1.03F, -1.73F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.2529F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -1.97F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.98F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.0291F, KeyframeAnimations.posVec(1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.0583F, KeyframeAnimations.posVec(0.98F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.1166F, KeyframeAnimations.posVec(0.94F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.1457F, KeyframeAnimations.posVec(0.87F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.1748F, KeyframeAnimations.posVec(0.77F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.2331F, KeyframeAnimations.posVec(0.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.4079F, KeyframeAnimations.posVec(-0.34F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.4662F, KeyframeAnimations.posVec(-0.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.5245F, KeyframeAnimations.posVec(-0.77F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.5536F, KeyframeAnimations.posVec(-0.87F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.5827F, KeyframeAnimations.posVec(-0.94F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.641F, KeyframeAnimations.posVec(-0.98F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.6702F, KeyframeAnimations.posVec(-1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.6993F, KeyframeAnimations.posVec(-0.98F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.7284F, KeyframeAnimations.posVec(-0.94F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.7576F, KeyframeAnimations.posVec(-0.87F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.8158F, KeyframeAnimations.posVec(-0.77F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.8741F, KeyframeAnimations.posVec(-0.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.049F, KeyframeAnimations.posVec(0.34F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.0781F, KeyframeAnimations.posVec(0.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.1655F, KeyframeAnimations.posVec(0.77F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.1946F, KeyframeAnimations.posVec(0.87F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.2238F, KeyframeAnimations.posVec(0.94F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.2529F, KeyframeAnimations.posVec(0.98F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -9.85F, 1.37F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.0291F, KeyframeAnimations.degreeVec(0.0F, -10.0F, 0.69F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.0583F, KeyframeAnimations.degreeVec(0.0F, -9.85F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.1166F, KeyframeAnimations.degreeVec(0.0F, -9.4F, -0.69F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.1457F, KeyframeAnimations.degreeVec(0.0F, -8.66F, -1.37F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.1748F, KeyframeAnimations.degreeVec(0.0F, -7.66F, -2.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.204F, KeyframeAnimations.degreeVec(0.0F, -6.43F, -2.57F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.2331F, KeyframeAnimations.degreeVec(0.0F, -5.0F, -3.06F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.3205F, KeyframeAnimations.degreeVec(0.0F, -1.74F, -3.76F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.3497F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -3.94F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.3788F, KeyframeAnimations.degreeVec(0.0F, 1.74F, -4.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.4079F, KeyframeAnimations.degreeVec(0.0F, 3.42F, -3.94F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.4662F, KeyframeAnimations.degreeVec(0.0F, 5.0F, -3.76F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.4953F, KeyframeAnimations.degreeVec(0.0F, 6.43F, -3.46F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.5245F, KeyframeAnimations.degreeVec(0.0F, 7.66F, -3.06F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.5536F, KeyframeAnimations.degreeVec(0.0F, 8.66F, -2.57F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.5827F, KeyframeAnimations.degreeVec(0.0F, 9.4F, -2.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.641F, KeyframeAnimations.degreeVec(0.0F, 9.85F, -1.37F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.6702F, KeyframeAnimations.degreeVec(0.0F, 10.0F, -0.69F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.6993F, KeyframeAnimations.degreeVec(0.0F, 9.85F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.7284F, KeyframeAnimations.degreeVec(0.0F, 9.4F, 0.69F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.7576F, KeyframeAnimations.degreeVec(0.0F, 8.66F, 1.37F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.8158F, KeyframeAnimations.degreeVec(0.0F, 7.66F, 2.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.845F, KeyframeAnimations.degreeVec(0.0F, 6.43F, 2.57F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.8741F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 3.06F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.9324F, KeyframeAnimations.degreeVec(0.0F, 1.74F, 3.76F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.9907F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 3.94F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.0198F, KeyframeAnimations.degreeVec(0.0F, -1.74F, 4.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.049F, KeyframeAnimations.degreeVec(0.0F, -3.42F, 3.94F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.0781F, KeyframeAnimations.degreeVec(0.0F, -5.0F, 3.76F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.1072F, KeyframeAnimations.degreeVec(0.0F, -6.43F, 3.46F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.1655F, KeyframeAnimations.degreeVec(0.0F, -7.66F, 3.06F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.1946F, KeyframeAnimations.degreeVec(0.0F, -8.66F, 2.57F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.2238F, KeyframeAnimations.degreeVec(0.0F, -9.4F, 2.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.2529F, KeyframeAnimations.degreeVec(0.0F, -9.85F, 1.37F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 6.84F, 4.7F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.0291F, KeyframeAnimations.degreeVec(0.0F, 3.47F, 4.33F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.0583F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 3.83F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.1457F, KeyframeAnimations.degreeVec(0.0F, -6.84F, 2.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.1748F, KeyframeAnimations.degreeVec(0.0F, -10.0F, 1.71F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.204F, KeyframeAnimations.degreeVec(0.0F, -12.86F, 0.87F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.2331F, KeyframeAnimations.degreeVec(0.0F, -15.32F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.2914F, KeyframeAnimations.degreeVec(0.0F, -17.32F, -0.87F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.3205F, KeyframeAnimations.degreeVec(0.0F, -18.79F, -1.71F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.3497F, KeyframeAnimations.degreeVec(0.0F, -19.7F, -2.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.3788F, KeyframeAnimations.degreeVec(0.0F, -20.0F, -3.21F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.4079F, KeyframeAnimations.degreeVec(0.0F, -19.7F, -3.83F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.4662F, KeyframeAnimations.degreeVec(0.0F, -18.79F, -4.33F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.4953F, KeyframeAnimations.degreeVec(0.0F, -17.32F, -4.7F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.5245F, KeyframeAnimations.degreeVec(0.0F, -15.32F, -4.92F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.5536F, KeyframeAnimations.degreeVec(0.0F, -12.86F, -5.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.5827F, KeyframeAnimations.degreeVec(0.0F, -10.0F, -4.92F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.641F, KeyframeAnimations.degreeVec(0.0F, -6.84F, -4.7F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.6702F, KeyframeAnimations.degreeVec(0.0F, -3.47F, -4.33F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.6993F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -3.83F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.7284F, KeyframeAnimations.degreeVec(0.0F, 3.47F, -3.21F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.7576F, KeyframeAnimations.degreeVec(0.0F, 6.84F, -2.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.8158F, KeyframeAnimations.degreeVec(0.0F, 10.0F, -1.71F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.845F, KeyframeAnimations.degreeVec(0.0F, 12.86F, -0.87F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.8741F, KeyframeAnimations.degreeVec(0.0F, 15.32F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.9033F, KeyframeAnimations.degreeVec(0.0F, 17.32F, 0.87F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.9324F, KeyframeAnimations.degreeVec(0.0F, 18.79F, 1.71F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.9907F, KeyframeAnimations.degreeVec(0.0F, 19.7F, 2.5F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.0198F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 3.21F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.049F, KeyframeAnimations.degreeVec(0.0F, 19.7F, 3.83F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.0781F, KeyframeAnimations.degreeVec(0.0F, 18.79F, 4.33F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.1072F, KeyframeAnimations.degreeVec(0.0F, 17.32F, 4.7F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.1655F, KeyframeAnimations.degreeVec(0.0F, 15.32F, 4.92F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.1946F, KeyframeAnimations.degreeVec(0.0F, 12.86F, 5.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.2238F, KeyframeAnimations.degreeVec(0.0F, 10.0F, 4.92F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(1.2529F, KeyframeAnimations.degreeVec(0.0F, 6.84F, 4.7F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone4", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.6119F, KeyframeAnimations.degreeVec(0.0F, -7.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.2529F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 7.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.6119F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.2529F, KeyframeAnimations.degreeVec(0.0F, 7.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.build();
	
	public static final AnimationDefinition TUNA_DRY = AnimationDefinition.Builder.withLength(0.75F).looping()
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 22.5F, 90.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.2083F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 90.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.375F, KeyframeAnimations.degreeVec(0.0F, 22.5F, 90.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5833F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 90.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 22.5F, 90.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.75F, KeyframeAnimations.posVec(0.0F, -2.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0417F, KeyframeAnimations.degreeVec(0.0F, -45.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 12.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4167F, KeyframeAnimations.degreeVec(0.0F, -45.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.625F, KeyframeAnimations.degreeVec(0.0F, 12.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, -45.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0417F, KeyframeAnimations.degreeVec(0.0F, 17.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, -35.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4167F, KeyframeAnimations.degreeVec(0.0F, 17.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.625F, KeyframeAnimations.degreeVec(0.0F, -35.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.75F, KeyframeAnimations.degreeVec(0.0F, 17.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.build();
}

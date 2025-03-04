package com.min01.oceanicrealms.entity.animation;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class MackerelFishAnimation 
{
	public static final AnimationDefinition MACKEREL_FISH_SWIM = AnimationDefinition.Builder.withLength(0.5F).looping()
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -1.97F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.0417F, KeyframeAnimations.degreeVec(0.0F, 1.93F, -1.73F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.0833F, KeyframeAnimations.degreeVec(0.0F, 2.6F, -1.29F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.125F, KeyframeAnimations.degreeVec(0.0F, 2.95F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.1667F, KeyframeAnimations.degreeVec(0.0F, 2.6F, 0.68F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.2083F, KeyframeAnimations.degreeVec(0.0F, 0.52F, 1.88F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, -0.52F, 2.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.2917F, KeyframeAnimations.degreeVec(0.0F, -1.5F, 1.88F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, -2.82F, 1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.375F, KeyframeAnimations.degreeVec(0.0F, -3.0F, 0.35F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.4167F, KeyframeAnimations.degreeVec(0.0F, -2.3F, -1.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.4583F, KeyframeAnimations.degreeVec(0.0F, -1.03F, -1.73F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -1.97F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.0417F, KeyframeAnimations.posVec(0.87F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.0833F, KeyframeAnimations.posVec(0.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.1667F, KeyframeAnimations.posVec(-0.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.2083F, KeyframeAnimations.posVec(-0.94F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.25F, KeyframeAnimations.posVec(-1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.2917F, KeyframeAnimations.posVec(-0.87F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.3333F, KeyframeAnimations.posVec(-0.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.4167F, KeyframeAnimations.posVec(0.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.4583F, KeyframeAnimations.posVec(0.87F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
				new Keyframe(0.5F, KeyframeAnimations.posVec(0.94F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("bone2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -10.0F, 0.69F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.0833F, KeyframeAnimations.degreeVec(0.0F, -5.0F, -3.06F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.1667F, KeyframeAnimations.degreeVec(0.0F, 5.0F, -3.76F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.25F, KeyframeAnimations.degreeVec(0.0F, 10.0F, -0.69F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.3333F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 3.06F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4167F, KeyframeAnimations.degreeVec(0.0F, -5.0F, 3.76F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, -9.4F, 2.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.1667F, KeyframeAnimations.degreeVec(15.0F, 32.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-23.012F, 17.6806F, 3.0608F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone5", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.1667F, KeyframeAnimations.degreeVec(15.0F, -32.5F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-23.012F, -17.6806F, -3.0608F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.5F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.build();

		public static final AnimationDefinition MACKEREL_FISH_DRY = AnimationDefinition.Builder.withLength(0.75F).looping()
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
			.build();
}

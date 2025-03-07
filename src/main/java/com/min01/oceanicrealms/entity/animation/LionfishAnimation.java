package com.min01.oceanicrealms.entity.animation;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class LionfishAnimation
{
	public static final AnimationDefinition LIONFISH_SWIM = AnimationDefinition.Builder.withLength(2.0129F).looping()
			.addAnimation("bone2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -1.97F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.355F, KeyframeAnimations.degreeVec(0.0F, 2.6F, -1.29F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7101F, KeyframeAnimations.degreeVec(0.0F, 2.6F, 0.68F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.3018F, KeyframeAnimations.degreeVec(0.0F, -2.82F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.6568F, KeyframeAnimations.degreeVec(0.0F, -2.3F, -1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0118F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -1.97F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone2", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.355F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8284F, KeyframeAnimations.posVec(-0.94F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.1834F, KeyframeAnimations.posVec(-0.87F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.6568F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0118F, KeyframeAnimations.posVec(0.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -10.0F, 0.69F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.355F, KeyframeAnimations.degreeVec(0.0F, -5.0F, -3.06F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7101F, KeyframeAnimations.degreeVec(0.0F, 5.0F, -3.76F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.9467F, KeyframeAnimations.degreeVec(0.0F, 10.0F, -0.69F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.3018F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 3.06F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.6568F, KeyframeAnimations.degreeVec(0.0F, -5.0F, 3.76F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0118F, KeyframeAnimations.degreeVec(0.0F, -10.0F, 0.69F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -10.0F, 0.69F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.355F, KeyframeAnimations.degreeVec(0.0F, -5.0F, -3.06F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7101F, KeyframeAnimations.degreeVec(0.0F, 5.0F, -3.76F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.9467F, KeyframeAnimations.degreeVec(0.0F, 10.0F, -0.69F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.3018F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 3.06F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.6568F, KeyframeAnimations.degreeVec(0.0F, -5.0F, 3.76F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0118F, KeyframeAnimations.degreeVec(0.0F, -10.0F, 0.69F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("left", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7101F, KeyframeAnimations.degreeVec(0.5F, 5.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.4201F, KeyframeAnimations.degreeVec(-2.0F, 2.0F, -1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0118F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("right", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7101F, KeyframeAnimations.degreeVec(0.5F, -5.0F, -1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.4201F, KeyframeAnimations.degreeVec(-2.0F, -2.0F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0118F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.build();
}

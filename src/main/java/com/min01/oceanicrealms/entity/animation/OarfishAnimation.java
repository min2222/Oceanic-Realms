package com.min01.oceanicrealms.entity.animation;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class OarfishAnimation
{
	public static final AnimationDefinition OARFISH_SWIM = AnimationDefinition.Builder.withLength(4.0339F).looping()
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -1.97F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.6723F, KeyframeAnimations.degreeVec(0.0F, 2.6F, -1.29F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.3445F, KeyframeAnimations.degreeVec(0.0F, 2.6F, 0.68F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0168F, KeyframeAnimations.degreeVec(0.0F, -0.52F, 2.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.6891F, KeyframeAnimations.degreeVec(0.0F, -2.82F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.3613F, KeyframeAnimations.degreeVec(0.0F, -2.3F, -1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0336F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -1.97F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.6723F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.6807F, KeyframeAnimations.posVec(-0.94F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.3529F, KeyframeAnimations.posVec(-0.87F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.3613F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0336F, KeyframeAnimations.posVec(0.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("right", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0084F, KeyframeAnimations.degreeVec(0.0F, 2.5F, -15.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.8487F, KeyframeAnimations.degreeVec(-10.3341F, 12.4784F, -22.5651F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.6891F, KeyframeAnimations.degreeVec(9.4274F, 15.9824F, -11.6213F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.3613F, KeyframeAnimations.degreeVec(-0.9508F, 7.9252F, -0.3579F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0336F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("left", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0084F, KeyframeAnimations.degreeVec(0.0F, -2.5F, 15.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.8487F, KeyframeAnimations.degreeVec(-10.3341F, -12.4784F, 22.5651F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.6891F, KeyframeAnimations.degreeVec(9.4274F, -15.9824F, 11.6213F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.3613F, KeyframeAnimations.degreeVec(-0.9508F, -7.9252F, 0.3579F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(4.0336F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.build();
}

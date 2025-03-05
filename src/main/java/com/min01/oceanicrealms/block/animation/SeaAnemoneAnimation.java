package com.min01.oceanicrealms.block.animation;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class SeaAnemoneAnimation 
{
	public static final AnimationDefinition ANEMONE_IDLE = AnimationDefinition.Builder.withLength(3.125F).looping()
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(2.1091F, 2.4976F, 2.5024F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5625F, KeyframeAnimations.degreeVec(-2.3909F, 2.4976F, 2.5024F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.125F, KeyframeAnimations.degreeVec(2.1091F, 2.4976F, 2.5024F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.SCALE, 
				new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 0.97F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7813F, KeyframeAnimations.scaleVec(1.0F, 1.02F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5625F, KeyframeAnimations.scaleVec(1.0F, 0.97F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.3438F, KeyframeAnimations.scaleVec(1.0F, 1.04F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(3.125F, KeyframeAnimations.scaleVec(1.0F, 0.97F, 1.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.build();
}

package com.min01.oceanicrealms.entity.animation;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class LionfishAnimation
{
	public static final AnimationDefinition LIONFISH_SWIM = AnimationDefinition.Builder.withLength(2.5966F).looping()
			.addAnimation("bone2", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -1.97F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.1208F, KeyframeAnimations.degreeVec(0.0F, 1.03F, -1.97F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.3019F, KeyframeAnimations.degreeVec(0.0F, 1.93F, -1.73F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4227F, KeyframeAnimations.degreeVec(0.0F, 2.6F, -1.29F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.6039F, KeyframeAnimations.degreeVec(0.0F, 2.95F, -0.68F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7246F, KeyframeAnimations.degreeVec(0.0F, 2.95F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8454F, KeyframeAnimations.degreeVec(0.0F, 2.6F, 0.68F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0266F, KeyframeAnimations.degreeVec(0.0F, 1.93F, 1.29F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.2077F, KeyframeAnimations.degreeVec(0.0F, 0.52F, 1.88F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.3889F, KeyframeAnimations.degreeVec(0.0F, -0.52F, 2.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5097F, KeyframeAnimations.degreeVec(0.0F, -1.5F, 1.88F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.6908F, KeyframeAnimations.degreeVec(0.0F, -2.3F, 1.53F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.8116F, KeyframeAnimations.degreeVec(0.0F, -2.82F, 1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.9324F, KeyframeAnimations.degreeVec(0.0F, -3.0F, 0.35F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.1135F, KeyframeAnimations.degreeVec(0.0F, -2.82F, -0.35F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.2343F, KeyframeAnimations.degreeVec(0.0F, -2.3F, -1.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.4758F, KeyframeAnimations.degreeVec(0.0F, -1.03F, -1.73F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.5966F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -1.97F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone2", new AnimationChannel(AnimationChannel.Targets.POSITION, 
				new Keyframe(0.0F, KeyframeAnimations.posVec(0.98F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.1208F, KeyframeAnimations.posVec(0.98F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.3019F, KeyframeAnimations.posVec(0.87F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4831F, KeyframeAnimations.posVec(0.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8454F, KeyframeAnimations.posVec(-0.34F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.087F, KeyframeAnimations.posVec(-0.77F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.2077F, KeyframeAnimations.posVec(-0.94F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.3889F, KeyframeAnimations.posVec(-1.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5097F, KeyframeAnimations.posVec(-0.94F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.6908F, KeyframeAnimations.posVec(-0.77F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.1739F, KeyframeAnimations.posVec(0.34F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.4155F, KeyframeAnimations.posVec(0.77F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.5966F, KeyframeAnimations.posVec(0.98F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, -9.85F, 1.37F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.1208F, KeyframeAnimations.degreeVec(0.0F, -9.85F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.3019F, KeyframeAnimations.degreeVec(0.0F, -8.66F, -1.37F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4227F, KeyframeAnimations.degreeVec(0.0F, -6.43F, -2.57F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.6643F, KeyframeAnimations.degreeVec(0.0F, -1.74F, -3.76F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.785F, KeyframeAnimations.degreeVec(0.0F, 1.74F, -4.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.9662F, KeyframeAnimations.degreeVec(0.0F, 5.0F, -3.76F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.087F, KeyframeAnimations.degreeVec(0.0F, 7.66F, -3.06F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.2077F, KeyframeAnimations.degreeVec(0.0F, 9.4F, -2.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.3889F, KeyframeAnimations.degreeVec(0.0F, 10.0F, -0.69F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5097F, KeyframeAnimations.degreeVec(0.0F, 9.4F, 0.69F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.6908F, KeyframeAnimations.degreeVec(0.0F, 7.66F, 2.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.8116F, KeyframeAnimations.degreeVec(0.0F, 5.0F, 3.06F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0531F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 3.94F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.1739F, KeyframeAnimations.degreeVec(0.0F, -3.42F, 3.94F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.2947F, KeyframeAnimations.degreeVec(0.0F, -6.43F, 3.46F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.4758F, KeyframeAnimations.degreeVec(0.0F, -8.66F, 2.57F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.5966F, KeyframeAnimations.degreeVec(0.0F, -9.85F, 1.37F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("bone3", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 6.84F, 4.7F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.0604F, KeyframeAnimations.degreeVec(0.0F, 3.47F, 4.33F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.1208F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 3.83F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.3019F, KeyframeAnimations.degreeVec(0.0F, -6.84F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.3623F, KeyframeAnimations.degreeVec(0.0F, -10.0F, 1.71F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4227F, KeyframeAnimations.degreeVec(0.0F, -12.86F, 0.87F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.4831F, KeyframeAnimations.degreeVec(0.0F, -15.32F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.6039F, KeyframeAnimations.degreeVec(0.0F, -17.32F, -0.87F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.6643F, KeyframeAnimations.degreeVec(0.0F, -18.79F, -1.71F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.7246F, KeyframeAnimations.degreeVec(0.0F, -19.7F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.785F, KeyframeAnimations.degreeVec(0.0F, -20.0F, -3.21F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.8454F, KeyframeAnimations.degreeVec(0.0F, -19.7F, -3.83F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.9662F, KeyframeAnimations.degreeVec(0.0F, -18.79F, -4.33F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.0266F, KeyframeAnimations.degreeVec(0.0F, -17.32F, -4.7F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.087F, KeyframeAnimations.degreeVec(0.0F, -15.32F, -4.92F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.1473F, KeyframeAnimations.degreeVec(0.0F, -12.86F, -5.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.2077F, KeyframeAnimations.degreeVec(0.0F, -10.0F, -4.92F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.3285F, KeyframeAnimations.degreeVec(0.0F, -6.84F, -4.7F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.3889F, KeyframeAnimations.degreeVec(0.0F, -3.47F, -4.33F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.4493F, KeyframeAnimations.degreeVec(0.0F, 0.0F, -3.83F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.5097F, KeyframeAnimations.degreeVec(0.0F, 3.47F, -3.21F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.57F, KeyframeAnimations.degreeVec(0.0F, 6.84F, -2.5F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.6908F, KeyframeAnimations.degreeVec(0.0F, 10.0F, -1.71F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.7512F, KeyframeAnimations.degreeVec(0.0F, 12.86F, -0.87F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.8116F, KeyframeAnimations.degreeVec(0.0F, 15.32F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.872F, KeyframeAnimations.degreeVec(0.0F, 17.32F, 0.87F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.9324F, KeyframeAnimations.degreeVec(0.0F, 18.79F, 1.71F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.0531F, KeyframeAnimations.degreeVec(0.0F, 19.7F, 2.5F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.1135F, KeyframeAnimations.degreeVec(0.0F, 20.0F, 3.21F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.1739F, KeyframeAnimations.degreeVec(0.0F, 19.7F, 3.83F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.2343F, KeyframeAnimations.degreeVec(0.0F, 18.79F, 4.33F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.2947F, KeyframeAnimations.degreeVec(0.0F, 17.32F, 4.7F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.4155F, KeyframeAnimations.degreeVec(0.0F, 15.32F, 4.92F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.4758F, KeyframeAnimations.degreeVec(0.0F, 12.86F, 5.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.5362F, KeyframeAnimations.degreeVec(0.0F, 10.0F, 4.92F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.5966F, KeyframeAnimations.degreeVec(0.0F, 6.84F, 4.7F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("left", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.6039F, KeyframeAnimations.degreeVec(0.0F, -10.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.2681F, KeyframeAnimations.degreeVec(-2.5F, -10.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.9324F, KeyframeAnimations.degreeVec(-2.1276F, -2.5608F, -1.0239F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.5966F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.addAnimation("right", new AnimationChannel(AnimationChannel.Targets.ROTATION, 
				new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(0.6039F, KeyframeAnimations.degreeVec(0.0F, 10.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.2681F, KeyframeAnimations.degreeVec(-2.5F, 10.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(1.9324F, KeyframeAnimations.degreeVec(-2.1276F, 2.5608F, 1.0239F), AnimationChannel.Interpolations.CATMULLROM),
				new Keyframe(2.5966F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.build();
}

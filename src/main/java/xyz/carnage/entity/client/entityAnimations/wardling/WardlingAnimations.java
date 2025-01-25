package xyz.carnage.entity.client.entityAnimations.wardling;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

public class WardlingAnimations {
	public static final Animation SPAWN = Animation.Builder.create(2.3397F)
			.addBoneAnimation("body2", new Transformation(Transformation.Targets.TRANSLATE,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, -29.0F, 0.0F), Transformation.Interpolations.LINEAR),
					new Keyframe(0.5609F, AnimationHelper.createTranslationalVector(0.0F, -9.0F, 0.0F), Transformation.Interpolations.LINEAR),
					new Keyframe(0.7853F, AnimationHelper.createTranslationalVector(0.0F, -13.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("rightarm", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(-180.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
					new Keyframe(0.1923F, AnimationHelper.createRotationalVector(-180.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
					new Keyframe(0.2564F, AnimationHelper.createRotationalVector(-92.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
					new Keyframe(0.5128F, AnimationHelper.createRotationalVector(-80.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
					new Keyframe(0.5609F, AnimationHelper.createRotationalVector(-45.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
					new Keyframe(0.5769F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("rightarm", new Transformation(Transformation.Targets.TRANSLATE,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
					new Keyframe(0.1923F, AnimationHelper.createTranslationalVector(0.0F, 21.0F, 0.0F), Transformation.Interpolations.LINEAR),
					new Keyframe(0.5769F, AnimationHelper.createTranslationalVector(0.0F, 14.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.build();

	public static final Animation ATTACK = Animation.Builder.create(0.3805F)
			.addBoneAnimation("rightarm", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
					new Keyframe(0.1522F, AnimationHelper.createRotationalVector(-59.7283F, -21.1545F, -5.4518F), Transformation.Interpolations.LINEAR),
					new Keyframe(0.3234F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("rightarm", new Transformation(Transformation.Targets.TRANSLATE,
					new Keyframe(0.0F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
					new Keyframe(0.1522F, AnimationHelper.createTranslationalVector(0.0F, 2.0F, -2.0F), Transformation.Interpolations.LINEAR),
					new Keyframe(0.3234F, AnimationHelper.createTranslationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.build();

	public static final Animation BOOM = Animation.Builder.create(2.4917F)
			.addBoneAnimation("body", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
					new Keyframe(1.675F, AnimationHelper.createRotationalVector(25.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
					new Keyframe(1.7833F, AnimationHelper.createRotationalVector(-15.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE,
					new Keyframe(0.0F, AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
					new Keyframe(1.675F, AnimationHelper.createRotationalVector(40.0F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR),
					new Keyframe(1.7833F, AnimationHelper.createRotationalVector(-17.5F, 0.0F, 0.0F), Transformation.Interpolations.LINEAR)
			))
			.build();
}
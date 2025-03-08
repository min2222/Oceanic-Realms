package com.min01.oceanicrealms.misc;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class WormChain 
{
    public static void tick(Worm worm, LivingEntity owner, float distance, float speed)
    {
        Vec3 direction = owner.getLookAngle().normalize().scale(distance);
        Vec3 targetPos = owner.position().subtract(direction);

        worm.setPos(targetPos);

        float prevYRot = worm.getYRot();
        float prevXRot = worm.getXRot();

        worm.setYRot(owner.getYRot());
        worm.setXRot(owner.getXRot());

        float yRotDiff = Mth.wrapDegrees(owner.getYRot() - prevYRot);
        float xRotDiff = Mth.wrapDegrees(owner.getXRot() - prevXRot);

        worm.setYRot(prevYRot + yRotDiff * speed);
        worm.setXRot(prevXRot + xRotDiff * speed);

        worm.setYBodyRot(worm.getYRot());
        worm.setYHeadRot(worm.getYRot());

        worm.yRotO = worm.getYRot();
        worm.xRotO = worm.getXRot();
        worm.yBodyRotO = worm.getYRot();
        worm.yHeadRotO = worm.getYRot();
    }
    
    public static void tick(Worm worm, Worm owner, float distance, float speed)
    {
        Vec3 direction = owner.getLookAngle().normalize().scale(distance);
        Vec3 targetPos = owner.position().subtract(direction);

        worm.setPos(targetPos);

        float prevYRot = worm.getYRot();
        float prevXRot = worm.getXRot();

        worm.setYRot(owner.getYRot());
        worm.setXRot(owner.getXRot());

        float yRotDiff = Mth.wrapDegrees(owner.getYRot() - prevYRot);
        float xRotDiff = Mth.wrapDegrees(owner.getXRot() - prevXRot);

        worm.setYRot(prevYRot + yRotDiff * speed);
        worm.setXRot(prevXRot + xRotDiff * speed);

        worm.setYBodyRot(worm.getYRot());
        worm.setYHeadRot(worm.getYRot());

        worm.yRotO = worm.getYRot();
        worm.xRotO = worm.getXRot();
        worm.yBodyRotO = worm.getYRot();
        worm.yHeadRotO = worm.getYRot();
    }
    
    public static class Worm
    {
    	private float yRot;
    	private float xRot;
    	public float yRotO;
    	public float xRotO;
    	
    	public float yBodyRot;
    	public float yBodyRotO;
    	public float yHeadRot;
    	public float yHeadRotO;
    	
    	private Vec3 position = Vec3.ZERO;
    	
    	public boolean setupRot;
    	
    	public Vec2 getRot(float partialTick)
    	{
            float headPitch = Mth.lerp(partialTick, this.xRotO, this.getXRot());
            //float headRot = Mth.rotLerp(partialTick, this.yHeadRotO, this.yHeadRot);
            float bodyRot = Mth.rotLerp(partialTick, this.yBodyRotO, this.yBodyRot);
            //float realHeadRot = headRot - bodyRot;
    		return new Vec2(headPitch, bodyRot);
    	}
    	
    	public void setPos(Vec3 pos)
    	{
    		this.position = pos;
    	}
    	
    	public Vec3 position()
    	{
    		return this.position;
    	}
    	
    	public Vec3 getLookAngle() 
    	{
    		return this.calculateViewVector(this.getXRot(), this.getYRot());
    	}
    	
    	protected final Vec3 calculateViewVector(float xRot, float yRot) 
    	{
    		float f = xRot * ((float)Math.PI / 180.0F);
    		float f1 = -yRot * ((float)Math.PI / 180.0F);
    		float f2 = Mth.cos(f1);
    		float f3 = Mth.sin(f1);
    		float f4 = Mth.cos(f);
    		float f5 = Mth.sin(f);
    		return new Vec3((double)(f3 * f4), (double)(-f5), (double)(f2 * f4));
    	}
    	
    	public float getYRot() 
    	{
    		return this.yRot;
    	}
    	
    	public void setYRot(float yRot) 
    	{
    		if(Float.isFinite(yRot))
    		{
    			this.yRot = yRot;
    		}
    	}

    	public float getXRot()
    	{
    		return this.xRot;
    	}

    	public void setXRot(float xRot) 
    	{
    		if(Float.isFinite(xRot))
    		{
    			this.xRot = xRot;
    		}
    	}
    	
    	public void setYHeadRot(float yHeadRot)
    	{
    		this.yHeadRot = yHeadRot;
    	}

    	public void setYBodyRot(float yBodyRot)
    	{
    		this.yBodyRot = yBodyRot;
    	}
    }
}
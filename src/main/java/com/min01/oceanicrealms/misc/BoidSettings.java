package com.min01.oceanicrealms.misc;

public class BoidSettings
{
    public float minSpeed = 2;
    public float maxSpeed = 5;
    public float perceptionRadius = 2.5F;
    public float avoidanceRadius = 1;
    public float maxSteerForce = 3;

    public float alignWeight = 1;
    public float cohesionWeight = 1;
    public float seperateWeight = 1;

    public float targetWeight = 1;

    public float boundsRadius = 0.27F;
    public float avoidCollisionWeight = 1;
    public float collisionAvoidDst = 5;
}

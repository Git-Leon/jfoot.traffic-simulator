package com.github.curriculeon.jfoot.template;
import greenfoot.*;
public class Vehicle extends SubActor {
    private int acts;
    private int speed;

    private void init(int speed, int carNum) {
        this.speed = speed;
        setimage("car"+ carNum + ".png");
    }    

    public Vehicle(int speed, int carNum) {
        init(speed, carNum);
    }

    public Vehicle() {
        init(5, Util.ran(3));
    }

    public void act() {        
        go();
        disappearAtEdge();
        acts++;
    }

    private void disappearAtEdge() {
        if(atWorldEdge(5,5) || acts > 500)
            getWorld().removeObject(this);
    }

    private void go() {
        Light light = (Light)touchingNose(Light.class);
        boolean isRed = light != null ? light.isRed(this) : false;
        boolean inIntersection = (Light)getOneObjectAtOffset(0, 0,Light.class) != null;

        boolean stop = noseContact(Vehicle.class) || (isRed && !inIntersection);
        boolean go = !isRed || inIntersection;

        if(stop)
            move(0);
        else if(go) {
            move(speed);
        }
    }
}
package com.github.curriculeon.jfoot.template;
import greenfoot.*;
public class Util {
    public static boolean isInRange(int testVal, int min, int max) {
        boolean inRange = false;
        if(min > max) 
            min = max;
        return (testVal >= min) && (testVal <= max);
    }

    public static int ran(int maxVal) {
        return Greenfoot.getRandomNumber(maxVal);
    }

    public static void printCoords(Actor actor) {
        System.out.format("\nX:\t%d\nY:\t%d", actor.getX(), actor.getY());
    }
}
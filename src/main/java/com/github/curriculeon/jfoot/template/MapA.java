package com.github.curriculeon.jfoot.template;
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.ArrayList;
public class MapA extends World {
    public int worldFrames;
    private int[] vMatrix = { 0, 0, 0, 0 };
    private boolean isPaused;
    public final static int mapWidth = 855;
    public final static int mapHeight = 600;

    private void resize() {
        GreenfootImage newBackground = getBackground();
        newBackground.scale(mapWidth, mapHeight);
        setBackground(newBackground); 
    }

    private void populate() {
        addObject(new Structure(new GreenfootImage("Building.png")),65,73);
        addObject(new Light(), 425, 308);
    }

    public MapA() {
        super(mapWidth, mapHeight, 1);
        setBackground(new GreenfootImage("Traffic1.jpg"));
        resize();
        populate();
        isPaused = false;
        Greenfoot.start();
    }

    public void act() {
        pause();
        trafficRate(10);        
        worldFrames++;
    }

    public void pause() {
        if(Greenfoot.isKeyDown("p"))
            togglePlay();
    }

    private void togglePlay() {
        if(isPaused)
            Greenfoot.start();
        else {
            Greenfoot.stop();
            Greenfoot.delay(50);     
            //isPaused = !isPaused;
        }
    }   

    private void trafficRate(int percent) {
        if(ran(100) <= percent) spawnCar();
    }

    private void spawnCar() {
        spawnCar(selectLocation());
    }

    private void spawnCar(int NSEW){
        Vehicle vehicle = new Vehicle();

        int lane = ran(3);
        int h = 27*lane;
        int v = 41*lane;

        switch(NSEW) {
            case 0: //vehicle.drive("East");
            addObject(vehicle, 31, 326+h);
            break;

            case 1: //vehicle.drive("South");
            vehicle.setRotation(90);
            addObject(vehicle, 315+v, 30);
            break;

            case 2: //vehicle.drive("West");
            vehicle.setRotation(180);
            addObject(vehicle, 818, 237+h);
            break;

            case 3: //vehicle.drive("North");
            vehicle.setRotation(270);
            addObject(vehicle, 452+v, 567);
            break;
        }
        checkSpawn(vehicle);
    }

    private void checkSpawn(SubActor actor) {
        if(actor.isInside(actor.getClass())) {
            removeObject(actor);
        }
    }

    /** Algorithm to select next car spawn-location */
    private int selectLocation() {
        boolean emptyMatrix = true;

        //check if matrix empty
        for(int i : vMatrix) {
            if(i != 0) {
                emptyMatrix = false;
                break;
            }
        }

        int i, j, current, next = 0, location = 0;
        if(!emptyMatrix) {
            i = Util.ran(4);
            j = i+4;
            for(;i < j; i++) { // for each element in matrix
                current = i%4;
                next = (i+1)%4;
                if(vMatrix[current] < vMatrix[next]) {// if fewer cars have spawned in this location
                    location = current; // force spawn location to be here
                }
            }
        }
        vMatrix[location]++;
        return location;
    }

    private int ran(int maxVal) {
        return new Random().nextInt(maxVal);
    }
}
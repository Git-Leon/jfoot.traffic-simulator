package com.github.curriculeon.jfoot.template;
import greenfoot.*;
import java.util.List;
public class SubActor extends Actor {
    public SubActor() {
        rescale();
    }

    public GreenfootImage setimage(String fileName) {
        GreenfootImage image = new GreenfootImage(fileName);
        setImage(image);
        rescale(image);
        return image;
    }

    public boolean isInside(Class cls) {
        getWorld().getWidth();
        getWorld().getHeight();
        return getOneObjectAtOffset(0,0, cls) != null;	        
    }

    public void rescale() { /* Adjust image size relative to that of map size */
        rescale(MapA.mapWidth, MapA.mapHeight);
    }

    public void rescale(GreenfootImage... images) {
        rescale(MapA.mapWidth, MapA.mapHeight, images);
    }

    public void rescale(int worldWidth, int worldHeight) {
        rescale(worldWidth, worldHeight, getImage());
    }

    public void rescale(int worldWidth, int worldHeight, GreenfootImage... images) {
        for(GreenfootImage image : images) {
            double width = image.getWidth();
            double height = image.getHeight();

            double xRatio = (worldWidth/1017.0);
            double yRatio = (worldHeight/841.0);

            double x = width*xRatio;
            double y = height*yRatio;

            int xInt = (int) Math.floor(x);
            int yInt = (int) Math.floor(y);
            image.scale(xInt, yInt);
        }
    }

    public boolean atWorldEdge(int x, int y) {
        if(getX() < x || getX() > getWorld().getWidth() - x)
            return true;
        if(getY() < y || getY() > getWorld().getHeight() - y)
            return true;
        else
            return false;
    }

    public void turnRight() {
        setRotation(getRotation()+90);
    }

    public List<Actor> solidObjectInFront(Class cls)
    {
        List<Object> objs = getWorld().getObjects(cls);
        List<Actor> actors = getWorld().getObjects(Actor.class);
        actors.clear();
        for(Object obj: objs){
            Actor actor = (Actor)obj;
            double deg = degreesAway(actor);
            if( Math.abs(degreesAway(actor)) < 90 )
                actors.add(actor);
        }
        return actors;
    }

    public double degreesAway(Actor actor) {
        int r = (int) Math.atan2(actor.getY() - getY(), actor.getY() - getX());
        return r-getRotation();
    }

    public boolean noseContact(Class cls) {
        return touchingNose(cls) != null;
    }

    public boolean noseContact(Class cls, int xOffset, int yOffset) {
        return touchingNose(cls, xOffset, yOffset) != null;
    }

    public Actor touchingNose(Class cls) {
        int imageWidth = (this.getImage().getWidth());
        int imageHeight = (this.getImage().getHeight());
        int offset = imageWidth > imageHeight ? imageWidth : imageHeight;

        return touchingNose(cls, offset/2);
    }

    public Actor touchingNose(Class cls, int offset) {
        return touchingNose(cls, offset, offset);
    }

    public Actor touchingNose(Class cls, int xOffset, int yOffset) {        
        int deltaX = (int)Math.round((Math.cos(getRotation()*Math.PI/180)));
        int deltaY = (int)Math.round((Math.sin(getRotation()*Math.PI/180)));
        int x = xOffset * deltaX;
        int y = yOffset * deltaY;
        return getOneObjectAtOffset(x, y, cls);
    }
}
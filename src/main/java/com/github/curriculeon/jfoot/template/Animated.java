package com.github.curriculeon.jfoot.template;

import greenfoot.*;
public class Animated extends SubActor {
    private GreenfootImage[] images;
    private int currentImage = 0;

    private void init(String basename, String suffix, int noOfImages) {
        images = new GreenfootImage[noOfImages];
        for(int i=0; i < noOfImages; i++) {
            images[i] = new GreenfootImage(basename + i + suffix);
        }
        rescale(images);
        setImage(images[currentImage]);
    }

    public Animated(String basename, String suffix, int noOfImages) {
        super();
        init(basename, suffix, noOfImages);
    }

    public void animate()  {
        if(images != null) {
            currentImage++;
            currentImage %= images.length;
            setImage(images[currentImage]);
        }
    }

    /** return the Image that we have */
    public int getCurImage() {
        return currentImage;
    }

}
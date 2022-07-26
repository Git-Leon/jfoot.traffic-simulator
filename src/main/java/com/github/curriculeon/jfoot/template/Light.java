package com.github.curriculeon.jfoot.template;
import greenfoot.*;
public class Light extends Animated {
    private int frames;
    public Light(String base, String suffix, int noimages) {
        super(base, suffix, noimages);
    }

    public Light() {
        super("Light", ".png", 6);
    }

    public void act()  {
        animate(150);
        frames++;
    }

    public void animate(int frequency)  {
        if(frames%frequency == 1) {
            animate();
        }
    }

    public boolean isRed(Vehicle vehicle) {
        int rotation = vehicle.getRotation();
        int curImage = getCurImage();

        if(rotation == 0 || rotation == 180) {
            if(Util.isInRange(curImage, 1, 2)) {
                return false;
            } else
                return true;
        }
        else if(Util.isInRange(curImage, 4, 5)) {
            return false;
        } else {
            return true;
        }
    }   
}
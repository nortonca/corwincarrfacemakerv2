package up.edu.corwincarrfacemakerv2;

import android.graphics.Canvas;
import java.util.Random;

/**
 * Represents a face with customizable properties for an Android application.
 *
 * @author Corwin Carr
 */

public class Face {
    // Properties
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle;

    // Constructor
    public Face() {
        randomize();
    }

    // Method to randomize face properties
    public void randomize() {
        Random rand = new Random();
        // Assuming 0xFFFFFF is the max color value and 3 is the max index for hair style
        skinColor = rand.nextInt(0xFFFFFF + 1);
        eyeColor = rand.nextInt(0xFFFFFF + 1);
        hairColor = rand.nextInt(0xFFFFFF + 1);
        hairStyle = rand.nextInt(4); // If there are 4 styles (0-3)
    }

    // Stub for the onDraw method
    public void onDraw(Canvas canvas) {
        // This will be implemented later
    }

    // Getter and Setter methods
    public int getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(int skinColor) {
        this.skinColor = skinColor;
    }

    public int getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(int eyeColor) {
        this.eyeColor = eyeColor;
    }

    public int getHairColor() {
        return hairColor;
    }

    public void setHairColor(int hairColor) {
        this.hairColor = hairColor;
    }

    public int getHairStyle() {
        return hairStyle;
    }

    public void setHairStyle(int hairStyle) {
        this.hairStyle = hairStyle;
    }
}


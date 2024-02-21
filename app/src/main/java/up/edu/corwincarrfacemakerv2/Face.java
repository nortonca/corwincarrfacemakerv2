package up.edu.corwincarrfacemakerv2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import java.util.Random;

public class Face {
    private int skinColor;
    private int eyeColor;
    private int hairColor;
    private int hairStyle;

    private Paint skinPaint, eyePaint, hairPaint, mouthPaint;

    public Face() {
        initializePaints(); // Initialize Paint objects first
        randomize();
    }

    public void randomize() {
        Random rand = new Random();
        // Randomize the properties
        skinColor = Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        eyeColor = Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        hairColor = Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
        hairStyle = rand.nextInt(4); // Extend this for more styles

        // Update paint objects based on the randomized colors
        skinPaint.setColor(skinColor);
        eyePaint.setColor(eyeColor);
        hairPaint.setColor(hairColor);
        // Mouth color is preset to red but can be randomized or adjusted as needed
    }

    private void initializePaints() {
        skinPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        eyePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        hairPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mouthPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        // Set up the initial paint styles
        skinPaint.setColor(skinColor);
        eyePaint.setColor(eyeColor);
        hairPaint.setColor(hairColor);
        mouthPaint.setColor(Color.RED); // Default color for the mouth
        mouthPaint.setStyle(Paint.Style.FILL);
    }

    public void onDraw(Canvas canvas) {
        drawFace(canvas);
        drawEyes(canvas);
        drawHair(canvas);
        drawMouth(canvas);
    }

    private void drawFace(Canvas canvas) {
        // Draw the face shape
        canvas.drawOval(100, 100, 300, 400, skinPaint);
    }

    private void drawEyes(Canvas canvas) {
        // Draw the whites of the eyes
        eyePaint.setColor(Color.WHITE);
        canvas.drawCircle(150, 200, 25, eyePaint); // Left eye white
        canvas.drawCircle(250, 200, 25, eyePaint); // Right eye white

        // Draw the irises
        eyePaint.setColor(eyeColor);
        canvas.drawCircle(150, 200, 15, eyePaint); // Left iris
        canvas.drawCircle(250, 200, 15, eyePaint); // Right iris
    }

    private void drawHair(Canvas canvas) {
        // Simplified hair drawing logic
        hairPaint.setStyle(Paint.Style.FILL);
        if (hairStyle == 0) {
            // Example for short hair
            canvas.drawRect(100, 50, 300, 150, hairPaint);
        }
        // Additional hair styles can be implemented here with more drawing logic
    }

    private void drawMouth(Canvas canvas) {
        // Drawing a simple mouth
        Path mouthPath = new Path();
        mouthPath.moveTo(150, 300);
        mouthPath.quadTo(200, 350, 250, 300);
        mouthPath.quadTo(200, 380, 150, 300);
        canvas.drawPath(mouthPath, mouthPaint);
    }

    // Getter and Setter methods
    public int getSkinColor() { return skinColor; }
    public void setSkinColor(int skinColor) {
        this.skinColor = skinColor;
        skinPaint.setColor(skinColor);
    }

    public int getEyeColor() { return eyeColor; }
    public void setEyeColor(int eyeColor) {
        this.eyeColor = eyeColor;
        eyePaint.setColor(eyeColor);
    }

    public int getHairColor() { return hairColor; }
    public void setHairColor(int hairColor) {
        this.hairColor = hairColor;
        hairPaint.setColor(hairColor);
    }

    public int getHairStyle() { return hairStyle; }
    public void setHairStyle(int hairStyle) {
        this.hairStyle = hairStyle;
        // Trigger a redraw if your app supports dynamic updates
    }

    // Method to convert separate RGB values into a single color
    public int getColorFromRGB(int red, int green, int blue) {
        return Color.rgb(red, green, blue);
    }
}

package up.edu.corwincarrfacemakerv2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import java.util.Random;

public class Face {
    // Face attribute variables
    private int skinColor, eyeColor, hairColor, hairStyle;
    private Paint skinPaint, eyePaint, hairPaint, mouthPaint;
    private final Random rand = new Random(); // Single Random instance for efficiency

    /**
     * Constructor initializes paints and randomizes face features.
     */
    public Face(Context context) {
        initializePaints();
        randomize();
    }

    /**
     * Initializes Paint objects for drawing different face parts.
     * Paint objects are configured with anti-aliasing for smoother edges.
     */
    private void initializePaints() {
        skinPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        eyePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        hairPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mouthPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        // Configure mouth paint for drawing
        mouthPaint.setColor(Color.RED);
        mouthPaint.setStyle(Paint.Style.FILL);
    }

    /**
     * Randomizes the face's attributes like skin color, eye color, and hair color/style.
     * Utilizes a single Random instance for all random number generation.
     */
    public void randomize() {
        skinColor = generateRandomColor();
        eyeColor = generateRandomColor();
        hairColor = generateRandomColor();
        hairStyle = rand.nextInt(4); // Assume 4 different hair styles
        updatePaintColors();
    }

    /**
     * Generates a random RGB color.
     * @return An integer representing a color.
     */
    private int generateRandomColor() {
        return Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }

    /**
     * Updates the Paint objects' colors based on the current attributes.
     */
    private void updatePaintColors() {
        skinPaint.setColor(skinColor);
        eyePaint.setColor(eyeColor); // This will be overridden for sclera
        hairPaint.setColor(hairColor);
    }

    /**
     * Main method for drawing the face on a canvas.
     * Calls helper methods to draw individual face parts.
     */
    public void onDraw(Canvas canvas) {
        drawHair(canvas);
        drawFace(canvas);
        drawEyes(canvas);
        drawMouth(canvas);
    }

    /**
     * Draws the face using an oval shape.
     */
    private void drawFace(Canvas canvas) {
        canvas.drawOval(100, 100, 300, 400, skinPaint);
    }

    /**
     * Draws the eyes, including the sclera and iris.
     */
    private void drawEyes(Canvas canvas) {
        eyePaint.setColor(Color.WHITE); // For sclera
        canvas.drawCircle(150, 200, 25, eyePaint);
        canvas.drawCircle(250, 200, 25, eyePaint);

        eyePaint.setColor(eyeColor); // For iris
        canvas.drawCircle(150, 200, 15, eyePaint);
        canvas.drawCircle(250, 200, 15, eyePaint);
    }

    /**
     * Draws the hair on the canvas based on the current hair style.
     */
    private void drawHair(Canvas canvas) {
        hairPaint.setStyle(Paint.Style.FILL);
        switch (hairStyle) {
            case 0: // Bald, intentionally left blank
                break;
            case 1: // Short hair
                canvas.drawRect(100, 50, 300, 150, hairPaint);
                break;
            case 2: // Long hair
                canvas.drawRect(100, 25, 300, 200, hairPaint);
                break;
            case 3: // Curly hair
                int startX = 100, startY = 50, radius = 10;
                for (int i = 0; i < 10; i++) {
                    canvas.drawCircle(startX + (i * 20), startY + (rand.nextInt(41) - 20), radius, hairPaint);
                }
                break;
            default: // No default action required
                break;
        }
    }

    /**
     * Draws the mouth using a Path for more complex shapes like curves.
     */
    private void drawMouth(Canvas canvas) {
        Path mouthPath = new Path();
        mouthPath.moveTo(150, 300);
        mouthPath.quadTo(200, 350, 250, 300); // Upper lip
        mouthPath.quadTo(200, 380, 150, 300); // Lower lip
        canvas.drawPath(mouthPath, mouthPaint);
    }

    // Getters and setters for face attributes allow changing individual aspects of the face.

    public int getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(int skinColor) {
        this.skinColor = skinColor;
        updatePaintColors();
    }

    public int getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(int eyeColor) {
        this.eyeColor = eyeColor;
        updatePaintColors();
    }

    public int getHairColor() {
        return hairColor;
    }

    public void setHairColor(int hairColor) {
        this.hairColor = hairColor;
        updatePaintColors();
    }

    public int getHairStyle() {
        return hairStyle;
    }

    public void setHairStyle(int hairStyle) {
        this.hairStyle = hairStyle;
        // Potentially redraw the hair if the style changes
    }
}

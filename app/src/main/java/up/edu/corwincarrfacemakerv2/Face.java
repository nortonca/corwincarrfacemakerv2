package up.edu.corwincarrfacemakerv2;

import android.content.Context;
import android.content.res.Resources;
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
    private Context context; // Context to access resources

    // Constructor with Context parameter
    public Face(Context context) {
        this.context = context;
        initializePaints(); // Initialize Paint objects first
        randomize();
    }

    // Randomize face properties
    public void randomize() {
        Random rand = new Random();
        // Randomize the properties
        skinColor = generateRandomColor();
        eyeColor = generateRandomColor();
        hairColor = generateRandomColor();
        hairStyle = rand.nextInt(4); // Extend this for more styles

        // Update paint objects based on the randomized colors
        skinPaint.setColor(skinColor);
        eyePaint.setColor(eyeColor);
        hairPaint.setColor(hairColor);
    }

    // Initialize Paint objects
    private void initializePaints() {
        skinPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        eyePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        hairPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mouthPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        // Set up the initial paint styles
        mouthPaint.setColor(Color.RED); // Default color for the mouth
        mouthPaint.setStyle(Paint.Style.FILL);
    }

    // Draw the face components on canvas
    public void onDraw(Canvas canvas) {
        drawFace(canvas);
        drawEyes(canvas);
        drawHair(canvas);
        drawMouth(canvas);
    }

    // Draw the face shape
    private void drawFace(Canvas canvas) {
        canvas.drawOval(100, 100, 300, 400, skinPaint);
    }

    // Draw the eyes
    private void drawEyes(Canvas canvas) {
        eyePaint.setColor(Color.WHITE); // White color for eyes
        canvas.drawCircle(150, 200, 25, eyePaint); // Left eye
        canvas.drawCircle(250, 200, 25, eyePaint); // Right eye

        eyePaint.setColor(eyeColor); // Set eye color
        canvas.drawCircle(150, 200, 15, eyePaint); // Left iris
        canvas.drawCircle(250, 200, 15, eyePaint); // Right iris
    }

    // Draw the hair
    private void drawHair(Canvas canvas) {
        hairPaint.setStyle(Paint.Style.FILL);
        Resources resources = context.getResources();
        String[] hairstyles = resources.getStringArray(R.array.hair_style_options);
        String selectedHairstyle = hairstyles[hairStyle];

        switch (selectedHairstyle) {
            case "Bald":
                // No hair to draw for bald style
                break;
            case "Short":
                // Example for short hair
                canvas.drawRect(100, 50, 300, 150, hairPaint);
                break;
            case "Long":
                // Example for long hair
                canvas.drawRect(100, 50, 300, 300, hairPaint);
                break;
            case "Curly":
                // Example for curly hair
                drawCurlyHair(canvas);
                break;
            default:
                // Default to short hair
                canvas.drawRect(100, 50, 300, 150, hairPaint);
                break;
        }
    }

    // Draw curly hair
    private void drawCurlyHair(Canvas canvas) {
        // Implement your curly hair drawing logic here
    }

    // Draw the mouth
    private void drawMouth(Canvas canvas) {
        Path mouthPath = new Path();
        mouthPath.moveTo(150, 300);
        mouthPath.quadTo(200, 350, 250, 300);
        mouthPath.quadTo(200, 380, 150, 300);
        canvas.drawPath(mouthPath, mouthPaint);
    }

    // Generate a random color
    private int generateRandomColor() {
        Random rand = new Random();
        return Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
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

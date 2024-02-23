package up.edu.corwincarrfacemakerv2;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.widget.AdapterView;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Face face;
    private Spinner hairStyleSpinner;
    private SeekBar redSeekBar, greenSeekBar, blueSeekBar;
    private RadioGroup colorFeatureRadioGroup;
    private RadioButton hairRadioButton, eyesRadioButton, skinRadioButton;
    private Button randomFaceButton;
    private SurfaceView faceSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views from layout
        initializeViews();
        // Create a new Face instance
        face = new Face(this);

        // Setup listeners for UI components
        setupListeners();
        // Update UI with initial face attributes
        updateUIWithFace();
    }

    // Initialize views from layout
    private void initializeViews() {
        // Find views by their IDs
        hairStyleSpinner = findViewById(R.id.hairStyleSpinner);
        redSeekBar = findViewById(R.id.redSeekBar);
        greenSeekBar = findViewById(R.id.greenSeekBar);
        blueSeekBar = findViewById(R.id.blueSeekBar);
        colorFeatureRadioGroup = findViewById(R.id.colorFeatureRadioGroup);
        hairRadioButton = findViewById(R.id.hairRadioButton);
        eyesRadioButton = findViewById(R.id.eyesRadioButton);
        skinRadioButton = findViewById(R.id.skinRadioButton);
        randomFaceButton = findViewById(R.id.randomFaceButton);
        faceSurfaceView = findViewById(R.id.faceSurfaceView);

        // Setup a surface holder callback for the face surface view
        faceSurfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                drawFaceOnSurface();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                drawFaceOnSurface();
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                // Typically you handle releasing resources or pausing animations here
            }
        });
    }

    // Setup listeners for UI components
    private void setupListeners() {
        // Listener for hair style spinner
        hairStyleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                face.setHairStyle(position);
                drawFaceOnSurface();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case where no item is selected if needed
            }
        });

        // Listener for color feature radio group
        colorFeatureRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            updateSeekBarProgress();
            drawFaceOnSurface();
        });

        // Listener for random face button
        randomFaceButton.setOnClickListener(v -> {
            face.randomize();
            updateUIWithFace();
            drawFaceOnSurface();
        });

        // Listener for seek bars
        SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    updateFaceColor();
                    drawFaceOnSurface();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Handle start of touch event if necessary
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Handle end of touch event if necessary
            }
        };
        redSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        greenSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        blueSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
    }

    // Update face color based on seek bar progress
    private void updateFaceColor() {
        int color = Color.rgb(redSeekBar.getProgress(), greenSeekBar.getProgress(), blueSeekBar.getProgress());
        if (hairRadioButton.isChecked()) {
            face.setHairColor(color);
        } else if (eyesRadioButton.isChecked()) {
            face.setEyeColor(color);
        } else if (skinRadioButton.isChecked()) {
            face.setSkinColor(color);
        }
    }

    // Update seek bar progress based on selected color feature
    private void updateSeekBarProgress() {
        int color = 0;
        if (hairRadioButton.isChecked()) {
            color = face.getHairColor();
        } else if (eyesRadioButton.isChecked()) {
            color = face.getEyeColor();
        } else if (skinRadioButton.isChecked()) {
            color = face.getSkinColor();
        }
        setSeekBarsFromColor(color);
    }

    // Set seek bar progress based on color
    private void setSeekBarsFromColor(int color) {
        redSeekBar.setProgress(Color.red(color));
        greenSeekBar.setProgress(Color.green(color));
        blueSeekBar.setProgress(Color.blue(color));
    }

    // Draw the face on the surface view
    // Draw the face on the surface view with canvas clearing
    private void drawFaceOnSurface() {
        Canvas canvas = faceSurfaceView.getHolder().lockCanvas();
        if (canvas != null) {
            clearCanvas(canvas); // Clear the canvas before drawing
            face.onDraw(canvas); // Draw the face
            faceSurfaceView.getHolder().unlockCanvasAndPost(canvas); // Release the canvas
        }
    }

    // Method to clear the canvas with a background color
    private void clearCanvas(Canvas canvas) {
        canvas.drawColor(Color.WHITE); // Clears the canvas with a white color. Change this if needed.
    }

    // Update UI with current face attributes
    private void updateUIWithFace() {
        setSeekBarsFromColor(face.getSkinColor());
        hairStyleSpinner.setSelection(face.getHairStyle());
    }
}

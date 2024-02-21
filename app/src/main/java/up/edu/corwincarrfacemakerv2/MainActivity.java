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

        // Initialize views
        hairStyleSpinner = findViewById(R.id.hairStyleSpinner);
        redSeekBar = findViewById(R.id.redSeekBar);
        greenSeekBar = findViewById(R.id.greenSeekBar);
        blueSeekBar = findViewById(R.id.blueSeekBar);
        colorFeatureRadioGroup = findViewById(R.id.colorFeatureRadioGroup);
        hairRadioButton = findViewById(R.id.hairRadioButton);
        eyesRadioButton = findViewById(R.id.eyesRadioButton);
        skinRadioButton = findViewById(R.id.skinRadioButton);
        randomFaceButton = findViewById(R.id.randomFaceButton);
        faceSurfaceView = findViewById(R.id.faceSurfaceView); // Initialize SurfaceView

        face = new Face(); // Initialize your Face object here

        setupUI();
        setupListeners();
        updateUIWithFace(); // Initial UI update to reflect the random face

        // Add this line to call the drawFace() method when the SurfaceView is ready
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
            }
        });
    }

    private void drawFaceOnSurface() {
        Canvas canvas = faceSurfaceView.getHolder().lockCanvas();
        if (canvas != null) {
            face.onDraw(canvas);
            faceSurfaceView.getHolder().unlockCanvasAndPost(canvas);
        }
    }

    private void setupUI() {
        // UI setup code remains unchanged
    }

    private void setupListeners() {
        // Listeners setup code for spinner and seekbars remains unchanged

        colorFeatureRadioGroup.setOnCheckedChangeListener((group, checkedId) -> updateSeekBarProgress());

        randomFaceButton.setOnClickListener(v -> {
            face.randomize();
            updateUIWithFace();
            drawFaceOnSurface();
        });
    }

    private void updateFaceColor() {
        int red = redSeekBar.getProgress();
        int green = greenSeekBar.getProgress();
        int blue = blueSeekBar.getProgress();
        int newColor = Color.rgb(red, green, blue);

        if (hairRadioButton.isChecked()) {
            face.setHairColor(newColor);
        } else if (eyesRadioButton.isChecked()) {
            face.setEyeColor(newColor);
        } else if (skinRadioButton.isChecked()) {
            face.setSkinColor(newColor);
        }

        // Refresh drawing or view representing the face
        drawFaceOnSurface();
    }


    private void updateSeekBarProgress() {
        // Adjust SeekBar positions based on the currently selected feature's color
        if (hairRadioButton.isChecked()) {
            setSeekBarsFromColor(face.getHairColor());
        } else if (eyesRadioButton.isChecked()) {
            setSeekBarsFromColor(face.getEyeColor());
        } else if (skinRadioButton.isChecked()) {
            setSeekBarsFromColor(face.getSkinColor());
        }
    }

    private void setSeekBarsFromColor(int color) {
        redSeekBar.setProgress(Color.red(color));
        greenSeekBar.setProgress(Color.green(color));
        blueSeekBar.setProgress(Color.blue(color));
    }

    private void updateUIWithFace() {
        setSeekBarsFromColor(face.getSkinColor()); // Set seek bars to skin color
        hairStyleSpinner.setSelection(face.getHairStyle()); // Set hair style spinner

        // Refresh the view that displays the face if necessary
        drawFaceOnSurface(); // Example: assuming a method to draw the face on a SurfaceView
    }

}

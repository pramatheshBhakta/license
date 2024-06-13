package com.example.license;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.io.InputStream;

public class DependencyDetail extends AppCompatActivity {

    private TextView activityTitle;
    private TextView dependencyDetail;
    private ImageView backArrow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dependency_detail);

        activityTitle = findViewById(R.id.activity_title);
        dependencyDetail = findViewById(R.id.dependency_detail);

        String dependency = getIntent().getStringExtra("dependency");
        activityTitle.setText(dependency);

        String detailText = readTextFromFile(dependency + ".txt");
        dependencyDetail.setText(detailText);
        backArrow = findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Finish the current activity and go back to the previous one
            }
        });
        //adjustTextSize();
    }

    private String readTextFromFile(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStream inputStream = getAssets().open(fileName);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            String content = new String(buffer);

            String[] lines = content.split("\n");
            for (String line : lines) {
                stringBuilder.append(line.trim()).append("\n"); // Handle trailing spaces as new lines
            }

        } catch (IOException e) {
            e.printStackTrace();
            stringBuilder.append("Details not available.");
        }
        return stringBuilder.toString();
    }

    private void adjustTextSize() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float screenWidthDp = metrics.widthPixels / metrics.density;

        if (screenWidthDp >= 600) {
            dependencyDetail.setTextSize(20);
            activityTitle.setTextSize(28);
        } else if (screenWidthDp >= 400) {
            dependencyDetail.setTextSize(18);
            activityTitle.setTextSize(26);
        } else {
            dependencyDetail.setTextSize(16);
            activityTitle.setTextSize(24);
        }

        // Set monospaced font
        dependencyDetail.setTypeface(Typeface.MONOSPACE);
    }
}

package com.example.license;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.dependency_list);
        // Load dependencies from string resources
        String[] dependencies = getResources().getStringArray(R.array.dependencies_array);
        // Modify the ArrayAdapter to use the custom layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_item_dependency, R.id.dependency_name, dependencies);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String dependency = dependencies[position];
                Intent intent = new Intent(MainActivity.this, DependencyDetail.class);
                intent.putExtra("dependency", dependency);
                startActivity(intent);
            }
        });

        ImageView backArrow = findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity when back arrow is clicked
                finish();
            }
        });
    }
}

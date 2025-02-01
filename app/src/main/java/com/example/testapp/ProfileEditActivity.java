package com.example.testapp;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileEditActivity extends AppCompatActivity {

    private EditText editName;
    private Button saveButton;
    private TextView displayName;
    private ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // 各ビューを取得
        editName = findViewById(R.id.editName);
        saveButton = findViewById(R.id.saveButton);
        displayName = findViewById(R.id.displayName);
        profileImage = findViewById(R.id.profileImage);

        if (profileImage == null || editName == null || saveButton == null) {
            throw new NullPointerException("ビューが正しく初期化されていません。");
        }

        // 保存ボタンのクリック処理
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editName.getText().toString();
                displayName.setText("名前: " + name);
            }
        });
    }
}
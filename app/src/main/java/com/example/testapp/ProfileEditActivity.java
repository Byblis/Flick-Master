package com.example.testapp;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;

public class ProfileEditActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1; // ギャラリー選択リクエストコード

    private EditText editName;
    private Button saveButton;
    private TextView displayName;
    private ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        // ここでビューを取得
        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        } else {
            Log.e("ProfileEditActivity", "mainView が null です");
        }

        // 各ビューを取得
        editName = findViewById(R.id.editName);
        saveButton = findViewById(R.id.saveButton);
        displayName = findViewById(R.id.displayName);
        profileImage = findViewById(R.id.profileImage);

        if (profileImage == null || editName == null || saveButton == null) {
            throw new NullPointerException("ビューが正しく初期化されていません。");
        }

        // プロフィール画像タップリスナーを追加
        profileImage.setOnClickListener(v -> openGallery());

        // 保存ボタンのクリック処理
        saveButton.setOnClickListener(v -> {
            String name = editName.getText().toString();
            displayName.setText("名前: " + name);
        });
    }

    // ギャラリーを開くメソッド
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    // 選択した画像を受け取る
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData(); // 画像URIを取得
            profileImage.setImageURI(imageUri); // 画像をImageViewに設定
        }
    }
}

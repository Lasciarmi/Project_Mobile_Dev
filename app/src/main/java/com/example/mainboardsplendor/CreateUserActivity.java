package com.example.mainboardsplendor;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mainboardsplendor.databinding.ActivityCreateUserBinding;
import com.example.mainboardsplendor.databinding.ActivityStartUpBinding;
import com.example.mainboardsplendor.model.User;

public class CreateUserActivity extends AppCompatActivity {

    private ActivityCreateUserBinding binding;
    public final static String PLAYER_1 = "user1";
    public final static String PLAYER_2 = "user2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityCreateUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.backButton.setOnClickListener(view -> OpenStartUpActivity());
        binding.playButton.setOnClickListener(view -> OpenMainBoardActivity());
    }

    private void OpenMainBoardActivity() {
        String user1 = binding.player1Text.getText().toString();
        String user2 = binding.player2Text.getText().toString();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(PLAYER_1, user1);
        intent.putExtra(PLAYER_2, user2);
        startActivity(intent);
    }

    private void OpenStartUpActivity() {
        Intent intent = new Intent(this, StartUpActivity.class);
        startActivity(intent);
    }
}
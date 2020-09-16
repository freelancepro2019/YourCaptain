package com.your_captain.activities_fragments.activity_choose_user_type;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.your_captain.R;
import com.your_captain.activities_fragments.activity_splash.SplashActivity;
import com.your_captain.activities_fragments.activity_trainee_sign_up.TraineeSignUpActivity;
import com.your_captain.activities_fragments.activity_trainer_sign_up.TrainerSignUpActivity;
import com.your_captain.databinding.ActivityChooserBinding;
import com.your_captain.language.Language;

import io.paperdb.Paper;

public class ChooserActivity extends AppCompatActivity {
    private ActivityChooserBinding binding;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase,Paper.book().read("lang","en")));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_chooser);
        initView();
    }

    private void initView() {
        binding.btnTrainee.setOnClickListener(v -> {
            Intent intent = new Intent(this, TraineeSignUpActivity.class);
            startActivity(intent);
            finish();
        });

        binding.btnTrainer.setOnClickListener(v -> {
            Intent intent = new Intent(this, TrainerSignUpActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
        finish();
    }
}
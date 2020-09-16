package com.your_captain.activities_fragments.activity_login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;

import com.your_captain.R;
import com.your_captain.activities_fragments.activity_choose_user_type.ChooserActivity;
import com.your_captain.activities_fragments.activity_splash.SplashActivity;
import com.your_captain.databinding.ActivityLoginBinding;
import com.your_captain.language.Language;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;


    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase,Paper.book().read("lang","en")));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        initView();
    }

    private void initView() {

        binding.tvSignUp.setText(Html.fromHtml(getString(R.string.new_account_sign_up)));
        binding.tvSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(this, ChooserActivity.class);
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
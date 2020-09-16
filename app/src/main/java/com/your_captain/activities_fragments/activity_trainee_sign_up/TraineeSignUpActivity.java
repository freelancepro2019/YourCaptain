package com.your_captain.activities_fragments.activity_trainee_sign_up;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.your_captain.R;
import com.your_captain.activities_fragments.activity_choose_user_type.ChooserActivity;
import com.your_captain.activities_fragments.activity_login.LoginActivity;
import com.your_captain.activities_fragments.activity_splash.SplashActivity;
import com.your_captain.databinding.ActivityLoginBinding;
import com.your_captain.databinding.ActivityTraineeSignUpBinding;
import com.your_captain.language.Language;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import io.paperdb.Paper;

public class TraineeSignUpActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private ActivityTraineeSignUpBinding binding;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        super.attachBaseContext(Language.updateResources(newBase,Paper.book().read("lang","en")));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_trainee_sign_up);
        initView();
    }

    private void initView() {
        binding.llBack.setOnClickListener(v -> onBackPressed());
        binding.tvSignIn.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
        createDateDialog();
        binding.tvDate.setOnClickListener(v -> {
            try {
                datePickerDialog.show(getFragmentManager(),"");
            }catch (Exception e){}
        });
    }


    private void createDateDialog() {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        Calendar calendar_now = Calendar.getInstance(Locale.ENGLISH);

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.YEAR, (calendar_now.get(Calendar.YEAR)));

        datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.setYearRange(calendar.get(Calendar.YEAR)-50,calendar.get(Calendar.YEAR)-18);
        datePickerDialog.setOkText(R.string.select);
        datePickerDialog.setCancelText(getString(R.string.cancel));
        datePickerDialog.setAccentColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        datePickerDialog.setOkColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        datePickerDialog.setCancelColor(ContextCompat.getColor(this, R.color.gray4));
        datePickerDialog.setLocale(Locale.ENGLISH);
        datePickerDialog.setVersion(DatePickerDialog.Version.VERSION_1);
        datePickerDialog.setMaxDate(calendar);

    }




    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        binding.tvDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ChooserActivity.class);
        startActivity(intent);
        finish();
    }
}
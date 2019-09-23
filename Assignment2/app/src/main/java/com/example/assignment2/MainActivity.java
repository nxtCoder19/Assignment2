package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment2.util.commonUtil;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvTitle;
    Button btnLogin;
    EditText etEmail;
    EditText etPassword;
    ImageView ivIcon;


    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
    }


    private void initUi() {


        tvTitle = findViewById(R.id.tv_title);
        btnLogin = findViewById(R.id.btn_login);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        ivIcon=findViewById(R.id.iv_eye);
        Spannablestring();
        btnLogin.setOnClickListener(this);
        ivIcon.setOnClickListener(this);
    }


    private void Spannablestring() {
        String text = "Dont have an account? REGISTER";

        // initialize a new ClickableSpan
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Registration.class);
                startActivity(intent);

            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                int color = ContextCompat.getColor(MainActivity.this, R.color.backgroundColor);
                ds.setColor(color);
                ds.setUnderlineText(false);
            }
        };

        // initialize a new SpannableStringBuilder instance
        SpannableStringBuilder ssBuilder = new SpannableStringBuilder(text);

      //  SpannableStringBuilder ssBuilder = new SpannableStringBuilder(text);
        // apply the clickable text to the span
        ssBuilder.setSpan(
                clickableSpan, // span to add
                text.indexOf("REGISTER"), // start of the span (inclusive)
                text.indexOf("REGISTER") + String.valueOf("REGISTER").length(), // end of the span (exclusive)
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE // do not extend the span when text add later
        );


        // bold
        ssBuilder.setSpan(
                new StyleSpan(Typeface.BOLD),
                text.indexOf("REGISTER"),
                text.indexOf("REGISTER") + String.valueOf("REGISTER").length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        );

        tvTitle.setText(ssBuilder);

        // this step is mandated for the url and clickable styles
        tvTitle.setMovementMethod(LinkMovementMethod.getInstance());
        tvTitle.setHighlightColor(Color.TRANSPARENT);

    }

    private boolean validation() {
        View view = findViewById(R.id.btn_login);
        if (etEmail.getText().toString().trim().length() == 0) {
            Snackbar.make(view, getString(R.string.login_err_no_email), Snackbar.LENGTH_SHORT).show();
            return false;
        } else if (etPassword.getText().toString().trim().length() == 0) {
            Snackbar.make(view, getString(R.string.login_err_no_password), Snackbar.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.btn_login:
                if (validation())
                {
                    if(etEmail.getText().toString().trim().matches(emailPattern)) {
                        Intent i = new Intent(getApplicationContext(), otpActivity.class);
                        startActivity(i);
                        break;
                    }

                    else
                    {
                        View view1=findViewById(R.id.btn_login);
                        commonUtil.showSnackbar(view1,getString(R.string.login_err_no_valid_email));
                    }
                }
                break;

            case R.id.iv_eye:
                showIconPassword();
                break;
        }
    }

    private void showIconPassword()
    { if(etPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance()))
    {   ivIcon.setImageResource(R.drawable.ic_visibility_black_24dp);
        etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
    }else{
        ivIcon.setImageResource(R.drawable.ic_visibility_off_black_24dp);
        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
    }


    }
}
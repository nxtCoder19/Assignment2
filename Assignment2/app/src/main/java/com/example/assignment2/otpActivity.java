package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.assignment2.util.commonUtil;

public class otpActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView ivIcon;
    Button btnResend;
    Button btnSubmit;
    EditText etOtp1;
    EditText etOtp2;
    EditText etotp3;
    EditText etOtp4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        initUi();
    }

    private void initUi() {
        ivIcon = findViewById(R.id.ivIcon);
        btnResend = findViewById(R.id.btn_resend);
        btnSubmit = findViewById(R.id.btn_submit);
        etOtp1 = findViewById(R.id.et_otp1);
        etOtp2 = findViewById(R.id.et_otp2);
        etotp3 = findViewById(R.id.et_otp3);
        etOtp4 = findViewById(R.id.et_otp4);
        ivIcon.setOnClickListener(this);
        btnResend.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivIcon:
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                break;

            case R.id.btn_resend:
                View view=findViewById(R.id.btn_resend);
                commonUtil.showSnackbar(view,getString(R.string.resend_mess_otp));
                break;

            case R.id.btn_submit:

                if (submitValidation()) {
                    View view1=findViewById(R.id.btn_submit);
                    commonUtil.showSnackbar(view1,getString(R.string.submit_mess_otp));
                }
                break;
        }
    }


    private boolean submitValidation() {
        View view = findViewById(R.id.btn_submit);
        if (etOtp1.getText().toString().trim().length() == 0 || etOtp2.getText().toString().trim().length() == 0
                || etotp3.getText().toString().trim().length() == 0
                || etOtp4.getText().toString().trim().length() == 0) {


            commonUtil.showSnackbar(view, getString(R.string.submit_err_no_otp));
            return false;
        }
        return true;
    }
}







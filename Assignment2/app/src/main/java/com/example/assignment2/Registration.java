package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment2.util.commonUtil;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class Registration extends AppCompatActivity implements View.OnClickListener{

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    ImageView ivBackIcon;
    TextView tvDateIcon;
    Button btnContinue;
    TextView regTitle;
    EditText etFullName;
    EditText etEmail;
    EditText etPassword;
    EditText etGender;
    EditText etDob;
    EditText etUserType;
    EditText etOccupation;
    DatePickerDialog picker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initUi();
    }
    private void initUi()
    {
        btnContinue=findViewById(R.id.btn_continue);
        etFullName=findViewById(R.id.et_reg_fullName);
        etEmail=findViewById(R.id.et_reg_email);
        etPassword=findViewById(R.id.et_reg_password);
        etGender=findViewById(R.id.et_reg_gender);
        etDob=findViewById(R.id.et_reg_dob);
        etUserType=findViewById(R.id.et_reg_userType);
        etOccupation=findViewById(R.id.et_reg_occupation);
        ivBackIcon=findViewById(R.id.iv_arrowIcon);
        tvDateIcon=findViewById(R.id.tv_date_icon);
        regTitle=findViewById(R.id.tv_regTitle);
        ivBackIcon.setOnClickListener(this);
        btnContinue.setOnClickListener(this);
        regTitle.setOnClickListener(this);
        tvDateIcon.setOnClickListener(this);

    }

    private boolean validation()
    {
       View view=findViewById(R.id.btn_continue);
       if(etFullName.getText().toString().trim().isEmpty())
       {
           commonUtil.showSnackbar(view,getString(R.string.continue_err_no_fullName));
           return false;
       }

       else  if(etEmail.getText().toString().trim().isEmpty())
       {
           commonUtil.showSnackbar(view,getString(R.string.continue_err_no_email));
           return false;
       }

       else  if(etPassword.getText().toString().trim().isEmpty())
       {
           commonUtil.showSnackbar(view,getString(R.string.continue_err_no_password));
           return false;
       }

       else  if(etGender.getText().toString().trim().isEmpty())
       {
           commonUtil.showSnackbar(view,getString(R.string.continue_err_no_gender));
           return false;
       }

       else  if(etDob.getText().toString().trim().isEmpty())
       {
           commonUtil.showSnackbar(view,getString(R.string.continue_err_no_dob));
           return false;
       }
       else  if(etUserType.getText().toString().trim().isEmpty())
       {
           commonUtil.showSnackbar(view,getString(R.string.continue_err_no_userType));
           return false;
       }

       else  if(etOccupation.getText().toString().trim().isEmpty())
       {
           commonUtil.showSnackbar(view,getString(R.string.continue_err_no_occupation));
           return false;
       }
       return true;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_arrowIcon:
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                break;

            case R.id.btn_continue:
                if(validation()) {

                    if(etEmail.getText().toString().trim().matches(emailPattern))
                    {
                        Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i1);
                    }
                    else
                    {
                        View view1=findViewById(R.id.btn_continue);
                        commonUtil.showSnackbar(view1,getString(R.string.continue_err_err_in_email_pattern));
                    }
                }
                break;

            case R.id.tv_regTitle:
                Intent i2=new Intent(getApplicationContext(),Registration.class);
                startActivity(i2);
                break;

            case R.id.tv_date_icon:
                setCalender();
                break;
        }
    }
    private void setCalender(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        picker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                etDob.setText(dayOfMonth + "/" + (month + 1) + "/" + year);

            }
        }, year, month, day);
        picker.show();

    }
}

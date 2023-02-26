package com.example.app2_blood_pressure_v2;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SignUpActivity extends AppCompatActivity {
    final static String USER_USERNAME ="com.example.app2_blood_pressure_v2.USER_USERNAME";
    private EditText edt_username, edt_password, edt_confirm_password, edt_name, edt_birth_date;
    private TextView tv_username_error, tv_password_error, tv_confirm_password_error,tv_name_error,tv_gender_error,tv_birth_date_error;
    private Button bt_register;
    private SQLiteDatabase database_blood_pressure=null;
    private EditText FindViewById;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    DatabaseHelper databaseHelper;
    private int mYear, mMonth, mDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        databaseHelper = new DatabaseHelper(SignUpActivity.this);

        edt_username = (EditText)findViewById(R.id.edt_username_sup);
        edt_password = (EditText) findViewById(R.id.edt_password_sup);
        edt_confirm_password = (EditText) findViewById(R.id.edt_confirm_password_sup);

        edt_name = (EditText) findViewById(R.id.edt_name_sup);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        edt_birth_date = (EditText) findViewById(R.id.edt_birth_date_sup);

        tv_username_error = (TextView) findViewById(R.id.tv_error_username_sup);
        tv_password_error = (TextView) findViewById(R.id.tv_error_password_sup);
        tv_confirm_password_error = (TextView) findViewById(R.id.tv_error_confirm_password_sup);
        tv_name_error = (TextView) findViewById(R.id.tv_error_name_sup);
        tv_gender_error = (TextView) findViewById(R.id.tv_error_gender_sup);
        tv_birth_date_error = (TextView) findViewById(R.id.tv_error_birth_date_sup);

        tv_username_error.setText("");
        tv_password_error.setText("");
        tv_confirm_password_error.setText("");
        tv_name_error.setText("");
        tv_gender_error.setText("");
        tv_birth_date_error.setText("");

        bt_register = (Button) findViewById(R.id.bt_register_sup);

        edt_birth_date.setOnClickListener(this::onSelectDate);

        bt_register.setOnClickListener(this::bt_register_OnClick);

    }
    //add Dialog Calendar to select birthday date
    public void onSelectDate(View view){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        edt_birth_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    public void bt_register_OnClick(View view) {
        String username_to_check, password_to_check, confirm_password_to_check;
        String name_to_check, gender_to_check, birth_date_to_check;

        username_to_check=edt_username.getText().toString();
        password_to_check=edt_password.getText().toString();
        confirm_password_to_check=edt_confirm_password.getText().toString();
        name_to_check=edt_name.getText().toString();
        int radioID = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioID);
        gender_to_check=radioButton.getText().toString();
        System.out.println("Gender: " + gender_to_check);
        birth_date_to_check=edt_birth_date.getText().toString();


        User new_user= new User();

        Boolean add_new_user=true;

        if(TextUtils.isEmpty(username_to_check)){
            tv_username_error.setText("Please introduce a Username.");
        }else {
            if (databaseHelper.checkUser(username_to_check)) {
                tv_username_error.setText("Please choose other Username.");
                add_new_user=false;
            } else {
                new_user.setUsername(username_to_check);
            }
        }
        if(TextUtils.isEmpty(password_to_check)){
            tv_password_error.setText("Please introduce a Password.");
            add_new_user=false;
        }
        else{
            if(TextUtils.isEmpty(confirm_password_to_check)){
                tv_confirm_password_error.setText("Please confirm your Password.");
                add_new_user=false;
            }
            else{
                if(password_to_check.equals(confirm_password_to_check)) {
                    new_user.setPassword(password_to_check);
                }
                else{
                    tv_confirm_password_error.setText("Passwords don't match. Please confirm your Password.");
                    add_new_user=false;
                }
            }
        }
        if(TextUtils.isEmpty(name_to_check)){
            tv_name_error.setText("Please introduce a Name.");
            add_new_user=false;
        }else {
            new_user.setName(name_to_check);
        }
        if(TextUtils.isEmpty(gender_to_check) ){
            tv_gender_error.setText("Please introduce a Gender.");
            add_new_user=false;
        }else {
            new_user.setGender(gender_to_check);
        }
        if(TextUtils.isEmpty(birth_date_to_check)){
            tv_birth_date_error.setText("Please introduce a Birth Date.");
            add_new_user=false;
        }else {
            new_user.setBirthDate(birth_date_to_check);
        }

        if (add_new_user==true)
        {
            databaseHelper.addUser(new_user);
            Toast.makeText(SignUpActivity.this, "Registration Complete. Please Sign In", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, MainActivity.class);

            startActivity(intent);
        }
       else{
            Toast.makeText(SignUpActivity.this, "Not Possible to Register. Please try again or Sign Up", Toast.LENGTH_SHORT).show();
        }
    }

}
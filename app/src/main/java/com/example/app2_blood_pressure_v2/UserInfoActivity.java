package com.example.app2_blood_pressure_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserInfoActivity extends AppCompatActivity {
    final static String USER_ID="0";

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db=null;
    private TextView tv_user_username, tv_user_name, tv_user_gender, tv_user_birth_date;
    private Button bt_back, bt_sign_out;
    private Integer user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);

        databaseHelper = new DatabaseHelper(UserInfoActivity.this);

        Intent intent =getIntent();
        user_id =  Integer.valueOf(intent.getStringExtra(MainActivity.USER_ID));
        tv_user_username = (TextView) findViewById(R.id.tv_username_wel);
        tv_user_name = (TextView) findViewById(R.id.tv_name_wel);
        tv_user_gender = (TextView) findViewById(R.id.tv_gender_wel);
        tv_user_birth_date = (TextView) findViewById(R.id.tv_birth_date_wel);

        bt_back = (Button) findViewById(R.id.bt_back);
        bt_sign_out = (Button) findViewById(R.id.bt_sign_out_wel);

        bt_back.setOnClickListener(this::bt_back_OnClick);
        bt_sign_out.setOnClickListener(this::bt_sign_out_OnClick);

        User user_info= databaseHelper.getUserByID(user_id);

        tv_user_username.setText(user_info.getUsername());
        tv_user_name.setText(user_info.getName());
        tv_user_gender.setText(user_info.getGender());
        tv_user_birth_date.setText(user_info.getBirthDate());

    }
    public void bt_sign_out_OnClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(USER_ID, "-1");
        startActivity(intent);
    }

    public void bt_back_OnClick(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(USER_ID, user_id.toString());
        startActivity(intent);
    }
}
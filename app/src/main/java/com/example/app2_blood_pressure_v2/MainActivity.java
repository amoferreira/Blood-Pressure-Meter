package com.example.app2_blood_pressure_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final static String USER_ID="0";

    private EditText edt_username, edt_password;
    private Button bt_sign_in, bt_sign_up; //, bt_test;
    private TextView tv_username_error, tv_password_error;
    private SQLiteDatabase database_blood_pressure=null;
    private EditText FindViewById;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase db=null;
    private String back_info_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Intent intent =getIntent();
        //back_info_message =  intent.getStringExtra(MainActivity.USER_ID);

        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(MainActivity.this);

        bt_sign_up = (Button) findViewById(R.id.bt_sign_up);
        bt_sign_in = (Button) findViewById(R.id.bt_sign_in);

        bt_sign_up.setOnClickListener(this::bt_sign_up_OnClick);
        bt_sign_in.setOnClickListener(this::bt_sign_in_OnClick);

    }
    public void bt_sign_up_OnClick(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void bt_sign_in_OnClick(View view) {
        edt_username = (EditText)findViewById(R.id.edt_username);
        edt_password = (EditText) findViewById(R.id.edt_password);

        tv_username_error = (TextView) findViewById(R.id.tv_error_username);
        tv_password_error = (TextView) findViewById(R.id.tv_error_password);
        tv_username_error.setText("");
        tv_password_error.setText("");
        String username_to_check, password_to_check;
        username_to_check=edt_username.getText().toString();
        password_to_check=edt_password.getText().toString();


        if(TextUtils.isEmpty(username_to_check)){
            tv_username_error.setText("Please introduce a Username.");
        }
        else{
            if(TextUtils.isEmpty(password_to_check)) {
                tv_password_error.setText("Please introduce a Password.");
            }
            else{
                if(databaseHelper.checkUserPassword(username_to_check, password_to_check)){

                    Intent intent = new Intent(this, SecondActivity.class);

                    User user_info= databaseHelper.getUserByUsername(username_to_check);
                    Integer user_id=user_info.getID();
                    intent.putExtra(USER_ID, user_id.toString());
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Invalid Username and/or Password. Please try again or Sign Up", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    // Three dots menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater(); //from activity
        inflater.inflate(R.menu.main_page_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.help:{
                Intent intent = new Intent(this, HelpActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.version_info:{
                Toast.makeText(this,R.string.app_version,Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
package com.example.app2_blood_pressure_v2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;

public class SecondActivity extends AppCompatActivity {
    final static String USER_ID="0";
    private Integer user_id;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent =getIntent();
        user_id =  Integer.valueOf(intent.getStringExtra(MainActivity.USER_ID)); //From last activity
        databaseHelper = new DatabaseHelper(SecondActivity.this);
        NavigationBarView navigationView = findViewById(R.id.bottom_nav);
        AddFragment addFragment = new AddFragment();
        HistoryFragment historyFragment = new HistoryFragment();
        StatisticsFragment statisticsFragment = new StatisticsFragment();

        // put fragment add by default
        if (savedInstanceState == null) {
            setFragment(addFragment);
        }

        navigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 //Checking if the item is in checked state or not, if not make it in checked state
                 item.setChecked(true);

                 int id = item.getItemId();
                 switch (id){
                     case R.id.add_values:
                         setFragment(addFragment);
                         break;
                     case R.id.history:
                         setFragment(historyFragment);
                         break;
                     case R.id.statisctics:
                         setFragment(statisticsFragment);
                         break;
                 }

                 return false;
             }
         }
        );
    }
    // Three dots menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater(); //from activity
        inflater.inflate(R.menu.menu_second_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.my_profile:{
                Intent intent = new Intent(this, UserInfoActivity.class);
                intent.putExtra(USER_ID, user_id.toString());
                startActivity(intent);
                break;
            }
            case R.id.sign_out:{
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(USER_ID, "-1");
                startActivity(intent);
                break;
            }
        }
        return true;
    }
    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Bundle arguments = new Bundle();
        arguments.putInt("user_id", user_id);
        fragment.setArguments(arguments);
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
    }

}
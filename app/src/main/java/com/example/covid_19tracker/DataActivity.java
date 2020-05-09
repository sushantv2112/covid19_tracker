package com.example.covid_19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class DataActivity extends AppCompatActivity {

    static  int j=1;
    private int k=0;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener  authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        themeUtils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_data);

        firebaseAuth = FirebaseAuth.getInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //setting the title
        toolbar.setTitle("Covid-19 Tracker");

        //placing toolbar in place of actionbar
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

//        Intent intent=getIntent();
//        Bundle b=intent.getExtras();
//        user=b.getString("user");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void onStart(){
        super.onStart();
        k=0;
    }

    @Override
    public void onBackPressed() {
        k++;
        if(k == 1) {
            String message ="Click one more time to exist app";
            Toast toast= Toast.makeText(DataActivity.this,message, Toast.LENGTH_SHORT);
            View view = toast.getView();
            TextView text = view.findViewById(android.R.id.message);
            text.setBackgroundColor(16777215);
            toast.show();
        } else {
            //exit app to home screen
            Intent homeScreenIntent = new Intent(Intent.ACTION_MAIN);
            homeScreenIntent.addCategory(Intent.CATEGORY_HOME);
            homeScreenIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(homeScreenIntent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.about_dev){
            Intent intent2 = new Intent(DataActivity.this, MainActivity4.class);

            startActivity(intent2);
        }
        else if( item.getItemId()== R.id.change_theme){
            if(j%2!=0) {
                themeUtils.changeToTheme(this, themeUtils.LIGHT);
                j++;
            }
            else {
                themeUtils.changeToTheme(this, themeUtils.DARK);
                j++;
            }
        }
        else if(item.getItemId()==R.id.logout){
            SharedPreferences sharedPrefs =getSharedPreferences(LoginActivity.PREFS_NAME,MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.clear();
            editor.apply();
//            user="";
            startActivity(new Intent(getApplicationContext(),WelcomeActivity.class));
            finish();

        }
        return super.onOptionsItemSelected(item);
    }
    public void aboutCovid19( View v){

        Intent intent = new Intent(DataActivity.this, MainActivity3.class);
        startActivity(intent);
    }
    public void National( View v){

        Intent intent = new Intent(DataActivity.this, MainActivity2.class);
        startActivity(intent);
    }
    public void Global( View v){
        Intent intent = new Intent(DataActivity.this, GlobalActivity.class);
        startActivity(intent);
    }
}
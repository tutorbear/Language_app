package com.hfad.languageapp;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);

        //chnage action bar title, if you do not change it, it will be according to ypur systems default language

        ActionBar actionBar =getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        Button changeLang = findViewById(R.id.changeMyLang);

        changeLang.setOnClickListener (new View.OnClickListener() {

            @Override
            public void onClick(View view) {
            showChangeLanguageDialog();
            }
        });


        }



        private void showChangeLanguageDialog(){
        final String[] listItems ={"Bangla","Hindi","French","English"};
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
            mBuilder.setTitle("Choose Language ...");
            mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                 if (i==0)  {
                     //bangla
                     setLocale("bn");
                    recreate();
                 }

                 else if (i==1){
                     //hindi
                     setLocale("hi");
                     recreate();
                 }

                 else if(i==2){

                     //french
                     setLocale("fr");
                     recreate();
                 }
                 else if(i==3){

                     //english
                     setLocale("en");
                     recreate();
                 }

                 //dismiss alert dialog when language selected
                    dialogInterface.dismiss();



                }
            });

            AlertDialog mDialog = mBuilder.create();
            // show alert box
            mDialog.show();

    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale= locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        //save data to shared preferences
        SharedPreferences.Editor editor = getSharedPreferences( "Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();


    }

    //load language saved in shared preferences
    public void loadLocale(){
        SharedPreferences prefs =getSharedPreferences("Setttings",MODE_PRIVATE);
        String language = prefs.getString("My_Lang","");
        setLocale(language);

    }

}

package com.app.generalscience7and8;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;


public class MainActivity2 extends AppCompatActivity {

    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        pdfView=findViewById(R.id.pdfView);

        int book_position=getIntent().getIntExtra("key_position",0);

        if (book_position==0){
            ActionBar actionBar = getSupportActionBar();
            assert actionBar != null;
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("Grade 7 General Science");

            pdfView.fromAsset("General science Grade 7 .pdf").fitEachPage(true).load();


        }
        else if (book_position==1){
            ActionBar actionBar = getSupportActionBar();
            assert actionBar != null;
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("Grade 8 General Science");

            pdfView.fromAsset("General science Grade 8 .pdf").fitEachPage(true).load();
        }
    }


}
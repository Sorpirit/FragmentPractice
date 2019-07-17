package com.practice.fragmentpractice;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity {

    private Button add;
    private Button replace;
    private Button remove;

    private FilmPage page;
    private FilmsList list;

    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.AddFragment);
        replace = findViewById(R.id.ReplaceFragment);
        remove = findViewById(R.id.RemoveFragment);
        //page = new FilmPage();
        list = new FilmsList();
    }

    @Override
    protected void onStart() {
        super.onStart();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                transaction = getSupportFragmentManager().beginTransaction();

                switch (v.getId()){
                    case R.id.AddFragment:
                        page = new FilmPage();
                        transaction.add(R.id.content,page);
                        transaction.addToBackStack(null);
                        break;
                    case R.id.RemoveFragment:
                        transaction.remove(page);
                        break;
                    case R.id.ReplaceFragment:
                        transaction.replace(R.id.content,new FilmsList());
                        transaction.addToBackStack(null);
                }

                transaction.commit();
            }
        };
        add.setOnClickListener(listener);
        remove.setOnClickListener(listener);
        replace.setOnClickListener(listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        add.setOnClickListener(null);
    }
}

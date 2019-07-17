package com.practice.fragmentpractice;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity {

    private Button add;

    private FilmPage page;
    private FilmsList list;

    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = findViewById(R.id.AddFragment);
        page = new FilmPage();
        list = new FilmsList();
    }

    @Override
    protected void onStart() {
        super.onStart();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.AddFragment:
                        transaction = getSupportFragmentManager().beginTransaction();

                        transaction.add(R.id.content,new FilmPage());
                        transaction.addToBackStack(null);

                        transaction.commit();
                        break;
                }
            }
        };
        add.setOnClickListener(listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        add.setOnClickListener(null);
    }
}

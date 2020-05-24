package com.davis.navigationmenu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        btn = (Button)findViewById(R.id.btn_normal);
        btn.setOnClickListener(this);

        btn = (Button)findViewById(R.id.btn_add);
        btn.setOnClickListener(this);

        btn = (Button)findViewById(R.id.btn_add_view);
        btn.setOnClickListener(this);

        btn = (Button)findViewById(R.id.btn_more);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()){
            case R.id.btn_normal:
                intent = new Intent(MainActivity.this, NormalActivity.class);
                break;
            case R.id.btn_add:
                intent = new Intent(MainActivity.this, AddActivity.class);
                break;
            case R.id.btn_add_view:
                intent = new Intent(MainActivity.this, AddViewActivity.class);
                break;
            case R.id.btn_more:
                intent = new Intent(MainActivity.this, MoreActivity.class);
                break;
        }

        if(intent != null){
            startActivity(intent);
        }
    }
}

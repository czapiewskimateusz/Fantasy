package com.example.mateusz.fantasy.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.mateusz.fantasy.Login.view.LoginActivity;
import com.example.mateusz.fantasy.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_logOut)
    public void logOut(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}

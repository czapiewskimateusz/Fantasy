package com.example.mateusz.fantasy.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mateusz.fantasy.Login.view.LoginActivity;
import com.example.mateusz.fantasy.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends Activity {

    private static int sBackButtonCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sBackButtonCounter = 0;
    }

    @OnClick(R.id.btn_logOut)
    public void logOut(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {

        if (sBackButtonCounter > 0){
            finish();
            moveTaskToBack(true);
        } else {
            Toast.makeText(this,getString(R.string.back_button_close_app_warning),Toast.LENGTH_SHORT).show();
            sBackButtonCounter++;
        }

    }
}

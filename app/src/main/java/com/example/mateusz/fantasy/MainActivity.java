package com.example.mateusz.fantasy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mateusz.fantasy.networkUtils.FetchData;

public class MainActivity extends Activity {

    private EditText mEt_Id;
    private TextView mTv_Result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEt_Id = findViewById(R.id.et_id);
        mTv_Result = findViewById(R.id.tv_Result);
    }

    public void login(View view){
        String id = mEt_Id.getText().toString();

        new FetchData(this,mTv_Result).execute(id);

    }
}

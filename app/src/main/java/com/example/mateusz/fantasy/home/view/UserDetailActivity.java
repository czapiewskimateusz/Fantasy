package com.example.mateusz.fantasy.home.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.home.presenter.UserDetailPresenter;

import static com.example.mateusz.fantasy.home.view.fragment.HomeFragment.BUNDLE_EXTRA;
import static com.example.mateusz.fantasy.home.view.fragment.HomeFragment.EMAIL_EXTRA;
import static com.example.mateusz.fantasy.home.view.fragment.HomeFragment.FIRST_NAME_EXTRA;
import static com.example.mateusz.fantasy.home.view.fragment.HomeFragment.LAST_NAME_EXTRA;
import static com.example.mateusz.fantasy.home.view.fragment.HomeFragment.PASSWORD_EXTRA;

public class UserDetailActivity extends Activity implements UserDetailView {

    //UI
    private TextInputEditText mEtFirstName;
    private TextInputEditText mEtLastName;
    private TextInputEditText mEtEmail;
    private TextInputEditText mEtPassword;
    private TextInputEditText mEtPasswordRepeat;

    private TextInputLayout mLayoutFirstName;
    private TextInputLayout mLayoutLastName;
    private TextInputLayout mLayoutEmail;
    private TextInputLayout mLayoutPassword;
    private TextInputLayout mLayoutPasswordRepeat;
    private Button mBtnSave;

    private String mUserPassword;
    //Dependencies
    private UserDetailPresenter mUserDetailPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        initViews();
        getDataFromIntent();

        mUserDetailPresenter = new UserDetailPresenter(this, this);
    }

    @Override
    public void onEmailError(String string) {
        mLayoutEmail.setError(string);
    }

    @Override
    public void onFirstNameError(String string) {
        mLayoutFirstName.setError(string);
    }

    @Override
    public void onLastNameError(String string) {
        mLayoutLastName.setError(string);
    }

    @Override
    public void onPasswordError(String string) {
        mLayoutPassword.setError(string);
    }

    @Override
    public void onPasswordRepeatError(String string) {
        mLayoutPasswordRepeat.setError(string);
    }

    @Override
    public void clearErrors() {
        mLayoutFirstName.setError("");
        mLayoutLastName.setError("");
        mLayoutEmail.setError("");
        mLayoutPassword.setError("");
        mLayoutPasswordRepeat.setError("");
    }

    private void initViews() {
        mEtFirstName = findViewById(R.id.et_edit_first_name);
        mEtLastName = findViewById(R.id.et_edit_last_name);
        mEtEmail = findViewById(R.id.et_edit_email);
        mEtPassword = findViewById(R.id.et_edit_password);
        mEtPasswordRepeat = findViewById(R.id.et_edit_repeat_password);

        mLayoutFirstName = findViewById(R.id.edit_first_name_layout);
        mLayoutLastName = findViewById(R.id.edit_last_name_layout);
        mLayoutEmail = findViewById(R.id.edit_email_layout);
        mLayoutPassword = findViewById(R.id.edit_password_layout);
        mLayoutPasswordRepeat = findViewById(R.id.edit_repeat_password_layout);

        mLayoutEmail.setErrorEnabled(true);
        mLayoutFirstName.setErrorEnabled(true);
        mLayoutLastName.setErrorEnabled(true);
        mLayoutPassword.setErrorEnabled(true);
        mLayoutPasswordRepeat.setErrorEnabled(true);

        mBtnSave = findViewById(R.id.btn_edit_save);
        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editUser();
            }
        });
    }

    private void getDataFromIntent() {
        Bundle bundle = getIntent().getBundleExtra(BUNDLE_EXTRA);

        mLayoutFirstName.setHint(bundle.getString(FIRST_NAME_EXTRA));
        mLayoutLastName.setHint(bundle.getString(LAST_NAME_EXTRA));
        mLayoutEmail.setHint(bundle.getString(EMAIL_EXTRA));

        mUserPassword = bundle.getString(PASSWORD_EXTRA);
    }

    private void editUser() {
        String firstName = mEtFirstName.getText().toString();
        String lastName = mEtLastName.getText().toString();
        String email = mEtEmail.getText().toString();
        String newPassword = mEtPassword.getText().toString();
        String newPasswordRepeat = mEtPasswordRepeat.getText().toString();

        if (TextUtils.isEmpty(firstName)) firstName = mLayoutFirstName.getHint().toString();

        if (TextUtils.isEmpty(lastName)) lastName = mLayoutLastName.getHint().toString();

        if (TextUtils.isEmpty(email)) email = mLayoutEmail.getHint().toString();

        if (TextUtils.isEmpty(newPassword) && TextUtils.isEmpty(newPasswordRepeat)) newPassword = newPasswordRepeat = mUserPassword;

        mUserDetailPresenter.editUser(firstName, lastName, email, newPassword, newPasswordRepeat);
    }

}

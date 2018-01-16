package com.example.mateusz.fantasy.home.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.home.presenter.UserDetailPresenter;
import com.example.mateusz.fantasy.utils.NetworkUtils;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.mateusz.fantasy.home.view.fragment.HomeFragment.BUNDLE_EXTRA;
import static com.example.mateusz.fantasy.home.view.fragment.HomeFragment.EMAIL_EXTRA;
import static com.example.mateusz.fantasy.home.view.fragment.HomeFragment.FIRST_NAME_EXTRA;
import static com.example.mateusz.fantasy.home.view.fragment.HomeFragment.LAST_NAME_EXTRA;
import static com.example.mateusz.fantasy.home.view.fragment.HomeFragment.PASSWORD_EXTRA;

public class UserDetailActivity extends Activity implements UserDetailView {

    //UI
    @BindView(R.id.et_edit_first_name)
    TextInputEditText mEtFirstName;
    @BindView(R.id.et_edit_last_name)
    TextInputEditText mEtLastName;
    @BindView(R.id.et_edit_email)
    TextInputEditText mEtEmail;
    @BindView(R.id.et_edit_password)
    TextInputEditText mEtPassword;
    @BindView(R.id.et_edit_repeat_password)
    TextInputEditText mEtPasswordRepeat;

    @BindView(R.id.edit_first_name_layout)
    TextInputLayout mLayoutFirstName;
    @BindView(R.id.edit_last_name_layout)
    TextInputLayout mLayoutLastName;
    @BindView(R.id.edit_email_layout)
    TextInputLayout mLayoutEmail;
    @BindView(R.id.edit_password_layout)
    TextInputLayout mLayoutPassword;
    @BindView(R.id.edit_repeat_password_layout)
    TextInputLayout mLayoutPasswordRepeat;

    @BindView(R.id.edit_PB)
    ProgressBar editPB;
    @BindView(R.id.btn_edit_save)
    Button mBtnSave;
    @BindView(R.id.tv_edit_error)
    TextView editError;

    private String mUserPassword;
    private String mUserEmail;
    //Dependencies
    private UserDetailPresenter mUserDetailPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        ButterKnife.bind(this);
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
        editError.setText("");
    }

    private void initViews() {
        mLayoutEmail.setErrorEnabled(true);
        mLayoutFirstName.setErrorEnabled(true);
        mLayoutLastName.setErrorEnabled(true);
        mLayoutPassword.setErrorEnabled(true);
        mLayoutPasswordRepeat.setErrorEnabled(true);
    }

    private void getDataFromIntent() {
        Bundle bundle = getIntent().getBundleExtra(BUNDLE_EXTRA);

        mLayoutFirstName.setHint(bundle.getString(FIRST_NAME_EXTRA));
        mLayoutLastName.setHint(bundle.getString(LAST_NAME_EXTRA));
        mLayoutEmail.setHint(bundle.getString(EMAIL_EXTRA));
        mUserEmail = bundle.getString(EMAIL_EXTRA);
        mUserPassword = bundle.getString(PASSWORD_EXTRA);
    }

    @OnClick(R.id.btn_edit_save)
    public void editUser() {
        String firstName = mEtFirstName.getText().toString();
        String lastName = mEtLastName.getText().toString();
        String email = mEtEmail.getText().toString();
        String newPassword = mEtPassword.getText().toString();
        String newPasswordRepeat = mEtPasswordRepeat.getText().toString();

        if (TextUtils.isEmpty(firstName)) firstName = mLayoutFirstName.getHint().toString();
        if (TextUtils.isEmpty(lastName)) lastName = mLayoutLastName.getHint().toString();
        if (TextUtils.isEmpty(email)) email = mLayoutEmail.getHint().toString();
        if (TextUtils.isEmpty(newPassword) && TextUtils.isEmpty(newPasswordRepeat))
            newPassword = newPasswordRepeat = mUserPassword;
        mUserDetailPresenter.editUser(mUserEmail,firstName, lastName, email, newPassword, newPasswordRepeat);
    }

    @Override
    public void onEditError() {
        editError.setText(getText(R.string.edit_error).toString());
    }

    @Override
    public void onConnectionError() {
        NetworkUtils.showConnectionErrorToast(this);
    }

    @Override
    public void showProgress(boolean show) {
        if (show) editPB.setVisibility(View.VISIBLE);
        else editPB.setVisibility(View.INVISIBLE);
    }
}

package com.example.qiaoy.qiao.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.example.qiaoy.qiao.ec.R;
import com.example.qiaoy.qiao_core.delegates.BackDelegate;
import com.example.qiaoy.qiao_core.net.RestClient;
import com.example.qiaoy.qiao_core.net.callback.IFailure;
import com.example.qiaoy.qiao_core.net.callback.ISuccess;
import com.example.qiaoy.qiao_core.uril.log.CoreLogger;

public class SignUpDelegate extends BackDelegate implements View.OnClickListener {

    private TextInputEditText mName;
    private TextInputEditText mEmail;
    private TextInputEditText mPhone;
    private TextInputEditText mPassword;
    private TextInputEditText mRePassword;

    private ISignListener mISignListener = null;

    private boolean checkForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();

        boolean isPass = true;
        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || !(rePassword.equals(password))) {
            mRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }

        return isPass;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        mName = rootView.findViewById(R.id.edit_sign_up_name);
        mEmail = rootView.findViewById(R.id.edit_sign_up_email);
        mPhone = rootView.findViewById(R.id.edit_sign_up_phone);
        mPassword = rootView.findViewById(R.id.edit_sign_up_password);
        mRePassword = rootView.findViewById(R.id.edit_sign_up_re_password);
        rootView.findViewById(R.id.btn_sign_up).setOnClickListener(this);
        rootView.findViewById(R.id.tv_link_sign_in).setOnClickListener(this);
    }

    private void onClickSignUp() {
        if (checkForm()) {
            RestClient.builder()
                    .url("http://192.168.31.133/RestServer/api/user_profile.php")
                    .params("name", mName.getText().toString())
                    .params("email", mEmail.getText().toString())
                    .params("phone", mPhone.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            CoreLogger.json("USER_PROFILE", response);
                            SignHandler.onSignUp(response, mISignListener);
                        }

                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                            CoreLogger.e("USER_PROFILE","注册失败");
                        }
                    }).build()
                    .post();

        }
    }

    private void onClickLink() {
        getSupportDelegate().startWithPop(new SignInDelegate());
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.btn_sign_up) {
            onClickSignUp();
        } else if (i == R.id.tv_link_sign_in) {
            onClickLink();
        }
    }

}

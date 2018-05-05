package com.example.qiaoy.qiao.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.qiaoy.qiao.ec.R;
import com.example.qiaoy.qiao_core.delegates.BackDelegate;
import com.example.qiaoy.qiao_core.net.RestClient;
import com.example.qiaoy.qiao_core.net.callback.IFailure;
import com.example.qiaoy.qiao_core.net.callback.ISuccess;
import com.example.qiaoy.qiao_core.uril.log.CoreLogger;

public class SignInDelegate extends BackDelegate implements View.OnClickListener {

    private TextInputEditText mEmail = null;
    private TextInputEditText mPassword = null;

    private ISignListener mISignListener = null;

    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
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
        return R.layout.delegate_sign_in;
    }

    @Override
    protected void onBindView(Bundle savedInstanceState, View rootView) {
        mEmail = rootView.findViewById(R.id.edit_sign_in_email);
        mPassword = rootView.findViewById(R.id.edit_sign_in_password);
        rootView.findViewById(R.id.btn_sign_in).setOnClickListener(this);
        rootView.findViewById(R.id.tv_link_sign_up).setOnClickListener(this);
        rootView.findViewById(R.id.icon_sign_in_wechat).setOnClickListener(this);
    }

    private void onClickSignIn() {
        if (checkForm()) {
            RestClient.builder()
                    .url("http://192.168.31.133/RestServer/api/user_profile.php")
                    .params("email", mEmail.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            CoreLogger.json("USER_PROFILE", response);
                            SignHandler.onSignIn(response, mISignListener);
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure() {
                           CoreLogger.e("USER_PROFILE","登陆失败");
                        }
                    }).build()
                    .post();
        }
    }

    private void onClickLink() {
        getSupportDelegate().startWithPop(new SignUpDelegate());
    }

    private void onClickWeChat() {
        Toast.makeText(getContext(), "微信····", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_sign_in) {
            onClickSignIn();
        } else if (i == R.id.tv_link_sign_up) {
            onClickLink();
        } else if (i == R.id.icon_sign_in_wechat) {
            onClickWeChat();
        }
    }
}

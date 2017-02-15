package com.babyplan.salt.babyplan;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.babyplan.salt.babyplan.bean.Person;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;

/**
 * Created by Salt on 2017/2/10.
 */

public class LoginActivity extends BaseActivity {

    private static final String BMOB_APP_KEY = "e86ab9e61a776f8335a7f782e1c01f0c";

    @InjectView(R.id.iv_left)
    ImageView iv_left;
    @InjectView(R.id.et_account)
    EditText et_account;
    @InjectView(R.id.et_password)
    EditText et_password;
    @InjectView(R.id.btn_login)
    Button btn_login;
    @InjectView(R.id.btn_onekey)
    Button btn_onekey;
    @InjectView(R.id.btn_register)
    Button btn_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.inject(this);
        //初始化Bmob sdk
        Bmob.initialize(this, BMOB_APP_KEY);


    }

    @OnClick(R.id.iv_left)
    public void back() {
        finish();
    }

    @OnClick(R.id.btn_login)
    public void login(View view) {
        login();
    }

    @OnClick(R.id.btn_register)

    public void register() {
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_onekey)
    public void oneKey() {
        Intent intent = new Intent(LoginActivity.this,LoginOneKeyActivity.class);
        startActivity(intent);
    }



    /** 登陆操作
     * @method login
     * @return void
    * @exception
    */
    private void login(){
        SharedPreferences sp_userInfo=getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String account = et_account.getText().toString();
        String password = et_password.getText().toString();
        SharedPreferences.Editor editor =sp_userInfo.edit();
        //保存用户名和密码
        editor.putString("USER_NAME", account);
        editor.putString("PASSWORD", password);
        editor.commit();

        if (TextUtils.isEmpty(account)) {
            showToast("账号不能为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            showToast("密码不能为空");
            return;
        }
        final ProgressDialog progress = new ProgressDialog(LoginActivity.this);
        progress.setMessage("正在登录中...");
        progress.setCanceledOnTouchOutside(false);
        progress.show();

        BmobUser.loginByAccount(account, password, new LogInListener<Person>() {
            @Override
            public void done(Person person, BmobException ex) {
                SharedPreferences sp_state=getSharedPreferences("state", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed =sp_state.edit();
                if(ex==null){
                    toast("登录成功");
                    ed.putBoolean("loginstate", true);
                    ed.commit();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("from", "login");
                    startActivity(intent);
                    finish();
                }else{
                    toast("登录失败：code="+ex.getErrorCode()+"，错误描述："+ex.getLocalizedMessage());
                    ed.putBoolean("loginstate", false);
                    ed.commit();
                }
            }
        });

    }

}


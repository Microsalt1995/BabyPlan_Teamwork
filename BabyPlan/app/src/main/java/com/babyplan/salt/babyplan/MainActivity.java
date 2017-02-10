package com.babyplan.salt.babyplan;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Bmob.initialize(this, "e86ab9e61a776f8335a7f782e1c01f0c");
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_dl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
  /*
//查找Person表里面id为6b6c11c537的数据
        BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
        bmobQuery.getObject("a31fc66533", new QueryListener<Person>() {
            @Override
            public void done(Person object,BmobException e) {
                if(e==null){

                    Toast.makeText(getApplicationContext(), "\"查询成功",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "\"查询失败",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        Person p2 = new Person();
        p2.setName("lucky");
        p2.setAddress("北京海淀");
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId,BmobException e) {
                if(e==null){
                    Toast.makeText(getApplicationContext(), "\"添加数据成功",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "\"添加数据失败",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        */

    }

}

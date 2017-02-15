package com.babyplan.salt.babyplan;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.babyplan.salt.babyplan.fragment.HomeFragment;
import com.babyplan.salt.babyplan.fragment.LoginFragment;
import com.babyplan.salt.babyplan.fragment.ProfileFragment;
import com.babyplan.salt.babyplan.fragment.StoreFragment;
import com.babyplan.salt.babyplan.fragment.TasksFragment;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.frame_layout)
    FrameLayout frameLayout;
    @InjectView(R.id.space)
    SpaceNavigationView spaceNavigationView;




    private Fragment currentFragment;
    private HomeFragment homeFragment;
    private TasksFragment tasksFragment;
    private StoreFragment storeFragment;
    private ProfileFragment profileFragment;
    private LoginFragment loginFragment;

    private final int INDEX_HOME=0;
    private final int INDEX_TASKS=1;
    private final int INDEX_STORE=2;
    private final int INDEX_ME=3;
    private boolean loginState=false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        SharedPreferences sp_state=getSharedPreferences("state", Context.MODE_PRIVATE);


  //     SharedPreferences.Editor editor =sp.edit();
        //保存用户名和密码
  //      editor.putBoolean("loginstate", true);
        //editor.putString("PASSWORD", password);
    //    editor.clear();
     //  editor.commit();

      // String name =sp.getString("USER_NAME", "");

        loginState =sp_state.getBoolean("loginstate",false);

        ButterKnife.inject(this);

        initToolBar();
        initSpaceView(savedInstanceState);
        initFragments();

    }

    private void initToolBar(){
        setSupportActionBar(toolbar);
    }

    private void initSpaceView(Bundle savedInstanceState){
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);

        spaceNavigationView.addSpaceItem(new SpaceItem("HOME", R.drawable.ic_home_white_18dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("TASKS", R.drawable.ic_featured_play_list_white_18dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("STORE", R.drawable.ic_store_white_18dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("ME", R.drawable.ic_person_white_18dp));
        spaceNavigationView.setCentreButtonIconColorFilterEnabled(false);
        spaceNavigationView.showIconOnly();

        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {

            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                switch (itemIndex){
                    case INDEX_HOME:
                        replaceFragment(homeFragment);
                        break;
                    case INDEX_TASKS:
                        replaceFragment(tasksFragment);
                        break;
                    case INDEX_STORE:
                        replaceFragment(storeFragment);
                        break;
                    case INDEX_ME:
                        if(loginState)
                        {
                            replaceFragment(profileFragment);

                        }
                        else
                            replaceFragment(loginFragment);
                        break;
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {

            }
        });
    }

    private void initFragments(){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        homeFragment=new HomeFragment();
        tasksFragment=new TasksFragment();
        storeFragment=new StoreFragment();
        if(loginState)
           profileFragment=new ProfileFragment();
        else
          loginFragment =new  LoginFragment();

        currentFragment=homeFragment;
        transaction.add(R.id.frame_layout, homeFragment);
        transaction.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        spaceNavigationView.onSaveInstanceState(outState);
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.hide(currentFragment);
        if(fragment.isAdded()){
            transaction.show(fragment);
        }
        else {
            transaction.add(R.id.frame_layout,fragment);
        }
        currentFragment=fragment;
        transaction.commit();
    }

}

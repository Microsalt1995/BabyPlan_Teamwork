package com.babyplan.salt.babyplan;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.content.Context;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.babyplan.salt.babyplan.fragment.ExploreFragment;
import com.babyplan.salt.babyplan.fragment.HomeFragment;
import com.babyplan.salt.babyplan.fragment.LoginFragment;
import com.babyplan.salt.babyplan.fragment.ProfileFragment;
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
    private ExploreFragment exploreFragment;
    private ProfileFragment profileFragment;
    private LoginFragment loginFragment;

    private final int INDEX_HOME=0;
    private final int INDEX_TASKS=1;
    private final int INDEX_EXPLORE =2;
    private final int INDEX_ME=3;

    private final int REQUEST_CODE_IMAGE =0x451;


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

        //loginState =sp_state.getBoolean("loginstate",false);
        loginState=true;

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
        spaceNavigationView.addSpaceItem(new SpaceItem("EXPLORE", R.drawable.ic_explore_white_18dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("ME", R.drawable.ic_person_white_18dp));
        spaceNavigationView.setCentreButtonIconColorFilterEnabled(false);
        spaceNavigationView.showIconOnly();

        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                showSendDialog();
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
                    case INDEX_EXPLORE:
                        replaceFragment(exploreFragment);
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
        exploreFragment=new ExploreFragment();
        profileFragment=new ProfileFragment();

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_IMAGE:
                Uri uri = data.getData();
                Intent intent=new Intent(this,DiarySendActivity.class);
                intent.setData(uri);
                startActivity(intent);
                break;

        }
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



    private void showSendDialog(){
        new AlertDialog.Builder(this)
                .setItems(R.array.send_photo, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent getImageByCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(getImageByCamera, REQUEST_CODE_IMAGE);
                                break;
                            case 1:
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");//相片类型
                                startActivityForResult(intent, REQUEST_CODE_IMAGE);
                                break;
                        }
                    }
                })
                .show();
    }

}

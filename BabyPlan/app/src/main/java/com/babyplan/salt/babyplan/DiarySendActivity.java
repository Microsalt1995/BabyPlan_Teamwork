package com.babyplan.salt.babyplan;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatEditText;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.babyplan.salt.babyplan.bean.BmobDiary;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

public class DiarySendActivity extends BaseActivity {

    @InjectView(R.id.activity_diary_send)
    View view;
    @InjectView(R.id.et_diary)
    AppCompatEditText diaryContentInput;
    @InjectView(R.id.iv_photo)
    ImageView photo;

    private Uri photoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_send);
        ButterKnife.inject(this);
        Bmob.initialize(this, "efe95be40a610d6b7a274eebf4f70d91");   //这里改成你的开发者tag


        setTitle("记录我的成长");

        final ActionBar ab = getSupportActionBar();
        if (ab != null)
            ab.setDisplayHomeAsUpEnabled(true);

        photoUri = getIntent().getData();

        if (photoUri != null)
            photo.setImageURI(photoUri);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_diary_send, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_send:
                sendDiary();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private String getAbsoluteImagePath(Uri uri) {
        // can post image
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri,
                proj,                 // Which columns to return
                null,       // WHERE clause; which rows to return (all rows)
                null,       // WHERE clause selection arguments (none)
                null);                 // Order-by clause (ascending by name)
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();

        return cursor.getString(column_index);
    }

    private void sendDiary() {

        final BmobFile bmobFile = new BmobFile(new File(getAbsoluteImagePath(photoUri)));

        bmobFile.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    BmobDiary bmobDiary = new BmobDiary();
                    bmobDiary.setUsername("test");
                    bmobDiary.setDiaryPicUrl(bmobFile.getFileUrl());
                    bmobDiary.setDiaryWords(diaryContentInput.getText().toString());
                    bmobDiary.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if (e == null) {
                                Snackbar.make(view, "发送成功", Snackbar.LENGTH_SHORT).show();
                            } else {
                                Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_SHORT).show();
                }
            }
        });

    }

}

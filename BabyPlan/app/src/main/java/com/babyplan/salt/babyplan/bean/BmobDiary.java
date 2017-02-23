package com.babyplan.salt.babyplan.bean;

import cn.bmob.v3.BmobObject;

/**
 * Bmob日记
 *
 * @author : _chf
 * @since : 2017/2/23
 */
public class BmobDiary extends BmobObject{

    private int diaryId;
    private String diaryPicUrl;
    private String  diaryWords;
    private String username;

    public int getDiaryId() {
        return diaryId;
    }

    public void setDiaryId(int diaryId) {
        this.diaryId = diaryId;
    }

    public String getDiaryPicUrl() {
        return diaryPicUrl;
    }

    public void setDiaryPicUrl(String diaryPicUrl) {
        this.diaryPicUrl = diaryPicUrl;
    }

    public String getDiaryWords() {
        return diaryWords;
    }

    public void setDiaryWords(String diaryWords) {
        this.diaryWords = diaryWords;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

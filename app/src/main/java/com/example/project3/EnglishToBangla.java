package com.example.project3;

public class EnglishToBangla {

    private String mEngWord, mBanWord;
    private int mImageResourceId, mAudioResourceId;
    private boolean mHasImage = false;

    public EnglishToBangla(String english, String bangla, int imageResourceId, int audioResourceId){
        mEngWord = english;
        mBanWord = bangla;
        mImageResourceId=imageResourceId;
        mAudioResourceId = audioResourceId;
        mHasImage = true;
    }
    public EnglishToBangla(String english, String bangla, int audioResourceId){
        mEngWord = english;
        mBanWord = bangla;
        mAudioResourceId = audioResourceId;
    }


    public String getBanglaWord()
    {
        return mBanWord;
    }
    public String getEngWord()
    {
        return mEngWord;
    }
    public int getImageResourceId(){return mImageResourceId;}
    public boolean hasImage(){return mHasImage;}
    public int getAudioResourceId(){return mAudioResourceId;}

}

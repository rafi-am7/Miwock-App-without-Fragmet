package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange==AudioManager.AUDIOFOCUS_LOSS)
            {
                releaseMediaPlayer();
            }
            if(focusChange==AudioManager.AUDIOFOCUS_GAIN)
            {
                mMediaPlayer.start();
            }
            if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
            {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);//start from 0 when resuming
            }
        }
    };
    MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eng_ban_duo_listview);

        final ArrayList<EnglishToBangla> phrasesArray = new ArrayList<EnglishToBangla>();
        phrasesArray.add(new EnglishToBangla(getString(R.string.englishp1), getString(R.string.banglap1), R.raw.phrase_where_are_you_going));
        phrasesArray.add(new EnglishToBangla(getString(R.string.englishp2), getString(R.string.banglap2), R.raw.phrase_what_is_your_name));
        phrasesArray.add(new EnglishToBangla(getString(R.string.englishp3), getString(R.string.banglap3), R.raw.phrase_my_name_is));
        phrasesArray.add(new EnglishToBangla(getString(R.string.englishp4), getString(R.string.banglap4), R.raw.phrase_how_are_you_feeling));
        phrasesArray.add(new EnglishToBangla(getString(R.string.englishp5), getString(R.string.banglap5), R.raw.phrase_im_feeling_good));
        phrasesArray.add(new EnglishToBangla(getString(R.string.englishp6), getString(R.string.banglap6), R.raw.phrase_are_you_coming));
        phrasesArray.add(new EnglishToBangla(getString(R.string.englishp7), getString(R.string.banglap7), R.raw.phrase_yes_im_coming));
        phrasesArray.add(new EnglishToBangla(getString(R.string.englishp8), getString(R.string.banglap8), R.raw.phrase_lets_go));
        phrasesArray.add(new EnglishToBangla(getString(R.string.englishp9), getString(R.string.banglap9), R.raw.phrase_come_here));

        WordCustomAdapter itemAdepter = new WordCustomAdapter(this, 0, phrasesArray, R.color.catagory_phrases);

        ListView rootListView = findViewById(R.id.rootListView);
        rootListView.setAdapter(itemAdepter);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        rootListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EnglishToBangla currentItem = phrasesArray.get(position);
                Toast.makeText(PhrasesActivity.this, currentItem.getBanglaWord() + " pronouncing", Toast.LENGTH_SHORT).show();

                releaseMediaPlayer();//if other songs clicked

                int requestResult = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(requestResult==AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                {
                    mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, currentItem.getAudioResourceId());
                    mMediaPlayer.start();
               /* mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        if(mediaPlayer!=null)
                        {
                            mediaPlayer.release();
                            mediaPlayer=null;
                        }
                    }
                });*/
                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);//release
                }

            }
        });

    }

    public void releaseMediaPlayer()
    {
        if(mMediaPlayer!=null)
        {
            mMediaPlayer.release();
            mMediaPlayer=null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
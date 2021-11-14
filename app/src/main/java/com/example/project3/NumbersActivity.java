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

public class NumbersActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS || focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
            {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0); //position at the begining of the audio file

            }
            else if(focusChange==AudioManager.AUDIOFOCUS_GAIN)
            {
                mMediaPlayer.start();
            }
            else if(focusChange==AudioManager.AUDIOFOCUS_LOSS)
            {
                releaseMediaPlayer();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eng_ban_duo_listview);


/*        ArrayList<String> numberEnglishArray= new ArrayList<String>();
        numberEnglishArray.add("One");
        numberEnglishArray.add("Two");
        numberEnglishArray.add("Three");
        numberEnglishArray.add("Four");
        numberEnglishArray.add("Five");
        numberEnglishArray.add("Six");
        numberEnglishArray.add("Seven");
        numberEnglishArray.add("Eight");
        numberEnglishArray.add("Nine");
        numberEnglishArray.add("Ten");*/

/*        LinearLayout numRootLayout = findViewById(R.id.numRootLayout);

        for(int i=0; i<numberEnglishArray.size(); i++)
        {
            TextView numEnglishText = new TextView(this);
            numEnglishText.setText(numberEnglishArray.get(i));
            numRootLayout.addView(numEnglishText);

        }*/

/*
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, numberEnglishArray );
        GridView rootListView = findViewById(R.id.rootListView);
        rootListView.setAdapter(itemAdapter);

        ArrayList<String> numBanglaArray = new ArrayList<String>();
        numBanglaArray.add("Ek");
        numBanglaArray.add("Doi");
        numBanglaArray.add("Tin");
        numBanglaArray.add("Char");
        numBanglaArray.add("Pach");
        numBanglaArray.add("Soi");
        numBanglaArray.add("Shat");
        numBanglaArray.add("Aat");
        numBanglaArray.add("Noy");
        numBanglaArray.add("Dosh");
*/
        final ArrayList<EnglishToBangla> wordArray = new ArrayList<EnglishToBangla>();

        //EnglishToBangla word = new EnglishToBangla("One","Ek");
        wordArray.add(new EnglishToBangla("One","Ek",R.drawable.number_one,R.raw.number_one));
        wordArray.add(new EnglishToBangla("Two","Doi",R.drawable.number_two, R.raw.number_two));
        wordArray.add(new EnglishToBangla("Three","Tin",R.drawable.number_three,R.raw.number_three));
        wordArray.add(new EnglishToBangla("Four","Char",R.drawable.number_four,R.raw.number_four));
        wordArray.add(new EnglishToBangla("Five","Pach",R.drawable.number_five,R.raw.number_five));
        wordArray.add(new EnglishToBangla("Six","Soi",R.drawable.number_six,R.raw.number_six));
        wordArray.add(new EnglishToBangla("Seven","Shat",R.drawable.number_seven,R.raw.number_seven));
        wordArray.add(new EnglishToBangla("Eight","Aat",R.drawable.number_eight,R.raw.number_eight));
        wordArray.add(new EnglishToBangla("Nine","Noy",R.drawable.number_nine,R.raw.number_nine));
        wordArray.add(new EnglishToBangla("Ten","Dosh",R.drawable.number_ten,R.raw.number_ten));

        //ArrayAdapter<EnglishToBangla> itemAdapter = new ArrayAdapter<EnglishToBangla>(this,R.layout.list_item_layout,wordArray);

        WordCustomAdapter itemAdapter = new WordCustomAdapter(this,0, wordArray, R.color.catagory_numbers);
        ListView rootListView = findViewById(R.id.rootListView);
        rootListView.setAdapter(itemAdapter);


        mAudioManager =(AudioManager) getSystemService(Context.AUDIO_SERVICE);//initialize

        rootListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(NumbersActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                EnglishToBangla currentItem = wordArray.get(position);
                Toast.makeText(NumbersActivity.this,currentItem.getBanglaWord()+" pronouncing", Toast.LENGTH_SHORT).show();

                releaseMediaPlayer();//if other songs clicked

                int AudioPlayRequestResult = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(AudioPlayRequestResult==AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                {
                    //if we don't receive audio focus we don't need to set up media player
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, currentItem.getAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);//Release
                }

                /*mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        releaseMediaPlayer();
                    }
                });*/


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
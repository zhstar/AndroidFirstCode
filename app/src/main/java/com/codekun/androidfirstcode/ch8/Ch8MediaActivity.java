package com.codekun.androidfirstcode.ch8;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;

import com.codekun.androidfirstcode.R;

import java.io.File;
import java.io.IOException;

public class Ch8MediaActivity extends AppCompatActivity implements View.OnClickListener {

    private Button playSoundButton;
    private Button playMovieButton;
    private Button playButton;
    private Button pauseButton;
    private Button stopButton;
    private ViewGroup ctrlGroup;
    private VideoView mVideoView;

    private MediaPlayer mMediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch8_media);

        playSoundButton = (Button)findViewById(R.id.ch8_media_playSound_button);
        playMovieButton = (Button)findViewById(R.id.ch8_media_playMovie_button);
        playButton = (Button)findViewById(R.id.ch8_media_play_button);
        pauseButton = (Button)findViewById(R.id.ch8_media_pause_button);
        stopButton = (Button)findViewById(R.id.ch8_media_stop_button);
        ctrlGroup = (ViewGroup) findViewById(R.id.ch8_media_ctrls_layout);
        mVideoView = (VideoView)findViewById(R.id.ch8_media_videoView);

        playSoundButton.setOnClickListener(this);
        playMovieButton.setOnClickListener(this);
        playButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);

        initMediaPlayer();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mMediaPlayer != null){
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ch8_media_playSound_button:
                ctrlGroup.setVisibility(View.VISIBLE);
                break;
            case R.id.ch8_media_play_button:
                if(!mMediaPlayer.isPlaying()){
                    mMediaPlayer.start();
                }
                break;
            case R.id.ch8_media_pause_button:
                if(mMediaPlayer.isPlaying()){
                    mMediaPlayer.pause();
                }
                break;
            case R.id.ch8_media_stop_button:
                if (mMediaPlayer.isPlaying()){
                    mMediaPlayer.stop();
                }
                break;
        }
    }

    private void initMediaPlayer(){
        File file = new File(Environment.getExternalStorageDirectory(), "sound.mp3");
        try {
            mMediaPlayer.setDataSource(file.getPath());
            mMediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initVideoPath(){
        File file = new File(Environment.getExternalStorageDirectory(), "movie.mp4");
        mVideoView.setVideoPath(file.getPath());

    }


}

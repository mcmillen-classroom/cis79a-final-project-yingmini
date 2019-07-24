package com.yinghsuenlin.introtoviolin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Violin_1Activity extends AppCompatActivity
{
    MediaPlayer violins;

    public static Intent newIntent(Context ctx)
    {
        Intent ret = new Intent(ctx, Violin_1Activity.class);
        return ret;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_violin_1);

        violins = MediaPlayer.create(Violin_1Activity.this, R.raw.violin_4);
        //violins.start();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        violins.stop();
        violins.release();
    }
}

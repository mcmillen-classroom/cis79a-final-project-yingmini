package com.yinghsuenlin.introtoviolin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ViolinActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button mViolinAButton;
    private Button mViolinBButton;
    private Button mViolinCButton;
    private Button mViolinDButton;
    private Button mViolinEButton;
    private Button mViolinFButton;
    private Button mViolinGButton;

    MediaPlayer violins;

    public static Intent newIntent(Context ctx)
    {
        Intent ret = new Intent(ctx, ViolinActivity.class);
        return ret;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_violin);

        violins = MediaPlayer.create(ViolinActivity.this, R.raw.violin_1);
        violins.start();

        mViolinAButton = (Button) findViewById(R.id.violinA_button);
        mViolinBButton = (Button) findViewById(R.id.violinB_button);
        mViolinCButton = (Button) findViewById(R.id.violinC_button);
        mViolinDButton = (Button) findViewById(R.id.violinD_button);
        mViolinEButton = (Button) findViewById(R.id.violinE_button);
        mViolinFButton = (Button) findViewById(R.id.violinF_button);
        mViolinGButton = (Button) findViewById(R.id.violinG_button);

        mViolinAButton.setOnClickListener(this);
        mViolinBButton.setOnClickListener(this);
        mViolinCButton.setOnClickListener(this);
        mViolinDButton.setOnClickListener(this);
        mViolinEButton.setOnClickListener(this);
        mViolinFButton.setOnClickListener(this);
        mViolinGButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.violinA_button)
        {
            Intent start = DoubleBassActivity.newIntent(this);
            startActivity(start);
            Toast myToast = Toast.makeText(this, "Double Bass", Toast.LENGTH_LONG);
            myToast.setGravity(Gravity.TOP, 0, 180);
            myToast.show();
        }
        else if (view.getId() == R.id.violinB_button)
        {
            Intent start = Violin_1Activity.newIntent(this);
            startActivity(start);
            Toast myToast = Toast.makeText(this, "It's Peg!", Toast.LENGTH_LONG);
            myToast.setGravity(Gravity.TOP, 0, 180);
            myToast.show();
        }
        else if (view.getId() == R.id.violinC_button)
        {
            Intent start = Violin_2Activity.newIntent(this);
            startActivity(start);
            Toast myToast = Toast.makeText(this, "Four Strings!", Toast.LENGTH_LONG);
            myToast.setGravity(Gravity.TOP, 0, 180);
            myToast.show();
        }
        else if (view.getId() == R.id.violinD_button)
        {
            Intent start = Violin_3Activity.newIntent(this);
            startActivity(start);
            Toast myToast = Toast.makeText(this, "Bow Parts!", Toast.LENGTH_LONG);
            myToast.setGravity(Gravity.TOP, 0, 180);
            myToast.show();
        }
        else if (view.getId() == R.id.violinE_button)
        {
            Intent start = Violin_4Activity.newIntent(this);
            startActivity(start);
            Toast myToast = Toast.makeText(this, "Bridge!", Toast.LENGTH_LONG);
            myToast.setGravity(Gravity.TOP, 0, 180);
            myToast.show();
        }
        else if (view.getId() == R.id.violinF_button)
        {
            Intent start = Violin_5Activity.newIntent(this);
            startActivity(start);
            Toast myToast = Toast.makeText(this, "Tail Piece!", Toast.LENGTH_LONG);
            myToast.setGravity(Gravity.TOP, 0, 180);
            myToast.show();
        }
        else if (view.getId() == R.id.violinG_button)
        {
            Intent start = Violin_6Activity.newIntent(this);
            startActivity(start);
            Toast myToast = Toast.makeText(this, "Chinrest!", Toast.LENGTH_LONG);
            myToast.setGravity(Gravity.TOP, 0, 180);
            myToast.show();
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        violins.stop();
        violins.release();
    }
}

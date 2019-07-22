package com.yinghsuenlin.introtoviolin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button mInApp;

    public static final String EXTRA_MESSAGE = "com.yinghsuenlin.introtoviolin";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInApp = (Button) findViewById(R.id.app_in_button);

        mInApp.setOnClickListener(this);
    }

    public void setInApp(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.edit_text);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.app_in_button)
        {
            Intent start = ViolinActivity.newIntent(this);
            startActivity(start);
        }

        Toast t = Toast.makeText(this, "Let's Get This Started", Toast.LENGTH_SHORT);
        t.setGravity(Gravity.TOP, 0, 180);
        t.show();
    }
}

package com.yinghsuenlin.introtoviolin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DoubleBassActivity extends AppCompatActivity implements View.OnClickListener
{
    private static final int REQUEST_CODE_CHEAT = 0;

    private TextView mTextView;
    private EditText mEditText;

    private LinearLayout mTrueFalseContainer;
    private LinearLayout mFillTheBlankContainer;
    private LinearLayout mMultipleChoiceContainer;

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mpreButton;
    private ImageButton mNextButton;
    private Button mHintButton;
    private Button mCheckButton;
    private Button maButton;
    private Button mbButton;
    private Button mcButton;

    private Question[] mQuestions;
    private int mIndex;
    private int mScore = 0;

    private Button mResetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doublebass);
        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mpreButton = (ImageButton) findViewById(R.id.pre_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mHintButton = (Button) findViewById(R.id.hint_button);
        mCheckButton = (Button) findViewById(R.id.check_button);
        maButton = (Button) findViewById(R.id.a_button);
        mbButton = (Button) findViewById(R.id.b_button);
        mcButton = (Button) findViewById(R.id.c_button);

        mTrueFalseContainer = (LinearLayout) findViewById(R.id.true_false_container);
        mFillTheBlankContainer = (LinearLayout) findViewById(R.id.fill_the_blank_container);
        mMultipleChoiceContainer = (LinearLayout) findViewById(R.id.multiple_choice_container);

        mEditText = (EditText) findViewById(R.id.edit_text);

        mTrueButton.setOnClickListener(this);
        mFalseButton.setOnClickListener(this);
        mpreButton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
        mHintButton.setOnClickListener(this);
        mCheckButton.setOnClickListener(this);
        maButton.setOnClickListener(this);
        mbButton.setOnClickListener(this);
        mcButton.setOnClickListener(this);

        mTextView = (TextView) findViewById(R.id.text_view);

        mQuestions = new Question[7];
        mIndex = 0;

        mQuestions[0] = new TrueFalseQuestion(R.string.question_1, R.string.question_1_hint, true);
        mQuestions[1] = new TrueFalseQuestion(R.string.question_2, R.string.question_2_hint, true);
        mQuestions[2] = new TrueFalseQuestion(R.string.question_3, R.string.question_3_hint, false);
        mQuestions[3] = new TrueFalseQuestion(R.string.question_4, R.string.question_4_hint, true);
        mQuestions[4] = new TrueFalseQuestion(R.string.question_5, R.string.question_5_hint, false);

        String[] q6Answers = getResources().getStringArray(R.array.question_6_answers);
        mQuestions[5] = new FillTheBlankQuestion(R.string.question_6, R.string.question_6_hint, q6Answers);

        mQuestions[6] = new MultipleChoiceQuestion(R.string.question_7, R.string.question_7_hint, R.array.question_7_answers, 1);

        //Setup the first question.
        setupQuestion();
    }

    @Override
    public void onActivityResult(int requestionCode, int resultCode, Intent resultData)
    {
        if (resultCode != RESULT_OK)
        {
            return;
        }
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.true_button) {
            checkAnswer(true);
        } else if (view.getId() == R.id.false_button) {
            checkAnswer(false);
        } else if (view.getId() == R.id.check_button) {
            checkAnswer(mEditText.getText().toString());
        } else if (view.getId() == R.id.a_button) {
            checkAnswer(0);
        } else if (view.getId() == R.id.b_button) {
            checkAnswer(1);
        } else if (view.getId() == R.id.c_button) {
            checkAnswer(2);
        } else if (view.getId() == R.id.pre_button) {
            mIndex--;

            if (mIndex >= 0) {
                setupQuestion();
            }
        } else if (view.getId() == R.id.next_button) {
            //Change to the next question...

            //Increment the index by one.
            mIndex++;

            // DO IF STATEMENT HERE:
            // if the mIndex is greater than or equal to mQuestion.length
            if (mIndex < mQuestions.length) {
                setupQuestion();
            }
            //  - set mIndex to 0
            //  - end the quiz

            //Change text in view
        } else if (view.getId() == R.id.hint_button) {
            Toast myToast = Toast.makeText(this, mQuestions[mIndex].getHintTextResId(), Toast.LENGTH_LONG);
            myToast.setGravity(Gravity.TOP, 0, 180);
            myToast.show();
        }
    }

    public void setupQuestion()
    {
        mTextView.setText(mQuestions[mIndex].getTextResId());
        if(mQuestions[mIndex].isTrueFalseQuestion())
        {
            mTrueFalseContainer.setVisibility(View.VISIBLE);
            mFillTheBlankContainer.setVisibility(View.GONE);
            mMultipleChoiceContainer.setVisibility(View.GONE);
        }
        else if (mQuestions[mIndex].isFillTheBlankQuestion())
        {
            mTrueFalseContainer.setVisibility(View.GONE);
            mFillTheBlankContainer.setVisibility(View.VISIBLE);
            mMultipleChoiceContainer.setVisibility(View.GONE);
        }
        else if(mQuestions[mIndex].isMultipleChoiceQuestion())
        {
            // td: hide and show relevance containers
            mTrueFalseContainer.setVisibility(View.GONE);
            mFillTheBlankContainer.setVisibility(View.GONE);
            mMultipleChoiceContainer.setVisibility(View.VISIBLE);

            int optionsResId = ((MultipleChoiceQuestion) mQuestions[mIndex]).getOptionsResId();
            String[] options = getResources().getStringArray(optionsResId);

            // td: use options array to the text of each MC button
            // index 0 is for button a ... index 3 is for button D.
            maButton.setText(options[0]);
            mbButton.setText(options[1]);
            mcButton.setText(options[2]);
        }
    }

    public boolean checkAnswer(boolean userInput)
    {
        if(mQuestions[mIndex].checkAnswer(userInput))
        {
            Toast myToast = Toast.makeText(this, "You are correct!", Toast.LENGTH_SHORT);
            myToast.setGravity(Gravity.TOP, 0, 180);
            myToast.show();
            return true;
        }
        else
        {
            Toast myToast = Toast.makeText(this, "You are incorrect!", Toast.LENGTH_SHORT);
            myToast.setGravity(Gravity.TOP, 0, 180);
            myToast.show();
            return false;
        }
    }

    public boolean checkAnswer(String userInput)
    {
        if (mQuestions[mIndex].checkAnswer(userInput))
        {
            Toast myToast = Toast.makeText(this, "You are correct!", Toast.LENGTH_SHORT);
            myToast.setGravity(Gravity.TOP, 0, 180);
            myToast.show();
            return true;
        }
        else
        {
            Toast myToast = Toast.makeText(this, "You are incorrect!", Toast.LENGTH_SHORT);
            myToast.setGravity(Gravity.TOP, 0, 180);
            myToast.show();
            return false;
        }
    }

    public boolean checkAnswer(int userInput)
    {
        if(mQuestions[mIndex].checkAnswer(userInput))
        {
            Toast myToast = Toast.makeText(this, "You are correct!", Toast.LENGTH_SHORT);
            myToast.setGravity(Gravity.TOP, 0, 180);
            myToast.show();
            return true;
        }
        else
        {
            Toast myToast = Toast.makeText(this, "You are incorrect!", Toast.LENGTH_SHORT);
            myToast.setGravity(Gravity.TOP, 0, 180);
            myToast.show();
            return false;
        }
    }

    public static Intent newIntent(Context ctx)
    {
        Intent ret = new Intent(ctx, DoubleBassActivity.class);
        return ret;
    }
}
package com.example.yueyangzou.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE = "com.example.yueyangzou.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.example.yueyangzou.geoquiz.answer_shown";
    private static final String BOOLEAN_INDEX = "B_INDEX";
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswer;
    private boolean buttonPressed;
    private boolean[] res = new boolean[2];


    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return i;
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        mAnswerTextView = (TextView)findViewById(R.id.answer_Text_View);
        mShowAnswer = (Button) findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                }
                else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
                buttonPressed = true;
            }
        });

        if (savedInstanceState != null) {
            res = savedInstanceState.getBooleanArray(BOOLEAN_INDEX);
            if (res[0]) {
                if (res[1]) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }
            else {
                setAnswerShownResult(false);
            }
        }


    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        res[0] = buttonPressed;
        res[1] = mAnswerIsTrue;
        savedInstanceState.putBooleanArray(BOOLEAN_INDEX, res);
    }


    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

}

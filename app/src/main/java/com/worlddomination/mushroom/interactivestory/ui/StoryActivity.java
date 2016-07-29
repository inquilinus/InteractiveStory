package com.worlddomination.mushroom.interactivestory.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.worlddomination.mushroom.interactivestory.R;
import com.worlddomination.mushroom.interactivestory.model.Page;
import com.worlddomination.mushroom.interactivestory.model.Story;

public class StoryActivity extends AppCompatActivity {

    private static final String TAG = StoryActivity.class.getSimpleName();

    private Story mStory =new Story();
    private ImageView mImageView;
    private TextView mTextView;
    private Button mChoise1;
    private Button mChoise2;
    private String mName;
    private Page mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stroty);

        Intent intent = getIntent();
        mName = intent.getStringExtra(getString(R.string.key_name));

        if (mName == null){
            mName = "Friend";
        }
        Log.d(TAG, mName);

        mImageView=(ImageView)findViewById(R.id.storyImageView);
        mTextView=(TextView)findViewById(R.id.storyTextView);
        mChoise1=(Button)findViewById(R.id.choiseButton1);
        mChoise2=(Button)findViewById(R.id.choiseButton2);

        loadPage(0);

    }

    private void loadPage(int choise) {
        mCurrentPage = mStory.getPage(choise);
        Drawable drawable = getResources().getDrawable(mCurrentPage.getImageId());
        mImageView.setImageDrawable(drawable);

        String pageText = mCurrentPage.getText();
        //Add the name if placeholder included.Won`t add if no placeholder
        pageText = String.format(pageText, mName);
        mTextView.setText(pageText);

        if (mCurrentPage.isFinal()) {
            mChoise1.setVisibility(View.INVISIBLE);
            mChoise2.setText("PLAY AGAIN");
            mChoise2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });

        } else {
            mChoise1.setText(mCurrentPage.getChoice1().getText());
            mChoise2.setText(mCurrentPage.getChoice2().getText());

            mChoise1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int nextPage = mCurrentPage.getChoice1().getNextPage();
                    loadPage(nextPage);
                }
            });
            mChoise2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int nextPage = mCurrentPage.getChoice2().getNextPage();
                    loadPage(nextPage);
                }
            });
        }
    }
}


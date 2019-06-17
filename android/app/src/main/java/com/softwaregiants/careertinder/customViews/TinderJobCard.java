package com.softwaregiants.careertinder.customViews;

import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.LongClick;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeHead;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;
import com.mindorks.placeholderview.annotations.swipe.SwipeView;
import com.softwaregiants.careertinder.R;
import com.softwaregiants.careertinder.callback.ACTION_PERFORMED;
import com.softwaregiants.careertinder.callback.BaseListener;
import com.softwaregiants.careertinder.models.JobOpeningModel;

/**
 * Created by janisharali on 19/08/16.
 */
@NonReusable
@Layout(R.layout.tinder_card_view)
public class TinderJobCard {

    @View(R.id.profileImageView)
    ImageView profileImageView;

    @View(R.id.TVName)
    TextView TVName;

    @View(R.id.TVJobTitle)
    TextView TVJobTitle;

    @View(R.id.TVCity)
    TextView TVCity;

    @View(R.id.TVQualification)
    TextView TVQualification;

    @View(R.id.TVSkill1)
    TextView TVSkill1;

    @View(R.id.TVSkill2)
    TextView TVSkill2;

    @View(R.id.TVSkill3)
    TextView TVSkill3;

    @View(R.id.TVWorkEx)
    TextView TVWorkEx;

    @SwipeView
    android.view.View view;

    JobOpeningModel jobOpeningModel;
    int pos;
    BaseListener mBaseListener;

    private TinderJobCard() {}

    public TinderJobCard(JobOpeningModel jobOpeningModel, BaseListener mBaseListener, int pos) {
        this.jobOpeningModel = jobOpeningModel;
        this.pos = pos;
        this.mBaseListener = mBaseListener;
    }

    @Resolve
    public void onResolve() {
        TVName.setText("Company: " + jobOpeningModel.getCompanyName());
        TVJobTitle.setText("Job Title: " + jobOpeningModel.getJobTitle());
        TVQualification.setText("Qualification: " + jobOpeningModel.getDesiredQualification());
        TVCity.setText("City: " + jobOpeningModel.getPlaceOfWork());
        TVSkill1.setText("Skill 1: " + jobOpeningModel.getSkill1());
        TVSkill2.setText("Skill 2: " + jobOpeningModel.getSkill2());
        TVSkill3.setText("Skill 3: " + jobOpeningModel.getSkill3());
        TVWorkEx.setText("Required Work Exp: " + jobOpeningModel.getDesiredWorkExperience() + " months");
    }

    @SwipeOut
    public void onSwipedOut() {
        Log.d("DEBUG", "onSwipedOut");
    }

    @SwipeCancelState
    public void onSwipeCancelState() {
        Log.d("DEBUG", "onSwipeCancelState");
    }

    @SwipeIn
    public void onSwipeIn() {
        Log.d("DEBUG", "onSwipedIn");
    }

    @SwipeInState
    public void onSwipeInState() {
        Log.d("DEBUG", "onSwipeInState");
    }

    @SwipeOutState
    public void onSwipeOutState() {
        Log.d("DEBUG", "onSwipeOutState");
    }

    @SwipeHead
    public void onSwipeHead() {
        profileImageView.setBackgroundColor(Color.BLUE);

        Log.d("DEBUG", "onSwipeHead");
    }

    @Click(R.id.cardview)
    public void onClick() {
        mBaseListener.callback(ACTION_PERFORMED.JOB_CLICK, pos);
    }

    @Click(R.id.profileImageView)
    public void onClickImage() {
        mBaseListener.callback(ACTION_PERFORMED.JOB_CLICK, pos);
    }

    @LongClick(R.id.profileImageView)
    public void onProfileImageViewLongClick() {
        Log.d("DEBUG", "onProfileImageViewLongClick");
    }
}
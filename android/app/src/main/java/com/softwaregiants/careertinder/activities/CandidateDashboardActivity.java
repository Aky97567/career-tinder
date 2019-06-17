package com.softwaregiants.careertinder.activities;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.listeners.ItemRemovedListener;
import com.softwaregiants.careertinder.R;
import com.softwaregiants.careertinder.callback.ACTION_PERFORMED;
import com.softwaregiants.careertinder.callback.BaseListener;
import com.softwaregiants.careertinder.customViews.TinderJobCard;
import com.softwaregiants.careertinder.models.BaseBean;
import com.softwaregiants.careertinder.models.JobOpeningModel;
import com.softwaregiants.careertinder.models.JobOpeningsListModel;
import com.softwaregiants.careertinder.networking.ApiResponseCallback;
import com.softwaregiants.careertinder.networking.RetrofitClient;
import com.softwaregiants.careertinder.utilities.Constants;
import com.softwaregiants.careertinder.utilities.UtilityMethods;

import java.util.List;

public class CandidateDashboardActivity extends BaseActivity {

    private static final String TAG = "CandidateDashboard";

    private SwipePlaceHolderView swipePlaceHolderView;
    List<JobOpeningModel> jobOpeningModelList;
    private RetrofitClient mRetrofitClient;
    TextView TVNoItems;
    int items = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate_dashboard);
        addDrawer("Job Search",R.id.nav_job_search);
        init();
    }

    private void init() {
        mContext = this;
        swipePlaceHolderView = findViewById(R.id.swipeView);
        TVNoItems = findViewById(R.id.TVNoItems);
        initSwipeView();
        mRetrofitClient = RetrofitClient.getRetrofitClient(mApiResponseCallback,getApplicationContext());
        if ( UtilityMethods.isConnected(mContext) ) {
            mRetrofitClient.mApiInterface.getMatchedJobOpenings().enqueue(mRetrofitClient.createProgress(mContext));
        }
    }

    ApiResponseCallback mApiResponseCallback = new ApiResponseCallback() {
        @Override
        public void onSuccess(BaseBean baseBean) {
            if (baseBean.getStatusCode().equals("Success")) {
                jobOpeningModelList = ((JobOpeningsListModel) baseBean).getJobOpeningModelList();
                if ( null!=jobOpeningModelList && !jobOpeningModelList.isEmpty() ) {
                    addNextItems(10);
                    TVNoItems.setVisibility(View.GONE);
                }
            }
            else {
                Toast.makeText(mContext, Constants.MSG_ERROR,Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Throwable t) {
        }
    };

    private void initSwipeView() {
        swipePlaceHolderView.disableTouchSwipe();

        Point windowSize = UtilityMethods.getDisplaySize(mContext);

        swipePlaceHolderView.addItemRemoveListener(new ItemRemovedListener() {

            @Override
            public void onItemRemoved(int count) {
                Log.d(TAG, "onItemRemoved: " + count);
//                if(count < 3){
//                    addNextItems(5);
//                }
                if ( items == jobOpeningModelList.size() ) {
//                    TVNoItems.setVisibility(View.VISIBLE);
                }
            }
        });
        swipePlaceHolderView.getBuilder()
//                .setSwipeType(SwipePlaceHolderView.SWIPE_TYPE_VERTICAL)
                .setDisplayViewCount(3)
                .setIsUndoEnabled(true)
                .setWidthSwipeDistFactor(4)
                .setHeightSwipeDistFactor(6)
                .setSwipeDecor(new SwipeDecor()
                        .setViewWidth(windowSize.x)
                        .setViewHeight(windowSize.y - getActionBarHeight())
//                        .setMarginTop(300)
//                        .setMarginLeft(100)
//                        .setViewGravity(Gravity.CENTER)
//                        .setPaddingTop(20)
                        .setSwipeMaxChangeAngle(2f)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_out_msg_view));
    }

    private void addNextItems(int count) {
        int iterator = 1;
        JobOpeningModel jobOpeningModel;
        while ( iterator <= count && items < jobOpeningModelList.size() ) {
            jobOpeningModel = jobOpeningModelList.get(items);
            swipePlaceHolderView.addView(new TinderJobCard(jobOpeningModel,
                                                            mBaseListener, items));
            iterator++;
            items++;
        }
        swipePlaceHolderView.enableTouchSwipe();
    }

    BaseListener mBaseListener = new BaseListener() {
        @Override
        public void callback(ACTION_PERFORMED action, int pos, Object... args) {
            switch (action) {
                case JOB_CLICK:
                    JobOpeningModel jobOpeningModel = jobOpeningModelList.get(pos);
                    Intent jobDetail = new Intent(mContext,JobDetailActivity.class);
                    jobDetail.putExtra("job",jobOpeningModelList.get(pos));
                    mContext.startActivity( jobDetail );
                    break;
            }
        }
    };

}

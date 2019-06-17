package com.softwaregiants.careertinder.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.softwaregiants.careertinder.R;
import com.softwaregiants.careertinder.models.JobOpeningModel;

public class JobDetailActivity extends BaseActivity {

    JobOpeningModel jobOpeningModel;

    ImageView picture;
    TextView TVCompanyName;
    TextView TVJobTitle;
    TextView TVJobDescription;

    TextView TVDesiredQualification;
    TextView TVDesiredWorkExperience;
    TextView TVPlaceOfWork;

    TextView TVSkill1;
    TextView TVSkill2;
    TextView TVSkill3;

    TextView TVLanguage1;
    TextView TVLanguage2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);
        jobOpeningModel = getIntent().getParcelableExtra("job");
        init();
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getSupportActionBar().setTitle("Job Details");

        picture = findViewById(R.id.picture);
        TVCompanyName = findViewById(R.id.TVCompanyName);
        TVJobTitle = findViewById(R.id.TVJobTitle);
        TVJobDescription = findViewById(R.id.TVJobDescription);

        TVDesiredQualification = findViewById(R.id.TVDesiredQualification);
        TVDesiredWorkExperience = findViewById(R.id.TVDesiredWorkExperience);
        TVPlaceOfWork = findViewById(R.id.TVPlaceOfWork);

        TVSkill1 = findViewById(R.id.TVSkill1);
        TVSkill2 = findViewById(R.id.TVSkill2);
        TVSkill3 = findViewById(R.id.TVSkill3);

        TVLanguage1 = findViewById(R.id.TVLanguage1);
        TVLanguage2 = findViewById(R.id.TVLanguage2);

        TVCompanyName.setText(jobOpeningModel.getCompanyName());
        TVJobTitle.setText(jobOpeningModel.getJobTitle());
        TVJobDescription.setText(jobOpeningModel.getJobDescription());

        TVDesiredQualification.setText(jobOpeningModel.getDesiredQualification());
        TVDesiredWorkExperience.setText(jobOpeningModel.getDesiredWorkExperience() + "months");
        TVPlaceOfWork.setText(jobOpeningModel.getPlaceOfWork());

        TVSkill1.setText(jobOpeningModel.getSkill1());
        TVSkill2.setText(jobOpeningModel.getSkill2());
        TVSkill3.setText(jobOpeningModel.getSkill3());

        TVLanguage1.setText(jobOpeningModel.getPreferredlanguage1());
        TVLanguage2.setText(jobOpeningModel.getPreferredlanguage2());

    }
}

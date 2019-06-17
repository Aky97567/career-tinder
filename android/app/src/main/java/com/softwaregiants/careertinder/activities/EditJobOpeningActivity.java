package com.softwaregiants.careertinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.softwaregiants.careertinder.R;
import com.softwaregiants.careertinder.models.BaseBean;
import com.softwaregiants.careertinder.models.JobOpeningModel;
import com.softwaregiants.careertinder.networking.ApiResponseCallback;
import com.softwaregiants.careertinder.networking.RetrofitClient;
import com.softwaregiants.careertinder.preferences.PreferenceManager;
import com.softwaregiants.careertinder.utilities.Constants;
import com.softwaregiants.careertinder.utilities.UtilityMethods;

import org.apache.commons.validator.routines.EmailValidator;

public class EditJobOpeningActivity extends ImagePickerActivity {

    //region class variables
    private Button btn;
    RetrofitClient mRetrofitClient;

    EditText ETCompanyName;
    EditText ETJobTitle;
    EditText ETJobDescription;
    EditText ETDesiredQualification;
    EditText ETDesiredWorkExperience;
    EditText ETPlaceOfWork;
    EditText ETSkill1;
    EditText ETSkill2;
    EditText ETSkill3;
    EditText ETLanguage1;
    EditText ETLanguage2;
    EditText ETMobileNo;
    EditText ETEmail;
    Spinner jobTypeSpinner;

    String CompanyName = "";
    String JobTitle = "";
    String JobDescription = "";
    String DesiredQualification = "";
    String DesiredWorkExperience = "";
    String PlaceOfWOrk = "";
    String Skill1 = "";
    String Skill2 = "";
    String Skill3 = "";
    String Language1 = "";
    String Language2 = "";
    String MobileNo;
    String Email;
    String JobType = "";

    String authCode = "";
    //endregion

    JobOpeningModel jobOpeningModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_job_opening);

        Intent intent = getIntent();
        jobOpeningModel = (JobOpeningModel) intent.getParcelableExtra("jobOpeningObject");
        Spinner spinner = findViewById(R.id.spinnerJobType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.job_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        init();
    }

    public void init() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getSupportActionBar().setTitle("Edit Vacancy");
        authCode = PreferenceManager.getInstance(getApplicationContext()).getString(Constants.PK_AUTH_CODE, "");

        btn = findViewById(R.id.createJobOpeningBtn);
        btn.setOnClickListener(ocl);
        mContext = this;
        mRetrofitClient = RetrofitClient.getRetrofitClient(mApiResponseCallback,getApplicationContext());

        ETCompanyName = (EditText)findViewById(R.id.ETCompanyName);
        ETJobTitle = (EditText)findViewById(R.id.ETJobTitle);
        ETJobDescription = (EditText)findViewById(R.id.ETJobDescription);
        ETDesiredQualification = (EditText)findViewById(R.id.ETDesiredQualification);
        ETDesiredWorkExperience = (EditText)findViewById(R.id.ETDesiredWorkExperience);
        ETPlaceOfWork = (EditText)findViewById(R.id.ETPlaceOfWork);
        ETSkill1 = (EditText)findViewById(R.id.ETSkill1);
        ETSkill2 = (EditText)findViewById(R.id.ETSkill2);
        ETSkill3 = (EditText)findViewById(R.id.ETSkill3);
        ETLanguage1 = (EditText)findViewById(R.id.ETLanguage1);
        ETLanguage2 = (EditText)findViewById(R.id.ETLanguage2);
        ETMobileNo = (EditText)findViewById(R.id.ETmobileNo);
        ETEmail = (EditText)findViewById(R.id.ETemail);
        jobTypeSpinner = (Spinner)findViewById(R.id.spinnerJobType);

        ETCompanyName.setText(jobOpeningModel.getCompanyName());
        ETJobTitle.setText(jobOpeningModel.getJobTitle());
        ETJobDescription.setText(jobOpeningModel.getJobDescription());
        ETDesiredQualification.setText(jobOpeningModel.getDesiredQualification());
        ETDesiredWorkExperience.setText(jobOpeningModel.getDesiredWorkExperience());
        ETPlaceOfWork.setText(jobOpeningModel.getPlaceOfWork());
        ETSkill1.setText(jobOpeningModel.getSkill1());
        ETSkill2.setText(jobOpeningModel.getSkill2());
        ETSkill3.setText(jobOpeningModel.getSkill3());
        ETLanguage1.setText(jobOpeningModel.getPreferredLanguage1());
        ETLanguage2.setText(jobOpeningModel.getPreferredLanguage2());
        ETMobileNo.setText(jobOpeningModel.getMobileNo());
        ETEmail.setText(jobOpeningModel.geteMail());

        imageUser = findViewById(R.id.picture);
        updateImageButton = findViewById(R.id.updatePicture);
        updateImageButton.setOnClickListener(updateImageListener);
        requestMultiplePermissions();
    }

    //region onclick listener
    View.OnClickListener ocl = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CompanyName = ETCompanyName.getText().toString();
            JobTitle = ETJobTitle.getText().toString();
            JobDescription = ETJobDescription.getText().toString();
            DesiredQualification = ETDesiredQualification.getText().toString();
            DesiredWorkExperience = ETDesiredWorkExperience.getText().toString();
            PlaceOfWOrk = ETPlaceOfWork.getText().toString();
            Skill1 = ETSkill1.getText().toString();
            Skill2 = ETSkill2.getText().toString();
            Skill3 = ETSkill3.getText().toString();
            Language1 = ETLanguage1.getText().toString();
            Language2 = ETLanguage2.getText().toString();
            MobileNo = ETMobileNo.getText().toString();
            Email = ETEmail.getText().toString();
            JobType = jobTypeSpinner.getSelectedItem().toString();
            if (CompanyName.equals("")){
                Toast.makeText(mContext,"Please enter your Company Name", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (JobTitle.equals("")){
                Toast.makeText(mContext,"Please enter Job Title", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (JobDescription.equals("")){
                Toast.makeText(mContext,"Please enter job description", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (DesiredQualification.equals("")){
                Toast.makeText(mContext,"Please enter desired qualification", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (DesiredWorkExperience.equals("") || !isNumeric(DesiredWorkExperience)){
                if (DesiredWorkExperience.equals("")) {
                    Toast.makeText(mContext, "Please enter desired Work Experience", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isNumeric(DesiredWorkExperience)){
                    Toast.makeText(mContext, "Please enter desired Work Experience (in Months)", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            else if (PlaceOfWOrk.equals("")){
                Toast.makeText(mContext,"Please enter a Place of Work", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (Skill1.equals("")){
                Toast.makeText(mContext,"Please enter a skill", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (Language1.equals("")){
                Toast.makeText(mContext,"Please enter a language", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (!validateEmail(Email)){
                Toast.makeText(mContext,"Contact Email invalid", Toast.LENGTH_SHORT).show();
                return;
            }
            else if (!validatePhone(MobileNo)){
                Toast.makeText(mContext,"Contact number invalid", Toast.LENGTH_SHORT).show();
                return;
            }
            else{

                jobOpeningModel.setCompanyName(CompanyName);
                jobOpeningModel.setJobTitle(JobTitle);
                jobOpeningModel.setJobDescription(JobDescription);
                jobOpeningModel.setDesiredQualification(DesiredQualification);
                jobOpeningModel.setDesiredWorkExperience(DesiredWorkExperience);
                jobOpeningModel.setPlaceOfWork(PlaceOfWOrk);
                jobOpeningModel.setSkill1(Skill1);
                jobOpeningModel.setSkill2(Skill2);
                jobOpeningModel.setSkill3(Skill3);
                jobOpeningModel.setPreferredLanguage1(Language1);
                jobOpeningModel.setPreferredLanguage2(Language2);
                jobOpeningModel.setMobileNo(MobileNo);
                jobOpeningModel.seteMail(Email);
                jobOpeningModel.setJobType(JobType);
                if ( UtilityMethods.isConnected(mContext) ) {
                    mRetrofitClient.mApiInterface.updateJobOpening(jobOpeningModel, authCode).enqueue(mRetrofitClient.createProgress(mContext));
                }
            }
        }
    };
    //endregion

    ApiResponseCallback mApiResponseCallback = new ApiResponseCallback() {
        @Override
        public void onSuccess(BaseBean baseBean) {
            Toast.makeText(mContext, baseBean.getErrorMsg(), Toast.LENGTH_SHORT).show();
            Intent returnIntent = new Intent();
            setResult(RESULT_OK,returnIntent);
            finish();
        }

        @Override
        public void onFailure(Throwable t) {
        }
    };

    public boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        Intent returnIntent = new Intent();
        setResult(RESULT_OK,returnIntent);
        finish();
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    public boolean validateEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    public boolean validatePhone(String phone) {
        if (!isNumeric(phone)){
            return false;
        }
        if(!(phone.length() > 6)){
            return false;
        }
        return true;
    }
}

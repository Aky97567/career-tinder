package com.softwaregiants.careertinder.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CandidateListModel extends BaseBean {

    @SerializedName("applicantList")
    List<CandidateProfileModel> applicantProfiles;

    public List<CandidateProfileModel> getApplicantProfiles() {
        return applicantProfiles;
    }

    public void setApplicantProfiles(List<CandidateProfileModel> applicantProfiles) {
        this.applicantProfiles = applicantProfiles;
    }
}

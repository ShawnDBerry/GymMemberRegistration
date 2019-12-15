package com.example.gymmemberregistration.model;

import android.os.Parcel;
import android.os.Parcelable;

public class GymMember implements Parcelable {
    private String gymMemberId;
    private String gymMemberName;
    private String gymMemberPlan;

    protected GymMember(Parcel in) {
        gymMemberId = in.readString();
        gymMemberName = in.readString();
        gymMemberPlan = in.readString();
    }

    public static final Creator<GymMember> CREATOR = new Creator<GymMember>() {
        @Override
        public GymMember createFromParcel(Parcel in) {
            return new GymMember(in);
        }

        @Override
        public GymMember[] newArray(int size) {
            return new GymMember[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(gymMemberId);
        dest.writeString(gymMemberName);
        dest.writeString(gymMemberPlan);
    }


    public GymMember(String gymMemberId, String gymMbemberName, String gymMemberPlan) {
        this.gymMemberId = gymMemberId;
        this.gymMemberName = gymMbemberName;
        this.gymMemberPlan = gymMemberPlan;
    }

    public String getGymMemberId() {
        return gymMemberId;
    }

    public void setGymMemberId(String gymMemberId) {
        this.gymMemberId = gymMemberId;
    }

    public String getGymMbemberName() {
        return gymMemberName;
    }

    public void setGymMbemberName(String gymMbemberName) {
        this.gymMemberName = gymMbemberName;
    }

    public String getGymMemberPlan() {
        return gymMemberPlan;
    }

    public void setGymMemberPlan(String gymMemberPlan) {
        this.gymMemberPlan = gymMemberPlan;
    }
}

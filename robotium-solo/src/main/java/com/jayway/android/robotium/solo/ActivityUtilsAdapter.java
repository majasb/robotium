package com.jayway.android.robotium.solo;

import android.app.Activity;

public class ActivityUtilsAdapter implements ActivityProvider {

    private final ActivityUtils activityUtils;

    public ActivityUtilsAdapter(ActivityUtils activityUtils) {
        this.activityUtils = activityUtils;
    }

    public Activity getCurrentActivity(boolean shouldSleepFirst) {
        return activityUtils.getCurrentActivity(shouldSleepFirst);
    }

    public Activity getCurrentActivity() {
        return activityUtils.getCurrentActivity();
    }

}

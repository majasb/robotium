package com.jayway.android.robotium.solo;

import android.app.Activity;

public interface ActivityProvider {

    Activity getCurrentActivity(boolean shouldSleepFirst);

    Activity getCurrentActivity();

}

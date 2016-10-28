package com.lbconsulting.divelogtest;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds the initial download of
 * diveLogs, diveSites, and people
 */
public class InitialDiveLogsDownLoadObject {

    private List<DiveLog> mFilteredDiveLogs;
    private DiveLog mLastDiveLog;

    public InitialDiveLogsDownLoadObject() {
        mFilteredDiveLogs = new ArrayList<>();
        mLastDiveLog = null;
    }

    public void addDiveLog(@NonNull DiveLog diveLog) {
        mFilteredDiveLogs.add(diveLog);
        if (mLastDiveLog != null) {
            if (diveLog.getDiveStart() > mLastDiveLog.getDiveStart()) {
                mLastDiveLog = diveLog;
            }
        } else {
            mLastDiveLog = diveLog;
        }
    }

    public List<DiveLog> getFilteredDiveLogs() {
        return mFilteredDiveLogs;
    }


    public DiveLog getLastDiveLog() {
        return mLastDiveLog;
    }
}

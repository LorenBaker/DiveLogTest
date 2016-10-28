package com.lbconsulting.divelogtest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvDiveLogSummary;
    private TextInputEditText txtMaxDepth;
    private TextInputEditText txtBottomTime;
    private TextInputEditText txtArea;

    private TextInputEditText txtPosition;
    private Button btnPrevious;
    private Button btnNext;

    private List<DiveLog> mFilteredDiveLogs;
    private String mUserUid;

    private long mFirebaseDownloadStart;
    private boolean mIsInitialDownloadComplete;
    private long mTotalNumberOfDiveLogs;
    private long mDiveLogsRetrievedFromChildListener;
    private DiveLog mActiveDiveLog;

    private UserDiveLogsChildEventListener mUserDiveLogsChildEventListener;

    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("onCreate()");
        setContentView(R.layout.activity_main);

        tvDiveLogSummary = (TextView) findViewById(R.id.tvDiveLogSummary);
        txtMaxDepth = (TextInputEditText) findViewById(R.id.txtMaxDepth);
        txtBottomTime = (TextInputEditText) findViewById(R.id.txtBottomTime);
        txtArea = (TextInputEditText) findViewById(R.id.txtArea);
        Button btnSaveMaxDepth = (Button) findViewById(R.id.btnSaveMaxDepth);
        Button btnSaveBottomTime = (Button) findViewById(R.id.btnSaveBottomTime);
        Button btnSaveArea = (Button) findViewById(R.id.btnSaveArea);
        btnSaveMaxDepth.setOnClickListener(this);
        btnSaveBottomTime.setOnClickListener(this);
        btnSaveArea.setOnClickListener(this);

        txtPosition = (TextInputEditText) findViewById(R.id.txtPosition);
        btnPrevious = (Button) findViewById(R.id.btnPrevious);
        btnNext = (Button) findViewById(R.id.btnNext);
        Button btnSetPosition = (Button) findViewById(R.id.btnSetPosition);
        btnPrevious.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnSetPosition.setOnClickListener(this);

        mUserUid = "SGUtgr6kE4WHpkKTuHzpVVcYXuF2";
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.i("onResume()");
        Timber.i("Start of downLoad DiveLogs");
        mFirebaseDownloadStart = System.currentTimeMillis();
        removeUserDiveLogsChildEventListener();
        mIsInitialDownloadComplete = false;
        downloadDiveLogs();
    }

    private void downloadDiveLogs() {
        Timber.i("downloadDiveLogs(): Started");
        mFilteredDiveLogs = new ArrayList<>();
        mTotalNumberOfDiveLogs = 0;
        DiveLog.nodeUserDiveLogs(mUserUid)
                .addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        new RetrieveDiveLogsAsync(dataSnapshot).execute();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Timber.e("onCancelled(): DatabaseError: %s.", databaseError.getMessage());
                    }
                });
    }

    private void finishInitialDownLoadDiveLogs() {
        Timber.i("finishInitialDownLoadDiveLogs()");
        if (!mIsInitialDownloadComplete) {
            Timber.i("!mIsInitialDownloadComplete: finishInitialDownLoadDiveLogs");
            mIsInitialDownloadComplete = true;
            mDiveLogsRetrievedFromChildListener = 0;
            addUserDiveLogsChildEventListener();
        }
    }

    private void showDiveLog(int position) {
        enablePreviousAndNextButtons(position);
        mActiveDiveLog = mFilteredDiveLogs.get(position);
        txtPosition.setText(String.valueOf(position));
        tvDiveLogSummary.setText(mActiveDiveLog.getDataSummary());
        txtArea.setText(mActiveDiveLog.getArea());
        txtBottomTime.setText(String.valueOf(mActiveDiveLog.getBottomTime()));
        txtMaxDepth.setText(String.valueOf(mActiveDiveLog.getMaximumDepth()));
    }

    private void enablePreviousAndNextButtons(int position) {
        if (position == 0) {
            btnPrevious.setEnabled(false);
        } else {
            btnPrevious.setEnabled(true);
        }
        if (position == mFilteredDiveLogs.size() - 1) {
            btnNext.setEnabled(false);
        } else {
            btnNext.setEnabled(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Timber.i("onPause()");
        removeUserDiveLogsChildEventListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.i("onDestroy()");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSaveMaxDepth:
                double newMaxDepth = Double.parseDouble(txtMaxDepth.getText().toString());
                String msg = String.format(Locale.getDefault(),
                        "Changing maxDepth from %.1f to %.1f", mActiveDiveLog.getMaximumDepth(), newMaxDepth);
                Timber.i("btnSaveMaxDepth: onClick(): %s", msg);
                mActiveDiveLog.setMaximumDepth(newMaxDepth);
                showDiveLog(mPosition);
                DiveLog.save(mUserUid, mActiveDiveLog);
                break;

            case R.id.btnSaveBottomTime:
                long newBottomTime = Long.parseLong(txtBottomTime.getText().toString());
                msg = String.format(Locale.getDefault(), "Changing bottomTime from %d to %d", mActiveDiveLog.getBottomTime(), newBottomTime);
                Timber.i("btnSaveBottomTime: onClick(): %s", msg);
                mActiveDiveLog.setBottomTime(newBottomTime);
                showDiveLog(mPosition);
                DiveLog.save(mUserUid, mActiveDiveLog);
                break;

            case R.id.btnSaveArea:
                String newArea = txtArea.getText().toString().trim();
                msg = String.format(Locale.getDefault(), "Changing Area from %s to %s", mActiveDiveLog.getArea(), newArea);
                Timber.i("btnSaveArea: onClick(): %s", msg);
                mActiveDiveLog.setArea(newArea);
                showDiveLog(mPosition);
                DiveLog.save(mUserUid, mActiveDiveLog);
                break;

            case R.id.btnNext:
                mPosition++;
                showDiveLog(mPosition);
                break;

            case R.id.btnPrevious:
                mPosition--;
                showDiveLog(mPosition);
                break;

            case R.id.btnSetPosition:
                mPosition = Integer.parseInt(txtPosition.getText().toString());
                if (mPosition < 0) {
                    mPosition = 0;
                }
                if (mPosition > mFilteredDiveLogs.size() - 1) {
                    mPosition = mFilteredDiveLogs.size() - 1;
                }
                showDiveLog(mPosition);
                break;
        }
    }

    private class RetrieveDiveLogsAsync extends AsyncTask<Void, Void, InitialDiveLogsDownLoadObject> {
        private final DataSnapshot mDataSnapshot;

        public RetrieveDiveLogsAsync(DataSnapshot dataSnapshot) {
            Timber.i("RetrieveDiveLogsAsync() initiated.");
            mDataSnapshot = dataSnapshot;
        }

        @Override
        protected InitialDiveLogsDownLoadObject doInBackground(Void... voids) {
            Timber.i("RetrieveDiveLogsAsync: doInBackground()");
            InitialDiveLogsDownLoadObject diveLogObject = new InitialDiveLogsDownLoadObject();
            if (mDataSnapshot != null) {
                mTotalNumberOfDiveLogs = mDataSnapshot.getChildrenCount();
                for (DataSnapshot snapshot : mDataSnapshot.getChildren()) {
                    DiveLog diveLog = snapshot.getValue(DiveLog.class);
                    diveLogObject.addDiveLog(diveLog);
                }
                Collections.sort(diveLogObject.getFilteredDiveLogs(), DiveLog.mAscendingStartTime);
            }
            return diveLogObject;
        }

        @Override
        protected void onPostExecute(InitialDiveLogsDownLoadObject diveLogObject) {
            Timber.i("RetrieveDiveLogsAsync: onPostExecute(): Selected %d diveLogs out of a total of %d diveLogs.",
                    diveLogObject.getFilteredDiveLogs().size(), mTotalNumberOfDiveLogs);
            mFilteredDiveLogs = diveLogObject.getFilteredDiveLogs();
            finishInitialDownLoadDiveLogs();
        }
    }

    //region UserDiveLogsChildEventListener
    private void addUserDiveLogsChildEventListener() {
        mUserDiveLogsChildEventListener = new UserDiveLogsChildEventListener();
        DiveLog.nodeUserDiveLogs(mUserUid)
                .addChildEventListener(mUserDiveLogsChildEventListener);
    }

    private void removeUserDiveLogsChildEventListener() {
        if (mUserDiveLogsChildEventListener != null) {
            DiveLog.nodeUserDiveLogs(mUserUid)
                    .removeEventListener(mUserDiveLogsChildEventListener);
        }
    }

    private class UserDiveLogsChildEventListener implements ChildEventListener {

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String previousDiveLogUid) {
            if (dataSnapshot.getValue() != null) {
                mDiveLogsRetrievedFromChildListener++;
                if (mDiveLogsRetrievedFromChildListener == mTotalNumberOfDiveLogs) {
                    long firebaseDownloadFinish = System.currentTimeMillis();
                    long elapsedTime = firebaseDownloadFinish - mFirebaseDownloadStart;
                    mFirebaseDownloadStart = -1;
                    String msg = String.format(Locale.getDefault(), "Finish DownLoadDiveLogs(): Elapsed time %d mills.", elapsedTime);
                    Timber.i(msg);
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

                    // select a diveLog at random
                    Random random = new Random();
                    int Min = 0;
                    int Max = mFilteredDiveLogs.size() - 1;
                    mPosition = random.nextInt(Max - Min + 1) + Min;
                    showDiveLog(mPosition);
                }
            }
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String previousDiveLogUid) {
            // TODO: Figure out why onChildChanged is called twice for each DiveLog.save().
            if (dataSnapshot.getValue() != null) {
                DiveLog changedDiveLog = dataSnapshot.getValue(DiveLog.class);
                Timber.i("DiveLog: onChildChanged(). Dive Log Number: %d", changedDiveLog.getDiveNumber());
            }
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            if (dataSnapshot.getValue() != null) {
                DiveLog diveLog = dataSnapshot.getValue(DiveLog.class);
                Timber.i("DiveLog: onChildRemoved(). Dive Log Number: %d", diveLog.getDiveNumber());
            }
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String previousDiveLogUid) {
            // Not used. Do nothing
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Timber.e("onCancelled(): DatabaseError: %s.", databaseError.getMessage());
        }
    }

    //endregion UserDiveLogsChildEventListener
}

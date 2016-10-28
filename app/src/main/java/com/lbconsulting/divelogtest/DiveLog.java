package com.lbconsulting.divelogtest;

import android.support.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Comparator;

import timber.log.Timber;

/**
 * Java object for a diveLog entry.
 */
@IgnoreExtraProperties
public class DiveLog {


    public static final Comparator<DiveLog> mAscendingStartTime = new Comparator<DiveLog>() {
        public int compare(DiveLog diveLog1, DiveLog diveLog2) {
            Long diveStart1 = diveLog1.getDiveStart();
            Long diveStart2 = diveLog2.getDiveStart();
            return diveStart1.compareTo(diveStart2);
        }
    };

    private static final String NODE_DIVE_LOGS = "diveLogs";
    private static final DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference();

    //region Fields
    private Boolean sequencingRequired;

    private double airTemperature;
    private double airUsed;
    private double endingTankPressure;
    private double maximumDepth;
    private double startingTankPressure;
    private double visibility;
    private double waterTemperature;
    private double weightUsed;
    private float diveRating;

    private int diveNumber;
    private int tissueLoadingValue;

    private long accumulatedBottomTimeToDate;
    private long bottomTime;
    private long diveEnd;
    private long diveStart;
    private long surfaceInterval;

    private String diveSiteUid;
    private String area;
    private String country;
    private String state;

    private String diveBuddyPersonUid;
    private String diveMasterPersonUid;
    private String diveCompanyPersonUid;

    private String currentCondition;
    private String diveEntry;
    private String diveLogUid;
    private String diveNotes;
    private String divePhotosUrl;
    private String diveSiteTimeZoneID;
    private String diveStyle;
    private String diveType;
    private String equipmentList;
    private String marineLife;
    private String nextDiveLogUid;
    private String previousDiveLogUid;
    private String seaCondition;
    private String tankType;
    private String tissueLoadingColor;
    private String weatherCondition;
    //endregion  Fields

    public DiveLog() {
        // Default constructor.
    }

    //region Getters and Setters
    public int getDiveNumber() {
        return diveNumber;
    }

    public void setDiveNumber(int diveNumber) {
        this.diveNumber = diveNumber;
    }

    public long getDiveEnd() {
        return diveEnd;
    }

    public void setDiveEnd(long diveEnd) {
        this.diveEnd = diveEnd;
    }

    public long getDiveStart() {
        return diveStart;
    }

    public void setDiveStart(long diveStart) {
        this.diveStart = diveStart;
    }

    public long getAccumulatedBottomTimeToDate() {
        return accumulatedBottomTimeToDate;
    }

    public void setAccumulatedBottomTimeToDate(long accumulatedBottomTimeToDate) {
        this.accumulatedBottomTimeToDate = accumulatedBottomTimeToDate;
    }

    public String getDiveSiteTimeZoneID() {
        return diveSiteTimeZoneID;
    }

    public void setDiveSiteTimeZoneID(String diveSiteTimeZoneID) {
        this.diveSiteTimeZoneID = diveSiteTimeZoneID;
    }

    public String getDiveLogUid() {
        return diveLogUid;
    }

    public void setDiveLogUid(String diveLogUid) {
        this.diveLogUid = diveLogUid;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDiveBuddyPersonUid() {
        return diveBuddyPersonUid;
    }

    public void setDiveBuddyPersonUid(String diveBuddyPersonUid) {
        this.diveBuddyPersonUid = diveBuddyPersonUid;
    }

    public String getDiveMasterPersonUid() {
        return diveMasterPersonUid;
    }

    public void setDiveMasterPersonUid(String diveMasterPersonUid) {
        this.diveMasterPersonUid = diveMasterPersonUid;
    }

    public String getDiveCompanyPersonUid() {
        return diveCompanyPersonUid;
    }

    public void setDiveCompanyPersonUid(String diveCompanyPersonUid) {
        this.diveCompanyPersonUid = diveCompanyPersonUid;
    }

    public String getTankType() {
        return tankType;
    }

    public void setTankType(String tankType) {
        this.tankType = tankType;
    }

    public double getWeightUsed() {
        return weightUsed;
    }

    public void setWeightUsed(double weightUsed) {
        this.weightUsed = weightUsed;
    }

    public double getStartingTankPressure() {
        return startingTankPressure;
    }

    public void setStartingTankPressure(double startingTankPressure) {
        this.startingTankPressure = startingTankPressure;
    }

    public double getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(double airTemperature) {
        this.airTemperature = airTemperature;
    }

    public double getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(double waterTemperature) {
        this.waterTemperature = waterTemperature;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public String getCurrentCondition() {
        return currentCondition;
    }

    public void setCurrentCondition(String currentCondition) {
        this.currentCondition = currentCondition;
    }

    public String getDiveEntry() {
        return diveEntry;
    }

    public void setDiveEntry(String diveEntry) {
        this.diveEntry = diveEntry;
    }

    public String getDiveStyle() {
        return diveStyle;
    }

    public void setDiveStyle(String diveStyle) {
        this.diveStyle = diveStyle;
    }

    public String getDiveType() {
        return diveType;
    }

    public void setDiveType(String diveType) {
        this.diveType = diveType;
    }

    public String getSeaCondition() {
        return seaCondition;
    }

    public void setSeaCondition(String seaCondition) {
        this.seaCondition = seaCondition;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public String getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(String equipmentList) {
        this.equipmentList = equipmentList;
    }

    public Boolean isSequencingRequired() {
        return sequencingRequired;
    }

    public void setSequencingRequired(Boolean sequencingRequired) {
        this.sequencingRequired = sequencingRequired;
    }

    public long getSurfaceInterval() {
        return surfaceInterval;
    }

    public void setSurfaceInterval(long surfaceInterval) {
        this.surfaceInterval = surfaceInterval;
    }

    public long getBottomTime() {
        return bottomTime;
    }

    public void setBottomTime(long bottomTime) {
        this.bottomTime = bottomTime;
    }

    public String getDiveSiteUid() {
        return diveSiteUid;
    }

    public void setDiveSiteUid(String diveSiteUid) {
        this.diveSiteUid = diveSiteUid;
    }

    public double getMaximumDepth() {
        return maximumDepth;
    }

    public void setMaximumDepth(double maximumDepth) {
        this.maximumDepth = maximumDepth;
    }

    public String getNextDiveLogUid() {
        return nextDiveLogUid;
    }

    public void setNextDiveLogUid(String nextDiveLogUid) {
        this.nextDiveLogUid = nextDiveLogUid;
    }

    public String getPreviousDiveLogUid() {
        return previousDiveLogUid;
    }

    public void setPreviousDiveLogUid(String previousDiveLogUid) {
        this.previousDiveLogUid = previousDiveLogUid;
    }

    public String getTissueLoadingColor() {
        return tissueLoadingColor;
    }

    public void setTissueLoadingColor(String tissueLoadingColor) {
        this.tissueLoadingColor = tissueLoadingColor;
    }

    public int getTissueLoadingValue() {
        return tissueLoadingValue;
    }

    public void setTissueLoadingValue(int tissueLoadingValue) {
        this.tissueLoadingValue = tissueLoadingValue;
    }

    public float getDiveRating() {
        return diveRating;
    }

    public void setDiveRating(float diveRating) {
        this.diveRating = diveRating;
    }

    public double getEndingTankPressure() {
        return endingTankPressure;
    }

    public void setEndingTankPressure(double endingTankPressure) {
        this.endingTankPressure = endingTankPressure;
    }

    public double getAirUsed() {
        return airUsed;
    }

    public void setAirUsed(double airUsed) {
        this.airUsed = airUsed;
    }

    public String getDiveNotes() {
        return diveNotes;
    }

    public void setDiveNotes(String diveNotes) {
        this.diveNotes = diveNotes;
    }

    public String getMarineLife() {
        return marineLife;
    }

    public void setMarineLife(String marineLife) {
        this.marineLife = marineLife;
    }

    public String getDivePhotosUrl() {
        return divePhotosUrl;
    }

    public void setDivePhotosUrl(String divePhotosUrl) {
        this.divePhotosUrl = divePhotosUrl;
    }
    //endregion  Getters and Setters

    @Exclude
    public String getDataSummary() {
        final int numberOfLetters = 10;
        StringBuilder dataSummary = new StringBuilder();
        dataSummary.append("Dive Log " + diveNumber + " Summary:").append(System.getProperty("line.separator"));

        dataSummary.append(System.getProperty("line.separator"));
        dataSummary.append("DOUBLE AND FLOAT VALUES").append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("maximumDepth = ").append(String.valueOf(maximumDepth)).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("startingTankPressure = ").append(String.valueOf(startingTankPressure)).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("endingTankPressure = ").append(String.valueOf(endingTankPressure)).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("airUsed = ").append(String.valueOf(airUsed)).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("visibility = ").append(String.valueOf(visibility)).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("airTemperature = ").append(String.valueOf(airTemperature)).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("waterTemperature = ").append(String.valueOf(waterTemperature)).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("weightUsed = ").append(String.valueOf(weightUsed)).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("diveRating = ").append(String.valueOf(diveRating)).append(System.getProperty("line.separator"));

        dataSummary.append(System.getProperty("line.separator"));
        dataSummary.append("LONG VALUES").append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("surfaceInterval = ").append(String.valueOf(surfaceInterval)).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("diveStart = ").append(String.valueOf(diveStart)).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("diveEnd = ").append(String.valueOf(diveEnd)).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("bottomTime = ").append(String.valueOf(bottomTime)).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("accumulatedBottomTimeToDate = ").append(String.valueOf(accumulatedBottomTimeToDate)).append(System.getProperty("line.separator"));

        dataSummary.append(System.getProperty("line.separator"));
        dataSummary.append("BOOLEAN AND INT VALUES").append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("sequencingRequired = ").append(String.valueOf(sequencingRequired)).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("diveNumber = ").append(String.valueOf(diveNumber)).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("tissueLoadingValue = ").append(String.valueOf(tissueLoadingValue)).append(System.getProperty("line.separator"));

        dataSummary.append(System.getProperty("line.separator"));
        dataSummary.append("STRING VALUES").append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("diveSiteUid = ").append(diveSiteUid).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("area = ").append(area).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("country = ").append(country).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("state = ").append(state).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("diveBuddyPersonUid = ").append(diveBuddyPersonUid).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("diveMasterPersonUid = ").append(diveMasterPersonUid).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("diveCompanyPersonUid = ").append(diveCompanyPersonUid).append(System.getProperty("line.separator"));

        dataSummary.append("   ").append("currentCondition = ").append(currentCondition).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("diveEntry = ").append(diveEntry).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("diveLogUid = ").append(diveLogUid).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("diveNotes = ").append(diveNotes.substring(0, Math.min(diveNotes.length(), numberOfLetters))).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("divePhotosUrl = ").append(divePhotosUrl).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("diveSiteTimeZoneID = ").append(diveSiteTimeZoneID).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("diveStyle = ").append(diveStyle).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("diveType = ").append(diveType).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("equipmentList = ").append(equipmentList.substring(0, Math.min(equipmentList.length(), numberOfLetters))).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("marineLife = ").append(marineLife).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("nextDiveLogUid = ").append(nextDiveLogUid).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("previousDiveLogUid = ").append(previousDiveLogUid).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("seaCondition = ").append(seaCondition).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("tankType = ").append(tankType).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("tissueLoadingColor = ").append(tissueLoadingColor).append(System.getProperty("line.separator"));
        dataSummary.append("   ").append("weatherCondition = ").append(weatherCondition).append(System.getProperty("line.separator"));

        return dataSummary.toString();
    }

    @Override
    public String toString() {
//        return getDisplayName();
        return "Dive Number " + String.valueOf(diveNumber);
    }


    public static String save(@NonNull String userUid, @NonNull DiveLog diveLog) {
        if (diveLog.getDiveLogUid() == null
                || diveLog.getDiveLogUid().isEmpty()
                || diveLog.getDiveLogUid().equals("N/A")) {
            String newDiveLogUid = nodeUserDiveLogs(userUid).push().getKey();
            diveLog.setDiveLogUid(newDiveLogUid);
            Timber.i("Created diveLog number %d.", diveLog.getDiveNumber());
        }
        nodeUserDiveLogs(userUid).child(diveLog.getDiveLogUid()).setValue(diveLog);
        Timber.i("Saved diveLog number %d.", diveLog.getDiveNumber());
        return diveLog.getDiveLogUid();
    }

    public static DatabaseReference nodeUserDiveLogs(@NonNull String userUid) {
        return dbReference.child(NODE_DIVE_LOGS).child(userUid);
    }
}

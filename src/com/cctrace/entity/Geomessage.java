package com.cctrace.entity;

public class Geomessage {
    private Integer id;

    private String containerId;

    private String alertTime;

    private String alertLongTime;

    private String alertType;

    private String alertContent;

    private Double lat;

    private Double lon;

    private String readed;

    private Integer alarm_num;

    private String gpsPosition;

    private String updateTime;

    private String updateLongTime;

    private Integer companyId;

    private String ext1;

    private String ext2;

    private String ext3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId == null ? null : containerId.trim();
    }

    public String getalertTime() {
        return alertTime;
    }

    public void setAlertTime(String alertTime) {
        this.alertTime = alertTime == null ? null : alertTime.trim();
    }

    public String getAlertLongTime() {
        return alertLongTime;
    }

    public void setAlertLongTime(String alertLongTime) {
        this.alertLongTime = alertLongTime == null ? null : alertLongTime.trim();
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType == null ? null : alertType.trim();
    }

    public String getAlertContent() {
        return alertContent;
    }

    public void setAlertContent(String alertContent) {
        this.alertContent = alertContent == null ? null : alertContent.trim();
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getReaded() {
        return readed;
    }

    public void setReaded(String readed) {
        this.readed = readed == null ? null : readed.trim();
    }

    public Integer getAlarm_num() {
        return alarm_num;
    }

    public void setAlarm_num(Integer alarm_num) {
        this.alarm_num = alarm_num;
    }

    public String getGpsPosition() {
        return gpsPosition;
    }

    public void setGpsPosition(String gpsPosition) {
        this.gpsPosition = gpsPosition == null ? null : gpsPosition.trim();
    }

    public String getupdateTime() {
        return updateTime;
    }

    public void setupdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getupdateLongTime() {
        return updateLongTime;
    }

    public void setupdateLongTime(String updateLongTime) {
        this.updateLongTime = updateLongTime == null ? null : updateLongTime.trim();
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }
}
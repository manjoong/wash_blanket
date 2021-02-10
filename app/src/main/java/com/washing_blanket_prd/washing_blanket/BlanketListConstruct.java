package com.washing_blanket_prd.washing_blanket;

public class BlanketListConstruct {

    private Integer id;
    private String blanket_name;
    private String icon;
    private String image;
    private Integer alarm_period;
    private String washed_check;
    private String create_date;

    public Integer getId() {
        return id;
    }
    public String getBlanketName() {
        return blanket_name;
    }

    public String getIcon() {
        return icon;
    }

    public String getImage() {
        return image;
    }

    public Integer getAlarmPeriod() {
        return alarm_period;
    }

    public String getWashedCheck() {
        return washed_check;
    }
    public String getCreateDate() {
        return create_date;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setBlanketName(String blanket_name) {
        this.blanket_name = blanket_name;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAlarmPeriod(Integer alarmPeriod) {
        this.alarm_period = alarmPeriod;
    }

    public void setWashedCheck(String washed_check) {
        this.washed_check = washed_check;
    }
    public void setCreateDate(String create_date) {
        this.create_date = create_date;
    }


}
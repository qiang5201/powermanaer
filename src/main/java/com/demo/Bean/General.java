package com.demo.Bean;


public class General {

    Integer general_id;
    String general_name;
    String general_sex;
    String general_image;

    @Override
    public String toString() {
        return "General{" +
                "general_id=" + general_id +
                ", general_name='" + general_name + '\'' +
                ", general_sex='" + general_sex + '\'' +
                ", general_image='" + general_image + '\'' +
                '}';
    }

    public Integer getGeneral_id() {
        return general_id;
    }

    public void setGeneral_id(Integer general_id) {
        this.general_id = general_id;
    }

    public String getGeneral_name() {
        return general_name;
    }

    public void setGeneral_name(String general_name) {
        this.general_name = general_name;
    }

    public String getGeneral_sex() {
        return general_sex;
    }

    public void setGeneral_sex(String general_sex) {
        this.general_sex = general_sex;
    }

    public String getGeneral_image() {
        return general_image;
    }

    public void setGeneral_image(String general_image) {
        this.general_image = general_image;
    }
}

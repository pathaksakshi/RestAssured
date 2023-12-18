package com.rest.pojo.Workspace;

public class Workspace_geo {
    private String lat;
    private String lng;
public Workspace_geo(){

}
    public  Workspace_geo(String lat, String lng){
        this.lat=lat;
        this.lng=lng;
    }
    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }


}

package com.rest.pojo.Workspace;

public class Workspace_address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Workspace_geo workspaceGeo;

    public Workspace_address(){

    }
    public Workspace_address(String street, String suite,String city, String zipcode,Workspace_geo workspaceGeo){
        this.street= street;
        this.suite=suite;
        this.city=city;
        this.zipcode= zipcode;
        this.workspaceGeo =workspaceGeo;
    }
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    public Workspace_geo getWorkspaceGeo() {
        return workspaceGeo;
    }

    public void setWorkspaceGeo(Workspace_geo workspaceGeo) {
        this.workspaceGeo = workspaceGeo;
    }




}

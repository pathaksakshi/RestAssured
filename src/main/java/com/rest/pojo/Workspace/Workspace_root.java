package com.rest.pojo.Workspace;

public class Workspace_root {
    public Workspace_root(){

    }
    public Workspace_root(Workspace_address workspaceAddress,String name,String username,String email){
        this.workspaceAddress=workspaceAddress;
        this.name=name;
        this.username= username;
        this.email=email;

    }

    private String name;
    private String username;
    private String email;
    Workspace_address workspaceAddress;
    Workspace_geo workspaceGeo;
    public Workspace_address getWorkspaceAddress() {
        return workspaceAddress;
    }

    public void setWorkspaceAddress(Workspace_address workspaceAddress) {
        this.workspaceAddress = workspaceAddress;
    }

    public Workspace_geo getWorkspaceGeo() {
        return workspaceGeo;
    }

    public void setWorkspaceGeo(Workspace_geo workspaceGeo) {
        this.workspaceGeo = workspaceGeo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}

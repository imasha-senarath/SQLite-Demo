package com.example.sqlitedemo;

public class UserModel {
    private String nic, name;

    public UserModel() {
    }

    public UserModel(String nic, String name) {
        this.nic = nic;
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "nic='" + nic + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

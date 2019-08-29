package com.example.kidz.project_uas.DataMember;


public class Member {
    int id;
    String name, list, joiningDate, alamat;

    public Member(int id, String name, String list, String joiningDate, String alamat) {
        this.id = id;
        this.name = name;
        this.list = list;
        this.joiningDate = joiningDate;
        this.alamat = alamat;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getList() { return list; }

    public String getJoiningDate() {
        return joiningDate;
    }

    public String getAlamat() { return alamat; }
}

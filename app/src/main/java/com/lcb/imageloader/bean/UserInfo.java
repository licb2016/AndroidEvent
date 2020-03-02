package com.lcb.imageloader.bean;


import com.lcb.imageloader.annotation.DbField;
import com.lcb.imageloader.annotation.DbTable;

@DbTable("tb_user")
public class UserInfo {

    @DbField("_id")
    private String  id ;
    @DbField("name")
    private String name;

    public UserInfo() {
    }

    public UserInfo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

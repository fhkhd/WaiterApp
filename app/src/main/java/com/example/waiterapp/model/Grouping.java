package com.example.waiterapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "grouping_table")
public class Grouping {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "name_group")
    public String name;
    @ColumnInfo(name = "picture")
    public String picture;


    public Grouping(int id, String name,String picture) {
        this.id = id;
        this.name = name;
        this.picture = picture;
    }

    @Ignore
    public Grouping(String name,String picture) {
        this.name = name;
        this.picture = picture;
    }
}

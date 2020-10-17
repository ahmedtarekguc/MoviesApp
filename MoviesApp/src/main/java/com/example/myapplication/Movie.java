package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie_table")
public class Movie {

    @PrimaryKey(autoGenerate = true)
    private int ID;

    private String name;
    private String description;
    private int productionyear;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }



    public String getDescription() {
        return description;
    }



    public int getProductionyear() {
        return productionyear;
    }



    public Movie(String name, String description, int productionyear) {
        this.name = name;
        this.description = description;
        this.productionyear = productionyear;
    }
}

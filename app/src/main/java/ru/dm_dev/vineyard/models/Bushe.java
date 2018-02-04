package ru.dm_dev.vineyard.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

@Table(name = "Bushes", id = "_id")
public class Bushe extends Model {
    @Column(name = "Name")
    public String name;
    @Column(name = "Photo")
    public String photo;
    @Column(name = "Variety")
    public Variety variety;
    @Column(name = "Area")
    public Area area;

    @Column(name = "guid")
    public long Guid;

    public Bushe() {
        Guid = new Date().getTime();
    }
}

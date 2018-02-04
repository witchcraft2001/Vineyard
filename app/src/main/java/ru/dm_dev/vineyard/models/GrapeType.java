package ru.dm_dev.vineyard.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;
import java.util.List;

@Table(name = "GrapeTypes", id = "_id")
public class GrapeType extends Model {
    @Column(name = "Name")
    public String name;
    @Column(name = "guid")
    public long Guid;
    public GrapeType() {
        Guid = new Date().getTime();
    }
}

package ru.dm_dev.vineyard.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Table(name = "Areas", id = "_id")
public class Area extends Model {
    @Column(name = "Name")
    public String name;
    @Column(name = "Description")
    public String description;
    @Column(name = "guid")
    public long Guid;

    public Area() {
        Guid = new Date().getTime();
    }

    @Override
    public String toString() {
        return name;
    }

    public List<Bushe> busheList() {
        return getMany(Bushe.class, "Area");
    }
}

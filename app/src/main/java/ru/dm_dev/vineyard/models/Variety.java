package ru.dm_dev.vineyard.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = "Varieties", id = "_id")
public class Variety extends Model {
    @Column(name = "Name")
    public String name;
    @Column(name = "Description")
    public String description;
    @Column(name = "Color")
    public String color;
    @Column(name = "GrapeType")
    public GrapeType grapeType;

    public List<Bushe> busheList() {
        return getMany(Bushe.class, "Variety");
    }
}

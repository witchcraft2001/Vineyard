package ru.dm_dev.vineyard.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

@Table(name = "Areas", id = "_id")
public class Area extends Model {
    @Column(name = "Name")
    public String name;
    @Column(name = "Description")
    public String description;

    public List<Bushe> busheList() {
        return getMany(Bushe.class, "Area");
    }
}

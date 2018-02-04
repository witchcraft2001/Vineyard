package ru.dm_dev.vineyard.views;

import java.util.List;

import ru.dm_dev.vineyard.models.Area;
import ru.dm_dev.vineyard.models.Variety;

public interface IEditBusheView {
    void setTitle(String title);
    void setName(String name);
    void setDescription(String description);
    String getName();
    String getDescription();
    void goBack();
    void setAreasListAdapter(List<Area> list);
    void setVarietiesListAdapter(List<Variety> list);
}

package ru.dm_dev.vineyard.views;

import java.util.List;

import ru.dm_dev.vineyard.models.Area;
import ru.dm_dev.vineyard.models.Variety;

public interface IEditBusheView {
    void setTitle(String title);
    void setName(String name);
    void setDescription(String description);
    void setPlantedAt(String date);
    void setSelectedVariety(int pos);
    void setSelectedArea(int pos);
    String getName();
    String getDescription();
    String getPlantedAt();
    Variety getSelectedVariety();
    Area getSelectedArea();
    void goBack();
    void setAreasListAdapter(List<Area> list);
    void setVarietiesListAdapter(List<Variety> list);
}

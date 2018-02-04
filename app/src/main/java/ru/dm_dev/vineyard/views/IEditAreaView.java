package ru.dm_dev.vineyard.views;

public interface IEditAreaView {
    void setTitle(String title);
    void setName(String name);
    void setDescription(String description);
    String getName();
    String getDescription();
    void goBack();
}

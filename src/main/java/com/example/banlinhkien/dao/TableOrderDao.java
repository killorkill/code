package com.example.banlinhkien.dao;

import com.example.banlinhkien.entity.TableOrder;

import java.util.List;

public interface TableOrderDao {
    public TableOrder getById(int id);

    public List<TableOrder> getListByActive(boolean active);

    public void addOrUpdate(TableOrder tableOrder);

    public TableOrder getByAccountUsernameAndActive(String username, boolean active);

    public void delete(int id);
}

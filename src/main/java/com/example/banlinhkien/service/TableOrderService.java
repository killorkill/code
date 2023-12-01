package com.example.banlinhkien.service;

import com.example.banlinhkien.models.TableOrderDTO;

import java.util.List;

public interface TableOrderService {
    public List<TableOrderDTO> getListByActive(boolean active);

    public TableOrderDTO getById(int id);

    public void addOrUpdate(TableOrderDTO tableOrderDTO);

    public TableOrderDTO getByAccountUsernameAndActive(String username, boolean active);

    public void delete(int id);
}

package com.example.banlinhkien.service.impl;


import com.example.banlinhkien.converter.TableOrderConverter;
import com.example.banlinhkien.dao.TableOrderDao;
import com.example.banlinhkien.entity.TableOrder;
import com.example.banlinhkien.models.TableOrderDTO;
import com.example.banlinhkien.service.TableOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TableOrderServiceImpl implements TableOrderService {
    @Autowired
    private TableOrderDao tableOrderDao;

    @Autowired
    private TableOrderConverter tableOrderConverter;

    @Override
    public void addOrUpdate(TableOrderDTO tableOrderDTO) {

        TableOrder tableOrder = tableOrderConverter.toEntity(tableOrderDTO);
        this.delete(tableOrder.getId());
        tableOrderDao.addOrUpdate(tableOrder);
    }

    @Override
    public TableOrderDTO getByAccountUsernameAndActive(String username, boolean active) {
        TableOrder tableOrder = tableOrderDao.getByAccountUsernameAndActive(username, active);

        if (tableOrder != null) {
            TableOrderDTO tableOrderDTO = tableOrderConverter.toDTO(tableOrder);
            return tableOrderDTO;
        }

        return null;
    }

    @Override
    public List<TableOrderDTO> getListByActive(boolean active) {
        List<TableOrderDTO> tableOrderDTOs = new ArrayList<TableOrderDTO>();

        List<TableOrder> tableOrders = tableOrderDao.getListByActive(active);
        for (TableOrder tableOrder : tableOrders) {
            TableOrderDTO tableOrderDTO = tableOrderConverter.toDTO(tableOrder);
            tableOrderDTOs.add(tableOrderDTO);
        }

        return tableOrderDTOs;
    }

    @Override
    public TableOrderDTO getById(int id) {
        TableOrder tableOrder = tableOrderDao.getById(id);

        TableOrderDTO tableOrderDTO = tableOrderConverter.toDTO(tableOrder);

        return tableOrderDTO;
    }

    @Override
    public void delete(int id) {
        tableOrderDao.delete(id);

    }

}

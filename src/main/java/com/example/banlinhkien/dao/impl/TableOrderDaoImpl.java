package com.example.banlinhkien.dao.impl;


import com.example.banlinhkien.dao.TableOrderDao;
import com.example.banlinhkien.entity.TableOrder;
import com.example.banlinhkien.repository.TableOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TableOrderDaoImpl implements TableOrderDao {
    @Autowired
    private TableOrderRepository tableOrderRepository;

    @Override
    public void addOrUpdate(TableOrder tableOrder) {
        tableOrderRepository.save(tableOrder);
    }

    @Override
    public TableOrder getByAccountUsernameAndActive(String username, boolean active) {

        return tableOrderRepository.findByAccountUsernameAndActive(username, active);
    }

    @Override
    public List<TableOrder> getListByActive(boolean active) {

        return tableOrderRepository.findAllByActive(active);
    }

    @Override
    public void delete(int id) {
        tableOrderRepository.delete(getById(id));
    }

    @Override
    public TableOrder getById(int id) {

        return tableOrderRepository.getOne(id);
    }
}

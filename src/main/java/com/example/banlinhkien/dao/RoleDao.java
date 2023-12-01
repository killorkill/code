package com.example.banlinhkien.dao;

import com.example.banlinhkien.entity.Role;

import java.util.List;

public interface RoleDao {

    public List<Role> getList();

    public Role getById(int id);

    public Role getByName(String name);

    public void addOrUpDate(Role role);

    public void delete(int id);
}

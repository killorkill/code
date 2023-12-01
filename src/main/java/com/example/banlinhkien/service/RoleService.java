package com.example.banlinhkien.service;

import com.example.banlinhkien.models.RoleDTO;

import java.util.List;

public interface RoleService {
    public List<RoleDTO> getList();

    public RoleDTO getById(int id);

    public RoleDTO getByName(String name);

    public void addOrUpdate(RoleDTO roleDTO);

    public void delete(int id);
}

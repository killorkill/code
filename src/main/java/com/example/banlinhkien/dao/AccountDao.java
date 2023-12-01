package com.example.banlinhkien.dao;

import com.example.banlinhkien.entity.Account;

import java.util.List;

public interface AccountDao {

    public List<Account> getList();

    public Account getById(int id);

    public Account getByUsername(String username);

    public Account getByEmail(String email);

    public Account getByUsernameOrEmail(String username, String email);

    public Account getByUsernameAndActive(String username, boolean active);

    public void addOrUpdate(Account account);

    public void delete(int id);
}

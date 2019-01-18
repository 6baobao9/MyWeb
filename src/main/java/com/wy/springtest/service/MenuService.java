package com.wy.springtest.service;

import com.wy.springtest.data.model.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> queryMenu();

    void modifyMenu(List<Menu> menus, List<Menu> menus_del);
}

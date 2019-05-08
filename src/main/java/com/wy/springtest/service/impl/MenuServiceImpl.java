package com.wy.springtest.service.impl;

import com.wy.springtest.data.mapper.MenuMapper;
import com.wy.springtest.data.model.Menu;
import com.wy.springtest.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    MenuMapper menuMapper;

    @Override
    public List<Menu> queryMenu() {
        return menuMapper.selectAllMenu();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void modifyMenu(List<Menu> menus, List<Menu> menus_del) {
        for (Menu menu : menus) {
            if (menu.getId() == null) {
                menuMapper.insert(menu);
            } else {
                menuMapper.update(menu);
            }
        }
        for (Menu menu : menus_del) {
            menuMapper.delete(menu);
        }
    }
}

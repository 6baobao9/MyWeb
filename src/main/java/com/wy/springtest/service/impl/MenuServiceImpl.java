package com.wy.springtest.service.impl;

import com.wy.springtest.data.mapper.MenuMapper;
import com.wy.springtest.data.model.Menu;
import com.wy.springtest.service.MenuService;
import org.springframework.stereotype.Service;

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
}

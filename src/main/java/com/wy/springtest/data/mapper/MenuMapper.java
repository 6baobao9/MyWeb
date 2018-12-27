package com.wy.springtest.data.mapper;


import com.wy.springtest.data.model.Menu;
import com.wy.springtest.data.model.User;

import java.util.List;

public interface MenuMapper {
    List<Menu> selectAllMenu();
}


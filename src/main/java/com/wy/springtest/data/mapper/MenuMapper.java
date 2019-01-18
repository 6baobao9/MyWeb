package com.wy.springtest.data.mapper;


import com.wy.springtest.data.model.Menu;
import com.wy.springtest.data.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {
    List<Menu> selectAllMenu();

    int insert(@Param("menu") Menu menu);

    int update(@Param("menu") Menu menu);

    int delete(@Param("menu") Menu menu);
}


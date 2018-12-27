package com.wy.springtest.data.mapper;


import com.wy.springtest.data.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> selectAllUser();

    User selectByAccount(@Param("account") String account);

    void insert(@Param("u") User u);
}


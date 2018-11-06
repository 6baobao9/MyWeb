package com.wy.springtest.data.mapper;


import com.wy.springtest.data.model.User;

import java.util.List;

public interface UserMapper {
    //这个方式我自己加的
    List<User> selectAllUser();

    User selectByAccount(String account);
}


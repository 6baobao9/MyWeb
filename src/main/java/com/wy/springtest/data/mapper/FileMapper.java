package com.wy.springtest.data.mapper;

import com.wy.springtest.data.model.FileData;
import org.apache.ibatis.annotations.Param;

public interface FileMapper {
    int insert(@Param("fileData") FileData fileData);

    FileData queryById(@Param("id") Integer id);
}

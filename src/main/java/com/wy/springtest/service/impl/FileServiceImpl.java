package com.wy.springtest.service.impl;

import com.wy.springtest.data.mapper.FileMapper;
import com.wy.springtest.data.model.FileData;
import com.wy.springtest.service.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FileServiceImpl implements FileService {
    @Resource
    FileMapper fileMapper;

    public void addFile(byte[] bytes) {
        FileData data = new FileData();
        data.setData(bytes);
        addFile(data);
    }

    public void addFile(FileData data) {
        fileMapper.insert(data);
    }

    public FileData getFile(Integer id) {
        return fileMapper.queryById(id);
    }
}

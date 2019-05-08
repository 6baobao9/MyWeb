package com.wy.springtest.service;

import com.wy.springtest.data.model.FileData;

public interface FileService {
    void addFile(byte[] bytes);

    void addFile(FileData data);

    FileData getFile(Integer id);
}

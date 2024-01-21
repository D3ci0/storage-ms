package com.gamma.storage.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    void init() throws IOException;
    void store(MultipartFile file);
}

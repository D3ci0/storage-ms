package com.gamma.storage.service;

import com.gamma.storage.configuration.FileSystemStorageConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileSystemStorageService implements StorageService{
    private static final Logger logger = LoggerFactory.getLogger(FileSystemStorageService.class);
    private FileSystemStorageConfig storageConfig;
    @Override
    public void init() throws IOException {
        Files.createDirectories(Paths.get(storageConfig.getConfig().get("home")));
    }
    @Override
    public void store(MultipartFile file) {
        try {
            if (file == null || file.isEmpty() || file.getOriginalFilename() == null) {
                throw new RuntimeException("Failed to store empty file");
            }

            Path destinationFile = Paths.get(storageConfig.getConfig().get("home")).resolve(
                            Paths.get(file.getOriginalFilename()))
                            .normalize().toAbsolutePath();

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }catch (IOException e){
            logger.error("Exception while storing the file: ", e);
            throw new RuntimeException(e);
        }
    }

    @Autowired
    public void setStorageConfig(FileSystemStorageConfig storageConfig) {
        this.storageConfig = storageConfig;
    }
}

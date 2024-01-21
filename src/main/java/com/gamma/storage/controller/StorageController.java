package com.gamma.storage.controller;

import com.gamma.storage.model.StoreFileResponseMessage;
import com.gamma.storage.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1")
public class StorageController {
    private static final Logger logger = LoggerFactory.getLogger(StorageController.class);
    private StorageService storageService;

    @PostMapping("/store")
    public StoreFileResponseMessage storeFiles(MultipartFile[] files){
        try {

            for(MultipartFile file : files){
                storageService.store(file);
            }

            return new StoreFileResponseMessage("0");
        } catch (Exception e) {
            logger.error("Exception while storing the files: ", e);
            return new StoreFileResponseMessage("-1");
        }
    }

    @Autowired
    @Qualifier("fileSystemStorageService")
    public void setStorageService(StorageService storageService) {
        this.storageService = storageService;
    }
}

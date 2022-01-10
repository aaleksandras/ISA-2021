package com.example.backend.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.MalformedURLException;

@Service
public class FileService {

    @Value("${image.storage}")
    private String storageDirectoryPath;

    public UrlResource getContent(String contentName) throws MalformedURLException {
        return new UrlResource("file:" + storageDirectoryPath + File.separator + contentName);
    }
}

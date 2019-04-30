package com.mai.pilot_assistent.service;

import com.mai.pilot_assistent.util.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Сервис для работы с облачным хранилищем медиафайлов Cloudinary
 */
public interface ImageCloudService {

    /**
     * Метод загружает файл в облако
     * @param file
     * @return возвращает url на загруженный файл
     */
    Result<String> upload(MultipartFile file);
}

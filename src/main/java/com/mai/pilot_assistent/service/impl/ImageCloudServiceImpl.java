package com.mai.pilot_assistent.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.mai.pilot_assistent.service.ImageCloudService;
import com.mai.pilot_assistent.util.Result;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@Service
public class ImageCloudServiceImpl implements ImageCloudService {

    /**
     * API key для использования Cloudinary, неоходимо установить системную переменную CLOUDINARY_URL
     * {@see https://cloudinary.com/documentation/upload_images}
     */
    private final String mCloudUrl;

    @Autowired
    public ImageCloudServiceImpl(@Qualifier("cloudinary.url") String mCloudUrl) {
        this.mCloudUrl = mCloudUrl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Result<String> upload(MultipartFile file) {
        try {
            File createFile = Files.createTempFile("temp", file.getOriginalFilename()).toFile();
            file.transferTo(createFile);
            Cloudinary cloudinary = new Cloudinary(mCloudUrl);
            Map response = cloudinary.uploader().upload(createFile, ObjectUtils.emptyMap());
            JSONObject json = new JSONObject(response);
            return Result.success(json.getString("url"));
        } catch (IOException ex) {
            return Result.fail(ex, "Произошла ошибка при сохранении файла!");
        }
    }
}

package com.mai.pilot_assistent.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.mai.pilot_assistent.controller.dto.base.ErrorResponse;
import com.mai.pilot_assistent.controller.dto.base.SuccessResponse;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    /**
     * API key для использования Cloudinary, неоходимо установить системную переменную CLOUDINARY_URL
     * {@see https://cloudinary.com/documentation/upload_images}
     */
    @Autowired
    @Qualifier("cloudinary.url")
    String mCloudUrl;

    /**
     * Метод загружает файлы в облако Cloudinary (пока это просто тестовый конетроллер, в дальнейшем это перенесу в сервис, чтобы можно было везде работать с загрузкой)
     * @param file пришедший с клиента
     * @return результат загрузки файла в облако
     */
    @PostMapping(value = "/uploadFile", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        Cloudinary c = new Cloudinary(mCloudUrl);
        try {
            File createFile = Files.createTempFile("temp", file.getOriginalFilename()).toFile();
            file.transferTo(createFile);

            Map response = c.uploader()
                    .upload(createFile, ObjectUtils.emptyMap());

            JSONObject json = new JSONObject(response);
            String url = json.getString("url");
            return ResponseEntity
                    .ok(SuccessResponse.builder().message(url).build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ErrorResponse.builder().errorText(e.getMessage()).build());
        }
    }
}

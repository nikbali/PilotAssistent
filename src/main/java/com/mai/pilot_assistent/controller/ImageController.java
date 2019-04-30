package com.mai.pilot_assistent.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.mai.pilot_assistent.controller.dto.base.ErrorResponse;
import com.mai.pilot_assistent.controller.dto.base.SuccessResponse;
import com.mai.pilot_assistent.service.ImageCloudService;
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

    private final ImageCloudService imageCloudService;

    @Autowired
    public ImageController(ImageCloudService imageCloudService) {
        this.imageCloudService = imageCloudService;
    }

    /**
     * Метод загружает файлы в облако Cloudinary (пока это просто тестовый конетроллер, в дальнейшем это перенесу в сервис, чтобы можно было везде работать с загрузкой)
     *
     * @param file пришедший с клиента
     * @return результат загрузки файла в облако
     */
    @PostMapping(value = "/uploadFile", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        return imageCloudService.upload(file).fold(
                (success) -> ResponseEntity.ok(SuccessResponse.builder()
                        .message(success).build()),
                failure -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(ErrorResponse.builder().errorText(failure.getMessage()).build())
        );
    }
}

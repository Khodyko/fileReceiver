package com.example.file_receiver;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import org.jcp.xml.dsig.internal.DigesterOutputStream;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/file_receiver/file")
public class FileController {

    @PostMapping
    public ResponseEntity<String> getFile(HttpServletRequest httpServletRequest){


        String uploadDirectory="/work/learn/trywrite";

        try (ServletInputStream inputStream = httpServletRequest.getInputStream()){
            // Создаем путь к директории, куда будет сохранен файл
            Path path = Paths.get(uploadDirectory+"/name.jpeg");

            // Записываем файл в директорию
            Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);

            return ResponseEntity.status(HttpStatus.OK)
                    .body("Файл успешно загружен: " + "name.jpeg");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка при загрузке файла: " + e.getMessage());
        }
    }
}

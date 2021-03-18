package co.kr.paka.controller;

import co.kr.paka.configuration.GlobalConfig;
import co.kr.paka.configuration.exception.BaseException;
import co.kr.paka.configuration.http.BaseResponse;
import co.kr.paka.configuration.http.BaseResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileController {
    @Autowired
    private GlobalConfig config;

    @PostMapping("/save")
    public BaseResponse<Boolean> save(@RequestParam("uploadFile")MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL);
        }
        String uploadFilePath = config.getUploadFilePath();
        String prefix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1, multipartFile.getOriginalFilename().length());
        String fileName = UUID.randomUUID().toString() + "." + prefix;
        String pathname = uploadFilePath + fileName;

        File folder = new File(uploadFilePath);
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }

        File file = new File(pathname);
        try {
            multipartFile.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        return new BaseResponse<>(true);
    }
}

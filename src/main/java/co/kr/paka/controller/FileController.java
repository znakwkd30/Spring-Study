package co.kr.paka.controller;

import co.kr.paka.configuration.GlobalConfig;
import co.kr.paka.configuration.exception.BaseException;
import co.kr.paka.configuration.http.BaseResponse;
import co.kr.paka.configuration.http.BaseResponseCode;
import co.kr.paka.request.UploadFileParameter;
import co.kr.paka.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

@RestController
public class FileController {
    @Autowired
    private GlobalConfig config;

    @Autowired
    private UploadFileService uploadFileService;

    @PostMapping("/save")
    public BaseResponse<Boolean> save(@RequestParam("uploadFile")MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL);
        }
        String currentDate = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
        String uploadFilePath = config.getUploadFilePath() + currentDate + "/";
        String prefix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1, multipartFile.getOriginalFilename().length());
        String filename = UUID.randomUUID().toString() + "." + prefix;
        String pathname = uploadFilePath + filename;
        String resourcePathname = config.getUploadResourcePath() +currentDate + "/" + filename;

        File folder = new File(uploadFilePath);
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }

        File file = new File(pathname);
        try {
            multipartFile.transferTo(file);
            UploadFileParameter parameter = new UploadFileParameter();
            parameter.setContentType(multipartFile.getContentType());
            parameter.setOriginalFilename(multipartFile.getOriginalFilename());
            parameter.setFilename(filename);
            parameter.setPathname(pathname);
            parameter.setSize((int) multipartFile.getSize());
            parameter.setResourcePathname(resourcePathname);
            uploadFileService.save(parameter);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }

        return new BaseResponse<>(true);
    }
}

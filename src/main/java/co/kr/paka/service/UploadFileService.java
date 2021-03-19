package co.kr.paka.service;

import co.kr.paka.repository.UploadFileRepository;
import co.kr.paka.request.UploadFileParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadFileService {
    @Autowired
    private UploadFileRepository uploadFileRepository;

    public void save(UploadFileParameter parameter) {
        uploadFileRepository.save(parameter);
    }
}

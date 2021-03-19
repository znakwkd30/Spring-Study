package co.kr.paka.repository;

import co.kr.paka.request.UploadFileParameter;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadFileRepository {
    void save(UploadFileParameter parameter);
}

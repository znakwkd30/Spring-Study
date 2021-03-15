package co.kr.paka.configuration.http;

import lombok.Data;

@Data
public class BaseResponse<T> {
    private BaseResponseCode code;
    private String message;
    private T data;

    public BaseResponse(T data) {
        this.code = BaseResponseCode.SUCCESS;
        this.data = data;
    }
}

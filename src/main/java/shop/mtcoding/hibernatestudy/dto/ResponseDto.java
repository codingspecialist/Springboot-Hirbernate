package shop.mtcoding.hibernatestudy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseDto<T> {
    private String msg;
    private T data;

    public ResponseDto(String msg) {
        this.msg = msg;
    }

    public ResponseDto(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }
}

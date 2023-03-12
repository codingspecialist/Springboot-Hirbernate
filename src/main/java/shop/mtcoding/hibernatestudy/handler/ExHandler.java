package shop.mtcoding.hibernatestudy.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import shop.mtcoding.hibernatestudy.dto.ResponseDto;


@ControllerAdvice
public class ExHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> dbError(RuntimeException e){
        ResponseDto<?> dto = new ResponseDto<>(e.getMessage());
        return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

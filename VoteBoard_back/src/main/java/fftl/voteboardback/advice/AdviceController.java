package fftl.voteboardback.advice;

import fftl.voteboardback.advice.errors.BadRequest;
import fftl.voteboardback.response.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice
public class AdviceController {

    //잘못된 입력을 받았을 때,
    @ExceptionHandler(BadRequest.class)
    protected ResponseEntity BadRequest(BadRequest e){
        return new ResponseEntity(new BasicResponse(false, null, e.getMessage()), HttpStatus.OK);
    }

    //request의 제약조건을 벗어나는 입력을 받았을 때,
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e){
        return new ResponseEntity(new BasicResponse(false, null, e.getFieldError().getDefaultMessage()), HttpStatus.OK);
    }

    //예상하지 못한 오류가 발생 했을 때.
    @ExceptionHandler(Exception.class)
    protected ResponseEntity basicException(Exception e){
        return new ResponseEntity(new BasicResponse(false, null, e.getMessage()), HttpStatus.OK);
    }
}

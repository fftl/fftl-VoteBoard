package fftl.voteboardback.controller;

import fftl.voteboardback.response.BasicResponse;
import fftl.voteboardback.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService userService;

    /**
     * 신규 아이디 생성 및 DB에 추가하기 ( 요구사항에는 존재하지 않지만 테스트의 편의를 위해서 만들어 보았습니다. )
     * */
    @GetMapping()
    public ResponseEntity saveUser(){
        String uId = userService.Save();
        return new ResponseEntity(new BasicResponse(true, uId ), HttpStatus.OK );
    }
}

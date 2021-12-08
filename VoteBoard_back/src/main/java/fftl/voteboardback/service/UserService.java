package fftl.voteboardback.service;

import fftl.voteboardback.entity.User;
import fftl.voteboardback.repository.UserRepository;
import fftl.voteboardback.service.other.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final Utils utils;

    /**
     * 아이디 저장!
     * */
    public String Save() {
        String userId = utils.createId(4);

        //만약에라도 중복되는 아이디가 생성될 경우 존재여부를 체크하여(존재하지 않는 아이디가 나올때 까지) 새로운 아이디를 만들어줍니다.
        while(userRepository.findById(userId).isPresent()){
            userId = utils.createId(4);
        }

        User user = User.builder().userId(userId).build();
        userRepository.save(user);

        return userId;
    }

}

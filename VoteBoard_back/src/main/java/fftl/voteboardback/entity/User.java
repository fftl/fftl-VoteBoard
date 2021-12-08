package fftl.voteboardback.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class User {

    /**
     * 사용자 id 네글자 문자 아이디를 직접 입력하여 만들어줍니다.
     * */

    @Id
    private String userId;

}

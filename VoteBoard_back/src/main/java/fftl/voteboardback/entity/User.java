package fftl.voteboardback.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name="USERS")
public class User {

    /**
     * 사용자 id 네글자 문자 아이디를 직접 입력하여 만들어줍니다.
     * */

    @Column(name="user_id")
    @Id
    private String userId;

}

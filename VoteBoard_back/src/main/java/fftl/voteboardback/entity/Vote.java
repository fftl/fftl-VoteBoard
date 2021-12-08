package fftl.voteboardback.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
public class Vote {

    /**
     * 생성된 투표 id 열글자 문자 아이디를 직접 입력하여 만들어줍니다.
     *
     * * */

    @Id
    private String voteId;          //투표 id
    private Long boardId;           //게시글 id (투표가 종속됨)
    private String title;           //투표 제목

    @Column(length=10000)
    private String description;     //투표 내용

    private String deadLine;        //마감 시간
    private String userId;          //투표를 작성한 사용자 id

}

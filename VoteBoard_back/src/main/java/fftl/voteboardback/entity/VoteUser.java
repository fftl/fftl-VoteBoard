package fftl.voteboardback.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class VoteUser {

    /**
     * 해당 투표에 참여한 사람인지 판단하기 위한 VoteUser Entity 입니다.
     * 해당 투표에 투표를 이미 참여했다면, 다른 항목을 고를 수 없습니다.
     * */

    @Id
    @GeneratedValue
    private Long voteUserId;        //투표유저 id

    private String voteId;          //투표의 id
    private String userId;          //투표한 유저의 id
}

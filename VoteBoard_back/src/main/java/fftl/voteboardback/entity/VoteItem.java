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
@Getter
@Builder
@Entity
public class VoteItem {

    /**
     * 투표 항목 id
     *
     * * */

    @Id
    @GeneratedValue
    private Long voteItemId;        //투표 항목 id

    private String content;         //투표 항목 내용
    private Long cnt;               //투표 항목 투표수
    private String voteId;          //투표 항목이 종속된 투표 id

    public void plusCnt(){
        this.cnt += 1;
    }

}

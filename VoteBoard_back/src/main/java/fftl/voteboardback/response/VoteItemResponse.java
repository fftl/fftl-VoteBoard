package fftl.voteboardback.response;

import lombok.Getter;

/**
 * Response
 * 투표 항목 가져올 때 투표항목의 투표 수와 투표 항목 Id를 함께 보내줍니다.
 * */

@Getter
public class VoteItemResponse {

    private Long voteItemId;
    private String content;
    private Long cnt;

    public VoteItemResponse(Long voteItemId, String content, Long cnt) {
        this.voteItemId = voteItemId;
        this.content = content;
        this.cnt = cnt;
    }
}

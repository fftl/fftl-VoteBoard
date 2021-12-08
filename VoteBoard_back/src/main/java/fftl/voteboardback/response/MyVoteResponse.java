package fftl.voteboardback.response;

import lombok.Getter;

/**
 * Response
 * 특정 사용자가 생성한 투표 목록 가져오기
 * */

@Getter
public class MyVoteResponse {

    private Long boardId;
    private String voteId;
    private String title;
    private String deadLine;
    private String userId;

    public MyVoteResponse(Long boardId, String voteId, String title, String deadLine, String userId) {
        this.boardId = boardId;
        this.voteId = voteId;
        this.title = title;
        this.deadLine = deadLine;
        this.userId = userId;
    }
}

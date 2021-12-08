package fftl.voteboardback.response;

import lombok.Getter;

import java.util.List;

/**
 * response
 * 투표 조회하기
 * */

@Getter
public class GetVoteResponse {

    private String userId;
    private String title;
    private String description;
    private List<VoteItemResponse> voteItems;
    private String deadLine;
    private boolean meVote;
    private List<VoteItemResponse> voteCnts;

    public GetVoteResponse(String userId, String title, String description, List<VoteItemResponse> voteItems, String deadLine, boolean meVote, List<VoteItemResponse> voteCnts) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.voteItems = voteItems;
        this.deadLine = deadLine;
        this.meVote = meVote;
        this.voteCnts = voteCnts;
    }
}

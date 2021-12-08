package fftl.voteboardback.request;


import fftl.voteboardback.entity.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * request
 * 투표 생성
 * */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveVoteRequest {

    private String voteId;

    @NotNull(message = "게시글 id를 입력해주세요.")
    private Long boardId;

    @NotBlank(message = "투표제목을 입력해주세요.")
    @Size(max=100, message = "투표제목은 100글자를 넘을수 없습니다.")
    private String title;

    @Size(max=10000, message = "투표설명은 10000글자를 넘을수 없습니다.")
    private String description;

    @Valid
    @NotNull(message = "투표항목은 최소 2개 이상 등록해주세요.")
    @Size(min=2, max=100, message = "투표항목은 2개 이상 100개 이하로 등록할 수 있습니다.")
    private List<SaveVoteItemRequest> voteItemRequest;

    private String deadLine;

    private String userId;

    public Vote toEntity(){
        return Vote.builder()
                .voteId(voteId)
                .boardId(boardId)
                .title(title)
                .description(description)
                .deadLine(deadLine)
                .userId(userId)
                .build();
    }
}

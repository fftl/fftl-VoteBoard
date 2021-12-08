package fftl.voteboardback.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * request
 * 투표 시행
 * */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoVoteRequest {

    @NotNull(message = "게시글 id가 비었습니다.")
    private Long boardId;

    @NotBlank(message = "투표 id가 비었습니다.")
    private String voteId;

    @NotNull(message = "투표항목 id가 비었습니다.")
    private Long voteItemId;
}

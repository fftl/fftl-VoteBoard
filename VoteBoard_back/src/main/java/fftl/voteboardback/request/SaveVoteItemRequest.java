package fftl.voteboardback.request;

import fftl.voteboardback.entity.VoteItem;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * request
 * 투표 항목 생성
 * */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveVoteItemRequest {

    @NotBlank(message = "투표항목 내용을 모두 입력해주세요.")
    @Size(max=50, message = "투표은 50글자를 넘을수 없습니다.")
    private String content;
    private String voteId;

    public VoteItem toEntity(){
        return VoteItem.builder()
                .content(content)
                .cnt(0L)
                .voteId(voteId)
                .build();
    }
}

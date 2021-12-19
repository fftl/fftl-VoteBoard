package fftl.voteboardback.controller;

import fftl.voteboardback.advice.errors.BadRequest;
import fftl.voteboardback.entity.Vote;
import fftl.voteboardback.request.GoVoteRequest;
import fftl.voteboardback.request.SaveVoteRequest;
import fftl.voteboardback.response.BasicResponse;
import fftl.voteboardback.response.GetVoteResponse;
import fftl.voteboardback.response.MyVoteResponse;
import fftl.voteboardback.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/vote")
public class VoteController {

    private final VoteService voteService;

    /**
     * 투표 생성 API
     * */
    @PostMapping
    public ResponseEntity saveVote(@RequestBody @Valid SaveVoteRequest saveVoteRequest, @RequestHeader Map<String, String> requestHeader){
        String userId = requestHeader.get("x-user-id");
        if(userId == null){
            throw new BadRequest("존재하는 userId를 입력해주세요.");
        }

        if(userId.length() != 4){
            throw new BadRequest("올바른 userId를 입력해주세요.");
        }

        String voteId = voteService.save(saveVoteRequest, userId);
        return new ResponseEntity(new BasicResponse(true, voteId ), HttpStatus.OK);
    }

    /**
     * 투표 조회 API
     * */
    @GetMapping("/{boardId}/{voteId}")
    public ResponseEntity getVote(@PathVariable Long boardId, @PathVariable String voteId, @RequestHeader Map<String, String> requestHeader){
        String userId = requestHeader.get("x-user-id");
        if(userId == null || userId.length() != 4){
            throw new BadRequest("올바른 userId를 입력해주세요.");
        }

        GetVoteResponse getVoteResponse = voteService.getOne(userId, boardId, voteId);
        return new ResponseEntity(new BasicResponse(true, getVoteResponse ), HttpStatus.OK);
    }

    /**
     * 투표 선택 API(투표하기)
     * */
    @PatchMapping()
    public ResponseEntity goVote(@RequestBody @Valid GoVoteRequest goVoteRequest, @RequestHeader Map<String, String> requestHeader){
        String userId = requestHeader.get("x-user-id");
        if(userId == null || userId.length() != 4){
            throw new BadRequest("올바른 userId를 입력해주세요.");
        }

        String msg = "";
        boolean check = voteService.goVote(goVoteRequest, userId);

        if(check){
            msg += "투표가 완료되었습니다.";
        } else {
            msg += "이미 투표한 아이디입니다.";
        }
        return new ResponseEntity(new BasicResponse(true, check, msg), HttpStatus.OK);
    }

    /**
     * 사용자 본인이 작성한 투표목록 보기
     * */
    @GetMapping("/myVote")
    public ResponseEntity myVote(@RequestHeader Map<String, String> requestHeader){
        String userId = requestHeader.get("x-user-id");
        if(userId == null || userId.length() != 4){
            throw new BadRequest("올바른 userId를 입력해주세요.");
        }

        List<MyVoteResponse> myVotes = voteService.myVotes(userId);
        return new ResponseEntity(new BasicResponse(true, myVotes ), HttpStatus.OK);
    }

    /**
     * 모든 투표목록 가져오기
     * */
    @GetMapping()
    public ResponseEntity allVote(){
        List<Vote> votes = voteService.getAll();
        return new ResponseEntity(new BasicResponse(true, votes ), HttpStatus.OK);
    }

    /**
     * 사용자가 해당 투표에 참여했는지 확인하기
     * */
    @GetMapping("/{voteId}")
    public ResponseEntity checkVote(@PathVariable String voteId, @RequestHeader Map<String, String> requestHeader){
        String userId = requestHeader.get("x-user-id");
        if(userId == null || userId.length() != 4){
            throw new BadRequest("올바른 userId를 입력해주세요.");
        }

        boolean checkVote = voteService.checkVote(userId, voteId);
        return new ResponseEntity(new BasicResponse(true, checkVote ), HttpStatus.OK);
    }
}

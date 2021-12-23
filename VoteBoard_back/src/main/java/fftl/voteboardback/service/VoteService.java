package fftl.voteboardback.service;

import fftl.voteboardback.advice.errors.BadRequest;
import fftl.voteboardback.entity.Vote;
import fftl.voteboardback.entity.VoteUser;
import fftl.voteboardback.entity.VoteItem;
import fftl.voteboardback.repository.VoteUserRepository;
import fftl.voteboardback.repository.VoteItemRepository;
import fftl.voteboardback.repository.VoteRepository;
import fftl.voteboardback.request.GoVoteRequest;
import fftl.voteboardback.request.SaveVoteItemRequest;
import fftl.voteboardback.request.SaveVoteRequest;
import fftl.voteboardback.response.GetVoteResponse;
import fftl.voteboardback.response.MyVoteResponse;
import fftl.voteboardback.response.VoteItemResponse;
import fftl.voteboardback.service.other.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RequiredArgsConstructor
@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final VoteItemRepository voteItemRepository;
    private final VoteUserRepository voteUserRepository;
    private final Utils utils;

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 투표 생성 API
     * */
    public String save(SaveVoteRequest saveVoteRequest, String userId){

        saveVoteRequest.setUserId(userId);
        saveVoteRequest.setVoteId(utils.createId(10));

        //DeadLine을 입력하지 않았을 때 현재시간 +24h 한 값을 DeadLine에 입력
        if(saveVoteRequest.getDeadLine() == null || saveVoteRequest.getDeadLine().equals(" ") || saveVoteRequest.getDeadLine().equals("")) {
            saveVoteRequest.setDeadLine(LocalDateTime.now().plusDays(1).format(dateTimeFormatter));

        //DeadLine을 입력할 때, 날짜 혹은 시간만 입력했을 경우
        } else if (saveVoteRequest.getDeadLine().length()<19){
            throw new BadRequest("날짜와 시간을 모두 입력해 주세요.");

        //현시간보다 이른 시간을 입력했을 경우
        } else if( LocalDateTime.now().isAfter(utils.timeChange(saveVoteRequest.getDeadLine())) ){
            throw new BadRequest("현재 시간보다 이른 시간은 입력할 수 없습니다.");
        }

        //투표를 먼저 생성합니다.
        Vote vote = voteRepository.save(saveVoteRequest.toEntity());

        //투표항목의 개수를 세어 투표항목을 생성해줍니다.
        for(int i=0; i<saveVoteRequest.getVoteItemRequest().size(); i++){
            @Valid SaveVoteItemRequest saveVoteItemRequest = saveVoteRequest.getVoteItemRequest().get(i);
            saveVoteItemRequest.setVoteId(vote.getVoteId());
            voteItemRepository.save(saveVoteItemRequest.toEntity());
        }

        return vote.getVoteId();
    }

    /**
     * 투표 조회 API
     * */
    public GetVoteResponse getOne(String userId, Long boardId, String voteId){
        System.out.println("service!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(voteId);
        Vote vote = voteRepository.findById(voteId).orElseThrow(() -> new BadRequest("잘못된 투표 id 입니다."));

        List<VoteItem> Items = voteItemRepository.findByVoteId(voteId);
        List<VoteItemResponse> voteItems = new ArrayList<>();
        boolean meVote = false;

        //투표항목과, 투표항목 별 투표 수의 데이터를 채워넣습니다.
        for(int i=0; i< Items.size(); i++){
            voteItems.add(new VoteItemResponse(Items.get(i).getVoteItemId(), Items.get(i).getContent(), Items.get(i).getCnt()));
        }

        //해당 투표에 참여했을 경우
        List<VoteUser> voteUsers = voteUserRepository.findByVoteId(voteId);
        for(int i=0; i<voteUsers.size(); i++){
            if(voteUsers.get(i).getUserId().equals(userId)){
                meVote = true;
            }
        }

        return new GetVoteResponse(vote.getUserId(), vote.getTitle(), vote.getDescription(), voteItems, vote.getDeadLine(), meVote, voteItems);
    }

    /**
     * 투표 선택 API(투표 진행하기)
     * */
    public boolean goVote(GoVoteRequest goVoteRequest, String userId){
        Vote vote = voteRepository.findById(goVoteRequest.getVoteId()).orElseThrow(() -> new BadRequest("잘못된 투표 id 입니다."));

        //DeadLine을 지난 시간에 요청을 했다면.
        if(utils.timeChange(vote.getDeadLine()).isBefore(LocalDateTime.now())){
            throw new BadRequest("이미 마감된 투표입니다.");
        }

        //이미 해당 투표에 참여했다면
        List<VoteUser> voteUsers = voteUserRepository.findByVoteId(goVoteRequest.getVoteId());
        for(int i=0; i<voteUsers.size(); i++){
            if(voteUsers.get(i).getUserId().equals(userId)){
                return false;
            }
        }

        VoteItem voteItem = voteItemRepository.findById(goVoteRequest.getVoteItemId()).orElseThrow(() -> new BadRequest("잘못된 투표항목 id 입니다."));
        voteItem.plusCnt(); //해당 투표항목의 투표 회수를 1 증가시킵니다.

        voteUserRepository.save(
                VoteUser.builder().
                        voteId(goVoteRequest.getVoteId()).
                        userId(userId).
                        build());

        return true;
    }

    /**
     * 로그인한 사용자가 생성한 투표를 가져옵니다.
     * */
    public List<MyVoteResponse> myVotes(String userId){
        List<Vote> votes = voteRepository.findByUserId(userId);

        if(votes.size() <= 0){
            throw new BadRequest("해당 사용자가 생성한 투표가 존재하지 않습니다.");
        }

        List<MyVoteResponse> myVotes = new ArrayList<>();

        for(int i=0; i<votes.size(); i++){
            myVotes.add(new MyVoteResponse(
                    votes.get(i).getBoardId(),
                    votes.get(i).getVoteId(),
                    votes.get(i).getTitle(),
                    votes.get(i).getDeadLine(),
                    userId
            ));
        }

        return myVotes;
    }

    /**
     * 모든 투표를 가져옵니다.
     * */
    public List<Vote> getAll(){
        List<Vote> votes = voteRepository.findAll();
        return votes;
    }

    /**
     * 사용자의 투표참여 여부 확인합니다.
     * */
    public boolean checkVote(String userId, String voteId){
        List<VoteUser> voteUsers = voteUserRepository.findByUserId(userId);

        if(voteUsers.size() <= 0){
            return true;
        }

        //이미 해당 투표에 참여했다!!
        for(int i=0; i<voteUsers.size(); i++){
            if(voteUsers.get(i).getVoteId().equals(voteId)){
                return false;
            }
        }

        return true;
    }
}

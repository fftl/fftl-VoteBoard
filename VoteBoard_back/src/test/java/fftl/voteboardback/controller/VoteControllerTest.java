package fftl.voteboardback.controller;

import fftl.voteboardback.repository.VoteRepository;
import fftl.voteboardback.service.VoteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class VoteControllerTest {

    @Autowired
    VoteService voteService;
    @Autowired
    VoteRepository voteRepository;

    @Autowired
    private MockMvc mvc;


    @Autowired
    public void setMvc(MockMvc mvc){
        this.mvc = mvc;
    }

    @DisplayName("투표 등록 테스트")
    @Test
    void saveVote() {
//        ResultActions actions = mvc.perform(
//            post("")
//        );
    }

    @Test
    void getVote() {
    }

    @Test
    void goVote() {
    }

    @Test
    void myVote() {
    }

    @Test
    void allVote() {
    }

    @Test
    void checkVote() {
    }
}
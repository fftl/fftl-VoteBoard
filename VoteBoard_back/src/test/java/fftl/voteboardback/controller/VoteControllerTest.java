package fftl.voteboardback.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fftl.voteboardback.advice.AdviceController;
import fftl.voteboardback.repository.VoteRepository;
import fftl.voteboardback.service.UserService;
import fftl.voteboardback.service.VoteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class VoteControllerTest {

    @Autowired
    VoteService voteService;

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    UserService userService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    public void setMvc(MockMvc mvc){
        this.mvc = MockMvcBuilders.standaloneSetup(new VoteController(voteService))
            .addFilters(new CharacterEncodingFilter("UTF-8", true))// utf-8 필터 추가
            .setControllerAdvice(AdviceController.class) //실제 Cotroller에서 발생하는 exception을 해
            .build();
    }

    @DisplayName("투표 등록 테스트")
    @Test
    void saveVote() throws Exception{
        JsonObject Item1 = new JsonObject();
        Item1.addProperty("content", "찬성");
        Item1.addProperty("voteId", "osie");
        JsonObject Item2 = new JsonObject();
        Item2.addProperty("content", "반대");
        Item2.addProperty("voteId", "osie");

        JsonArray Items = new JsonArray();
        Items.add(Item1);
        Items.add(Item2);

        JsonObject obj = new JsonObject();
        obj.addProperty("voteId","osie");
        obj.addProperty("boardId",2L);
        obj.addProperty("title","제목");
        obj.addProperty("description","내용");
        obj.add("voteItemRequest", Items);
        obj.addProperty("deadLine","2021-12-30 23:27:00");
        obj.addProperty("userId","fftl");

        ResultActions actions = mvc.perform(
            post("/vote")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .header("x-user-id", "fftl")
                    .content(String.valueOf(obj)));

        actions
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.data").isString())
            .andExpect((jsonPath("$.message").doesNotExist()));
    }

    @DisplayName("투표 조회하기 테스트")
    @Test
    void getVote() throws Exception{
        ResultActions actions = mvc.perform(
            get("/vote/1/abcd")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .header("x-user-id", "fftl"));

        actions.andDo(print())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").exists());
    }

    @DisplayName("투표하기 테스트")
    @Test
    void goVote() throws Exception{
        JsonObject obj = new JsonObject();
        obj.addProperty("boardId", 1L);
        obj.addProperty("voteId","abcd");
        obj.addProperty("voteItemId", 2L);

        ResultActions actions = mvc.perform(
            patch("/vote")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .header("x-user-id", "fftl")
                    .content(String.valueOf(obj)));

        actions.andDo(print())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").exists());
    }

    @DisplayName("내가 작성한 투표 가져오기 테스트")
    @Test
    void myVote() throws Exception{
        ResultActions actions = mvc.perform(
            get("/vote/myVote")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .header("x-user-id", "fftl"));

        actions.andDo(print())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").exists());
    }

    @DisplayName("모든 투표 목록 가져오기 테스트")
    @Test
    void allVote() throws Exception{
        ResultActions actions = mvc.perform(
            get("/vote")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .header("x-user-id", "fftl"));

        actions.andDo(print())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").exists());
    }

    @DisplayName("투표 여부 확인 테스트")
    @Test
    void checkVote() throws Exception{
        ResultActions actions = mvc.perform(
            get("/vote/abcd")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .header("x-user-id", "fftl"));

        actions.andDo(print())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").exists());
    }
}
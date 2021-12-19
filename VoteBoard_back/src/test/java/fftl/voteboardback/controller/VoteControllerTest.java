package fftl.voteboardback.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fftl.voteboardback.advice.AdviceController;
import fftl.voteboardback.repository.VoteRepository;
import fftl.voteboardback.service.UserService;
import fftl.voteboardback.service.VoteService;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        Item1.addProperty("voteId", "abcd");
        JsonObject Item2 = new JsonObject();
        Item2.addProperty("content", "반대");
        Item2.addProperty("voteId", "abcd");

        JsonArray Items = new JsonArray();
        Items.add(Item1);
        Items.add(Item2);

        JsonObject obj = new JsonObject();
        obj.addProperty("voteId","abcd");
        obj.addProperty("boardId",1L);
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

    @Test
    void getVote() {
        ResultActions actions = mvc.perform(
            post("/vote/1/abcd")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .header("x-user-id", "fftl");
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
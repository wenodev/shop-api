package com.weno.shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weno.shop.entity.Board;
import com.weno.shop.entity.BoardRepository;
import com.weno.shop.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardController.class)
class BoardControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private BoardService boardService;

    @MockBean
    private BoardRepository boardRepository;

    @Test
    void getAllBoardsTest() throws Exception {

        //given
        Board board = Board.builder()
                .title("title1")
                .content("content1")
                .build();

        List<Board> boardList = new ArrayList<>();
        boardList.add(board);

        given(boardService.getAllBoards()).willReturn(boardList);

        //when
        final ResultActions actions = mvc.perform(get("/api/v1/boards")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        //then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].title").value("title1"));
    }

    @Test
    void createBoardsTest() throws Exception {

        //given
        Board board = Board.builder()
                .id(1l)
                .title("title1")
                .content("content1")
                .build();

        ObjectMapper mapper = new ObjectMapper();

        //when
        final ResultActions actions = mvc.perform(post("/api/v1/boards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(board))
        );

        //then
        actions
                .andExpect(status().isCreated());
    }



}
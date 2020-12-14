package com.weno.shop.service;

import com.weno.shop.entity.Board;
import com.weno.shop.entity.BoardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
class BoardServiceTest {

    @Autowired
    private BoardRepository boardRepository;


    @AfterEach
    void cleanUpRepo(){
        boardRepository.deleteAll();
    }


    @Test
    void createBoardsTest(){
        //given
        Board board = Board.builder()
                .title("title1")
                .content("content1")
                .build();

        //when
        boardRepository.save(board);
        Board newBoard = boardRepository.findByTitle("title1").orElseThrow();

        //then
        assertEquals(board.getTitle(), newBoard.getTitle());

    }

    @Test
    void getAllBoardsTest(){
        //given
        Board board = Board.builder()
                .title("title1")
                .content("content1")
                .build();

        //When
        boardRepository.save(board);
        List<Board> boardList = boardRepository.findAll();

        //then
        assertEquals(board.getTitle(), boardList.get(0).getTitle());

    }

    @Test
    void getBoardByIdTest(){
        //given
        Board board = Board.builder()
                .title("title1")
                .content("content1")
                .build();

        //when
        boardRepository.save(board);
        Board newBoard = boardRepository.findById(1L).orElseThrow();

        //then
        assertEquals(board.getTitle(), newBoard.getTitle());

    }

    @Test
    void updateBoardById(){
        //given
        Board board = Board.builder()
                .title("title1")
                .content("content1")
                .build();

        //when
        boardRepository.save(board);

        Board newBoard = boardRepository.findByTitle("title1").orElseThrow();
        newBoard.updateBoards(Board.builder().title("title2").build());

        //then
        assertEquals("title2", newBoard.getTitle());

    }

    @Test
    void deleteBoardById(){
        //given
        Board board = Board.builder()
                .title("title1")
                .content("content1")
                .build();

        //when
        boardRepository.save(board);
    }




}
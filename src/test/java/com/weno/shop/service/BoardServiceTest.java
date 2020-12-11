package com.weno.shop.service;

import com.weno.shop.entity.Board;
import com.weno.shop.entity.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BoardServiceTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void createBoardsTest(){

        //given
        Board board = Board.builder()
                .title("title1")
                .content("content1")
                .build();


    }

}
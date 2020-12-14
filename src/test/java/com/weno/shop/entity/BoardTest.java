package com.weno.shop.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardTest {

    @Test
    void initBoardTest(){

        // given
        Board board = Board.builder()
                .title("title1")
                .content("content1")
                .build();

        //then
        assertEquals("title1", board.getTitle());
        assertEquals("content1", board.getContent());

    }

}
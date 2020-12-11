package com.weno.shop.controller;

import com.weno.shop.entity.Board;
import com.weno.shop.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards")
    public ResponseEntity getAllBoards(){
        List<Board> boardList = boardService.getAllBoards();
        return new ResponseEntity(boardList, HttpStatus.OK);
    }

    @PostMapping("/boards")
    public ResponseEntity addBoards(@RequestBody Board resource){
        boardService.createBoards(resource);
        return new ResponseEntity("success", HttpStatus.CREATED);
    }




}

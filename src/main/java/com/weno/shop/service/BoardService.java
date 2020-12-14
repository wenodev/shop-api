package com.weno.shop.service;

import com.weno.shop.entity.Board;
import com.weno.shop.entity.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Board createBoards(Board resource){
        return boardRepository.save(resource);
    }

    @Transactional
    public List<Board> getAllBoards(){
        return boardRepository.findAll();
    }

    @Transactional
    public Board getBoardById(Long id){
        return boardRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Board updateBoardById(Long id, Board resource){

        Board board = boardRepository.findById(id).orElseThrow();
        board.updateBoards(resource);

        return board;
    }

    @Transactional
    public void deleteBoardById(Long id){
        boardRepository.deleteById(id);
    }

}

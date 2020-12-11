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
    public List<Board> getAllBoards(){
        return boardRepository.findAll();
    }

    @Transactional
    public void createBoards(Board resource){
        boardRepository.save(resource);
    }


}

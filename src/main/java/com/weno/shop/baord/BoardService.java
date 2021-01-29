package com.weno.shop.baord;

import com.weno.shop.baord.Board;
import com.weno.shop.baord.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

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
        return boardRepository.findById(id).orElseThrow(() -> new ResourceAccessException("no board id : " + id));
    }

    @Transactional
    public Board updateBoardById(Long id, Board resource){

        Board board = boardRepository.findById(id).orElseThrow(() -> new ResourceAccessException("no board id : " + id));
        board.updateBoards(resource);

        return board;
    }

    @Transactional
    public void deleteBoardById(Long id){
        boardRepository.deleteById(id);
    }

}

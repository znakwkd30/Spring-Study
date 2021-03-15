package co.kr.paka.service;

import co.kr.paka.domain.Board;
import co.kr.paka.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> getList() {
        return boardRepository.getList();
    }

    public Board get(int boardSeq) {
        return boardRepository.get(boardSeq);
    }

    public Board save(Board board) {
        boardRepository.save(board);

        return boardRepository.get(board.getBoardSeq());
    }

    public void update(Board board) {
        boardRepository.update(board);
    }

    public void delete(int boardSeq) {
        boardRepository.delete(boardSeq);
    }
}

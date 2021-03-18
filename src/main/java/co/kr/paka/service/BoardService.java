package co.kr.paka.service;

import co.kr.paka.domain.Board;
import co.kr.paka.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> getList(String keyword) {
        return boardRepository.getList(keyword);
    }

    public Board get(int boardSeq) {
        return boardRepository.get(boardSeq);
    }

    public Board save(Board board) {
        boardRepository.save(board);

        return boardRepository.get(board.getBoardSeq());
    }

    public void saveList1(List<Board> list) {
        for (Board board : list) {
            boardRepository.save(board);
        }
    }

    public void saveList2(List<Board> boardList) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("boardList", boardList);
        boardRepository.saveList(paramMap);
    }

    public void update(Board board) {
        boardRepository.update(board);
    }

    public void delete(int boardSeq) {
        boardRepository.delete(boardSeq);
    }
}

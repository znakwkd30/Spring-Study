package co.kr.paka.repository;

import co.kr.paka.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BoardRepository {
    List<Board> getList(String keyword);

    Board get(int boardSeq);

    void save(Board board);

    void saveList(Map<String, Object> paramMap);

    void update(Board board);

    void delete(int boardSeq);
}

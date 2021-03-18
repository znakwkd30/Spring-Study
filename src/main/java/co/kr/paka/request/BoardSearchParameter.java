package co.kr.paka.request;

import co.kr.paka.domain.BoardType;
import lombok.Data;

import java.util.List;

@Data
public class BoardSearchParameter {
    private String keyword;
    private List<BoardType> boardTypes;
}

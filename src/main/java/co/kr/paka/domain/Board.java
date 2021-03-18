package co.kr.paka.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Board {

    private int boardSeq;
    private String title;
    private String content;
    private Date regDate;
    private BoardType boardType;

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

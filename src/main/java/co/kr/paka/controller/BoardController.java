package co.kr.paka.controller;

import co.kr.paka.domain.Board;
import co.kr.paka.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@Api(tags = "게시판 API")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping
    @ApiOperation(value = "전체 조회", notes = "게시물 전체 조회를 할 수 있습니다.")
    public List<Board> getList() {
        return boardService.getList();
    }

    @GetMapping("/{boardSeq}")
    @ApiOperation(value = "상세 조회", notes = "게시물 번호에 해당하는 상세 정보를 조회할 수 있습니다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1")
    })
    public Board get(@PathVariable int boardSeq) {
        return boardService.get(boardSeq);
    }

    @PostMapping("/save")
    @ApiOperation(value = "보드 생성", notes = "신규 게시물 생성")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "title", value = "게시글 제목", example = "테스트"),
        @ApiImplicitParam(name = "content", value = "게시글 내용", example = "테스트입니다.")
    })
    public Board save(Board board) {
        return boardService.save(board);
    }

    @PutMapping("/update")
    public void update(Board board) {
        boardService.update(board);
    }

    @DeleteMapping("/delete/{boardSeq}")
    public void delete(@PathVariable int boardSeq) {
        boardService.delete(boardSeq);
    }
}

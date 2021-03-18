package co.kr.paka.controller;

import co.kr.paka.configuration.exception.BaseException;
import co.kr.paka.configuration.http.BaseResponse;
import co.kr.paka.configuration.http.BaseResponseCode;
import co.kr.paka.domain.Board;
import co.kr.paka.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
    public BaseResponse<List<Board>> getList() {
        return new BaseResponse<>(boardService.getList());
    }

    @GetMapping("/{boardSeq}")
    @ApiOperation(value = "상세 조회", notes = "게시물 번호에 해당하는 상세 정보를 조회할 수 있습니다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1")
    })
    public BaseResponse<Board> get(@PathVariable int boardSeq) {
        Board board = boardService.get(boardSeq);

        if (board == null) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] { "게시물" });
        }

        return new BaseResponse<>(boardService.get(boardSeq));
    }

    @PostMapping("/save")
    @ApiOperation(value = "보드 생성", notes = "신규 게시물 생성")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "title", value = "게시글 제목", example = "테스트"),
        @ApiImplicitParam(name = "content", value = "게시글 내용", example = "테스트입니다.")
    })
    public BaseResponse<Board> save(Board board) {
        if (StringUtils.isEmpty(board.getTitle())) {
            throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] { "title", "제목" });
        }

        if (StringUtils.isEmpty(board.getContents())) {
            throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] { "contents", "내용" });
        }

        return new BaseResponse<>(boardService.save(board));
    }

    @PutMapping("/update")
    @ApiOperation(value = "보드 수정", notes = "게시물 번호에 해당하는 게시물의 정보를 수정할 수 있습니다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "title", value = "게시물 제목", example = "수정"),
        @ApiImplicitParam(name = "content", value = "게시물 내용", example = "수정합니다.")
    })
    public void update(Board board) {
        boardService.update(board);
    }

    @DeleteMapping("/delete/{boardSeq}")
    @ApiOperation(value = "보드 삭제", notes = "게시물 번호에 해당하는 게시물의 정보를 삭제합니다.")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1")
    })
    public void delete(@PathVariable int boardSeq) {
        boardService.delete(boardSeq);
    }
}

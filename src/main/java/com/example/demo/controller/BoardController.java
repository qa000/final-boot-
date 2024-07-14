package com.example.demo.controller;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentRepository commentRepository;

    // 특정 게시글 조회
    @GetMapping("/view/{bnum}")
    public ResponseEntity<BoardDTO> getBoardById(@PathVariable int bnum) {
        Board board = boardService.getBoardById(bnum);
        if (board == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBnum(board.getBnum());
        boardDTO.setTitle(board.getTitle());
        boardDTO.setWriter(board.getWriter());
        boardDTO.setContents(board.getContents());
        boardDTO.setFilename(board.getFilename());
        boardDTO.setLikes(board.getLikes() != null ? board.getLikes() : 0);
        boardDTO.setHit(board.getHit() != null ? board.getHit() : 0);
        boardDTO.setCommentCount(commentRepository.countByBoardnum(board.getBnum()));
        boardDTO.setRegdate(board.getRegdate());
        return ResponseEntity.ok(boardDTO);
    }

    // 게시글 생성
    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestPart("board") BoardDTO boardDTO, @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String uploadDir = uploadPath + "/";
            Path path = Paths.get(uploadDir + file.getOriginalFilename());
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());
            boardDTO.setFilename(file.getOriginalFilename());
        }
        return ResponseEntity.ok(boardService.createBoard(boardDTO, file));
    }

    // 게시글 삭제
    @DeleteMapping("/{bnum}")
    public ResponseEntity<Void> deleteBoard(@PathVariable int bnum) {
        boardService.deleteBoard(bnum);
        return ResponseEntity.noContent().build();
    }

    // 모든 게시글 조회 (내림차순)
    @GetMapping("/list")
    public ResponseEntity<List<BoardDTO>> getAllBoards() {
        List<BoardDTO> boards = boardService.getAllBoards();
        return ResponseEntity.ok(boards);
    }

    // 게시글 수정
    @PutMapping("/modify/{bnum}")
    public ResponseEntity<Board> modifyBoard(@PathVariable int bnum, @RequestPart("board") BoardDTO boardDTO, @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        Board existingBoard = boardService.getBoardById(bnum);
        if (existingBoard == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        if (file != null && !file.isEmpty()) {
            String uploadDir = uploadPath + "/";
            Path path = Paths.get(uploadDir + file.getOriginalFilename());
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());
            boardDTO.setFilename(file.getOriginalFilename());
        }

        return ResponseEntity.ok(boardService.updateBoard(bnum, boardDTO, file));
    }
}

package com.example.demo.service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;

    // 게시글 목록 조회
    public List<BoardDTO> getAllBoards() {
        List<Board> boards = boardRepository.findAllByOrderByBnumDesc();
        List<BoardDTO> boardDTOs = new ArrayList<>();
        for (Board board : boards) {
            BoardDTO dto = new BoardDTO();
            dto.setBnum(board.getBnum());
            dto.setTitle(board.getTitle());
            dto.setWriter(board.getWriter());
            dto.setContents(board.getContents());
            dto.setFilename(board.getFilename());
            dto.setLikes(board.getLikes() != null ? board.getLikes() : 0);
            dto.setHit(board.getHit() != null ? board.getHit() : 0);
            dto.setCommentCount(commentRepository.countByBoardnum(board.getBnum()));
            dto.setRegdate(board.getRegdate());
            boardDTOs.add(dto);
        }
        return boardDTOs;
    }

    // 게시글 조회
    public Board getBoardById(int bnum) {
        return boardRepository.findById(bnum).orElse(null);
    }

    // 게시글 생성
    public Board createBoard(BoardDTO boardDTO, MultipartFile file) throws IOException {
        Board board = new Board();
        board.setWriter(boardDTO.getWriter());
        board.setTitle(boardDTO.getTitle());
        board.setContents(boardDTO.getContents());
        board.setRegdate(LocalDateTime.now());

        if (file != null && !file.isEmpty()) {
            board.setFilename(boardDTO.getFilename());
            board.setFileData(file.getBytes());
        }

        return boardRepository.save(board);
    }

    // 게시글 삭제
    public void deleteBoard(int bnum) {
        boardRepository.deleteById(bnum);
    }

    public List<BoardDTO> getAllBoardDTOs() {
        List<Board> boards = boardRepository.findAll();
        List<BoardDTO> boardDTOs = new ArrayList<>();
        for (Board board : boards) {
            BoardDTO dto = new BoardDTO();
            dto.setBnum(board.getBnum());
            dto.setTitle(board.getTitle());
            dto.setWriter(board.getWriter());
            dto.setContents(board.getContents());
            dto.setFilename(board.getFilename());
            dto.setLikes(board.getLikes() != null ? board.getLikes() : 0);
            dto.setHit(board.getHit() != null ? board.getHit() : 0);
            dto.setCommentCount(commentRepository.countByBoardnum(board.getBnum()));
            dto.setRegdate(board.getRegdate());
            boardDTOs.add(dto);
        }
        return boardDTOs;
    }

    // 게시글 수정
    public Board updateBoard(int bnum, BoardDTO boardDTO, MultipartFile file) throws IOException {
        Optional<Board> optionalBoard = boardRepository.findById(bnum);
        if (!optionalBoard.isPresent()) {
            return null;
        }

        Board board = optionalBoard.get();
        board.setTitle(boardDTO.getTitle());
        board.setContents(boardDTO.getContents());

        if (file != null && !file.isEmpty()) {
            board.setFilename(file.getOriginalFilename());
            board.setFileData(file.getBytes());
        }

        return boardRepository.save(board);
    }
}

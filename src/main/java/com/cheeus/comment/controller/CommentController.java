package com.cheeus.comment.controller;

import com.cheeus.comment.dto.CommentDto;
import com.cheeus.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{boardId}")
    public List<CommentDto> getComments(@PathVariable("boardId") int boardId) {
        return commentService.findByBoardId(boardId);
    }

    @GetMapping("/")
    public List<CommentDto> getComments() {
        return commentService.findAll();
    }

    @PostMapping("/{id}")
    public void insertComment(@RequestBody CommentDto comment) {
        commentService.insert(comment);
    }

    @PutMapping("/{id}")
    public void updateComment(@PathVariable("id") int id, @RequestBody CommentDto comment) {
        comment.setId(id);
        commentService.update(comment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable("id") int id) {
        commentService.delete(id);
    }
}

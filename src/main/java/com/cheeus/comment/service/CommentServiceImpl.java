package com.cheeus.comment.service;

import com.cheeus.comment.dto.CommentDto;
import com.cheeus.comment.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<CommentDto> findAll(){
        return commentMapper.findAll();
    }

    @Override
    public List<CommentDto> findByBoardId(int boardId) {
        return commentMapper.findByBoardId(boardId);
    }

    @Override
    public void insert(CommentDto comment) {
        commentMapper.insert(comment);
    }

    @Override
    public void update(CommentDto comment) {
        commentMapper.update(comment);
    }

    @Override
    public void delete(int id) {
        commentMapper.delete(id);
    }
}

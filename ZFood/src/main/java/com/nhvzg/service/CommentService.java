package com.nhvzg.service;

import com.nhvzg.dao.CommentMapper;
import com.nhvzg.entity.Comment;
import com.nhvzg.tools.UUIDTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by NHVZG on 2018/2/27.
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;


    public void addComment(Comment comment){
        comment.setCommentId(UUIDTools.getPrimaryKey());
        commentMapper.insert(comment);
    }
}

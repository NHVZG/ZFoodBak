package com.nhvzg.dao;

import com.nhvzg.entity.Comment;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(String commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(String commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> selectOrder(String orderId);
}
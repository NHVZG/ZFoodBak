package com.nhvzg.controller.food;

import com.nhvzg.entity.Comment;
import com.nhvzg.service.CommentService;
import com.nhvzg.tools.JsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by NHVZG on 2018/2/27.
 */
@RestController
@CrossOrigin
public class CommentController {
    @Autowired
    private CommentService commentService;

   /* @GetMapping("/comment")
    //foodId
    public List<Comment> getCommentByFood(@RequestBody String json) throws IOException {
        Comment comment= JsonTools.GetObject(json,Comment.class);
        return commentService.getCommentByFood(comment.getOrderId());
    }*/

    @PostMapping("/comment/add")
    //foodId userId source comment
    public void addComment(@RequestBody String json) throws IOException {
        Comment comment=JsonTools.GetObject(json,Comment.class);
        commentService.addComment(comment);
    }
}

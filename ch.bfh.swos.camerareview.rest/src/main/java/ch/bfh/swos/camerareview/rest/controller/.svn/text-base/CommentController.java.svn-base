package ch.bfh.swos.camerareview.rest.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.bfh.swos.camerareview.model.Comment;
import ch.bfh.swos.camerareview.service.ICommentDao;

@Controller
@RequestMapping("/comments")
public class CommentController extends GenericController<Comment> {
	
	@Inject
	public CommentController(ICommentDao commentDao) {
		dao = commentDao;
	}
}

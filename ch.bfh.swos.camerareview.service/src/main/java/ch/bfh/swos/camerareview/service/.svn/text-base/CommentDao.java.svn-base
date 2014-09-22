package ch.bfh.swos.camerareview.service;

import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import ch.bfh.swos.camerareview.model.Comment;

@Named
public class CommentDao extends GenericJpaDao<Comment> implements ICommentDao {

	public CommentDao() {
		super(Comment.class);
	}
	
	@Override
	@Transactional
	public Comment update(Comment comment) {
		
		Comment updated = entityManager.merge(comment);
		updated.getTestReport().addComment(updated);
		return updated;
	}
	
	@Override
	@Transactional
	public void delete(Comment comment) {
		
		Comment updated = entityManager.merge(comment);
		updated.getTestReport().removeComment(updated);
		entityManager.remove(updated);
	}
}

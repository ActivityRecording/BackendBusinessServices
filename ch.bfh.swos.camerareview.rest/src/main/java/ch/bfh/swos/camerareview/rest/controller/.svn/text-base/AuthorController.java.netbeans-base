package ch.bfh.swos.camerareview.rest.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.bfh.swos.camerareview.model.Author;
import ch.bfh.swos.camerareview.service.IAuthorDao;

@Controller
@RequestMapping("/authors")
public class AuthorController extends GenericController<Author> {

	@Inject
	public AuthorController(IAuthorDao authorDao) {
		dao = authorDao;
	}
}

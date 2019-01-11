package sh.fable.endpoint;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sh.fable.book.Book;
import sh.fable.book.BookService;

@RestController
@RequestMapping(value = "/test")
public class Controller {
	

	@Autowired
	private BookService bookService ;
	
	@RequestMapping(value = { "/book01" }, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Book getBook() {
		return bookService.get(1);
	}
	 
	@RequestMapping(value = { "/book/{author}" }, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Book saveBook(@PathVariable("author") String author ){
		return bookService.save(author);
	}
}

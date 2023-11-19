package com.luv2code.srpingbootlibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.srpingbootlibrary.entity.Book;
import com.luv2code.srpingbootlibrary.service.BookService;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/books")
public class BookController {

	private BookService bookService;
	
	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/secure/currentloans/count")
	public int currentLoansCount(){
		String userEmail ="hoantt27@gmail.com";
		return bookService.currentLoansCount(userEmail);
	}

	@GetMapping("/secure/ischeckout/byuser")
	public Boolean checkoutBookByUser(@RequestParam Long bookId) {
		String userEmail = "hoantt27@gmail.com";
		return bookService.checkoutByUser(userEmail, bookId);
	}

	@PutMapping("/secure/checkout")
	public Book checkoutBook (@RequestParam Long bookId) throws Exception {
		String userEmail = "hoantt27@gmail.com";
		return bookService.checkoutBook(userEmail, bookId);
	}
}

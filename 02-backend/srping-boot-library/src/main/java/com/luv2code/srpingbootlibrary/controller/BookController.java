package com.luv2code.srpingbootlibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import com.luv2code.srpingbootlibrary.entity.Book;
import com.luv2code.srpingbootlibrary.service.BookService;
import com.luv2code.srpingbootlibrary.utils.ExtractJWT;
import com.luv2code.srpingbootlibrary.utils.LibraryCommonUtils;

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
	public int currentLoansCount(@RequestHeader(value = "Authorization") String token){
		String userEmail = ExtractJWT.payloadJWTExtraction(token, LibraryCommonUtils.SUBJWT);
		return bookService.currentLoansCount(userEmail);
	}

	@GetMapping("/secure/ischeckout/byuser")
	public Boolean checkoutBookByUser( @RequestHeader(value = "Authorization") String token,
																		@RequestParam Long bookId) {
		String userEmail = ExtractJWT.payloadJWTExtraction(token, LibraryCommonUtils.SUBJWT);
		return bookService.checkoutByUser(userEmail, bookId);
	}

	@PutMapping("/secure/checkout")
	public Book checkoutBook (@RequestHeader(value = "Authorization") String token, 
														@RequestParam Long bookId) throws Exception {
		String userEmail = ExtractJWT.payloadJWTExtraction(token, LibraryCommonUtils.SUBJWT);
		return bookService.checkoutBook(userEmail, bookId);
	}
}

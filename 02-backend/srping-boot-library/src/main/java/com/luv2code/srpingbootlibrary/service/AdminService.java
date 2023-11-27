package com.luv2code.srpingbootlibrary.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.srpingbootlibrary.entity.Book;

import com.luv2code.srpingbootlibrary.dao.BookRepository;
import com.luv2code.srpingbootlibrary.dao.CheckoutRepository;
import com.luv2code.srpingbootlibrary.dao.ReviewRepository;
import com.luv2code.srpingbootlibrary.requestmodels.AddBookRequest;

@Service
@Transactional
public class AdminService {

	private BookRepository bookRepository;
	private ReviewRepository reviewRepository;
	private CheckoutRepository checkoutRepository;

	@Autowired
	public AdminService(BookRepository bookRepository, ReviewRepository reviewRepository,
			CheckoutRepository checkoutRepository) {
		this.bookRepository = bookRepository;
		this.reviewRepository = reviewRepository;
		this.checkoutRepository = checkoutRepository;
	}

	public void increaseBookQuantity(Long bookId) throws Exception {

		Optional<Book> originalBook = bookRepository.findById(bookId);

		if (!originalBook.isPresent()) {
			throw new Exception("Book not found");
		}

		originalBook.get().setCopiesAvailable(originalBook.get().getCopiesAvailable() + 1);
		originalBook.get().setCopies(originalBook.get().getCopies() + 1);

		bookRepository.save(originalBook.get());
	}

	public void decreaseBookQuantity(Long bookId) throws Exception {

		Optional<Book> originalBook = bookRepository.findById(bookId);

		if (!originalBook.isPresent() || originalBook.get().getCopiesAvailable() <= 0
				|| originalBook.get().getCopies() <= 0) {
			throw new Exception("Book not found or Quantity locked");
		}

		originalBook.get().setCopiesAvailable(originalBook.get().getCopiesAvailable() - 1);
		originalBook.get().setCopies(originalBook.get().getCopies() - 1);

		bookRepository.save(originalBook.get());
	}

	public void postBook(AddBookRequest addBookRequest) {
		Book book = new Book();
		book.setTitle(addBookRequest.getTitle());
		book.setAuthor(addBookRequest.getAuthor());
		book.setDescription(addBookRequest.getDescription());
		book.setCopies(addBookRequest.getCopies());
		book.setCopiesAvailable(addBookRequest.getCopies());
		book.setCategory(addBookRequest.getCategory());
		book.setImg(addBookRequest.getImg());
		bookRepository.save(book);
	}

	public void deleteBook(Long bookId) throws Exception {

		Optional<Book> book = bookRepository.findById(bookId);

		if (!book.isPresent()) {
			throw new Exception("Book not found");
		}

		bookRepository.delete(book.get());
		reviewRepository.deleteAllByBookId(bookId);
		checkoutRepository.deleteAllByBookId(bookId);
	}
}

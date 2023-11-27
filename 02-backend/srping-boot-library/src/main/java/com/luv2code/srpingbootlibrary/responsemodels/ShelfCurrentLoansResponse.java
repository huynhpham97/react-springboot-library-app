package com.luv2code.srpingbootlibrary.responsemodels;

import com.luv2code.srpingbootlibrary.entity.Book;

import lombok.Data;

@Data
public class ShelfCurrentLoansResponse {

	private Book book;

	private int daysLeft;

	public ShelfCurrentLoansResponse() {
		super();
	}

	public ShelfCurrentLoansResponse(Book book, int daysLeft) {
		super();
		this.book = book;
		this.daysLeft = daysLeft;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getDaysLeft() {
		return daysLeft;
	}

	public void setDaysLeft(int daysLeft) {
		this.daysLeft = daysLeft;
	}
}

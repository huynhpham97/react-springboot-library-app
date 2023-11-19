package com.luv2code.srpingbootlibrary.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "review")
@Data
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "date_review")
	@CreationTimestamp
	private Date dateReview;

	@Column(name = "rating")
	private double rating;

	@Column(name = "book_id")
	private Long bookId;

	@Column(name = "review_description")
	private String reviewDescription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Date getDateReview() {
		return dateReview;
	}

	public void setDate(Date dateReview) {
		this.dateReview = dateReview;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getReviewDescription() {
		return reviewDescription;
	}

	public void setReviewDescription(String reviewDescription) {
		this.reviewDescription = reviewDescription;
	}

	public Review(Long id, String userEmail, Date dateReview, double rating, Long bookId, String reviewDescription) {
		super();
		this.id = id;
		this.userEmail = userEmail;
		this.dateReview = dateReview;
		this.rating = rating;
		this.bookId = bookId;
		this.reviewDescription = reviewDescription;
	}

	public Review() {
	}
}

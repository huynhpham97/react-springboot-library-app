package com.luv2code.srpingbootlibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.srpingbootlibrary.entity.Message;
import com.luv2code.srpingbootlibrary.requestmodels.AdminQuestionRequest;
import com.luv2code.srpingbootlibrary.service.MessagesService;
import com.luv2code.srpingbootlibrary.utils.ExtractJWT;
import com.luv2code.srpingbootlibrary.utils.LibraryCommonUtils;

@CrossOrigin("https://localhost:3000")
@RestController
@RequestMapping("/api/messages")
public class MessagesController {

	private MessagesService messagesService;

	@Autowired
	public MessagesController(MessagesService messagesService) {
		this.messagesService = messagesService;
	}

	@PostMapping("/secure/add/message")
	public void postMessage(@RequestHeader(value = "Authorization") String token, @RequestBody Message messageRequest) {
		String userEmail = ExtractJWT.payloadJWTExtraction(token, LibraryCommonUtils.SUBJWT);
		messagesService.postMessage(messageRequest, userEmail);
	}

	@PutMapping("/secure/admin/message")
	public void putMessage(@RequestHeader(value = "Authorization") String token,
			@RequestBody AdminQuestionRequest adminQuestionRequest) throws Exception {
		String userEmail = ExtractJWT.payloadJWTExtraction(token, LibraryCommonUtils.SUBJWT);
		String admin = ExtractJWT.payloadJWTExtraction(token, LibraryCommonUtils.ADMIN_JWT);
		if (admin == null || !admin.equals("admin")) {
			throw new Exception("Administration page only.");
		}
		messagesService.putMessage(adminQuestionRequest, userEmail);
	}
}

package com.hackthon.controller;

import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestHandler {

	@GetMapping(value = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
	public String ping() {
		return "Hello";
	}

	@PostMapping(value = "/handle-msg")
	public String twilloHook(@RequestBody String request) {
		return null;
	}
}

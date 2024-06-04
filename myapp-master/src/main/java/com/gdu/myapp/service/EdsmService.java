package com.gdu.myapp.service;

import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;

public interface EdsmService {
	void registerForm(HttpServletRequest request);
	void loadSampleList(HttpServletRequest request, Model model);
	void loadSample(HttpServletRequest request, Model model);
	void modifyForm(HttpServletRequest request);
	void removeForm(String sampleCode);
}

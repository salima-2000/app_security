package com.aseds.inpt.appsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller


public class FileInclusionController {
	/*
	@GetMapping("/fichiers/fileInclusion")
	public String acceder() {
		return "pages/fileInclusion";
	}
	@GetMapping("/fichiers/fileInclusion2")
	public String fileInclusion() {
		return "pages/fileInclusion2";
	}
*/
	@GetMapping("/fileInclusion")
	public String acceder() {
		return "pages/fileInclusion";
	}
	@GetMapping("/files/fileInclusion2")
	public String fileInclusion() {
		return "pages/fileInclusion2";
	}
}

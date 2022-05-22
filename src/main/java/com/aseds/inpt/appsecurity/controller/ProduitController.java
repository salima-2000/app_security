package com.aseds.inpt.appsecurity.controller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;




@Controller
public class ProduitController {
	
	
	@GetMapping("/produits/images")
	public String afficherImagesProduits() {
		return "pages/uploadImage";
	}
	@PostMapping("/produits/upload")
	public String uploaderImages(@RequestParam("file") MultipartFile file,HttpSession httpSession) throws IOException {
	    String folder ="C:\\Users\\Perfect PC\\Downloads\\appSecurity\\appSecurity\\src\\main\\resources\\uploads\\";
	    Path path=Paths.get(folder+ file.getOriginalFilename());
	    byte[] bytes = file.getBytes();
	    Files.write(path, bytes);
	    httpSession.setAttribute("etat", "succes");	    
		return "redirect:/produits/images";
	}
}

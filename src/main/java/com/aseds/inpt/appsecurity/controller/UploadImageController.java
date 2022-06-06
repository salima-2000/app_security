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
public class UploadImageController {
	
	@GetMapping("/images")
	public String afficherImagesProduits() {
		return "pages/uploadImage";
	}
	@PostMapping("/images/upload")
	public String uploaderImages(@RequestParam("file") MultipartFile file,HttpSession httpSession) throws IOException {
	    String folder ="C:\\Users\\Perfect PC\\Downloads\\appSecurity\\appSecurity\\src\\main\\resources\\uploads\\";
	    Path path=Paths.get(folder+ file.getOriginalFilename());
	    byte[] bytes = file.getBytes();
	  /*  try {
	    	 Files.write(path, bytes);
	 	    httpSession.setAttribute("etat", "succes");	
	    }catch (Exception e) {
	    	
	    	httpSession.setAttribute("etat", "erreur");	
	    }
	       */
	    String fileName = file.getOriginalFilename();
		String fe = "";
		if (fileName.contains(".")) {
			int i = fileName.lastIndexOf('.');
			fe = i > 0 ? fileName.substring(i + 1) : "";
		}
		if(fe.contains("png") ||fe.contains("PNG") || fe.contains("jpg") ) {
			 Files.write(path, bytes);
		 	    httpSession.setAttribute("etat", "succes");	
		}else {
			httpSession.setAttribute("etat", "erreur");	
		}
		return "redirect:/images";
	}
	
}

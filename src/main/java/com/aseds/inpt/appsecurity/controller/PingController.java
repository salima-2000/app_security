package com.aseds.inpt.appsecurity.controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;


@Controller
public class PingController {
	
	@GetMapping("/ping")
	public String ping() {
		
		return "pages/ping";
	}
	
	@PostMapping("/pinger")
     public String ping(@RequestParam String ip, Model model,HttpSession httpSession) {
	     String pingResult = "";

	        String pingCmd = "ping " + ip;
	        try {
	            Runtime r = Runtime.getRuntime();
	            Process p = r.exec(pingCmd);

	            BufferedReader in = new BufferedReader(new
	            InputStreamReader(p.getInputStream()));
	            String inputLine;
	            while ((inputLine = in.readLine()) != null) {
	                System.out.println(inputLine);
	                pingResult += inputLine;
	            }  httpSession.setAttribute("ping", pingResult);	
	            in.close();
               
	        } catch (IOException e) {
	            System.out.println(e);
	        }
	   
		return "redirect:/ping";
	}
}

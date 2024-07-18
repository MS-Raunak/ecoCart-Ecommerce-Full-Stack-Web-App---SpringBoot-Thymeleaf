package com.eco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/index")
	public String indexPage() {
		return "index";
	}
	
	@GetMapping("/signin")
	public String loginPage() {
		return "login";
	}
	       
	@GetMapping("/register")
	public String registerPage() {
		return "register";
	}
	
	@GetMapping("/products")
	public String productsPage() {
		return "product";
	}
	
	@GetMapping("/product")
	public String productInfo() {
		return "view_product";
	}
	
	
}

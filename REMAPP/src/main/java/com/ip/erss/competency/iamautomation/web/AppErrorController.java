package com.ip.erss.competency.iamautomation.web;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AppErrorController implements ErrorController{
	
	private static final String PATH = "/error";
	
	
    

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return PATH;
	}
	
	@RequestMapping(value = PATH)
	public String error() { 
	    return "error/error"; 
	  } 

}

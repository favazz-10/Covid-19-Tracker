package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Reports;
import com.example.demo.services.CoronaVirusDataServices;

@Controller
public class HomeController 
{
	@Autowired
	CoronaVirusDataServices crnService;
	
	  @GetMapping("/")
	    public String welcome() {
	        return "welcome"; 
	    }
	
	@GetMapping("/home")
	public String home(Model model)
	{
		List<Reports> allReport=crnService.getAllReport();
		int totalDeathsReported=allReport.stream().mapToInt(stat->stat.getTotalDeaths()).sum();
		model.addAttribute("LocationStates",allReport);
		model.addAttribute("totalDeathsReported",totalDeathsReported);
		return "home";
	}

}

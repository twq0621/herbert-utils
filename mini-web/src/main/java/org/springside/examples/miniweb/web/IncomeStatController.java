package org.springside.examples.miniweb.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/income")
public class IncomeStatController {

	@RequestMapping(value = { "list", "" })
	public String list(Model model) {
		return "income/cmdList";
	}
	
}

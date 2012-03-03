package org.springside.examples.miniweb.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/userCount")
public class UserCountStatController {

	@RequestMapping(value = { "list", "" })
	public String list(Model model) {
		return "userCount/ucList";
	}

}

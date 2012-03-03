package org.springside.examples.miniweb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.examples.miniweb.service.stat.CommonStatManager;

@Controller
@RequestMapping(value = "/common")
public class CommonStatController {

	@Autowired
	private CommonStatManager commonStatManager;

	@RequestMapping(value = { "list", "" })
	public String list(Model model) {
		return "common/pageList";
	}

	@RequestMapping(value = { "newRole" })
	public String viewNewRole(Model model) {
		return "common/newRoleInput";
	}

	@RequestMapping(value = { "passRookie" })
	public String viewPassRookie(Model model) {
		return "common/passRookieInput";
	}

	@RequestMapping(value = { "newRole/result" })
	public String viewNewRoleResult(
			@ModelAttribute("queryDate") String queryDate, Model model) {
		int newUserCount = commonStatManager.getNewRole(queryDate);
		model.addAttribute("queryDate", queryDate);
		model.addAttribute("newUserCount", newUserCount);
		return "common/newRoleResult";
	}

	@RequestMapping(value = { "passRookie/result" })
	public String viewPassRookieResult(
			@ModelAttribute("queryDate") String queryDate, Model model) {
		int passRookieCount = commonStatManager.getPassRookieCount(queryDate);
		model.addAttribute("queryDate", queryDate);
		model.addAttribute("passRookieCount", passRookieCount);
		return "common/passRookieResult";
	}

}

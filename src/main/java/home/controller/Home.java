package home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home {

	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/faqs")
	public String faqs() {
		return "faqs";
	}

	@RequestMapping(value = "/chat")
	public String chat() {
		return "chat";
	}
}

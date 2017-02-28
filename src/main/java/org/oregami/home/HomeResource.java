package org.oregami.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeResource {

	public HomeResource() {
	}

    @GetMapping(value = "/")
	public String list(Model model) {
        return "index";
	}

}

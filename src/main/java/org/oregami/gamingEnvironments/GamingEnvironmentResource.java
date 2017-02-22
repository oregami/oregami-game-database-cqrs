package org.oregami.gamingEnvironments;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class GamingEnvironmentResource {

	public GamingEnvironmentResource() {
	}

    @RequestMapping(value = "/gamingEnvironments", method = RequestMethod.GET)
	public String list(Model model) {
        return "gamingEnvironments/list";
	}

}

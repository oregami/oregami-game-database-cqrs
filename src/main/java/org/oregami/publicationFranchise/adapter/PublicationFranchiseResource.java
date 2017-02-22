package org.oregami.publicationFranchise.adapter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class PublicationFranchiseResource {

	public PublicationFranchiseResource() {
	}

    @RequestMapping(value = "/publicationFranchise", method = RequestMethod.GET)
	public String list(Model model) {
        return "publicationFranchise/list";
	}

}

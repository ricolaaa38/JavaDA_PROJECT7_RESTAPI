package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * HomeController class for handling requests to the home page and admin home page.
 * It provides methods to render the home view and redirect to the bid list.
 */
@Controller
public class HomeController
{

	/**
	 * Displays the home page.
	 *
	 * @param model the model to add attributes for the view
	 * @return the name of the view to render
	 */
	@RequestMapping("/")
	public String home(Model model)
	{
		return "home";
	}

	/**
	 * Redirects to the admin home page.
	 *
	 * @param model the model to add attributes for the view
	 * @return a redirect to the bid list
	 */
	@RequestMapping("/admin/home")
	public String adminHome(Model model)
	{
		return "redirect:/bidList/list";
	}


}

package it.objectmethod.worlddb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.objectmethod.worlddb.dao.IDaoCity;
import it.objectmethod.worlddb.dao.IDaoCountry;
import it.objectmethod.worlddb.domain.Country;

@Controller
public class HomeController {

	@Autowired
	IDaoCity iDaoCity;

	@Autowired
	IDaoCountry iDaoCountry;

	@GetMapping("/login")
	public String prepareLogin(HttpServletResponse resp) throws IOException {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam("username") String username, HttpSession session, ModelMap map) {

		String nextPage = null;
		if (username.isBlank() || username.isEmpty()) {
			nextPage = "login";
			map.addAttribute("loginErrorMessage", "Username Non Valido");
		} else {
			nextPage = "home";
			session.setAttribute("username", username);
		}
		return nextPage;
	}

	@GetMapping("/home")
	public String getBackToHome() {
		return "home";
	}

	@GetMapping("/cityInfo")
	public String cityInfo() {
		return "cityInfo";
	}

	@GetMapping("/countryInfo")
	public String countryInfo() {
		return "countryInfo";
	}

	@GetMapping("/searchByContinents")
	public String searchByContinents(ModelMap map) {
		List<String> continentsList = new ArrayList<String>();
		continentsList = iDaoCountry.getContinents();
		map.addAttribute("continentsList", continentsList);
		return "searchByContinents";
	}

	@GetMapping("/{selectedContinent}/countries")
	public String searchByContinents(@PathVariable("selectedContinent") String selectedContinent, HttpSession session,
			ModelMap map) {
		List<Country> countriesList = iDaoCountry.getCountriesByContinent(selectedContinent);
		session.setAttribute("selectedContinent", selectedContinent);
		map.addAttribute("countriesList", countriesList);
		return "searchByCountry";
	}
}
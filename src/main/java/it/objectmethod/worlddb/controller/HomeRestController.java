package it.objectmethod.worlddb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.worlddb.dao.IDaoCity;
import it.objectmethod.worlddb.dao.IDaoCountry;
import it.objectmethod.worlddb.domain.City;
import it.objectmethod.worlddb.domain.Country;

@RestController
public class HomeRestController {
	@Autowired
	IDaoCity iDaoCity;

	@Autowired
	IDaoCountry iDaoCountry;

	@PostMapping("/login")
	public String login(@RequestParam("username") String username, HttpServletResponse response) {

		if (username.isBlank()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		return username;
	}

	@GetMapping("/cityInfo")
	public List<City> citySearch(@RequestParam("cityName") String cityName, HttpServletResponse response) {
		List<City> citiesList = null;
		if (cityName == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} else {
			citiesList = iDaoCity.getCityByName(cityName);
			if (citiesList.size() == 0) {
				response.setStatus(204);
			}
		}
		return citiesList;
	}

	@GetMapping("/countryInfo")
	public List<Country> countrySearch(@RequestParam("countryName") String countryName,
			@RequestParam("continentName") String continentName, HttpServletResponse response) {
		List<Country> countriesList = null;
		if (countryName.isBlank()) {
			countryName = "";
		}
		if (continentName.isBlank()) {
			continentName = "";
		}
		countriesList = iDaoCountry.getCountriesByNameAndContinent('%' + countryName + '%', '%' + continentName + '%');
		if (countriesList.size() == 0) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
		return countriesList;
	}

	@GetMapping("/{countryCode}/cities")
	public List<City> searchByCountry(@PathVariable("countryCode") String selectedCountryCode,
			HttpServletResponse response) {
		List<City> citiesList = null;
		if (selectedCountryCode == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} else {
			citiesList = iDaoCity.getCitiesByCountryCode(selectedCountryCode);
			if (citiesList.size() == 0) {
				response.setStatus(204);
			}
		}
		return citiesList;
	}

	@GetMapping("/searchByContinents")
	public List<String> searchByContinents() {
		List<String> continentsList = new ArrayList<String>();
		continentsList = iDaoCountry.getContinents();
		return continentsList;
	}

	@GetMapping("/{selectedContinent}/countries")
	public List<Country> searchByContinents(@PathVariable("selectedContinent") String selectedContinent,
			HttpServletResponse response) {
		List<Country> countriesList = null;
		if (selectedContinent == null) {
			response.setStatus(400);
		} else {
			countriesList = iDaoCountry.getCountriesByContinent(selectedContinent);
			if (countriesList.size() == 0) {
				response.setStatus(204);
			}
		}
		return countriesList;
	}
}

package it.objectmethod.worlddb.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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

	@PostMapping("/cityInfo")
	public List<City> citySearch(@RequestParam("cityName") String cityName) {
		List<City> citiesList = null;
		citiesList = iDaoCity.getCityByName(cityName);
		return citiesList;
	}

	@PostMapping("/countryInfo")
	public List<Country> countrySearch(@RequestParam("countryName") String countryName,
			@RequestParam("continentName") String continentName) {
		List<Country> countriesList = null;
		if (countryName.isBlank())
			countryName = "";
		if (continentName.isBlank())
			continentName = "";
		countriesList = iDaoCountry.getCountriesByNameAndContinent('%' + countryName + '%', '%' + continentName + '%');
		return countriesList;
	}

	@GetMapping("/{country.code}/cities")
	public List<City> searchByCountry(@PathVariable("country.code") String selectedCountryCode, HttpSession session) {

		List<City> citiesList = iDaoCity.getCitiesByCountryCode(selectedCountryCode);
		session.setAttribute("selectedCountryCode", selectedCountryCode);
		return citiesList;
	}
}

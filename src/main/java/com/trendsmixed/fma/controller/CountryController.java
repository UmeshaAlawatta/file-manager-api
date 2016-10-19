package com.trendsmixed.fma.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.trendsmixed.fma.entity.AppSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trendsmixed.fma.entity.Country;
import com.trendsmixed.fma.jsonView.CountryView;
import com.trendsmixed.fma.service.AppSessionService;
import com.trendsmixed.fma.service.CountryService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@CrossOrigin
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private AppSessionService appSessionService;
    @Autowired
    private CountryService countryService;

    @JsonView(CountryView.All.class)
    @GetMapping
    public List<Country> findAll() {
        return countryService.findAll();
    }

    @PostMapping
    public Country save(@RequestBody Country country, @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {
        AppSession appSession = appSessionService.findOne(email);
        if (appSession == null) {
            throw new Error("Unauthorized access");
        } else {
            try {
                country = countryService.save(country);
                return country;

            } catch (Throwable e) {
                while (e.getCause() != null) {
                    e = e.getCause();
                }
                throw new Error(e.getMessage());
            }
        }
    }

    @GetMapping("/{id}")
    public Country findOne(@PathVariable("id") int id) {
        return countryService.findOne(id);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable int id) {
        countryService.delete(id);
        return "Deleted";

    }

    @PutMapping("/{id}")
    public Country updateCustomer(@PathVariable int id, @RequestBody Country country) {
        country.setId(id);
        country = countryService.save(country);
        return country;
    }

}

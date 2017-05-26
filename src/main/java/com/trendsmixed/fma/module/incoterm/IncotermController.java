package com.trendsmixed.fma.module.incoterm;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trendsmixed.fma.entity.Incoterm;
import com.trendsmixed.fma.module.incoterm.IncotermView;
import com.trendsmixed.fma.module.appsession.AppSessionService;
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
@RequestMapping("/incoterms")
public class IncotermController {

    @Autowired
    private AppSessionService appSessionService;
    @Autowired
    private IncotermService incotermService;

    @JsonView(IncotermView.All.class)
    @PostMapping
    public Incoterm save(@RequestBody Incoterm incoterm, @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {
        appSessionService.isValid(email, request);
        try {
            incoterm = incotermService.save(incoterm);
            return incoterm;

        } catch (Throwable e) {
            while (e.getCause() != null) {
                e = e.getCause();
            }
            throw new Error(e.getMessage());
        }
    }

    @JsonView(IncotermView.All.class)
    @GetMapping
    public List<Incoterm> findAll() {
        return incotermService.findAll();
    }

    @GetMapping("/{id}")
    public Incoterm findOne(@PathVariable("id") int id) {
        return incotermService.findOne(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id, @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {
        appSessionService.isValid(email, request);
        incotermService.delete(id);

    }

    @PutMapping("/{id}")
    public Incoterm updateCustomer(@PathVariable int id, @RequestBody Incoterm incoterm, @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {
        appSessionService.isValid(email, request);
        incoterm.setId(id);
        incoterm = incotermService.save(incoterm);
        return incoterm;
    }
}
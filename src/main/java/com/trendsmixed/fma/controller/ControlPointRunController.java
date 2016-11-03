package com.trendsmixed.fma.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.trendsmixed.fma.entity.AppSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trendsmixed.fma.entity.ControlPointRun;
import com.trendsmixed.fma.jsonView.ControlPointRunView;
import com.trendsmixed.fma.service.AppSessionService;
import com.trendsmixed.fma.service.ControlPointRunService;
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
@RequestMapping("/controlPointRuns")
public class ControlPointRunController {

    @Autowired
    private AppSessionService appSessionService;
    @Autowired
    private ControlPointRunService controlPointRunService;

    
    @GetMapping
    public List<ControlPointRun> findAll() {
        return controlPointRunService.findAll();
    }

    
    @PostMapping
    public ControlPointRun save(@RequestBody ControlPointRun controlPointRun, @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {
        appSessionService.isValid(email, request);
        try {
            controlPointRun = controlPointRunService.save(controlPointRun);
            return controlPointRun;

        } catch (Throwable e) {
            while (e.getCause() != null) {
                e = e.getCause();
            }
            throw new Error(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ControlPointRun findOne(@PathVariable("id") int id) {
        return controlPointRunService.findOne(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id, @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {
        appSessionService.isValid(email, request);
        controlPointRunService.delete(id);

    }

    @PutMapping("/{id}")
    public ControlPointRun updateCustomer(@PathVariable int id, @RequestBody ControlPointRun controlPointRun, @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {
        appSessionService.isValid(email, request);
        controlPointRun.setId(id);
        controlPointRun = controlPointRunService.save(controlPointRun);
        return controlPointRun;
    }

}
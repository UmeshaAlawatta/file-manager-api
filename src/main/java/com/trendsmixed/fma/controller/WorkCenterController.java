package com.trendsmixed.fma.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.trendsmixed.fma.entity.AppSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trendsmixed.fma.entity.WorkCenter;
import com.trendsmixed.fma.jsonView.WorkCenterView;
import com.trendsmixed.fma.service.AppSessionService;
import com.trendsmixed.fma.service.WorkCenterService;
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
@RequestMapping("/workCenters")
public class WorkCenterController {

    @Autowired
    private AppSessionService appSessionService;
    @Autowired
    private WorkCenterService workCenterService;

    @GetMapping
    @JsonView(WorkCenterView.AllAndCostCenterAll.class)
    public List<WorkCenter> findAll() {
        return workCenterService.findAll();
    }

    @PostMapping
    public WorkCenter save(@RequestBody WorkCenter workCenter, @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {
        appSessionService.isValid(email, request);
        try {
            workCenter = workCenterService.save(workCenter);
            return workCenter;

        } catch (Throwable e) {
            while (e.getCause() != null) {
                e = e.getCause();
            }
            throw new Error(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public WorkCenter findOne(@PathVariable("id") int id) {
        return workCenterService.findOne(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id, @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {
        appSessionService.isValid(email, request);
        workCenterService.delete(id);

    }

    @PutMapping("/{id}")
    public WorkCenter updateCustomer(@PathVariable int id, @RequestBody WorkCenter workCenter, @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {
        appSessionService.isValid(email, request);
        workCenter.setId(id);
        workCenter = workCenterService.save(workCenter);
        return workCenter;
    }

}

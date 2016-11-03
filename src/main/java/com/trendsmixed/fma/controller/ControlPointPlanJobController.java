package com.trendsmixed.fma.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.trendsmixed.fma.entity.AppSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trendsmixed.fma.entity.ControlPointPlanJob;
import com.trendsmixed.fma.jsonView.ControlPointPlanJobView;
import com.trendsmixed.fma.service.AppSessionService;
import com.trendsmixed.fma.service.ControlPointPlanJobService;
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
@RequestMapping("/controlPointPlanJobs")
public class ControlPointPlanJobController {

    @Autowired
    private AppSessionService appSessionService;
    @Autowired
    private ControlPointPlanJobService controlPointPlanJobService;

   
    @GetMapping
    public List<ControlPointPlanJob> findAll() {
        return controlPointPlanJobService.findAll();
    }


    @PostMapping
    public ControlPointPlanJob save(@RequestBody ControlPointPlanJob controlPointPlanJob, @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {
        appSessionService.isValid(email, request);
        try {
            controlPointPlanJob = controlPointPlanJobService.save(controlPointPlanJob);
            return controlPointPlanJob;

        } catch (Throwable e) {
            while (e.getCause() != null) {
                e = e.getCause();
            }
            throw new Error(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ControlPointPlanJob findOne(@PathVariable("id") int id) {
        return controlPointPlanJobService.findOne(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id, @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {
        appSessionService.isValid(email, request);
        controlPointPlanJobService.delete(id);

    }

    @PutMapping("/{id}")
    public ControlPointPlanJob updateCustomer(@PathVariable int id, @RequestBody ControlPointPlanJob controlPointPlanJob, @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {
        appSessionService.isValid(email, request);
        controlPointPlanJob.setId(id);
        controlPointPlanJob = controlPointPlanJobService.save(controlPointPlanJob);
        return controlPointPlanJob;
    }

}
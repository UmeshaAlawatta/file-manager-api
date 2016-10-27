package com.trendsmixed.fma.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.trendsmixed.fma.entity.AppSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trendsmixed.fma.entity.CostCenter;
import com.trendsmixed.fma.jsonView.CostCenterView;
import com.trendsmixed.fma.service.AppSessionService;
import com.trendsmixed.fma.service.CostCenterService;
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
@RequestMapping("/costCenters")
public class CostCenterController {

    @Autowired
    private AppSessionService appSessionService;
    @Autowired
    private CostCenterService costCenterService;

    @GetMapping
    @JsonView(CostCenterView.AllAndSectionAll.class)
    public List<CostCenter> findAll() {
        return costCenterService.findAll();
    }

    @PostMapping
    @JsonView(CostCenterView.AllAndSectionId.class)
    public CostCenter save(@RequestBody CostCenter costCenter, @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {
        appSessionService.isValid(email, request);
        try {
            costCenter = costCenterService.save(costCenter);
            return costCenter;

        } catch (Throwable e) {
            while (e.getCause() != null) {
                e = e.getCause();
            }
            throw new Error(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public CostCenter findOne(@PathVariable("id") int id) {
        return costCenterService.findOne(id);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable int id) {
        costCenterService.delete(id);
        return "Deleted";

    }

    @PutMapping("/{id}")
    public CostCenter updateCustomer(@PathVariable int id, @RequestBody CostCenter costCenter) {
        costCenter.setId(id);
        costCenter = costCenterService.save(costCenter);
        return costCenter;
    }

}

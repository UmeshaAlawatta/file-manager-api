package com.trendsmixed.fma.controller;

import com.trendsmixed.fma.entity.AppSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trendsmixed.fma.entity.LossReason;
import com.trendsmixed.fma.service.AppSessionService;
import com.trendsmixed.fma.service.LossReasonService;
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
@RequestMapping("/lossReasons")
public class LossReasonController {

    @Autowired
    private AppSessionService appSessionService;
    @Autowired
    private LossReasonService lossReasonService;

    @GetMapping
    public List<LossReason> findAll() {
        return lossReasonService.findAll();
    }

    @PostMapping
    public LossReason save(@RequestBody LossReason lossReason, @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {
        AppSession appSession = appSessionService.findOne(email);
        if (appSession == null) {
            throw new Error("Unauthorized access");
        } else {
            try {
                lossReason = lossReasonService.save(lossReason);
                return lossReason;

            } catch (Throwable e) {
                while (e.getCause() != null) {
                    e = e.getCause();
                }
                throw new Error(e.getMessage());
            }
        }
    }

    @GetMapping("/{id}")
    public LossReason findOne(@PathVariable("id") int id) {
        return lossReasonService.findOne(id);
    }

    @DeleteMapping(value = "/{id}")

    public String delete(@PathVariable int id) {
        lossReasonService.delete(id);
        return "Deleted";

    }

    @PutMapping("/{id}")
    public LossReason updateCustomer(@PathVariable int id, @RequestBody LossReason lossReason) {
        lossReason.setId(id);
        lossReason = lossReasonService.save(lossReason);
        return lossReason;
    }
}

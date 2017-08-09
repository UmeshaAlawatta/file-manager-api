package com.trendsmixed.fma.module.lossreason;

import com.fasterxml.jackson.annotation.JsonView;
import com.trendsmixed.fma.dao.Combo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trendsmixed.fma.module.losstype.LossType;
import com.trendsmixed.fma.module.appsession.AppSessionService;
import com.trendsmixed.fma.module.losstype.LossTypeService;
import com.trendsmixed.fma.utility.Page;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
    private LossReasonService service;
    @Autowired
    private LossTypeService lossTypeService;

    @JsonView(LossReasonView.AllAndLossTypeAll.class)
    @GetMapping
    public Iterable<LossReason> findAll() {
        return service.findAll();
    }

    @JsonView(LossReasonView.All.class)
    @GetMapping("/page")
    Page<LossReason> page(Pageable pageable) {
        return new Page<LossReason>(service.findAll(pageable));
    }

    @GetMapping("/combo")
    List<Combo> combo() {
        return service.getCombo();
    }

    @JsonView(LossReasonView.All.class)
    @PostMapping
    public LossReason save(@RequestBody LossReason lossReason, @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {
        appSessionService.isValid(email, request);
        try {
            lossReason = service.save(lossReason);
            return lossReason;

        } catch (Throwable e) {
            while (e.getCause() != null) {
                e = e.getCause();
            }
            throw new Error(e.getMessage());
        }
    }

    @PostMapping("/many")
    public void saveMany(@RequestBody List<LossReason> lossReasons, @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {

        appSessionService.isValid(email, request);
        try {
            for (LossReason lossReason : lossReasons) {
                LossType lossType = lossReason.getLossType();
                lossType = lossTypeService.findByCode(lossType.getCode());
                lossReason.setLossType(lossType);
                LossReason existingLossReason = service.findByCode(lossReason.getCode());
                if (existingLossReason != null) {
                    lossReason.setId(existingLossReason.getId());
                }
            }
            service.save(lossReasons);
        } catch (Throwable e) {
            while (e.getCause() != null) {
                e = e.getCause();
            }
            throw new Error(e.getMessage());
        }
    }

    @JsonView(LossReasonView.All.class)
    @GetMapping("/{id}")
    public LossReason findOne(@PathVariable("id") int id) {
        return service.findOne(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id, @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {
        appSessionService.isValid(email, request);
        service.delete(id);

    }

    @JsonView(LossReasonView.All.class)
    @PutMapping("/{id}")
    public LossReason updateCustomer(@PathVariable int id, @RequestBody LossReason lossReason, @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {
        appSessionService.isValid(email, request);
        lossReason.setId(id);
        lossReason = service.save(lossReason);
        return lossReason;
    }
}

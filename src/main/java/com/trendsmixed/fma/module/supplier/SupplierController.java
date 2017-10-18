package com.trendsmixed.fma.module.supplier;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.trendsmixed.fma.module.appsession.AppSessionService;
import com.trendsmixed.fma.utility.Page;

@RestController
@CrossOrigin
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private AppSessionService appSessionService;
    @Autowired
    private SupplierService service;

    @JsonView(SupplierView.All.class)
    @GetMapping
    public Iterable<Supplier> findAll() {
        return service.findAll();
    }

    @JsonView(SupplierView.AllAndCurrencyAllAndSupplierTypeAllAndDeliveryTermAllAndPaymentTermAll.class)
    @GetMapping("/page")
    Page<Supplier> page(Pageable pageable) {
        return new Page<>(service.findAll(pageable));
    }

    @JsonView(SupplierView.All.class)
    @PostMapping
    public Supplier save(@RequestBody Supplier supplier, @RequestHeader(value = "email", defaultValue = "") String email,
            HttpServletRequest request) {
        appSessionService.isValid(email, request);
        try {
            supplier = service.save(supplier);
            return supplier;
        } catch (Throwable e) {
            while (e.getCause() != null) {
                e = e.getCause();
            }
            throw new Error(e.getMessage());
        }
    }

    @PostMapping("/many")
    public void saveMany(@RequestBody List<Supplier> suppliers,
            @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {

        appSessionService.isValid(email, request);
        try {
            service.save(suppliers);
        } catch (Throwable e) {
            while (e.getCause() != null) {
                e = e.getCause();
            }
            throw new Error(e.getMessage());
        }
    }

    @JsonView(SupplierView.AllAndCurrencyAllAndSupplierTypeAllAndDeliveryTermAllAndPaymentTermAll.class)
    @GetMapping("/{id}")
    public Supplier findOne(@PathVariable("id") int id) {
        return service.findOne(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id, @RequestHeader(value = "email", defaultValue = "") String email,
            HttpServletRequest request) {
        appSessionService.isValid(email, request);
        service.delete(id);

    }

    @JsonView(SupplierView.All.class)
    @PutMapping("/{id}")
    public Supplier updateCustomer(@PathVariable int id, @RequestBody Supplier supplier,
            @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {
        appSessionService.isValid(email, request);
        supplier.setId(id);
        supplier = service.save(supplier);
        return supplier;
    }
}
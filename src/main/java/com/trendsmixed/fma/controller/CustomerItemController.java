package com.trendsmixed.fma.controller;

import com.trendsmixed.fma.entity.AppSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trendsmixed.fma.entity.CustomerItem;
import com.trendsmixed.fma.service.AppSessionService;
import com.trendsmixed.fma.service.CustomerItemService;
import java.util.List;
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
@RequestMapping("/customerItmes")
public class CustomerItemController {

    @Autowired
    private AppSessionService appSessionService;
    @Autowired
    private CustomerItemService customerItemsService;

    @GetMapping
    public List<CustomerItem> findAll() {
        return customerItemsService.findAll();
    }

    @PostMapping
    public CustomerItem save(@RequestBody CustomerItem customerItems, @RequestHeader(value = "email", defaultValue = "") String email) {
        AppSession appSession = appSessionService.findOne(email);
        if (appSession == null) {
            throw new Error("Unauthorized access");
        } else {
            try {
                customerItems = customerItemsService.save(customerItems);
                return customerItems;

            } catch (Throwable e) {
                while (e.getCause() != null) {
                    e = e.getCause();
                }
                throw new Error(e.getMessage());
            }
        }
    }

    @GetMapping("/{id}")
    public CustomerItem findOne(@PathVariable("id") int id) {
        return customerItemsService.findOne(id);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable int id) {
        customerItemsService.delete(id);
        return "Deleted";

    }

    @PutMapping("/{id}")
    public CustomerItem updateCustomer(@PathVariable int id, @RequestBody CustomerItem customerItems) {
        customerItems.setId(id);
        customerItems = customerItemsService.save(customerItems);
        return customerItems;
    }

}

package com.trendsmixed.fma.module.dispatchnote;

import com.fasterxml.jackson.annotation.JsonView;
import com.trendsmixed.fma.dao.Combo;
import com.trendsmixed.fma.module.customer.Customer;
import com.trendsmixed.fma.module.dispatch.Dispatch;
import com.trendsmixed.fma.module.loadingplan.LoadingPlan;
import com.trendsmixed.fma.module.loadingplan.LoadingPlanService;
import com.trendsmixed.fma.module.location.Location;
import com.trendsmixed.fma.utility.Format;
import com.trendsmixed.fma.utility.Page;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/dispatchNotes")
public class DispatchNoteController {

    
    private final DispatchNoteService service;
    private final LoadingPlanService loadingPlanService;

    @JsonView(DispatchNoteView.AllAndCustomerAndLocation.class)
    @GetMapping
    public Iterable<DispatchNote> findAll() {
        return service.findAll();
    }

    @JsonView(DispatchNoteView.AllAndLoadingPlanAndLoadingPlanItemAndDispatchScheduleAndJobAndItemAndSalesOrderItemAndSalesOrderAndCustomerItemAndPackagingSpecificationAndPortOfLoadingAndContainerSizeAndAddressAndCountryAndPortAndCustomerAndLocation.class)
    @GetMapping("/page")
    Page<DispatchNote> page(Pageable pageable) {
        return new Page<>(service.findAll(pageable));

    }
    @JsonView(DispatchNoteView.AllAndLoadingPlanAndLoadingPlanItemAndDispatchScheduleAndJobAndItemAndSalesOrderItemAndSalesOrderAndCustomerItemAndPackagingSpecificationAndPortOfLoadingAndContainerSizeAndAddressAndCountryAndPortAndCustomerAndLocation.class)
    @GetMapping(value = "/dispatchNoteDurationPage", params = {"startDate", "endDate"})
    public Page<DispatchNote> dispatchNoteDurationPage(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, Pageable pageable) throws ParseException {
        return new Page(service.findByDispatchDateBetween(Format.yyyy_MM_dd.parse(startDate), Format.yyyy_MM_dd.parse(endDate), pageable));
    }
    @JsonView(DispatchNoteView.AllAndLoadingPlanAndLoadingPlanItemAndDispatchScheduleAndJobAndItemAndSalesOrderItemAndSalesOrderAndCustomerItemAndPackagingSpecificationAndPortOfLoadingAndContainerSizeAndAddressAndCountryAndPortAndCustomerAndLocation.class)
    @GetMapping(value = "/dispatchDateAndLocationPage", params = {"dispatchDate", "location"})
    public Page<DispatchNote> dispatchDateAndLocationPage(@RequestParam("dispatchDate") String dispatchDate, @RequestParam("location") String location, Pageable pageable) throws ParseException {
        return new Page(service.findByDispatchDateAndLocation(Format.yyyy_MM_dd.parse(dispatchDate), new Location(Integer.valueOf(location)), pageable));
    }
    @JsonView(DispatchNoteView.AllAndLoadingPlanAndLoadingPlanItemAndDispatchScheduleAndJobAndItemAndSalesOrderItemAndSalesOrderAndCustomerItemAndPackagingSpecificationAndPortOfLoadingAndContainerSizeAndAddressAndCountryAndPortAndCustomerAndLocation.class)
    @GetMapping(value = "/dispatchNoteDurationAndLocationPage", params = {"startDate", "endDate", "location"})
    public Page<DispatchNote> dispatchNoteDurationAndLocationPage(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, @RequestParam("location") String location, Pageable pageable) throws ParseException {
        return new Page(service.findByDispatchDateBetweenAndLocation(Format.yyyy_MM_dd.parse(startDate), Format.yyyy_MM_dd.parse(endDate), new Location(Integer.valueOf(location)), pageable));
    }
    @JsonView(DispatchNoteView.AllAndLoadingPlanAndLoadingPlanItemAndDispatchScheduleAndJobAndItemAndSalesOrderItemAndSalesOrderAndCustomerItemAndPackagingSpecificationAndPortOfLoadingAndContainerSizeAndAddressAndCountryAndPortAndCustomerAndLocation.class)
    @GetMapping(value = "/customerAndDispatchNoteDurationPage", params = {"customer", "startDate", "endDate"})
    public Page<DispatchNote> customerAndDispatchNoteDurationPage(@RequestParam("customer") String customer, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, Pageable pageable) throws ParseException {
        return new Page(service.findByCustomerAndDispatchDateBetween(new Customer(Integer.valueOf(customer)), Format.yyyy_MM_dd.parse(startDate), Format.yyyy_MM_dd.parse(endDate), pageable));
    }
    @JsonView(DispatchNoteView.AllAndLoadingPlanAndLoadingPlanItemAndDispatchScheduleAndJobAndItemAndSalesOrderItemAndSalesOrderAndCustomerItemAndPackagingSpecificationAndPortOfLoadingAndContainerSizeAndAddressAndCountryAndPortAndCustomerAndLocation.class)
    @GetMapping(value = "/customerAndDispatchDateAndLocationPage", params = {"customer", "dispatchDate", "location"})
    public Page<DispatchNote> customerAndDispatchDateAndLocationPage(@RequestParam("customer") String customer, @RequestParam("dispatchDate") String dispatchDate, @RequestParam("location") String location, Pageable pageable) throws ParseException {
        return new Page(service.findByCustomerAndDispatchDateAndLocation(new Customer(Integer.valueOf(customer)), Format.yyyy_MM_dd.parse(dispatchDate), new Location(Integer.valueOf(location)), pageable));
    }
    @JsonView(DispatchNoteView.AllAndLoadingPlanAndLoadingPlanItemAndDispatchScheduleAndJobAndItemAndSalesOrderItemAndSalesOrderAndCustomerItemAndPackagingSpecificationAndPortOfLoadingAndContainerSizeAndAddressAndCountryAndPortAndCustomerAndLocation.class)
    @GetMapping(value = "/customerAndDispatchNoteDurationAndLocationPage", params = {"customer", "startDate", "endDate", "location"})
    public Page<DispatchNote> customerAndDispatchNoteDurationAndLocationPage(@RequestParam("customer") String customer, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, @RequestParam("location") String location, Pageable pageable) throws ParseException {
        return new Page(service.findByCustomerAndDispatchDateBetweenAndLocation(new Customer(Integer.valueOf(customer)), Format.yyyy_MM_dd.parse(startDate), Format.yyyy_MM_dd.parse(endDate), new Location(Integer.valueOf(location)), pageable));
    }
    
    @JsonView(DispatchNoteView.AllAndLoadingPlanAndLoadingPlanItemAndDispatchScheduleAndJobAndItemAndSalesOrderItemAndSalesOrderAndCustomerItemAndPackagingSpecificationAndPortOfLoadingAndContainerSizeAndAddressAndCountryAndPortAndCustomerAndLocation.class)
    @GetMapping(value = "/dispatchDateAndCustomerPage", params = {"dispatchDate", "customer"})
    public Page<DispatchNote> dispatchDateAndCustomerPage(@RequestParam("dispatchDate") String dispatchDate, @RequestParam("customer") String customer, Pageable pageable) throws ParseException {
        return new Page(service.findByDispatchDateAndCustomer(Format.yyyy_MM_dd.parse(dispatchDate), new Customer(Integer.valueOf(customer)), pageable));
    }
    
    @JsonView(DispatchNoteView.AllAndLoadingPlanAndLoadingPlanItemAndDispatchScheduleAndJobAndItemAndSalesOrderItemAndSalesOrderAndCustomerItemAndPackagingSpecificationAndPortOfLoadingAndContainerSizeAndAddressAndCountryAndPortAndCustomerAndLocation.class)
    @GetMapping(value = "/dispatchNote")
    public Page<DispatchNote> getDispatchNote(
        @RequestParam(value = "customer", required = false, defaultValue = "0") String customer,
        @RequestParam(value = "startDate", required = false, defaultValue = "1970-01-01") String startDate,
        @RequestParam(value = "endDate", required = false, defaultValue = "2100-12-31") String endDate, 
        Pageable pageable) throws ParseException {
        Page<DispatchNote> page ;
        if(customer.equals("0") ){
            page = new Page(service.findByDispatchDateBetween(Format.yyyy_MM_dd.parse(startDate), Format.yyyy_MM_dd.parse(endDate), pageable));
        } 
        else {
            page = new Page(service.findByCustomerAndDispatchDateBetween(new Customer(Integer.valueOf(customer)), Format.yyyy_MM_dd.parse(startDate), Format.yyyy_MM_dd.parse(endDate), pageable));
        }
        return page;
    }

    @GetMapping("/combo")
    List<Combo> combo() {
        return service.getCombo();
    }

    @JsonView(DispatchNoteView.AllAndLoadingPlanAndLoadingPlanItemAndDispatchScheduleAndJobAndItemAndSalesOrderItemAndSalesOrderAndCustomerItemAndPackagingSpecificationAndPortOfLoadingAndContainerSizeAndAddress.class)
    @GetMapping("/customer/{id}")
    public Iterable<DispatchNote> findByCustomer(@PathVariable("id") int id) {
        return service.findByCustomer(new Customer(id));
    }

    @JsonView(DispatchNoteView.AllAndLoadingPlanAndLoadingPlanItemAndDispatchScheduleAndJobAndItemAndSalesOrderItemAndSalesOrderAndCustomerItemAndPackagingSpecificationAndPortOfLoadingAndContainerSizeAndAddress.class)
    @GetMapping("/customerAndInvoiceIsNull/{id}")
    public Iterable<DispatchNote> findByCustomerAndInvoiceIsNull(@PathVariable("id") int id) {
        return service.findByCustomerAndInvoiceIsNull(new Customer(id));
    }
    

    // @JsonView(DispatchNoteView.AllAndLoadingPlanAndLoadingPlanItemAndDispatchScheduleAndJobAndItemAndSalesOrderItemAndSalesOrderAndCustomerItemAndPackagingSpecificationAndPortOfLoadingAndContainerSizeAndAddressAndCountryAndPortAndCustomerAndLocation.class)
    // @GetMapping("/id/{id}")
    // public DispatchNote findById(@PathVariable("id") String id) {
    //     return service.findById(id);
    // }

    @JsonView(DispatchNoteView.AllAndCustomerAndLocation.class)
    @PostMapping
    public DispatchNote save(@RequestBody DispatchNote dispatchNote) {
        
        try {

            List<Dispatch> dispatches = dispatchNote.getDispatchList();
            if (dispatches != null) {
                for (Dispatch dispatch : dispatches) {
                    dispatch.setDispatchNote(dispatchNote);
                }
            }

            List<LoadingPlan> loadingPlansToUpdate = new ArrayList(); 
            List<LoadingPlan> loadingPlans = dispatchNote.getLoadingPlanList();
                      
                      if (loadingPlans != null) {
                          for (LoadingPlan loadingPlan : loadingPlans) {
                            LoadingPlan loadingPlanToUpdate = loadingPlanService.findOne(loadingPlan.getId());
                            loadingPlanToUpdate.setDispatchNote(dispatchNote);
                            loadingPlansToUpdate.add(loadingPlanToUpdate);
                          }
                          dispatchNote.setLoadingPlanList(loadingPlansToUpdate);
                     }
 
            dispatchNote = service.save(dispatchNote);
            return dispatchNote;

        } catch (Throwable e) {
            while (e.getCause() != null) {
                e = e.getCause();
            }
            throw new Error(e.getMessage());
        }
    }

    @JsonView(DispatchNoteView.AllAndCustomerAndLocation.class)
    @PostMapping("/release")
    public DispatchNote saveReleaseInformation(@RequestBody DispatchNote dispatchNote) {
        
        try {
            DispatchNote existingDispatchNote = service.findOne(dispatchNote.getId());
            existingDispatchNote.setRecipient(dispatchNote.getRecipient());
            existingDispatchNote.setContainerNumber(dispatchNote.getContainerNumber());
            existingDispatchNote.setVehicleNumber(dispatchNote.getVehicleNumber());
            existingDispatchNote.setDispatchReleaseTime(dispatchNote.getDispatchReleaseTime());
            existingDispatchNote.setLocation(dispatchNote.getLocation());
            return service.save(existingDispatchNote);

        } catch (Throwable e) {
            while (e.getCause() != null) {
                e = e.getCause();
            }
            throw new Error(e.getMessage());
        }
    }

    @PostMapping("/many")
    public void saveMany(@RequestBody List<DispatchNote> dispatchNotes) {

        
        try {
            service.save(dispatchNotes);
        } catch (Throwable e) {
            while (e.getCause() != null) {
                e = e.getCause();
            }
            throw new Error(e.getMessage());
        }
    }

    @JsonView(DispatchNoteView.AllAndLoadingPlanAndLoadingPlanItemAndInvoiceAndDispatchScheduleAndJobAndItemAndSalesOrderItemAndSalesOrderAndCustomerItemAndPackagingSpecificationAndPortOfLoadingAndContainerSizeAndAddressAndCountryAndPortAndCustomerAndLocation.class)
    @GetMapping("/{id}")
    public DispatchNote findOne(@PathVariable("id") int id) {
        return service.findOne(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id) {
        
        service.delete(id);

    }

    @JsonView(DispatchNoteView.AllAndCustomerAndLocation.class)
    @PutMapping("/{id}")
    public DispatchNote updateCustomer(@PathVariable int id, @RequestBody DispatchNote dispatchNote) {
        
        dispatchNote.setId(id);
        dispatchNote = service.save(dispatchNote);
        return dispatchNote;
    }

}

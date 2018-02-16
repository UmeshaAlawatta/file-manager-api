package com.trendsmixed.fma.module.exchangerate;

import com.fasterxml.jackson.annotation.JsonView;
import com.trendsmixed.fma.dao.Combo;
import com.trendsmixed.fma.module.appsession.AppSessionService;
import com.trendsmixed.fma.utility.Page;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/exchangeRates")
public class ExchangeRateController {

    private final AppSessionService appSessionService;
    private final ExchangeRateService service;

    @GetMapping
    @JsonView(ExchangeRateView.AllAndCurrencyAll.class)
    public Iterable<ExchangeRate> findAll() {
        return service.findAll();
    }

    @JsonView(ExchangeRateView.AllAndCurrencyAll.class)
    @GetMapping("/page")
    Page<ExchangeRate> page(Pageable pageable) {
        return new Page<ExchangeRate>(service.findAll(pageable));
    }

    @GetMapping("/combo")
    List<Combo> combo() {
        return service.getCombo();
    }

    @PostMapping
    @JsonView(ExchangeRateView.AllAndCurrencyAll.class)
    public ExchangeRate save(@RequestBody ExchangeRate exchangeRate, @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {
        appSessionService.isValid(email,request);
        try {
           exchangeRate = service.save(exchangeRate);
            return exchangeRate;

        } catch (Throwable e) {
            while (e.getCause() != null) {
                e = e.getCause();
            }
            throw new Error(e.getMessage());
        }
    }

    @PostMapping("/many")
    public void saveMany(@RequestBody List<ExchangeRate>exchangeRates, @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {

        appSessionService.isValid(email, request);
        try {
            // for (ExchangeRate exchangeRate :exchangeRates) {
            //    exchangeRate.setCode( exchangeRate.getCode().trim());
            //    exchangeRate.setName( exchangeRate.getName().trim());
            //     ExchangeRate existingExchangeRate = service.findByCode( exchangeRate.getCode());
            //     if (existingExchangeRate != null) {
            //        exchangeRate.setId(existingExchangeRate.getId());
            //     }
            //     Section section =exchangeRate.getCurrency();
            //     if (section != null) {
            //         section = sectionService.findByCode(section.getCode());
            //        exchangeRate.setSection(section);
            //     }
            // }
            service.save( exchangeRates);
        } catch (Throwable e) {
            while (e.getCause() != null) {
                e = e.getCause();
            }
            throw new Error(e.getMessage());
        }
    }

    @JsonView(ExchangeRateView.AllAndCurrencyAll.class)
    @GetMapping("/{id}")
    public ExchangeRate findOne(@PathVariable("id") int id) {
        return service.findOne(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);

    }

    @JsonView(ExchangeRateView.AllAndCurrencyAll.class)
    @PutMapping("/{id}")
    public ExchangeRate updateCustomer(@PathVariable int id, @RequestBody ExchangeRate exchangeRate) {
       exchangeRate.setId(id);
       exchangeRate = service.save(exchangeRate);
        return exchangeRate;
    }

}
package com.trendsmixed.fma.module.port;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;
import com.fasterxml.jackson.annotation.JsonView;
import com.trendsmixed.fma.dao.Combo;
import com.trendsmixed.fma.module.appsession.AppSessionService;
import com.trendsmixed.fma.utility.Page;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/ports")
public class PortController {

    private final AppSessionService appSessionService;
    private final PortService service;

    @JsonView(PortView.All.class)
    @GetMapping
    public Iterable<Port> findAll() {
        return service.findAll();
    }

    @JsonView(PortView.All.class)
    @GetMapping("/page")
    Page<Port> page(Pageable pageable) {
        return new Page<Port>(service.findAll(pageable));
    }

    @GetMapping("/combo")
    List<Combo> combo() {
        return service.getCombo();
    }

    @JsonView(PortView.All.class)
    @PostMapping
    public Port save(@RequestBody Port port, @RequestHeader(value = "email", defaultValue = "") String email,
            HttpServletRequest request) {
        appSessionService.isValid(email, request);
        try {
            port = service.save(port);
            return port;
        } catch (Throwable e) {
            while (e.getCause() != null) {
                e = e.getCause();
            }
            throw new Error(e.getMessage());
        }
    }

    @PostMapping("/many")
    public void saveMany(@RequestBody List<Port> ports,
            @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {

        appSessionService.isValid(email, request);
        try {
            service.save(ports);
        } catch (Throwable e) {
            while (e.getCause() != null) {
                e = e.getCause();
            }
            throw new Error(e.getMessage());
        }
    }

    @JsonView(PortView.All.class)
    @GetMapping("/{id}")
    public Port findOne(@PathVariable("id") int id) {
        return service.findOne(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id, @RequestHeader(value = "email", defaultValue = "") String email,
            HttpServletRequest request) {
        appSessionService.isValid(email, request);
        service.delete(id);

    }

    @JsonView(PortView.All.class)
    @PutMapping("/{id}")
    public Port updateCustomer(@PathVariable int id, @RequestBody Port port,
            @RequestHeader(value = "email", defaultValue = "") String email, HttpServletRequest request) {
        appSessionService.isValid(email, request);
        port.setId(id);
        port = service.save(port);
        return port;
    }
}

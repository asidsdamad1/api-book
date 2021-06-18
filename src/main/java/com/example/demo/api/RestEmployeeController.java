package com.example.demo.api;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.search.EmployeeSeachDto;
import com.example.demo.entity.Employee;
import com.example.demo.excel.EmployeeExcelExporter;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class RestEmployeeController {
    @Qualifier("employeeServiceImpl")
    @Autowired
    EmployeeService service;
    @Autowired
    EmployeeRepository repository;

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public ResponseEntity<Employee> insertData(@RequestBody EmployeeDto dto) {
        Employee value = service.insertData(dto);
        return ResponseEntity.ok().body(value);
    }

    @RequestMapping(path = "/employees", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeDto>> search(@RequestBody EmployeeSeachDto dto) {
        return ResponseEntity.ok().body(service.search(dto));
    }

    @RequestMapping(path = "/employees/{code}", method = RequestMethod.GET)
    public ResponseEntity<Employee> getEmployeeCode(@PathVariable String code) {
        Employee employee = repository.findByCode(code);
        return ResponseEntity.ok(employee);
    }

    @RequestMapping(path = "/employees/{code}", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> update(@RequestBody EmployeeDto dto) {
        return ResponseEntity.ok().body(service.update(dto));
    }

    @RequestMapping(path = "/employees/{code}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> delete(@PathVariable String code) {
        return ResponseEntity.ok().body(service.delete(code));
    }

    @RequestMapping(path = "/export/excel", method = RequestMethod.GET)
    public void exportToExcel(HttpServletResponse response) throws IOException {
        service.exportToExcel(response);
    }
}

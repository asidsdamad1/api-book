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


    @RequestMapping(value = "/employees/add", method = RequestMethod.POST)
    public ResponseEntity<Employee> insertData(@RequestBody EmployeeDto dto) {
        Employee value = service.insertData(dto);
        return ResponseEntity.ok().body(value);
    }
    /*
        http://localhost:8089/api/employees/add
        {
            "code": "000001",
            "name": "as",
            "email": "4@gmail.com",
            "phone": "092826732",
            "age": 21
        }
     */

    @RequestMapping(path = "/employees", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }
    /*
            http://localhost:8089/api/employees

     */

    @RequestMapping(path = "/employees/search", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeDto>> search(@RequestBody EmployeeSeachDto dto) {
        return ResponseEntity.ok().body(service.search(dto));
    }
    /*
            http://localhost:8089/api/employees/search
            {
                "code": "000001",
                "name": "as",
                "email": "4@gmail.com",
                "phone": "092826732",
                "age": 21
            }
         */

    @RequestMapping(path = "/employees/search/{code}", method = RequestMethod.GET)
    public ResponseEntity<Employee> getEmployeeCode(@PathVariable String code) {
        Employee employee = repository.findByCode(code);
        return ResponseEntity.ok(employee);
    }
    /*
            http://localhost:8089/api/employees/search/000001
    */

    @RequestMapping(path = "/employees/update/{code}", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> update(@RequestBody EmployeeDto dto) {
        return ResponseEntity.ok().body(service.update(dto));
    }
    /*
            http://localhost:8089/api/update
            {
                "code": "000001",
                "name": "hieu",
                "email": "hieu@gmail.com",
                "phone": "092826732",
                "age": 19
            }
     */

    @RequestMapping(path = "/employees/delete/{code}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> delete(@PathVariable String code) {
        return ResponseEntity.ok().body(service.delete(code));
    }
    /*
            http://localhost:8089/api/employees/delete/000001
    */

    @RequestMapping(path = "/export/excel", method = RequestMethod.GET)
    public void exportToExcel(HttpServletResponse response) throws IOException {
        service.exportToExcel(response);
    }
    /*
        http://localhost:8089/api/export/excel

     */

}

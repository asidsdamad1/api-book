package com.example.demo.rest;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.functiondto.EmployeeSeachDto;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/employees")
public class RestEmployeeController {

    @Autowired
    EmployeeService service;
    @Autowired
    EmployeeRepository repository;


    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<EmployeeDto> save(@RequestBody EmployeeDto dto) {
        EmployeeDto value = service.saveOrUpdate(dto, null);
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

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<List<EmployeeDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }
    /*
            http://localhost:8089/api/employees

     */

    @RequestMapping(path = "/search", method = RequestMethod.POST)
    public ResponseEntity<Page<EmployeeDto>> search(@RequestBody EmployeeSeachDto dto) {
        Page<EmployeeDto> result = service.searchByPage(dto);
        return new ResponseEntity<Page<EmployeeDto>>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
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

    @RequestMapping(path = "/getByCode/{code}", method = RequestMethod.POST)
    public ResponseEntity<EmployeeDto> getEmployeeByCode(@PathVariable String code) {
        EmployeeDto result = service.getByCode(code);
        return new ResponseEntity<EmployeeDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable UUID id) {
        EmployeeDto result = service.getEmployeeById(id);
        return new ResponseEntity<EmployeeDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
    /*
            http://localhost:8089/api/employees/000001
    */

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<EmployeeDto> update(@RequestBody EmployeeDto dto, @PathVariable("id") UUID id) {
        EmployeeDto result = service.saveOrUpdate(dto, id);
        return new ResponseEntity<EmployeeDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
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

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> delete(@PathVariable("id") UUID id) {
        return ResponseEntity.ok().body(service.delete(id));
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
    @RequestMapping(path = "/checkCode", method = RequestMethod.GET)
    public ResponseEntity<Boolean> checkCode(@RequestParam(value = "id", required = false) UUID id,
                                             @RequestParam("code") String code) {
        Boolean result = service.checkDuplicateCode(code, id);
        return new ResponseEntity<Boolean>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
    /*
        http://localhost:8089/api/export/excel

     */
}

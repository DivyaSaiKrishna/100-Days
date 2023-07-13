package com.Days.Java8API.Controller;

import com.Days.Java8API.Service.APIService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

        @GetMapping("/streamFilter")
        public ResponseEntity<String> response(){
                    new APIService().getGenderBasedEmployee();
            return new ResponseEntity<String>("Done", HttpStatus.OK);
        }

        @GetMapping("/predicate")
        public ResponseEntity<String> predicateResponse(){
            new APIService().setEmployeePredicate();
            return new ResponseEntity<String>("Done", HttpStatus.OK);
        }

    @GetMapping("/default")
    public ResponseEntity<String> defaultResponse(){
        APIService.EmployeeCheck employeeCheck = new APIService.EmployeeCheck();
        employeeCheck.printEmployee();
        return new ResponseEntity<String>("Done", HttpStatus.OK);
    }
    @GetMapping("/future")
    public ResponseEntity<String> futureResponse(){
       new APIService().completableFuture();
        return new ResponseEntity<String>("Done", HttpStatus.OK);
    }


}

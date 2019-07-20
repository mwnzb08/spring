package com.example.web;

import com.example.model.Coffee;
import com.example.service.CoffeeService;
import com.example.web.exception.ForValidException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
@RequestMapping(value = "/coffee")
public class CoffeeController {
    @Autowired
    private CoffeeService coffeeService;

    @GetMapping(value = "/",params = "!name")
    public List<Coffee> findAllOfCoffee(){
        return coffeeService.getAllCoffee();
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Coffee> getById(@PathVariable Long id) {
        Coffee coffee = coffeeService.getCoffee(id);
        log.info("Coffee {}:", coffee);
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(20, TimeUnit.HOURS)).body(coffee);
    }

    @DeleteMapping(value = "/{name}")
    private Boolean deleteCoffee(@PathVariable String name){
        coffeeService.DeleteName(name);
        return true;
    }

    @GetMapping(path = "/", params = "name")
    @ResponseBody
    public List<Coffee> getByName(@RequestParam String name) {
        return coffeeService.getCoffeeName(name);
    }

//    @PostMapping(value = "/",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ResponseStatus(HttpStatus.CREATED)
//    public Coffee addCoffeeByJson(@Valid @RequestBody  NewCoffeeRequest newCoffeeRequest, BindingResult result){
//        if (result.hasErrors()){
//            log.warn("error :{}" ,result.toString());
//            throw new ValidationException(result.toString());
//        }
//        return  coffeeService.saveSingle(newCoffeeRequest.getName(),);
//    }
//    @PostMapping(value = "/",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    @ResponseStatus(HttpStatus.CREATED)
//    public Coffee addCoffee(@Valid NewCoffeeRequest newCoffeeRequest, BindingResult result){
//        if (result.hasErrors()){
////            throw new ForValidException(result);
//            throw new ValidationException(result.toString());
//        }
//        return  coffeeService.saveSingle(newCoffeeRequest.getName(),);
//    }
//    @PostMapping(path = "/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.CREATED)
//    public Coffee addCoffeeWithoutBindingResult(@Valid   NewCoffeeRequest newCoffee) {
//        return coffeeService.saveSingle(newCoffee.getName(), newCoffee.getPrice());
//    }
//    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    @ResponseBody
//    @ResponseStatus(HttpStatus.CREATED)
//    public Coffee addJsonCoffeeWithoutBindingResult(@Valid @RequestBody  NewCoffeeRequest newCoffee) {
//        return coffeeService.saveSingle(newCoffee.getName(), newCoffee.getPrice());
//    }


    @PostMapping(path = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public List<Coffee> batchAddCoffee(@RequestParam("file") MultipartFile file) {
        List<Coffee> coffees = new ArrayList<>();
        if (!file.isEmpty()) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(
                        new InputStreamReader(file.getInputStream()));
                String str;
                while ((str = reader.readLine()) != null) {
                    String[] arr = StringUtils.split(str, " ");
                    if (arr != null && arr.length == 2) {
                        coffees.add(coffeeService.saveSingle(arr[0],
                                Long.valueOf(arr[1])));
                    }
                }
            } catch (IOException e) {
                log.error("exception", e);
            } finally {
                IOUtils.closeQuietly(reader);
            }
        }
        return coffees;
    }

}

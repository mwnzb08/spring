package com.mo.sss.controller;

import com.mo.sss.controller.ValidRequest.JsonAdd;
import com.mo.sss.model.Students;
import com.mo.sss.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class WebSssController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(path = "/",method = RequestMethod.GET)
    public List<Students> findAll(){
        return studentService.selectAll();
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public Optional<Students> findById(@PathVariable Long id){
        return studentService.selectById(id);
    }

    @RequestMapping(path = "/",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,produces =MediaType.APPLICATION_JSON_UTF8_VALUE )
    public Students insertByJson(@RequestBody @Valid JsonAdd jsonAdd){
        Students students =Students.builder().number(jsonAdd.getNumber()).name(jsonAdd.getName()).age(jsonAdd.getAge()).classes(jsonAdd.getClasses()).build();
        return studentService.save(students);
    }

    @RequestMapping(path = "/",method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE )
    public Students insertByUrl(@Valid JsonAdd jsonAdd){
        Students students =Students.builder().number(jsonAdd.getNumber()).name(jsonAdd.getName()).age(jsonAdd.getAge()).classes(jsonAdd.getClasses()).build();
        return studentService.save(students);
    }

    @RequestMapping(path = "/",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public List<Students> insertByData(@RequestParam("students") MultipartFile file) throws Exception{
        List<Students> students = new ArrayList<>();
        if (!file.isEmpty()){
            BufferedReader reader =null;
            reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String str;
            while((str=reader.readLine())!=null){
                log.info("{}",str);
                String[] arr = StringUtils.split(str,":");
                if (arr!=null&&arr.length==4){
                    Students students1 = Students.builder().number(Long.valueOf(arr[0])).name(arr[1]).age(Long.valueOf(arr[2])).classes(arr[3]).build();
                    students.add(studentService.save(students1));
                }
            }
        }
        else {
            log.error("ggsmd");
        }
        return students;
    }
}

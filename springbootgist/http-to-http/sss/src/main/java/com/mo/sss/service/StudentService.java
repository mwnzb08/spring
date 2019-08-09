package com.mo.sss.service;

import com.mo.sss.model.Students;
import com.mo.sss.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class StudentService {
    @Autowired
    private StudentsRepository studentsRepository;

    public List<Students> selectAll(){
        return studentsRepository.findAll();
    }

    public Optional<Students> selectById(Long id){
        return studentsRepository.findById(id);
    }

    public Students save(Students students){
        return studentsRepository.save(students);
    }

}

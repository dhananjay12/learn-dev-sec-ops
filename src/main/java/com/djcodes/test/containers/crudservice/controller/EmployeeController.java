package com.djcodes.test.containers.crudservice.controller;

import com.djcodes.test.containers.crudservice.controller.mapper.EmployeeMapper;
import com.djcodes.test.containers.crudservice.dto.EmployeeDTO;
import com.djcodes.test.containers.crudservice.exceptions.EntityNotFoundException;
import com.djcodes.test.containers.crudservice.exceptions.ValidationException;
import com.djcodes.test.containers.crudservice.persistence.EmployeeEntity;
import com.djcodes.test.containers.crudservice.persistence.EmployeeRepository;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable int id) {
        Optional<EmployeeEntity> result = employeeRepository.findById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok(EmployeeMapper.makeDTO(result.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO createCar(@Valid @RequestBody EmployeeDTO employeeDTO) {
        if(employeeDTO.getId()!=null){
            throw new ValidationException("Id should not be passed for creation");
        }
        EmployeeEntity employeeEntity = EmployeeMapper.makeEntity(employeeDTO);
        EmployeeEntity result = employeeRepository.save(employeeEntity);
        return EmployeeMapper.makeDTO(result);
    }

    @PutMapping("/{id}")
    public EmployeeDTO updateCar(
        @PathVariable int id, @Valid @RequestBody EmployeeDTO employeeDTO) throws EntityNotFoundException {
        if(employeeDTO.getId()!=null && !employeeDTO.getId().equals(id)){
            throw new ValidationException("Id in path and body does not match");
        }
        EmployeeEntity employeeEntity = findEmployeeById(id);
        employeeEntity.setFname(employeeDTO.getFname());
        employeeEntity.setLname(employeeDTO.getLname());
        employeeEntity.setEmail(employeeDTO.getEmail());
        EmployeeEntity result = employeeRepository.save(employeeEntity);
        return EmployeeMapper.makeDTO(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Optional<EmployeeEntity> result = employeeRepository.findById(id);
        if (result.isPresent()) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }


    private EmployeeEntity findEmployeeById(Integer id) throws EntityNotFoundException {
        return employeeRepository
            .findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + id));
    }

}

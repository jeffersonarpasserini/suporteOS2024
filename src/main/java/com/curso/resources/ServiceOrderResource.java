package com.curso.resources;

import com.curso.domains.ServiceOrder;
import com.curso.domains.Technician;
import com.curso.domains.dtos.ServiceOrderDTO;
import com.curso.domains.dtos.TechnicianDTO;
import com.curso.services.ServiceOrderService;
import com.curso.services.TechnicianService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/serviceorder")
public class ServiceOrderResource {

    @Autowired
    private ServiceOrderService osService;


    @GetMapping(value = "/{id}") //exemplo http://localhost:8080/serviceorder/1
    public ResponseEntity<ServiceOrderDTO> findById(@PathVariable UUID id){
        ServiceOrder obj = this.osService.findbyId(id);
        return ResponseEntity.ok().body(new ServiceOrderDTO(obj));
    }

    @GetMapping //exemplo http://localhost:8080/serviceorder
    public ResponseEntity<List<ServiceOrderDTO>> findAll(){
        return ResponseEntity.ok().body(osService.findAll());
    }


    @PostMapping
    public ResponseEntity<ServiceOrderDTO> create(@Valid @RequestBody ServiceOrderDTO objDto){
        ServiceOrder newObj = osService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ServiceOrderDTO> update(@PathVariable UUID id, @Valid @RequestBody ServiceOrderDTO objDto){
        ServiceOrder Obj = osService.update(id, objDto);
        return ResponseEntity.ok().body(new ServiceOrderDTO(Obj));
    }
}

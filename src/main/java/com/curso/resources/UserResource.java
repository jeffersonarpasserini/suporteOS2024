package com.curso.resources;

import com.curso.domains.User;
import com.curso.domains.dtos.UserDTO;
import com.curso.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

    @Autowired
    private UserService usersService;

    @GetMapping //exemplo http://localhost:8080/users
    public ResponseEntity<List<UserDTO>> findAll(){
        return ResponseEntity.ok().body(usersService.findAll());
    }

    @GetMapping(value = "/{id}") //exemplo http://localhost:8080/users/1
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        User obj = this.usersService.findbyId(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @GetMapping(value = "/cpf/{cpf}") //exemplo http://localhost:8080/users/cpf/11122233344
    public ResponseEntity<UserDTO> findByCpf(@PathVariable String cpf){
        User obj = this.usersService.findbyCpf(cpf);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @GetMapping(value = "/email/{email}") //exemplo http://localhost:8080/technician/email/email@email.com
    public ResponseEntity<UserDTO> findByEmail(@PathVariable String email){
        User obj = this.usersService.findbyEmail(email);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO objDto){
        User newObj = usersService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @Valid @RequestBody UserDTO objDto){
        User Obj = usersService.update(id, objDto);
        return ResponseEntity.ok().body(new UserDTO(Obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable Long id){
        usersService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

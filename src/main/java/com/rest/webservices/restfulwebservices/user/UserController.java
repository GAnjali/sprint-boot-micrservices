package com.rest.webservices.restfulwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserController {

    @Autowired
    private UserDAOService userDAOService;

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userDAOService.findAll();
    }

//    @GetMapping("users/{id}")
//    public User retrieveUser(@PathVariable int id){
//        User user = userDAOService.findOne(id);
//        if(user==null)
//            throw new UserNotFoundException("id-"+id);
//        return user;
//    }

    @GetMapping("users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id){
        User user = userDAOService.findOne(id);
        if(user==null)
            throw new UserNotFoundException("id-"+id);

        //HATEOAS user with links
        Resource<User> resource = new Resource<User>(user);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    @PostMapping("users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userDAOService.save(user);
        // /user/{id}
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = userDAOService.deleteById(id);
        if(user==null)
            throw new UserNotFoundException("id-"+id);
    }
}

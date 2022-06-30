package com.example.navigation.controller;

import com.example.navigation.entity.Navigate;
import com.example.navigation.service.NavigateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/navigate")
public class NavigateController {
    @Autowired
    private NavigateService service;

    @GetMapping
    public ResponseEntity<List<Navigate>> getAll(@RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted) {
        return service.getList(isDeleted);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Navigate navigate, BindingResult bindingResult, @RequestParam(value = "isDeleted", required = false, defaultValue = "false") boolean isDeleted) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return service.create(navigate, isDeleted);
        }
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(name = "id") int id) {
        service.delete(id);
        return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
    }
}

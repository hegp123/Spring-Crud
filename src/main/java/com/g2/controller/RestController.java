package com.g2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.g2.model.ContactModel;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

    @GetMapping("/checkrest")
    public ResponseEntity<String> checkRest() {

        return new ResponseEntity<String>("OK!", HttpStatus.OK);
    }

    @GetMapping("/contatcmodel")
    public ResponseEntity<ContactModel> c() {
        ContactModel cm = new ContactModel(2, "Juancho", "Perez", "3243", "valleupar");
        return new ResponseEntity<ContactModel>(cm, HttpStatus.OK);
    }
}

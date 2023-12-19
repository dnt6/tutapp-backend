package com.tutapp.tutorialsapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tutapp.tutorialsapp.repository.TutorialRepository;
import com.tutapp.tutorialsapp.model.Tutorial;

// REST Controller with request mapping methods for RESTful requests such as: getAllTutorials, createTutorial, updateTutorial, deleteTutorial, findByPublished...

@CrossOrigin(origins = "http://localhost:8081") // CORS
@RestController // indicate return value of methods should be bound to the web response body
@RequestMapping("/api") // declare all API's url in controller will start with "/api"
public class TutorialController {
    
    @Autowired // inject TutorialRepository bean into local variable
    TutorialRepository tutorialRepository;

    // indicate usage of ORM's GET functionality. Return type = ResponseEntity of type list of Tutorials. Parameters = title
    @GetMapping("tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorial (@RequestParam(required = false) String title) {
        // instantiate an ArrayList of Tutorials
        List<Tutorial> tutorials = new ArrayList<Tutorial>();

        // if title is not passed, query for all tutorials
        if (title == null) {
            tutorialRepository.findAll().forEach(t -> tutorials.add(t));
        } else {
            // else query for tutorial containing title
            tutorialRepository.findByTitleContaining(title).forEach(t -> tutorials.add(t));
        }

        // if ArrayList is empty -> return HTTP Status Code for no content
        if (tutorials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        // else -> return ResponseEntity object of type ArrayList with status of OK
        return new ResponseEntity<>(tutorials, HttpStatus.OK);
        
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById (@PathVariable("id") long id) {
        // instantiate an Optional object of type Tutorial
        Optional<Tutorial> data = tutorialRepository.findById(id);

        // if Optional object is empty -> return status code for no content
        if (data.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            // else -> query for tutorial by id and return ResponseEntity(body: tutorial)
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        }
    }

    // @PostMapping("/tutorials")
    // public ResponseEntity<Tutorial> createTutorial (@RequestBody Tutorial tutorial) {}

    // @PutMapping("/tutorials/{id}")
    // public ResponseEntity<Tutorial> updateTutorial (@PathVariable("id") long id, @RequestBody Tutorial tutorial){}

    // @DeleteMapping("/tutorials/{id}")
    // public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {}

    // @DeleteMapping("/tutorials")
    // public ResponseEntity<Tutorial> deleteAllTutorials () {}

    // @GetMapping("/tutorials/published")
    // public ResponseEntity<List<Tutorial>> findByPublished(){}

}

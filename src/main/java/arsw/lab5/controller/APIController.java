/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arsw.lab5.controller;

import arsw.lab5.model.Blueprint;
import arsw.lab5.services.BlueprintException;
import arsw.lab5.services.BlueprintServices;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 2135494
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/blueprint")
public class APIController {

    @Autowired
    BlueprintServices bps;

    @GetMapping
    public ResponseEntity<?> getBlueprintsHandler() {
        try {
            return new ResponseEntity<>(bps.getBlueprints(), HttpStatus.ACCEPTED);
        } catch (BlueprintException ex) {
            Logger.getLogger(BlueprintException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{author}")
    public ResponseEntity<?> getBlueprintByAuthorHandler(@PathVariable("author") String author) {
        try {
            return new ResponseEntity<>(bps.getBlueprintsByAuthor(author), HttpStatus.ACCEPTED);
        } catch (BlueprintException ex) {
            Logger.getLogger(BlueprintException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{author}/{name}")
    public ResponseEntity<?> getBlueprintHandler(@PathVariable("author") String author, @PathVariable("name") String name) {
        try {
            return new ResponseEntity<>(bps.getBlueprint(author, name), HttpStatus.ACCEPTED);
        } catch (BlueprintException ex) {
            Logger.getLogger(BlueprintException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> postOrderHandler(@RequestBody Blueprint blueprint) {
        try {
            bps.addNewBlueprintToAuthor(blueprint);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (BlueprintException ex) {
            Logger.getLogger(BlueprintException.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }

    }
}

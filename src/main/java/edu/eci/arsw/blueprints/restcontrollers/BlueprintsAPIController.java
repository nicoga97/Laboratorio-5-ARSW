package edu.eci.arsw.blueprints.restcontrollers;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class BlueprintsAPIController {

    @Autowired
    BlueprintsServices service;

    @RequestMapping(value = "/blueprints",method = RequestMethod.GET)
    public ResponseEntity<?> getAllBlueprints() {
        try {

            return new ResponseEntity<>(service.getAllBlueprints(), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(BlueprintsAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error while getting all blueprints", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/blueprints",method = RequestMethod.POST)
    public ResponseEntity<?> addNewBlueprint(@RequestBody Blueprint b){
        try {
            service.addNewBlueprint(b);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (BlueprintPersistenceException ex) {
            Logger.getLogger(BlueprintsAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error while adding blueprint.",HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/blueprints/{author}/{name}")
    @ResponseBody
    public ResponseEntity<?> getBlueprint (@PathVariable("author") String auth,@PathVariable("name") String nam) {
        try {
            Blueprint bp=service.getBlueprint(auth, nam);
            if(bp!=null){
                return new ResponseEntity<>(bp, HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(bp, HttpStatus.NOT_FOUND);
            }

        } catch (BlueprintNotFoundException e) {
            Logger.getLogger(BlueprintsAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BlueprintPersistenceException e) {
            Logger.getLogger(BlueprintsAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(value = "/blueprints/{author}")
    @ResponseBody
    public ResponseEntity<?> getAuthorBlueprints(@PathVariable("author") String auth) {
        try {
            return new ResponseEntity<>(service.getBlueprintsByAuthor(auth), HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException e) {
            Logger.getLogger(BlueprintsAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }


}

package edu.eci.arsw.blueprints.restcontrollers;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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


}

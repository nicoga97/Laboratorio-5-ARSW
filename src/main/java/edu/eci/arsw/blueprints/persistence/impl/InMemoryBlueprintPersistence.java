/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintFilter;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author hcadavid
 */
@Service
public class InMemoryBlueprintPersistence implements BlueprintsPersistence {

    private final Map<Tuple<String, String>, Blueprint> blueprints = new HashMap<>();

    @Autowired
    private BlueprintFilter filter;


    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts = new Point[]{new Point(140, 140), new Point(115, 115)};
        Blueprint bp = new Blueprint("_authorname_", "_bpname_ ", pts);
        blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);

    }

    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(), bp.getName()))) {
            throw new BlueprintPersistenceException("The given blueprint already exists: " + bp);
        } else {
            blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);
        }
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        Blueprint bp = null;
        try {
            bp = blueprints.get(new Tuple<>(author, bprintname));

        } catch (Exception ex) {
            throw new BlueprintNotFoundException("The given author and blue print name does not exists");
        }
        return filter.filterBlueprint(bp);
    }

    public Collection<Blueprint> getBlueprintByAuthor(String auth) {
        Collection<Blueprint> authorBlueprints = new HashSet<Blueprint>();
        for (Tuple<String, String> key : blueprints.keySet()) {
            if (key.getElem1().equals(auth)) {
                authorBlueprints.add(blueprints.get(key));
            }
        }
        return filter.filterBlueprintSet(authorBlueprints);
    }

    @Override
    public Collection< Blueprint> getAllBlueprints() {
        return filter.filterBlueprintSet(blueprints.values());
    }


}

package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintFilter;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SubsamplingFilter implements BlueprintFilter {
    @Override
    public Blueprint filterBlueprint(Blueprint bp) {
        for(int i=0;i<bp.getPoints().size()/2;i++){
            bp.getPoints().remove((int)Math.random()*bp.getPoints().size());
        }
        return null;
    }

    @Override
    public Collection<Blueprint> filterBlueprintSet(Collection<Blueprint> bp) {
        for(Blueprint b:bp){
            b=filterBlueprint(b);
        }
        return bp;
    }
}

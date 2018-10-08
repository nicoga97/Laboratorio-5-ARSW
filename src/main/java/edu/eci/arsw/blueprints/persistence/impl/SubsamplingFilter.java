package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintFilter;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SubsamplingFilter implements BlueprintFilter {
    @Override
    public Blueprint filterBlueprint(Blueprint bp) {
        boolean even = false;
        for (int i = 0; i < bp.getPoints().size(); i++) {
            if (even) {
                bp.getPoints().remove(i);
                i=i-1;
                even=false;
            }else{
                even=true;
            }
            
        }
        return bp;
    }

    @Override
    public Collection<Blueprint> filterBlueprintSet(Collection<Blueprint> bp) {
        for (Blueprint b : bp) {
            b = filterBlueprint(b);
        }
        return bp;
    }
}

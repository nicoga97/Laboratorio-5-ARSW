package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintFilter;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Primary
@Service
public class RedundanciesFilter implements BlueprintFilter {
    @Override
    public Blueprint filterBlueprint(Blueprint bp) {
        for(int i=0;i<bp.getPoints().size();i++){
            List<Point> p=bp.getPoints();


            if(i>0 && p.get(i).getX()==p.get(i-1).getX()&& p.get(i).getY()==p.get(i-1).getY()){
                bp.getPoints().remove(i);
                i=i-1;
            }

        }
        return bp;
    }

    @Override
    public Collection<Blueprint> filterBlueprintSet(Collection<Blueprint> bp) {
        for(Blueprint b:bp){
            b=filterBlueprint(b);
        }
        return bp;
    }
}

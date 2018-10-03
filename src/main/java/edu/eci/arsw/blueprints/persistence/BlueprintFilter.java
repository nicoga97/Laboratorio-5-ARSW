package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;

import java.util.Collection;

public interface BlueprintFilter {

    public Blueprint filterBlueprint(Blueprint bp);

    public Collection<Blueprint> filterBlueprintSet(Collection<Blueprint> bp);
}

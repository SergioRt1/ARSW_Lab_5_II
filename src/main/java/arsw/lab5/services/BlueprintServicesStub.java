/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arsw.lab5.services;

import arsw.lab5.beans.BlueprintFilter;
import arsw.lab5.beans.BlueprintPersistence;
import arsw.lab5.model.Blueprint;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sergio
 */
@Service
public class BlueprintServicesStub implements BlueprintServices{
    
    @Autowired
    BlueprintPersistence bp;
    
    @Autowired
    BlueprintFilter filter;
    
    @Override
    public List<Blueprint> getBlueprints() throws BlueprintException {
        return filter.blueprintsFilter(bp.blueprints());
    }

    @Override
    public List<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintException {
        return filter.blueprintsFilter(bp.AuthorBlueprints(author));
    }

    @Override
    public void addNewBlueprintToAuthor(Blueprint blueprint) throws BlueprintException{
        bp.addBlueprint(blueprint);
    }

    @Override
    public Blueprint getBlueprint(String author, String name) throws BlueprintException {
        return bp.getBlueprint(author,name);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arsw.lab5.beans;

import arsw.lab5.model.Blueprint;
import arsw.lab5.services.BlueprintException;
import java.util.List;

/**
 *
 * @author sergio
 */
public interface BlueprintPersistence {

    public List<Blueprint> blueprints() throws BlueprintException;

    public List<Blueprint> AuthorBlueprints(String author) throws BlueprintException;

    public void addBlueprint(Blueprint blueprint) throws BlueprintException;

    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintException;

    public void updateBlueprint(String author, String bpname, Blueprint blueprint) throws BlueprintException;
    
    public void clearMemory();

}

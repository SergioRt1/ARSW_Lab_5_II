/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arsw.lab5.services;

import arsw.lab5.model.Blueprint;
import java.util.List;

/**
 *
 * @author sergio
 */
public interface BlueprintServices {

    public List<Blueprint> getBlueprints() throws BlueprintException;
    
    public Blueprint getBlueprint(String author,String name) throws BlueprintException;

    public List<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintException;

    public void addNewBlueprintToAuthor(Blueprint blueprint) throws BlueprintException;

    public void updateBlueprint(String author, String bpname, Blueprint blueprint) throws BlueprintException;

}

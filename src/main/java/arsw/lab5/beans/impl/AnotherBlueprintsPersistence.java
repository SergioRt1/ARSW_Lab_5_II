/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arsw.lab5.beans.impl;

import arsw.lab5.beans.BlueprintPersistence;
import arsw.lab5.model.Blueprint;
import arsw.lab5.services.BlueprintException;
import java.util.List;

/**
 *
 * @author sergio
 */
public class AnotherBlueprintsPersistence implements BlueprintPersistence{

    @Override
    public List<Blueprint> blueprints() throws BlueprintException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Blueprint> AuthorBlueprints(String author) throws BlueprintException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addBlueprint(Blueprint blueprint) throws BlueprintException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}

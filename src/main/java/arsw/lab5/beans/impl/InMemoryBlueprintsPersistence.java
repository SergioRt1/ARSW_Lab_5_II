/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arsw.lab5.beans.impl;

import arsw.lab5.beans.BlueprintPersistence;
import arsw.lab5.model.Blueprint;
import arsw.lab5.model.Tuple;
import arsw.lab5.services.BlueprintException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author sergio
 */
@Service
public class InMemoryBlueprintsPersistence implements BlueprintPersistence {

    private List<Blueprint> blueprints = new ArrayList<>();
    private Map<String, List<Blueprint>> blueprintsByAuthor = new HashMap<>();
    private final Map<Tuple<String, String>, Blueprint> blueprintsMap = new HashMap<>();

    public InMemoryBlueprintsPersistence() {
        //load stub data

    }

    @Override
    public List<Blueprint> blueprints() throws BlueprintException {
        if (!blueprints.isEmpty()) {
            return blueprints;
        } else {
            throw new BlueprintException("The database is empty.");
        }
    }

    @Override
    public List<Blueprint> AuthorBlueprints(String author) throws BlueprintException {
        if (blueprintsByAuthor.containsKey(author)) {
            return blueprintsByAuthor.get(author);
        } else {
            throw new BlueprintException("The author requested not exists in the database.");
        }
    }

    @Override
    public void addBlueprint(Blueprint blueprint) throws BlueprintException {
        String autor = blueprint.getAuthor();
        List<Blueprint> autorsBlueprints;
        if (blueprintsMap.containsKey(new Tuple<>(blueprint.getAuthor(), blueprint.getName()))) {
            throw new BlueprintException("The given blueprint already exists: " + blueprint);
        } else {
            blueprintsMap.put(new Tuple<>(blueprint.getAuthor(), blueprint.getName()), blueprint);
        }
        blueprints.add(blueprint);
        if (blueprintsByAuthor.containsKey(autor)) {
            autorsBlueprints = blueprintsByAuthor.get(autor);
            autorsBlueprints.add(blueprint);
        } else {
            autorsBlueprints = new ArrayList<>();
            autorsBlueprints.add(blueprint);
            blueprintsByAuthor.put(autor, autorsBlueprints);
        }

    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintException {
        return blueprintsMap.get(new Tuple<>(author, bprintname));
    }

    @Override
    public void updateBlueprint(String author, String bpname, Blueprint blueprint) throws BlueprintException {
        if (!blueprintsMap.containsKey(new Tuple<>(blueprint.getAuthor(), blueprint.getName()))) {
            throw new BlueprintException("The given blueprint don't exists: " + blueprint);
        }
        blueprintsMap.put(new Tuple<>(blueprint.getAuthor(), blueprint.getName()), blueprint);
        for (int i = 0; i < blueprints.size(); i++) {
            if(blueprints.get(i).getAuthor().equals(blueprint.getAuthor()) && blueprints.get(i).getName().equals(blueprint.getName())){
                blueprints.set(i, blueprint);
            }
        }
        List<Blueprint> autorsBlueprints = blueprintsByAuthor.get(blueprint.getAuthor());
        for (int i = 0; i < autorsBlueprints.size(); i++) {
            if(autorsBlueprints.get(i).getAuthor().equals(blueprint.getAuthor()) && autorsBlueprints.get(i).getName().equals(blueprint.getName())){
                autorsBlueprints.set(i, blueprint);
            }
        }
        
        
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arsw.lab5.beans.impl;

import arsw.lab5.beans.BlueprintPersistence;
import arsw.lab5.model.Blueprint;
import arsw.lab5.model.Point;
import arsw.lab5.model.Tuple;
import arsw.lab5.services.BlueprintException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Service;

/**
 *
 * @author sergio
 */
@Service
public final class InMemoryBlueprintsPersistence implements BlueprintPersistence {

    private final List<Blueprint> blueprints = new CopyOnWriteArrayList<>();
    private final Map<String, List<Blueprint>> blueprintsByAuthor = new ConcurrentHashMap<>();
    private final Map<Tuple<String, String>, Blueprint> blueprintsMap = new ConcurrentHashMap<>();

    public InMemoryBlueprintsPersistence() throws BlueprintException {
        List<Point> points1 = new ArrayList<>();
        List<Point> points2 = new ArrayList<>();
        List<Point> points3 = new ArrayList<>();
        
        points1.add(new Point(0, 0));
        points1.add(new Point(0, 0));
        points1.add(new Point(1, 0));
        points1.add(new Point(0, 2));
        
        points2.add(new Point(99, 5));
        points2.add(new Point(0, 2));
        points2.add(new Point(1, 0));
        
        points3.add(new Point(0, 0));
        points3.add(new Point(9, 1));
        points3.add(new Point(9, 1));
        points3.add(new Point(8, 10));
        
        addBlueprint(new Blueprint("Sergio", "plano Casa", points1));
        addBlueprint(new Blueprint("Andres", "Plano 1", points2));
        addBlueprint(new Blueprint("Valentina", "Plano pesca", points3));
    }
    
    @Override
    public void clearMemory(){
        blueprints.clear();
        blueprintsByAuthor.clear();
        blueprintsMap.clear();
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
            blueprintsMap.putIfAbsent(new Tuple<>(blueprint.getAuthor(), blueprint.getName()), blueprint);
        }
        blueprints.add(blueprint);
        if (blueprintsByAuthor.containsKey(autor)) {
            autorsBlueprints = blueprintsByAuthor.get(autor);
            autorsBlueprints.add(blueprint);
        } else {
            autorsBlueprints = new CopyOnWriteArrayList<>();
            autorsBlueprints.add(blueprint);
            blueprintsByAuthor.putIfAbsent(autor, autorsBlueprints);
        }

    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintException {
        Tuple<String,String> key = new Tuple<>(author, bprintname);
        if (blueprintsMap.containsKey(key)) {
            return blueprintsMap.get(key);
        } else {
            throw new BlueprintException(String.format("The blueprint \"%s\" associated with the author \"%s\" requested not exists in the database.",bprintname,author));
        }
        
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

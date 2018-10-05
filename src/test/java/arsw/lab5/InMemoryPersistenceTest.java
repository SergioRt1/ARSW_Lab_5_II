package arsw.lab5;

import arsw.lab5.beans.BlueprintPersistence;
import arsw.lab5.beans.impl.InMemoryBlueprintsPersistence;
import arsw.lab5.model.Blueprint;
import arsw.lab5.services.BlueprintException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InMemoryPersistenceTest {

    @Test(expected = BlueprintException.class)
    public void mustThrowIfIsEmpty() throws BlueprintException {
        BlueprintPersistence persistence = new InMemoryBlueprintsPersistence();
        persistence.clearMemory();
        persistence.blueprints();
    }

    @Test(expected = BlueprintException.class)
    public void mustThrowIfAuthorIsNot() throws BlueprintException {
        BlueprintPersistence persistence = new InMemoryBlueprintsPersistence();
        persistence.clearMemory();
        Blueprint blueprint = new Blueprint("camilo","plano1", new ArrayList<>());
        persistence.addBlueprint(blueprint);
        persistence.AuthorBlueprints("juan");
    }

    @Test
    public void queryByAuthor() throws BlueprintException {
        BlueprintPersistence persistence = new InMemoryBlueprintsPersistence();
        persistence.clearMemory();
        Blueprint JuanBlueprint = new Blueprint("juan","plano2", new ArrayList<>());
        Blueprint camiloBlueprint = new Blueprint("camilo","plano1", new ArrayList<>());
        persistence.addBlueprint(JuanBlueprint);
        persistence.addBlueprint(camiloBlueprint);
        List<Blueprint> blueprints = persistence.AuthorBlueprints("juan");
        Assert.assertTrue("Must only be one element.", blueprints.size() == 1);
        Assert.assertTrue(JuanBlueprint.equals(blueprints.get(0)));
    }

    @Test
    public void addANewBlueprint() throws BlueprintException {
        BlueprintPersistence persistence = new InMemoryBlueprintsPersistence();
        persistence.clearMemory();
        Blueprint JuanBlueprint = new Blueprint("juan","plano2", new ArrayList<>());
        persistence.addBlueprint(JuanBlueprint);
        List<Blueprint> blueprints = persistence.blueprints();
        Assert.assertTrue("Must only be one element.", blueprints.size() == 1);
        Assert.assertEquals(JuanBlueprint, blueprints.get(0));
        Blueprint camiloBlueprint = new Blueprint("camilo","plano1", new ArrayList<>());
        persistence.addBlueprint(camiloBlueprint);
        Assert.assertTrue("Must only be two element.", blueprints.size() == 2);
        Assert.assertTrue(camiloBlueprint.equals(blueprints.get(1)));
        
    }


}

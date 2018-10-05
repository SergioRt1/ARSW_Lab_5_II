package arsw.lab5;

import arsw.lab5.beans.BlueprintFilter;
import arsw.lab5.beans.impl.RedundancyFilter;
import arsw.lab5.model.Blueprint;
import arsw.lab5.model.Point;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedundancyFilterTest {

    @Test
    public void doNothing() {
        BlueprintFilter filter = new RedundancyFilter();
        List<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(1, 0));
        points.add(new Point(0, 1));
        points.add(new Point(0, 0));
        Blueprint bp = new Blueprint("Sergio","plano2", points);
        Blueprint fitered = filter.filter(bp);
        Assert.assertTrue("Must be equials", fitered.equals(bp));
    }
    
    @Test
    public void filterTest() {
        BlueprintFilter filter = new RedundancyFilter();
        List<Point> points = new ArrayList<>();
        List<Point> answer = new ArrayList<>();
        
        points.add(new Point(0, 0));
        points.add(new Point(0, 0));
        points.add(new Point(0, 1));
        points.add(new Point(0, 1));
        points.add(new Point(1, 1));
        points.add(new Point(0, 0));
        points.add(new Point(0, 0));
        points.add(new Point(1, 1));
        
        answer.add(new Point(0, 0));
        answer.add(new Point(0, 1));
        answer.add(new Point(1, 1));
        answer.add(new Point(0, 0));
        answer.add(new Point(1, 1));
        
        Blueprint bp = new Blueprint("Sergio","plano2", points);
        Blueprint ans = new Blueprint("Sergio","plano2", answer);
        Blueprint fitered = filter.filter(bp);
        
        Assert.assertTrue("Must be equials", fitered.equals(ans));
    }
    
}

package arsw.lab5;

import arsw.lab5.beans.BlueprintFilter;
import arsw.lab5.beans.impl.SubsamplingFilter;
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
public class SubsamplingFilterTest {

    @Test
    public void filterTestEven() {
        BlueprintFilter filter = new SubsamplingFilter();
        List<Point> points = new ArrayList<>();
        List<Point> answer = new ArrayList<>();

        points.add(new Point(0, 0));
        points.add(new Point(0, 0));
        points.add(new Point(8, 1));
        points.add(new Point(0, 1));
        points.add(new Point(1, 1));
        points.add(new Point(9, 10));
        points.add(new Point(0, 0));
        points.add(new Point(1, 1));
        points.add(new Point(6, 8));
        points.add(new Point(6, 8));

        answer.add(new Point(0, 0));
        answer.add(new Point(8, 1));
        answer.add(new Point(1, 1));
        answer.add(new Point(0, 0));
        answer.add(new Point(6, 8));

        Blueprint bp = new Blueprint("Sergio","plano2", points);
        Blueprint ans = new Blueprint("Sergio","plano2", answer);
        Blueprint fitered = filter.filter(bp);

        Assert.assertTrue("Must be equials", fitered.equals(ans));
    }
    @Test
    public void filterTestOdd() {
        BlueprintFilter filter = new SubsamplingFilter();
        List<Point> points = new ArrayList<>();
        List<Point> answer = new ArrayList<>();

        points.add(new Point(0, 0));
        points.add(new Point(0, 0));
        points.add(new Point(8, 1));
        points.add(new Point(0, 1));
        points.add(new Point(1, 1));
        points.add(new Point(9, 10));
        points.add(new Point(0, 0));
        points.add(new Point(1, 1));
        points.add(new Point(6, 8));
        points.add(new Point(6, 8));
        points.add(new Point(9, 10));
        
        answer.add(new Point(0, 0));
        answer.add(new Point(8, 1));
        answer.add(new Point(1, 1));
        answer.add(new Point(0, 0));
        answer.add(new Point(6, 8));
        answer.add(new Point(9, 10));

        Blueprint bp = new Blueprint("Sergio","plano2", points);
        Blueprint ans = new Blueprint("Sergio","plano2", answer);
        Blueprint fitered = filter.filter(bp);

        Assert.assertTrue("Must be equials", fitered.equals(ans));
    }
    
}

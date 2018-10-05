/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arsw.lab5.beans.impl;

import arsw.lab5.beans.BlueprintFilter;
import arsw.lab5.model.Blueprint;
import arsw.lab5.model.Point;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author sergio
 */
@Service
public class RedundancyFilter implements BlueprintFilter {

    @Override
    public Blueprint filter(Blueprint blueprint) {
        List<Point> points = new ArrayList<>();
        Point last = null;
        if (!blueprint.getPoints().isEmpty()) {
            last = blueprint.getPoints().get(0);
            points.add(last);
        }
        for (Point pt : blueprint.getPoints()) {
            if (pt.getX() != last.getX() || pt.getY() != last.getY()) {
                points.add(pt);
                last = pt;
            }
        }
        return new Blueprint(blueprint.getAuthor(), blueprint.getName(), points);

    }

}

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
public class SubsamplingFilter implements BlueprintFilter {

    @Override
    public Blueprint filter(Blueprint blueprint) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < blueprint.getPoints().size(); i += 2) {
            points.add(blueprint.getPoints().get(i));
        }
        return new Blueprint(blueprint.getAuthor(), blueprint.getName(), points);
    }

}

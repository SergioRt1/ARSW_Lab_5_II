package arsw.lab5.model;

import java.util.List;

/**
 *
 * @author sergio
 */
public class Blueprint {
    
    private String author;
    private String name;
    private List<Point> points;
    
    public Blueprint(String author, String name, List<Point> points) {
        this.author = author;
        this.points = points;
        this.name = name;
    }
    
    public boolean equals(Blueprint blueprint) {
        boolean equalPoints = blueprint.getPoints().size() == this.getPoints().size();
        int i = 0;
        int size = blueprint.getPoints().size();
        while (equalPoints && i < size) {
            equalPoints &= blueprint.getPoints().get(i).equals(this.getPoints().get(i));
            i++;
        }
        return blueprint.getAuthor().equals(this.getAuthor()) && blueprint.getName().equals(this.getName()) && equalPoints;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public List<Point> getPoints() {
        return points;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return "Blueprint{" + "author=" + author + ", name=" + name + '}';
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
    
}

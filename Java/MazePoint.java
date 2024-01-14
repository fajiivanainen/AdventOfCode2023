import java.util.List;
import java.util.ArrayList;

public class MazePoint {
    private int xCoords;
    private int yCoords;
    private char value;
    private Maze maze;

    public MazePoint(int xCoords,int yCoords,char value,Maze maze){
        this.xCoords = xCoords;
        this.yCoords = yCoords;
        this.value = value;
        this.maze = maze;
    }

    public List<MazePoint> getNeighbors(){
        List<MazePoint> neighbors = new ArrayList<>();
        if(xCoords > 0){
            neighbors.add(maze.getPointAt(xCoords - 1,yCoords));
        }
        if(xCoords < maze.columns()){
            neighbors.add(maze.getPointAt(xCoords + 1,yCoords));
        }
        if(yCoords > 0){
            neighbors.add(maze.getPointAt(xCoords,yCoords - 1));
        }
        if(yCoords < maze.rows()){
            neighbors.add(maze.getPointAt(xCoords,yCoords + 1));
        }
        return neighbors;
    }

    public List<MazePoint> getLegalNeighbors(){
        List<MazePoint> neighbors = new ArrayList<>();
        if(value == '7'){
            if(xCoords > 0){
                neighbors.add(maze.getPointAt(xCoords - 1,yCoords));
            }
            if(yCoords < maze.rows()){
                neighbors.add(maze.getPointAt(xCoords,yCoords + 1));
            }
        }else if(value == 'F'){
            if(xCoords < maze.columns()){
                neighbors.add(maze.getPointAt(xCoords + 1,yCoords));
            }
            if(yCoords < maze.rows()){
                neighbors.add(maze.getPointAt(xCoords,yCoords + 1));
            }
        }else if(value == 'L'){
            if(xCoords < maze.columns()){
                neighbors.add(maze.getPointAt(xCoords + 1,yCoords));
            }
            if(yCoords > 0){
                neighbors.add(maze.getPointAt(xCoords,yCoords - 1));
            }
        }else if(value == 'J'){
            if(xCoords > 0){
                neighbors.add(maze.getPointAt(xCoords - 1,yCoords));
            }
            if(yCoords > 0){
                neighbors.add(maze.getPointAt(xCoords,yCoords - 1));
            }
        }else if (value == '-'){
            if(xCoords > 0){
                neighbors.add(maze.getPointAt(xCoords - 1,yCoords));
            }
            if(xCoords < maze.columns()){
                neighbors.add(maze.getPointAt(xCoords + 1,yCoords));
            }
        }else if (value == '|'){
            if(yCoords > 0){
                neighbors.add(maze.getPointAt(xCoords,yCoords - 1));
            }
            if(yCoords < maze.rows()){
                neighbors.add(maze.getPointAt(xCoords,yCoords + 1));
            }
        }else if (value == 'S'){
            neighbors = this.getNeighbors();
        }else{
            return neighbors;
        }
        
        return neighbors;
    }

    public char value(){
        return value;
    }

    public int xCoords(){
        return xCoords;
    }

    public int yCoords(){
        return yCoords;
    }

    public String toString(){
        return "Maze-point with value " + value + " at point (" + xCoords + ", " + yCoords + ")";
    }

    public boolean equals(MazePoint other){
        return (this.xCoords == other.xCoords()) && (this.yCoords == other.yCoords()) && (this.value == other.value());
    }

    public int shortestDistance(MazePoint other){
        int distance = Math.abs(this.xCoords - other.xCoords()) + Math.abs(this.yCoords - other.yCoords());
        return distance;
    }

    public int distanceWithExpansion(MazePoint other, int expansion,List<Integer> emptyRows, List<Integer> emptyColumns){
        int xDistance = Math.abs(this.xCoords - other.xCoords());
        for(int i : emptyColumns){
            if((i >= Math.min(this.xCoords,other.xCoords())) && (i <= Math.max(this.xCoords,other.xCoords()))){
                xDistance += expansion - 1;
            }
        }

        int yDistance = Math.abs(this.yCoords - other.yCoords());
        for(int i : emptyRows){
            if((i >= Math.min(this.yCoords,other.yCoords())) && (i <= Math.max(this.yCoords,other.yCoords()))){
                yDistance += expansion - 1;
            }
        }

        return xDistance + yDistance;
    }

    public void setValue(char value){
        this.value = value;
    }

}
import java.util.List;
import java.util.ArrayList;

public class Maze {
    private int rows;
    private int columns;
    private List<MazePoint> mazePoints;

    public Maze(List<String> mazeList){
        this.rows = mazeList.size();
        this.columns = mazeList.get(0).length();
        int x = 0;
        int y = 0;
        mazePoints = new ArrayList<>();
        for(String line : mazeList){
            for (char value : line.toCharArray()){
                MazePoint point = new MazePoint(x,y,value,this);
                //System.out.println(point);
                mazePoints.add(point);
                x++;
            }
            x = 0;
            y++;
        }
    }

    public MazePoint getPointAt(int xCoords,int yCoords){
        int index = columns * yCoords + xCoords;
        return this.mazePoints.get(index);
    }

    public List<Character> getColumnAt(int columnIndex){
        assert(columnIndex <= this.rows);
        List<Character> columnPoints = new ArrayList<>();
        for(int i = 0;i < this.rows();i++){
            columnPoints.add(this.getPointAt(columnIndex, i).value());
        }
        return columnPoints;
    }

    public List<Character> getRowAt(int rowIndex){
        assert(rowIndex <= this.columns);
        List<Character> rowPoints = new ArrayList<>();
        for(int i = 0; i < this.columns;i++){
            rowPoints.add(this.getPointAt(i,rowIndex).value());
        }
        return rowPoints;
    }

    public void swap(MazePoint point1, MazePoint point2){
        char value1 = point1.value();
        char value2 = point2.value();
        point1.setValue(value2);
        point2.setValue(value1);
    }

    public int rows() {
        return rows;
    }

    public int columns(){
        return columns;
    }

    public List<MazePoint> mazePoints(){
        return mazePoints;
    }

    public String toString(){
        return "Maze with " + this.rows + " rows, and " + this.columns + " columns.";
    }

    public void printMaze(){
        for(int i = 0;i < this.rows();i++){
            System.out.println(this.getRowAt(i));
        }
    }

    public void tiltNorth(){
        for(int i = 0;i < this.columns();i++){
            List<Character> column = this.getColumnAt(i);
            for(int j = 1;j < column.size();j++){
                if(this.getColumnAt(i).get(j) == 'O'){
                    moveNorth(this.getPointAt(i,j));
                }
            }
        }
    }

    public void moveNorth(MazePoint point){
        List<Character> column = this.getColumnAt(point.xCoords());
        int i = point.yCoords();
        while(i >= 1 && ((column.get(i - 1) != 'O') && (column.get(i - 1) != '#'))){
            this.swap(this.getPointAt(point.xCoords(),i),this.getPointAt(point.xCoords(), i - 1));
            i--;
        }
    }

}
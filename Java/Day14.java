import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class Day14 {

    public static void main(String[] args) throws IOException {
        String path = "Day14_input.txt";
        FileReader fileReader = new FileReader(path);
        List<String> lines = fileReader.lines();
        List<String> mazeLines = new ArrayList<>();

        for(String line : lines){
            if(!line.isEmpty()){
                mazeLines.add(line);
            }
        }

        Maze maze = new Maze(mazeLines);

        maze.tiltNorth();

        int weight = calculateWeight(maze);
        System.out.println(weight);


    }

    public static int calculateWeight(Maze maze){
        int sum = 0;
        for(int i = 0;i < maze.rows();i++){
            int columnWeight = maze.rows() - i;
            List<Character> row = maze.getRowAt(i);
            for(char c : row){
                if(c == 'O'){
                    sum += columnWeight;
                }
            }
        }
        return sum;
    }

    
}
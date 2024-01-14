import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

public class Day13 {

    public static void main(String[] args) throws IOException{
        String path = "Day13_input.txt";
        FileReader fileReader = new FileReader(path);
        List<String> lines = fileReader.lines();
        List<List<String>> patternList = new ArrayList<>();

        List<String> pattern = new ArrayList<>();
        for(String line : lines){
            if(!line.isEmpty()){
                pattern.add(line);
            }else{
                if(!pattern.isEmpty()){
                    patternList.add(pattern);
                    pattern = new ArrayList<>();
                }
            }
        }

        List<Maze> mazeList = new ArrayList<>();
        for(List<String> mazePattern : patternList){
            Maze maze = new Maze(mazePattern);
            mazeList.add(maze);
        }

        int sum = 0;
        for (Maze maze : mazeList){
            int reflectionH = findReflectionH(maze);
            int reflectionV = findReflectionV(maze);
            if(reflectionH > 0){
                sum += reflectionH*100;
            }
            if(reflectionV > 0){
                sum += reflectionV;
            }
        }

        System.out.println(sum);



    }

    public static int findReflectionH(Maze maze){
        int reflectionRow = 0;
        for(int i = 1; i < maze.rows()-1;i++){
            boolean isReflection = true;
            for(int j = 0;j <= Math.min(maze.rows()-i - 2,i);j++){
                List<Character> highRow = maze.getRowAt(i - j);
                List<Character> lowRow = maze.getRowAt(i + 1 + j);
                if(!highRow.equals(lowRow)){
                    isReflection = false;
                }
            }
            if(isReflection){
                reflectionRow = i + 1;
            }
        }
        return reflectionRow;
    }

    public static int findReflectionV(Maze maze){
        int reflectionColumn = 0;
        for(int i = 1; i < maze.columns()-1;i++){
            boolean isReflection = true;
            for(int j = 0;j <= Math.min(maze.columns()-i - 2,i);j++){
                List<Character> leftColumn = maze.getColumnAt(i - j);
                List<Character> rightColumn = maze.getColumnAt(i + 1 + j);
                if(!leftColumn.equals(rightColumn)){
                    isReflection = false;
                }
            }
            if(isReflection){
                reflectionColumn = i + 1;
            }
        }
        return reflectionColumn;
    }
}
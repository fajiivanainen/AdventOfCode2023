import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Day11 {

    public static void main (String[] args) throws IOException{

        String path = "Day11_input.txt";
        FileReader fileReader = new FileReader(path);
        List<String> lines = fileReader.lines();
        List<String> mazeLines = new ArrayList<>();

        // List<String> mazeLines1 = new ArrayList<>();
        // List<String> mazeLines2 = new ArrayList<>();
        // for(String line : lines){
        //     if(!line.isEmpty()){
        //         List<Character> chars = new ArrayList<>();
        //         for(char character : line.toCharArray()){
        //             chars.add(character);
        //         }
        //         int k = 0;
        //         for(int i : emptyColumns){
        //             chars.add(i + k,'.');
        //             k++;
        //         }
        //         char[] charArray = new char[chars.size()];
        //         for (int j = 0; j < chars.size(); j++) {
        //             charArray[j] = chars.get(j);
        //         }
        //         String newLine = new String(charArray);
        //         mazeLines1.add(newLine);
        //     }
        // }

        // for (String line : mazeLines1){
        //     if(!line.isEmpty()){
        //         mazeLines2.add(line);
        //         boolean hashtagFound = false;
        //         for(char character : line.toCharArray()){
        //             if(character == '#'){
        //                 hashtagFound = true;
        //             }
        //         }
        //         if(!hashtagFound){
        //             mazeLines2.add(line);
        //         }
        //     }
        // }
        

        for (String line : lines){
            if(!line.isEmpty()){
                mazeLines.add(line);
            }
        }
        int expansionFactor = 1000000;
        List<Integer> emptyColumns = findEmptyColumns(mazeLines);
        List<Integer> emptyRows = findEmptyRows(mazeLines);
        // System.out.println(emptyRows);
        // System.out.println(emptyColumns);

        Maze maze = new Maze(mazeLines);
        List<MazePoint> galaxies = findCharacters(maze, '#');
        long sumOfDistances = 0L;
        List<MazePoint> alreadyChecked = new ArrayList<>();
        for (MazePoint galaxy1 : galaxies){
            alreadyChecked.add(galaxy1);
            for(MazePoint galaxy2 : galaxies){
                if(!isInList(galaxy2,alreadyChecked)){
                    int shortestDistance = galaxy1.distanceWithExpansion(galaxy2,expansionFactor,emptyRows,emptyColumns);
                    // System.out.println(galaxy1);
                    // System.out.println(galaxy2);
                    // System.out.println(shortestDistance);
                    sumOfDistances += shortestDistance;
                }
            }
        }

        System.out.println(sumOfDistances);


    }

    public static List<MazePoint> findCharacters(Maze maze, char character){
        List<MazePoint> pointsWithCharacters = new ArrayList<>();
        for (MazePoint point : maze.mazePoints()){
            if(point.value() == character){
                pointsWithCharacters.add(point);
            }
        }
        return pointsWithCharacters;
    }

    public static boolean isInList(MazePoint point, List<MazePoint> list){
        for (MazePoint mazePoint : list){
            if (mazePoint.equals(point)){
                return true;
            }
        }
        return false;
    }


    public static List<Integer> findEmptyColumns(List<String> lines){
        List<Integer> emptyColumns = new ArrayList<>();
        for(int i = 0;i<lines.get(0).length();i++){
            boolean hashtagFound = false;
            for (String line : lines){

                if(!line.isEmpty() && line.charAt(i) == '#'){
                    hashtagFound = true;
                    break;
                }
            }
            if(!hashtagFound){
                emptyColumns.add(i);
            }
        }
        return emptyColumns;
    }

    public static List<Integer> findEmptyRows(List<String> lines){
        List<Integer> emptyRows = new ArrayList<>();
        int i = 0;
        for (String line : lines){
            boolean hashtagFound = false;
            for (char c : line.toCharArray()){
                if(c == '#'){
                    hashtagFound = true;
                }
            }
            if(!hashtagFound){
                emptyRows.add(i);
            }
            i++;
        }
        return emptyRows;
    }
}
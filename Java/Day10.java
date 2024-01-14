import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day10 {

    public static void main (String[] args) throws IOException{
        String path = "Day10_input.txt";
        FileReader fileReader = new FileReader(path);
        List<String> lines = fileReader.lines();
        List<String> mazeLines = new ArrayList<>();
        //System.out.println(lines.get(0).length());

        for (String line : lines){
            if(!line.isEmpty()){
                //System.out.println(line);
                mazeLines.add(line);
            }
        }

        Maze maze = new Maze(mazeLines);
        MazePoint startPoint = findStartPoint(maze);
        List<MazePoint> loop = findLoopIterative(startPoint);
        System.out.println(loop.size()/2);
        int areaInsideLoop = findLoopArea(loop, maze);
        System.out.println(areaInsideLoop);

    }

    public static MazePoint findStartPoint(Maze maze){
        MazePoint startPoint = maze.getPointAt(0,0);
        for(MazePoint point : maze.mazePoints()){
            if(point.value() == 'S'){
                startPoint = point;
            }
        }
        return startPoint;
    }

    public static List<MazePoint> findLoopRecursive(MazePoint startPoint, MazePoint previousPoint){
        List<MazePoint> list = new ArrayList<>();
        list.add(startPoint);
        List<MazePoint> neighbors = startPoint.getLegalNeighbors();
        MazePoint nextPoint = startPoint;
        for (MazePoint neighbor : neighbors){
            for(MazePoint newNeighbor : neighbor.getLegalNeighbors()){
                if((newNeighbor == startPoint) && (neighbor != previousPoint)){
                    nextPoint = neighbor;
                }
            }
        }
        if(nextPoint.value() == 'S'){
            return list;
        }else{
            list.addAll(findLoopRecursive(nextPoint,startPoint));
            return list;
        }
    }

    public static List<MazePoint> findLoopIterative(MazePoint startPoint){
        List<MazePoint> list = new ArrayList<>();
        List<MazePoint> neighbors = new ArrayList<>();
        MazePoint nextPoint = startPoint;
        MazePoint previousPoint = startPoint;
        do {
            list.add(startPoint);
            neighbors = startPoint.getLegalNeighbors();
            for (MazePoint neighbor : neighbors){
                for(MazePoint newNeighbor : neighbor.getLegalNeighbors()){
                    if((newNeighbor == startPoint) && (neighbor != previousPoint)){
                        nextPoint = neighbor;
                    }
                }
            }
            previousPoint = startPoint;
            startPoint = nextPoint;
        } while(nextPoint.value() != 'S');
        return list;
    }

    public static int findLoopArea(List<MazePoint> loop, Maze maze){
        int pointsInsideLoop = 0;
        List<MazePoint> mazePoints = maze.mazePoints();
        for (MazePoint point : mazePoints){
            int crosses = 0;
            char previousCrossValue = point.value();
            char ScrossValue = '|';
            if(!isInList(point,loop)){
                for(int i = 1;i <= point.yCoords();i++){
                    MazePoint currentPoint = maze.getPointAt(point.xCoords(), point.yCoords()-i);
                    if(isInList(currentPoint,loop)){
                        if(currentPoint.value() == '-'){
                            crosses += 1;
                        }else if((currentPoint.value() == 'F') && (previousCrossValue == 'J')){
                            crosses += 1;
                        }else if((currentPoint.value() == '7') && (previousCrossValue == 'L')){
                            crosses += 1;
                        }else if(currentPoint.value() == 'S'){
                            //System.out.println(currentPoint);
                            MazePoint upPoint = maze.getPointAt(currentPoint.xCoords(), currentPoint.yCoords()-1);
                            MazePoint downPoint = maze.getPointAt(currentPoint.xCoords(), currentPoint.yCoords()+1);
                            MazePoint rightPoint = maze.getPointAt(currentPoint.xCoords() + 1, currentPoint.yCoords());
                            MazePoint leftPoint = maze.getPointAt(currentPoint.xCoords()-1, currentPoint.yCoords());
                            if((isInList(rightPoint,loop) && isInList(downPoint,loop)) && (previousCrossValue == 'J')){
                                crosses += 1;
                                ScrossValue = 'F';
                            }else if((isInList(leftPoint,loop) && isInList(downPoint,loop)) && (previousCrossValue == 'L')){
                                crosses += 1;
                                ScrossValue = '7';
                            }else if((isInList(rightPoint,loop) && isInList(leftPoint,loop))){
                                crosses += 1;
                                ScrossValue = '-';
                            }else if(isInList(upPoint,loop) && isInList(rightPoint,loop)){
                                ScrossValue = 'L';
                            }else if(isInList(upPoint,loop) && isInList(leftPoint,loop)){
                                ScrossValue = 'J';
                            }
                        }
                        if((currentPoint.value() != '|') && currentPoint.value() != 'S'){
                            previousCrossValue = currentPoint.value();
                        }else if(currentPoint.value() == 'S' && ScrossValue != '|'){
                            previousCrossValue = ScrossValue;
                        }
                    }
                }
                if(crosses % 2 != 0){
                    //System.out.println(point);
                    // System.out.println(isInList(point,loop));
                    pointsInsideLoop += 1;
                }
            }
        }
        
        return pointsInsideLoop;
    }

    public static boolean isInList(MazePoint point, List<MazePoint> list){
        for (MazePoint mazePoint : list){
            if (mazePoint.equals(point)){
                return true;
            }
        }
        return false;
    }
}
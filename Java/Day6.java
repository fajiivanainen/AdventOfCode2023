import java.util.List;

public class Day6 {

    public static void main(String[] args){
        // Part 1:
        List<Integer> times = List.of(58,99,64,69);
        List<Integer> distances = List.of(478,2232,1019,1071);
        int product = 1;
        for(int i = 0; i < times.size();i++){
            product *= amountOfWins(times.get(i),distances.get(i));
        }
        System.out.println(product);

        // Part 2:
        long time = 58996469L;
        long distance = 478223210191071L;
        System.out.println(fastAmountOfWins(time,distance));

    }

    public static int amountOfWins(int time, int distance){
        int speed = 0;
        int wins = 0;
        for(int i = 0; i < time; i++){
            int timeLeft = time - i;
            int distanceTravelled = speed * timeLeft;
            if (distanceTravelled > distance){
                wins++;
            };
            speed = speed + 1;
        }

        return wins;
    }

    public static int fastAmountOfWins(long time, long distance){
        double point1 = (-time - Math.sqrt(Math.pow(time,2) - 4*distance)) / (-2);
        double point2 = (-time + Math.sqrt(Math.pow(time,2)- 4*distance)) / (-2);
        int range = (int) (Math.floor(point1) - Math.floor(point2));
        return range;
    }
}
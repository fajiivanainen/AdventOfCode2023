import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class Day12 {

    public static void main(String[] args) throws IOException{
        String path = "Day12_test_input.txt";
        FileReader fileReader = new FileReader(path);
        List<String> lines = fileReader.lines();
        List<String> infos = new ArrayList<>();
        List<List<Integer>> numbersList = new ArrayList<>();

        for(String line : lines){
            if(!line.isEmpty()){
                String[] infosAndNumbers = line.split(" ");
                infos.add(infosAndNumbers[0]);
                List<Integer> numbers = new ArrayList<>();
                String[] stringNumbers = infosAndNumbers[1].split(",");
                for (String number : stringNumbers){
                    numbers.add(Integer.parseInt(number));
                }
                numbersList.add(numbers);
            }
        }

        int sumOfArrangements = 0;
        for(int i = 0;i < infos.size();i++){
            int arrangements = findArrangements(infos.get(i),numbersList.get(i));
            sumOfArrangements += arrangements;
        }

        System.out.println(sumOfArrangements);

        // String testString = "#.###.??.####..??.#####.";
        // System.out.println(findNumbers(testString));
        // System.out.println(findChars(testString,'?'));
    }

    public static int findArrangements(String info, List<Integer> numbers){
        int unknowns = findChars(info,'?');
        int brokens = findChars(info,'#');
        int brokenUnknowns = sum(numbers) - brokens;
        List<List<Integer>> permutations = findPermutations(brokenUnknowns, unknowns);
        return brokenUnknowns;
    }

    public static List<List<Integer>> findPermutations(int k, int n){
        List<List<Integer>> permutations = new ArrayList<>();
        return permutations;
    }

    public static List<Integer> findNumbers(String info){
        List<Integer> numbers = new ArrayList<>();
        int count = 0;
        int i = 0;
        for(char c : info.toCharArray()){
            if(c == '#'){
                count += 1;
                if(i == info.length() - 1){
                    numbers.add(count);
                }
            }else {
                if(count != 0){
                    numbers.add(count);
                    count = 0;
                }
            }
            i++;
        }
        return numbers;
    }

    public static int findChars(String info, char character){
        int count = 0;
        for(char c : info.toCharArray()){
            if(c == character){
                count += 1;
            }
        }
        return count;
    }

    public static List<Integer> findCharIndices(String info, char character){
        List<Integer> indices = new ArrayList<>();
        int i = 0;
        for(char c : info.toCharArray()){
            if(c == character){
                indices.add(i);
            }
            i++;
        }
        return indices;
    }

    public static int sum(List<Integer> numbers){
        int sum = 0;
        for(int number : numbers){
            sum += number;
        }
        return sum;
    }


}
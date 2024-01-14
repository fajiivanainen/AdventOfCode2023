import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Day9 {

    public static void main (String[] args) throws IOException{
        String path = "Day9_test_input.txt";
        FileReader fileReader = new FileReader(path);
        List<String> lines = fileReader.lines();
        int sumOfNexts = 0;
        int sumOfPreviouses = 0;
        for(String line:lines){
            if (!line.isEmpty()){           
                List<Integer> numbers = new ArrayList<>();
                String[] numbers_str = line.split(" ");
                for(String number : numbers_str){
                    numbers.add(Integer.parseInt(number));
                }
                int nextNumber = getNext(numbers);
                int previousNumber = getPrevious(numbers);
                sumOfPreviouses += previousNumber;
                sumOfNexts += nextNumber;
            }
        }

        // List<Integer> testList = new ArrayList<>();
        // testList.add(10);
        // testList.add(13);
        // testList.add(16);
        // testList.add(21);
        // testList.add(30);
        // testList.add(45);
        // System.out.println(testList);

        // System.out.println(getPrevious(testList));

        System.out.println(sumOfNexts);
        System.out.println(sumOfPreviouses);
    }

    public static int getNext(List<Integer> sequence){
        boolean isZeros = true;
        int i = 0;
        List<Integer> newList = new ArrayList<>();
        for(int number : sequence){
            if(number != 0){
                isZeros = false;
            }
            if(i < sequence.size()-1){
                newList.add(sequence.get(i+1) - sequence.get(i));
            }
            i++;
        }
        if (isZeros){
            return 0;
        }else{
            return sequence.get(sequence.size()-1) + getNext(newList);
        }
    }

    public static int getPrevious(List<Integer> sequence){
        boolean isZeros = true;
        int i = 0;
        List<Integer> newList = new ArrayList<>();
        for(int number : sequence){
            if(number != 0){
                isZeros = false;
            }
            if(i < sequence.size()-1){
                newList.add(sequence.get(i+1) - sequence.get(i));
            }
            i++;
        }
        if(isZeros){
            return 0;
        }else{
            return sequence.get(0) - getPrevious(newList);
        }
    }
    
}

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Day7 {

    public static void main(String[] args) throws IOException {
        String path = "Day7_input.txt";
        FileReader fileReader = new FileReader(path);
        List<String> lines = fileReader.lines();

        List<String> hands = new ArrayList<>(lines.size());
        List<Integer> bids = new ArrayList<>(lines.size());
        for(String line : lines){
            if (!line.isEmpty()){
                String[] values = line.split(" ");
                hands.add(values[0]);
                bids.add(Integer.parseInt(values[1]));
            }
        }

        int totalWinnings = 0;
        Map<String,Integer> ranks = countRanks(hands);
        for (int i = 0;i < ranks.size();i++){
            totalWinnings += ranks.get(hands.get(i)) * bids.get(i);
        }
        
        System.out.println(totalWinnings);
        String hand1 = "AJJJJ";
        String hand2 = "JJJJA";
        System.out.println(getHandType(hand1));
        System.out.println(getHandType(hand2));
        System.out.println(compareHands(hand1, hand2));
        

    }

    public static Map<String,Integer> countRanks(List<String> hands){

        Map<String, Integer> rankMap = new HashMap<>();
        List<String> sortedHands = sortHands(hands);
        for (int i = 0; i < sortedHands.size(); i++){
            //System.out.println(sortedHands.get(i));
            //System.out.println(i + 1);
            rankMap.put(sortedHands.get(i),i + 1);
        }

        return rankMap;

    }

    public static boolean compareHands(String hand1, String hand2){

        boolean hand1Bigger = true;
        Map<Character, Integer> cardMap = Map.ofEntries(
            Map.entry('2', 2), Map.entry('3', 3), Map.entry('4', 4), Map.entry('5', 5), 
            Map.entry('6', 6), Map.entry('7', 7), Map.entry('8', 8), Map.entry('9', 9), 
            Map.entry('T', 10), Map.entry('J', 1), Map.entry('Q', 12), Map.entry('K', 13),
            Map.entry('A', 14));

        int handType1 = getHandType(hand1);
        int handType2 = getHandType(hand2);
        if (handType1 == handType2){
            for(int i = 0; i< hand1.length();i++){
                char char1 = hand1.charAt(i);
                char char2 = hand2.charAt(i);
                if (cardMap.get(char1) < cardMap.get(char2)){
                    hand1Bigger = false;
                    break;
                }else if(cardMap.get(char1) > cardMap.get(char2)){
                    break;
                }
            }
        }else{
            hand1Bigger = (handType1 > handType2);
        }
        return hand1Bigger;
    }

    public static int getHandType(String hand){
        Map<Character, Integer> charCountMap = new HashMap<>();
        
        int jCount = 0;
        for (char character : hand.toCharArray()){
            if (character == 'J'){
                jCount ++;
            }else{
                charCountMap.put(character,charCountMap.getOrDefault(character,0) + 1);
            }
        }

        char maxKey = extractMax(charCountMap);
        for(int j = 0; j < jCount;j++){
            charCountMap.put(maxKey,charCountMap.getOrDefault(maxKey,0) + 1);
        }

        List<Integer> values = new ArrayList<>(charCountMap.values());
        if(values.size() == 1){
            return 7;
        }else if(values.size() == 2){
            return Math.max(values.get(0),values.get(1)) + 2;
        }else if(values.size() == 3){
            int maxValue = Math.max(values.get(0),values.get(1));
            return Math.max(maxValue,values.get(2)) + 1;
        }else if(values.size() == 4){
            return 2;
        }else{
            return 1;
        }
    }

    public static char extractMax(Map<Character,Integer> map){
        int currentMax = 0;
        char returnChar = 'J';
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > currentMax) {
                currentMax = entry.getValue();
                returnChar = entry.getKey();
            }
        }
        return returnChar;
    }

    public static List<String> sortHands(List<String> hands){
        // Appy quick sort to the camel poker game
        if (hands.size() <= 1){
            return hands;
        }else{
            String pivot = hands.get(hands.size() / 2);
            List<String> left = new ArrayList<>();
            List<String> right = new ArrayList<>();

            for(int i = 0; i < hands.size();i++){
                if (i != hands.size()/2){
                    if (!compareHands(hands.get(i),pivot)){
                        left.add(hands.get(i));
                    }else{
                        right.add(hands.get(i));
                    }
                }
            }
            List<String> sortedLeft = sortHands(left);
            List<String> sortedRight = sortHands(right);
            sortedLeft.add(pivot);
            sortedLeft.addAll(sortedRight);
            return sortedLeft; 
        }
    }

}
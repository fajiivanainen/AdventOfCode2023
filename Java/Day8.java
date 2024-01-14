import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class Day8 {

    public static Map<String,List<String>> sourceToTargets = new HashMap<>();
    public static String initialSource;

    public static void main(String[] args) throws IOException{
        String path = "Day8_input.txt";

        FileReader fileReader = new FileReader(path);
        List<String> lines = fileReader.lines();

        String lrInfo = lines.get(0);

        for(int i = 2;i < lines.size();i++){
            if (!lines.get(i).isEmpty()){
                String[] sourcesAndTargets = lines.get(i).replaceAll("[\\s()]","").split("=");
                String source = sourcesAndTargets[0];
                if (i == 2){
                    initialSource = source;
                }
                List<String> target = Arrays.asList(sourcesAndTargets[1].split(","));
                sourceToTargets.put(source, target);
            }
        }
        // String steps = returnTarget(lrInfo,1);
        // System.out.println(steps);

        // Map<String,Integer> test = findSubSequence(lrInfo);
        // System.out.println(test);

        int steps = amountOfStepsGPT(lrInfo);
        System.out.println(steps);

        
    }

    public static int amountOfSteps(String lrInfo){   
        int i = 0;
        boolean z_found = false;
        String source = initialSource;
        while(!z_found){
            int j = i % lrInfo.length();
            char LorR = lrInfo.charAt(j);
            if (LorR == 'L'){
                source = sourceToTargets.get(source).get(0);
            }else{
                source = sourceToTargets.get(source).get(1);
            }
            System.out.println(source);
            if (source.equals("ZZZ")){
                z_found = true;
            }
            i++;
        }

        return i;
    }

    public static int amountOfStepsGPT(String lrInfo) {
        int i = 0;
        String source = initialSource;

        Set<String> endPoints = new HashSet<>();
        endPoints.add("ZZZ");

        // Calculate the length of the cycle
        int cycleLength = calculateCycleLength(source);

        // Calculate the number of full cycles before reaching ZZZ
        int fullCycles = (lrInfo.length() / cycleLength) * endPoints.size();

        while (!endPoints.contains(source)) {
            int j = i % lrInfo.length();
            char LorR = lrInfo.charAt(j);
            
            source = (LorR == 'L') ? sourceToTargets.get(source).get(0) : sourceToTargets.get(source).get(1);

            i++;
        }

        return fullCycles + i;
    }

    private static int calculateCycleLength(String startSource) {
        int cycleLength = 0;
        String source = startSource;
        Set<String> visited = new HashSet<>();

        while (!visited.contains(source)) {
            visited.add(source);
            source = sourceToTargets.get(source).get(0);
            cycleLength++;
        }

        return cycleLength;
    }


    // public static int fastAmountOfSteps(String lrInfo){
    //     Map<String, String> sourceToTarget = new HashMap<>();
    //     Map<String,Integer> sourceToSteps = new HashMap<>();
    //     for(int i = 0;i<sources.size();i++){
    //         String source = sources.get(i);
    //         List<String> targetAndSteps = returnTarget(lrInfo,i);
    //         String target = targetAndSteps.get(0);
    //         int steps = targetAndSteps.get(1).length();
    //         if(steps != 0){
    //             sourceToSteps.put(source,steps);
    //         }
    //         sourceToTarget.put(source,target);
    //     }
    //     String currentSource = sources.get(0);
    //     String previousSource = sources.get(0);
    //     int iterations = 0;
    //     while(!currentSource.equals("ZZZ")){
    //         previousSource = currentSource;
    //         currentSource = sourceToTarget.get(currentSource);
    //         iterations++;
    //     }

    //     int stepsTaken = sourceToSteps.get(previousSource);

    //     return ((iterations -1)*lrInfo.length() + stepsTaken);
    // }

    // public static List<String> returnTarget(String lrInfo, int initialIndex){
    //     String source = sources.get(initialIndex);
    //     String steps = "";
    //     for(int i = 0;i<lrInfo.length();i++){
    //         steps = steps + "i";
    //         int index = sources.indexOf(source);
    //         int j = i % lrInfo.length();
    //         char LorR = lrInfo.charAt(j);
    //         if (LorR == 'L'){
    //             source = targets.get(index).get(0);
    //         }else{
    //             source = targets.get(index).get(1);
    //         }
    //         if(source.equals("ZZZ")){
    //             break;
    //         }
    //     }
    //     List<String> sourceAndSteps = new ArrayList<>();
    //     sourceAndSteps.add(source);
    //     if(source.equals("ZZZ")){
    //         sourceAndSteps.add(steps);
    //     }else{
    //         sourceAndSteps.add("");
    //     }
    //     return sourceAndSteps;
    // }

    // public static Map<String,Integer> findSubSequence(String lrInfo){
    //     Map<String,Integer> returnMap = new HashMap<>();
    //     for(int i = 0;i<sources.size();i++){
    //         String source = sources.get(i);
    //         String target = returnTarget(lrInfo,i).get(0);
    //         if (target.equals("ZZZ")){
    //             returnMap.put(source,lrInfo.length());
    //         }
    //     }

    //     if(lrInfo.length() <= 1){
    //         return returnMap;
    //     }else{
    //         String newlrInfo = lrInfo.substring(0,lrInfo.length() - 1);
    //         returnMap.putAll(findSubSequence(newlrInfo));
    //         return returnMap;
    //     }
    // }
}
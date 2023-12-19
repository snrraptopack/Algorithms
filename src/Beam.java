import java.util.Arrays;
class Beam{
    public static void main(String[] args) {
        predictPosition(10,8);
    }
    public static void predictPosition(int numberOfBalls , int slots){
        int[] position = new int[slots];
        int[] filled = new int[slots];
        Arrays.fill(position,7);//let all of them have the same probability of having a chance....

        for(int i=1;i<=numberOfBalls;i++){
            //this loop track the path taken
            for(int j=1;j<=slots-1;j++){
                int generatedNum = (int)(Math.random()*2);
                int startPivot = getTheMaxAndMinIndex(position)[0];
                int endPivot = getTheMaxAndMinIndex(position)[1];
                getThePathTaken(generatedNum,position,j,startPivot,endPivot);
            }
            filled[getTheMaxAndMinIndex(position)[0]]++;
            Arrays.fill(position,7);
        }
        System.out.println(Arrays.toString(filled));
    }
    public static void getThePathTaken(int generatedNum,int[] position,int currentCount,int startPivot,int endPivot){
        //this method adds the count to positions that  the ball moved towards
        //and deduct the current count from the position that  was moved against
        boolean isGreater = generatedNum>0;
        if(isGreater){
            position[startPivot] = position[startPivot]-currentCount;
        }else{
            position[endPivot] = position[endPivot]-currentCount;
        }
        for(int i=(isGreater? startPivot+1: startPivot); i<=(isGreater? endPivot: endPivot-1); i++){
            position[i] = position[i]+currentCount;
        }
    }
    public static  int[] getTheMaxAndMinIndex(int[] position){
        int[] maxAndMin = new int[2];
        // get the first index that has the maximum num
        for(int i=0;i<position.length;i++){
            if(position[i]==getMaxNumber(position)){
                maxAndMin[0]=i;
                break;
            }
        }

        // get the last num that has the maximum number
        for(int i=position.length-1;i> -1;i--){
            if(position[i]==getMaxNumber(position)){
                maxAndMin[1]=i;
                break;
            }
        }
        return maxAndMin;
    }
    public static  int getMaxNumber(int[] position){
        int max =0;
        for (int possibleMax : position) {
            max = Math.max(max, possibleMax);
        }
        return max;
    }
}

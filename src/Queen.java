import java.util.Arrays;

class Queen {
    public static void main(String[] args) {
        String[][] queenArray = new String[8][8];
        placeQueens(queenArray);
        display(queenArray);
    }

    public static void placeQueens(String[][] queens){
        int len = queens.length;
        int[] occupiedCol = new int[len];
        // for all we know the positions the element can occupy start from 0-len-1 thus 0-7,
        // so I filled it all to avoid having 0 conflict
        Arrays.fill(occupiedCol,10);
        int gen = 4;//(int)(Math.random()*len);
        int prev = gen;
        occupiedCol[gen]=gen;
        int filled = 1;
        queens[0][gen]="Q";
        // this loop places the items starting from the generated first queen position
        for(int i=1;i<(len-gen);i++){
            int temp =0;
            for(int j=gen;j<len;j+=2){
                if(canPlace(occupiedCol,prev,j)){
                    queens[i][j]="Q";
                    occupiedCol[j]=j;
                    temp=j;
                    filled++;
                    break;
                }
            }
            prev=temp;
        }
        // this loop fills the gap that was left by the first loop
        //note this solution was brought up by mee it can generate all queens
        //when the first item is at index for this it will only
        // insert up to 7th queen leaving one place not solved
        for(int i=filled; i<len; i++){
            int temp = 0;
            for(int j=0;j<len;j++){
                if( canPlace(occupiedCol,prev,j)){
                    queens[i][j]="Q";
                    occupiedCol[j]=j;
                    temp=j;
                    break;
                }
            }
            prev = temp;
        }
        System.out.println(filled);
    }
    public static boolean canPlace(int[] occupiedCol,int prev,int j){
        // this method helps to check if the col has been already occupied or not
        //and it helps to check if the queen is place in the diagonal to avoid it
        for(int place : occupiedCol) if(place==j) return  false;
        if((j+1)==prev || (j-1)==prev || j==prev) return  false;
        return true;
    }
    public static void display(String[][] queenArray){
        // this method is used to display the element in the array
        for(String[] queens : queenArray){
            for(int i=0; i< queens.length;i++){
                if((i+1)%8==0) System.out.println(" | " + (queens[i]==null? " " :queens[i])+ " | " + " ");
                else System.out.print(" | " + (queens[i]==null? " " :queens[i]));
            }
        }
    }
}
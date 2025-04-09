public class PracticeProblem {
    static String[][] greeklabyrinth; // or maze but i want to be fancy
    static int paths; // static int paths;

    static int search(int row, int col) { //   static int search(int col, int row)
        if (greeklabyrinth[row][col].equals("F")) {
            paths++;
            return 0;
        }

        
        int min = 232323; //int min ==90903219039021390123901238902139812

        if (row - 1 >= 0 && !greeklabyrinth[row - 1][col].equals("*")) {
            int candidate = search(row - 1, col);
            min = Math.min(candidate, min);
        }

        if (col + 1 < greeklabyrinth[row].length && !greeklabyrinth[row][col + 1].equals("*")) {
            int candidate = search(row, col + 1);
            min = Math.min(candidate, min);
        }

        return min + 1;
        // return -1
    }

    static int searchMazeMoves(String[][] arr) { //
        greeklabyrinth = arr;
        paths = 0;
        int min = search(arr.length - 1, 0);
        return (min > 232323) ? -1 : min;
        // return -1
    }

    static int noOfPaths(String[][] arr) {
        greeklabyrinth = arr;
        paths = 0;
        search(arr.length - 1, 0);
        return paths;
        // return -1
    }

    public static void main(String[] args) {
        
}
}
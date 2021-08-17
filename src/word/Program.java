package word;

import java.util.*;

public class Program {

    private Scanner scanner = new Scanner(System.in);
    private char[] letters;
    private char[] word;
    private int length;
    private char[][] matrix;
    private String[] coordinates;

    public void createMatrix(String line) {
        this.letters = line.toCharArray();
        length = (int) Math.sqrt(letters.length);
        int counter = 0;
        this.matrix = new char[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                this.matrix[i][j] = letters[counter];
                counter++;
            }
        }
    }

    public void findWord(String line) {
        word = line.toCharArray();
        coordinates = new String[word.length];
        int[] currentPoint = new int[]{0, 0}; // coordinates
        loop:
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length; j++) {
                currentPoint[0] = i;
                currentPoint[1] = j;
                for (int k = 0; k < word.length - 1; k++) {
                    if (matrix[currentPoint[0]][currentPoint[1]] == word[k]) {
                        coordinates[k] = toString(currentPoint);
                        currentPoint = findLetter(currentPoint[0], currentPoint[1], word[k + 1]);
                        if (k + 1 == word.length - 1) {
                            break loop;
                        } else if (currentPoint == null) {
                            currentPoint = new int[2];
                            break;
                        }
                    } else break;
                }
            }
        }
        for (int i = 0; i < coordinates.length; i++) {
            if (currentPoint == null) {
                System.out.println("no matches");
                return;
            }
        }
        coordinates[coordinates.length - 1] = toString(currentPoint);

        System.out.println(Arrays.toString(coordinates));

    }

    public void printMatrix() {
        for (int i = 0; i < length; i++) {
            System.out.print("| ");
            for (int j = 0; j < length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("|");
        }
    }

    public String toString(int[] coordinates) {
        return coordinates[0] + "-" + coordinates[1];
    }

    private int[] findLetter(int i, int j, char letter) {
        int[] coordinates = new int[2];
        if (i < length - 1 && matrix[i + 1][j] == letter) {
            coordinates[0] = i + 1;
            coordinates[1] = j;
            return coordinates;
        } else if (j < length - 1 && matrix[i][j + 1] == letter) {
            coordinates[0] = i;
            coordinates[1] = j + 1;
            return coordinates;
        } else if (i != 0 && matrix[i - 1][j] == letter) {
            coordinates[0] = i - 1;
            coordinates[1] = j;
            return coordinates;
        } else if (j != 0 && matrix[i][j - 1] == letter) {
            coordinates[0] = i;
            coordinates[1] = j - 1;
            return coordinates;
        }
        return null;
    }
}

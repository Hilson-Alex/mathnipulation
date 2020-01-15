package document.project.java;

import document.project.java.math.algebra.Matrix;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        int[][] ok = new int[3][3];
        int a = 1;
        for (int i = 0; i < ok.length; i++){
            for (int j = 0; j < ok[i].length; j++){
                ok[i][j] = a++;
                System.out.print(ok[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        Integer[][] changed = Matrix.exchangeColumn(Matrix.wrap(ok),0, 2);
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                System.out.print(ok[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                System.out.print(changed[i][j] + " ");
            }
            System.out.println();
        }
    }
}

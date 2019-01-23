package com.example.juanra.juegov12;

import java.util.Random;

public class MatrizJuego {
    private int filas, columnas, nelementos;
    private int[][] matriz;
    public MatrizJuego(int f, int c, int e){
        filas=f;
        columnas=c;
        nelementos=e;
        matriz=new int[f][c];
        rellenarMatriz();
    }
    public void rellenarMatriz(){
        Random r=new Random(System.currentTimeMillis());
        for(int i=0; i<filas; i++){
            for(int j=0; j<columnas; j++){
                matriz[i][j]=r.nextInt(nelementos);
            }
        }
    }
    public int[][] getMatriz(){
        return matriz;
    }
}

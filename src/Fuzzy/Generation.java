/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fuzzy;

/**
 *
 * @author Win10
 */
public class Generation {
    
    //buat nilai masukan
    private static double generation;
    
    //range pada model fuzzy
    private static double titik1 = 0;
    private static double titik2 = 100;
    private static double titik3 = 200;
    
    //untuk mengakses nilai pada var
    public static double getGeneration(){
        return generation;
    }
    
    // method untuk memberi nilai 
    public static void setGeneration (double generation){ 
        Generation.generation = generation;
    }
    
    //public fungsi sedikit
    public static double sedikit() {
        if (generation >= titik1 && generation <= titik2){
            return 1;
        }else if (generation > titik2 && generation < titik3){
            return (titik3 - generation) / (titik3 - titik2);
        }else {
            return 0;
        }
    }
    
    //method fungsi banyak 
    public static double banyak(){
        if (generation > titik2 && generation < titik3) {
            return (generation - titik2) / (titik3 - titik2);
        }else if (generation >= titik3){
            return 1;
        }else {
            return 0;
        }
    }
}

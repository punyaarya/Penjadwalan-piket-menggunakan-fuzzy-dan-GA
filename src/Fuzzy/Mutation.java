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
public class Mutation {
    //buat masukan 
    private static double mutation;
    //buat var titik-titik range pada model fuzzy
    private static double titik1 = 0;
    private static double titik2 = 0.005;
    private static double titik3 = 0.01;
    
    //untuk akses nilai pada var
    public static double getMutation(){
        return mutation;
    }
    
    //method buat ngasih nilai vari
    public static void setMutation (double mutation){
        Mutation.mutation = mutation;
    }
    
    //method kurang 
    public static double kecil(){
        if(mutation >= titik1 && mutation <= titik2){
            return 1;
        }else if (mutation > titik2 && mutation < titik3){
            return (titik3 - mutation) / (titik3 - titik2);
        }else {
            return 0;
        }
    }
    
    //method fungsi tambah
    public static double besar(){
        if (mutation > titik2 && mutation < titik3){
            return (mutation - titik2) / (titik3 - titik2);
        }else if (mutation >= titik3) {
            return 1;
        }else {
            return 0;
        }
    }
    
    public static double kecil(double in) {
        return (titik3 - (in * (titik3 - titik2)));
    }
    
    public static double besar (double in){
        return (titik2 + (in * (titik3-titik2)));
    }
}

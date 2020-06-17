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
public class Populasi {
    
    
   //var buat masukan nilai
    private static double populasi;
    
    //var titik range buat model fuzzy nya
    private static double titik1=0;
    private static double titik2=50;
    private static double titik3=100;
    
    //buat akses nilai di var population
    public static double getPopulation(){
        return populasi;
    }
    
    //buat ngasih nilai var population
    public static void setPopulation(double populasi){
        Populasi.populasi = populasi;
    }
    
    //fungsi turun 
    public static double kecil(){
        if (populasi >= titik1 && populasi<=titik2){
            return 1;
        }else if (populasi > titik2 && populasi < titik3){
            return (titik3-populasi)/(titik3-titik2);
        }else{
            return 0;
        }
    }
    
    //fungsi naik
    public static double besar(){
        if (populasi > titik2 && populasi < titik3){
            return (populasi - titik2) / (titik3-titik2);
        }else if (populasi >= titik3){
            return 1;
        }else {
            return 0;
        }
    }
}

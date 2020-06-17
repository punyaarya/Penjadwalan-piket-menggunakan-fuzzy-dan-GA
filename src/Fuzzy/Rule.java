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
public class Rule {
    private static double [] u_produk = new double[6];
    private static double [] z_produk = new double[6];
    private static double bobot;
    
    public static void hitung_u(){
        u_produk[1]=Math.min(Populasi.kecil(), Generation.banyak());
        u_produk[2]=Math.min(Populasi.kecil(), Generation.sedikit());
        u_produk[3]=Math.min(Populasi.besar(), Generation.banyak());
        u_produk[4]=Math.min(Populasi.besar(), Generation.sedikit());
    }
    
    public static void hitung_z(){
        z_produk[0]=Mutation.kecil(u_produk[0]);
        z_produk[1]=Mutation.kecil(u_produk[1]);
        z_produk[2]=Mutation.besar(u_produk[2]);
        z_produk[3]=Mutation.kecil(u_produk[3]);
    }
    
    //nyari nilai bobot
    public static double bobot(){
        double atas=0, bawah=0;
        for(int i=0;i<6;i++){
            System.out.println("z_ke"+i+"="+z_produk[i]);
            System.out.println("u_ke"+i+"="+u_produk[i]);
            atas+=(u_produk[i]*z_produk[i]);
            bawah+=u_produk[i];
        }
        System.out.println(atas/bawah);
        return (atas/bawah);
    }
}

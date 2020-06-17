package Penjadwalan;

import java.io.*;
import java.text.DecimalFormat;
import Fuzzy.Rule;
import Fuzzy.Populasi;
import Fuzzy.Generation;

public class TimetableGA {

    public static void main(String[] args) {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        double generasi = 0, popSize = 0;
        double mr = 0;
        
        try {
            System.out.print ("Masukan Jumlah generasi max : ");
            generasi =Double.parseDouble(br.readLine());
            
            System.out.print("Masukan PopSize : ");
            popSize = Double.parseDouble(br.readLine());
            System.out.println("************************************************");
        }catch (IOException eox){
            System.out.println("eox");
        }
        
        //ambil bobot dari fuzzy
        Populasi.setPopulation(popSize);
        Generation.setGeneration(generasi);
        Rule.hitung_u();
        Rule.hitung_z();
        
        // Mutatation Rate
        mr = Rule.bobot();
        System.out.println("********************************************************");
        System.out.println("MR adalah" + mr);
        System.out.println("**********************************************************");
    	// Get a Timetable 
        Timetable timetable = initializeTimetable();       
        // Inisialisasi GA
        GeneticAlgorithm ga = new GeneticAlgorithm((int) popSize, mr, 0.9, 2, 5);       
        // Initialize Populasi
        Population population = ga.initPopulation(timetable);       
        // Evaluasi populasi
        ga.evalPopulation(population, timetable);
               
        int generation = 1;
        // Perulangan evolusi
       while (ga.isTerminationConditionMet(generation, (int) generasi) == false) {
//        while (ga.isTerminationConditionMet(generation, 1000) == false
//            && ga.isTerminationConditionMet(population) == false) {
            // Print fitness
            System.out.println("G" + generation + " Best fitness: " + population.getFittest(0).getFitness());
            //crossover
            population = ga.crossoverPopulation(population);
            // mutation
            population = ga.mutatePopulation(population, timetable);
            //evaluasi population
            ga.evalPopulation(population, timetable);
            // Increment generasi
            generation++;
        }
        // Print fitness
        timetable.createClasses(population.getFittest(0));
        System.out.println();
        System.out.println("Solusi ditemukan di " + generation + " generations");
        System.out.println("Final solution fitness: " + population.getFittest(0).getFitness());
        System.out.println("Bentrok: " + timetable.calcClashes());

        System.out.println();
        Class classes[] = timetable.getClasses();
        int classIndex = 0;
        for (Class bestClass : classes) {
            System.out.println("Kelas " + classIndex + ":");
            System.out.println("Pelajaran: " + timetable.getModule(bestClass.getModuleId()).getModuleName());
            System.out.println("Group: " + timetable.getGroup(bestClass.getGroupId()).getGroupId());
            System.out.println("Ruangan: " + timetable.getRoom(bestClass.getRoomId()).getRoomNumber());
            System.out.println("Pengajar: " + timetable.getProfessor(bestClass.getProfessorId()).getProfessorName());
            System.out.println("Waktu: " + timetable.getTimeslot(bestClass.getTimeslotId()).getTimeslot());
            System.out.println("********");
            classIndex++;
        }
    }
	private static Timetable initializeTimetable() {
		// Membuat Jadwal
		Timetable timetable = new Timetable();
                
		timetable.addRoom(1, "A1", 15);
		timetable.addRoom(2, "B1", 30);
		timetable.addRoom(4, "D1", 20);
		timetable.addRoom(5, "F1", 25);

		timetable.addTimeslot(1, "Senin 9:00 - 11:00");
		timetable.addTimeslot(2, "Senin 11:00 - 13:00");
		timetable.addTimeslot(3, "Senin 13:00 - 15:00");
		timetable.addTimeslot(4, "Selasa 9:00 - 11:00");
		timetable.addTimeslot(5, "Selasa 11:00 - 13:00");
		timetable.addTimeslot(6, "Selasa 13:00 - 15:00");
		timetable.addTimeslot(7, "Rabu 9:00 - 11:00");
		timetable.addTimeslot(8, "Rabu 11:00 - 13:00");
		timetable.addTimeslot(9, "Rabu 13:00 - 15:00");
		timetable.addTimeslot(10, "Kamis 9:00 - 11:00");
		timetable.addTimeslot(11, "Kamis 11:00 - 13:00");
		timetable.addTimeslot(12, "Kamis 13:00 - 15:00");
		timetable.addTimeslot(13, "Jumat 9:00 - 11:00");
		timetable.addTimeslot(14, "Jumat 11:00 - 13:00");
		timetable.addTimeslot(15, "Jumat 13:00 - 15:00");

		timetable.addProfessor(1, "Pak Toni");
		timetable.addProfessor(2, "Bu Ranti");
		timetable.addProfessor(3, "Bu Siti");
		timetable.addProfessor(4, "Pak Doni");

		timetable.addModule(1, "cs1", "TIK", new int[] { 1, 2 });
		timetable.addModule(2, "en1", "Bahasa Inggris", new int[] { 1, 3 });
		timetable.addModule(3, "ma1", "Matematika", new int[] { 1, 2 });
		timetable.addModule(4, "ph1", "Fisika", new int[] { 3, 4 });
		timetable.addModule(5, "hi1", "Sejarah", new int[] { 4 });
		timetable.addModule(6, "dr1", "Biologi", new int[] { 1, 4 });

		timetable.addGroup(1, 10, new int[] { 1, 3, 4 });
		timetable.addGroup(2, 30, new int[] { 2, 3, 5, 6 });
		timetable.addGroup(3, 18, new int[] { 3, 4, 5 });
		timetable.addGroup(4, 25, new int[] { 1, 4 });
		timetable.addGroup(5, 20, new int[] { 2, 3, 5 });
		timetable.addGroup(6, 22, new int[] { 1, 4, 5 });
		timetable.addGroup(7, 16, new int[] { 1, 3 });
		timetable.addGroup(8, 18, new int[] { 2, 6 });
		timetable.addGroup(9, 24, new int[] { 1, 6 });
		timetable.addGroup(10, 25, new int[] { 3, 4 });
		return timetable;
	}
}

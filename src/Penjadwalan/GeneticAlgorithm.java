package Penjadwalan;


public class GeneticAlgorithm {

	private int populationSize;
	private double mutationRate;
	private double crossoverRate;
	private int elitismCount;
	protected int tournamentSize;

	public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate, int elitismCount,
			int tournamentSize) {

		this.populationSize = populationSize;
		this.mutationRate = mutationRate;
		this.crossoverRate = crossoverRate;
		this.elitismCount = elitismCount;
		this.tournamentSize = tournamentSize;
	}

        //inisialisasi populasi
	public Population initPopulation(Timetable timetable) {
		Population population = new Population(this.populationSize, timetable);
		return population;
	}
         //Pengecekan jika populasi sdh menemukan kondisi berhenti
	public boolean isTerminationConditionMet(int generationsCount, int maxGenerations) {
		return (generationsCount > maxGenerations);
	}

	public boolean isTerminationConditionMet(Population population) {
		return population.getFittest(0).getFitness() == 1.0;
	}
        //Menghitung nilai fitnes dari individu
	public double calcFitness(Individual individual, Timetable timetable) {
		Timetable threadTimetable = new Timetable(timetable);
		threadTimetable.createClasses(individual);
		// Menghitung
		int clashes = threadTimetable.calcClashes();
		double fitness = 1 / (double) (clashes + 1);
		individual.setFitness(fitness);

		return fitness;
	}


	 //Evaluasi populasi
	public void evalPopulation(Population population, Timetable timetable) {
		double populationFitness = 0;
		// Perulangan untuk evaluasi lalu dijumlah dgn CalcFitness
		for (Individual individual : population.getIndividuals()) {
			populationFitness += this.calcFitness(individual, timetable);
		}

		population.setPopulationFitness(populationFitness);
	}

        //Memilih parent u/ crossover menggunakan tournament selection
	public Individual selectParent(Population population) {
		Population tournament = new Population(this.tournamentSize);
		population.shuffle();
		for (int i = 0; i < this.tournamentSize; i++) {
			Individual tournamentIndividual = population.getIndividual(i);
			tournament.setIndividual(i, tournamentIndividual);
		}

		// return ke hasil terbaik
		return tournament.getFittest(0);
	}

	public Population mutatePopulation(Population population, Timetable timetable) {
		Population newPopulation = new Population(this.populationSize);
		for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
			Individual individual = population.getFittest(populationIndex);
			Individual randomIndividual = new Individual(timetable);
			for (int geneIndex = 0; geneIndex < individual.getChromosomeLength(); geneIndex++) {
				if (populationIndex > this.elitismCount) {
					if (this.mutationRate > Math.random()) {
						individual.setGene(geneIndex, randomIndividual.getGene(geneIndex));
					}
				}
			}
			newPopulation.setIndividual(populationIndex, individual);
		}

		return newPopulation;
	}
            //Menambahkan Crossover ke populasi
	public Population crossoverPopulation(Population population) {
		// Membuat populasi baru
		Population newPopulation = new Population(population.size());
		// Pengulangan mendptkan populasi saat ini dgn nilai fitness
		for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
			Individual parent1 = population.getFittest(populationIndex);
                        //Percabangan untuk menentukan melakukan crossover pada individu
			if (this.crossoverRate > Math.random() && populationIndex >= this.elitismCount) {
				Individual offspring = new Individual(parent1.getChromosomeLength());
                                //mencari parent2 dengan turnamen selection
				Individual parent2 = selectParent(population);
				for (int geneIndex = 0; geneIndex < parent1.getChromosomeLength(); geneIndex++) {
					if (0.5 > Math.random()) {
						offspring.setGene(geneIndex, parent1.getGene(geneIndex));
					} else {
						offspring.setGene(geneIndex, parent2.getGene(geneIndex));
					}
				}
				newPopulation.setIndividual(populationIndex, offspring);
			} else {
				newPopulation.setIndividual(populationIndex, parent1);
			}
		}

		return newPopulation;
	}



}

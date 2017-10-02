package representation;

import moead.Individual;
import moead.LocalSearchOperator;
import moead.MOEAD;
import moead.Service;

public class IndirectLocalSearchOperator extends LocalSearchOperator {

	@Override
	public Individual doSearch(Individual ind, MOEAD init, int problemIndex) {
		if (!(ind instanceof IndirectIndividual))
			throw new RuntimeException("IndirectMutationOperator can only work on objects of type IndirectIndividual.");
		IndirectIndividual indirect = (IndirectIndividual) ind;

		// Choose a fixed index to swap other services with during local search
		int indexA = init.random.nextInt(indirect.getGenome().length);

		IndirectIndividual bestNeighbour = (IndirectIndividual) indirect.clone();
		double bestScore;
		if (MOEAD.tchebycheff)
			bestScore = init.calculateTchebycheffScore(bestNeighbour, problemIndex);
		else
			bestScore = init.calculateScore(bestNeighbour, problemIndex);


		IndirectIndividual neighbour;

		for (int i = 0; i < MOEAD.numLocalSearchTries; i++) {
			neighbour = (IndirectIndividual) indirect.clone();
			// Randomly select index for service to swap
	    	int indexB = init.random.nextInt(neighbour.getGenome().length);
	    	swapServices(neighbour.getGenome(), indexA, indexB);
	    	neighbour.evaluate();
	    	double score;
			if (MOEAD.tchebycheff)
				score = init.calculateTchebycheffScore(neighbour, problemIndex);
			else
				score = init.calculateScore(neighbour, problemIndex);

	    	// If the neighbour has a better fitness score than the current best, set current best to be neighbour
	        if (score < bestScore) {
	        	bestScore = score;
	        	bestNeighbour = neighbour;
	        }
		}
		return bestNeighbour;
	}

	private void swapServices(Service[] genome, int indexA, int indexB) {
		Service temp = genome[indexA];
		genome[indexA] = genome[indexB];
		genome[indexB] = temp;
	}

}

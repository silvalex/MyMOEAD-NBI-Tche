package moead;

/**
 * Represents an abstract mutation operator, to be implemented according to the
 * chosen representation.
 *
 * @author sawczualex
 */
public abstract class MutationOperator {
	/**
	 * Mutates the given individual, returning a modified version.
	 *
	 * @param ind - the original individual
	 * @param init - the main program class
	 * @return Mutated individual
	 */
	public abstract Individual mutate(Individual ind, MOEAD init);
}

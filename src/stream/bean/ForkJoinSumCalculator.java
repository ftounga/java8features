package stream.bean;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final long[] numbers;
	private final int start;
	private final int end;
	
	public static final long THREESHOLD=10000;
	
	
	
	public ForkJoinSumCalculator(long[] numbers) {
		this(numbers,0,numbers.length);
	}

	

	private ForkJoinSumCalculator(long[] numbers, int start, int end) {
		super();
		this.numbers = numbers;
		this.start = start;
		this.end = end;
	}



	@Override
	protected Long compute() {
		int length = end - start;
		if (length<=THREESHOLD){
			return computeSequentially();
		}
		ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start+length/2);
		leftTask.fork();
		ForkJoinSumCalculator rigthTask = new ForkJoinSumCalculator(numbers, start+length/2, end);
		Long rigthResult =rigthTask.compute();
		Long leftResult= leftTask.join();		
		return rigthResult+leftResult;
	}


/**
 * Cette méthode calcule la somme de façon itérative
 * @return
 */
	private Long computeSequentially() {
		long sum = 0;
		for(int i  = start; i<end;i++){
			sum+=numbers[i];
		}
		return sum;
	}
	

}

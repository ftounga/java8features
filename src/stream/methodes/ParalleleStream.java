package stream.methodes;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import stream.bean.ForkJoinSumCalculator;

public class ParalleleStream {

	private static class Accumulator{
		public long total = 0;
		public void add(long value){
			total+=value;
		}
	}
	/**
	 * Cette classe va nous permettre de mesurer les temps d'execution entre une fonction itérative, sequentielle, et parallel
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("iterative sum done in :" +measureSumPerf(ParalleleStream::iterativeSum, 10000000) +" ms");
		System.out.println("sequential sum done in :" +measureSumPerf(ParalleleStream::sequentialSum, 10000000)+" ms");
		System.out.println("sequential sum with rangclosed method done in :" +measureSumPerf(ParalleleStream::sequentialSumWithRangeClosed, 10000000)+" ms");
		System.out.println("parallel sum done in :" +measureSumPerf(ParalleleStream::parallelSum, 10000000)+" ms");
		System.out.println("parallel sum with rangclosed method done in :" +measureSumPerf(ParalleleStream::parallelSumWithRangeClosed, 10000000)+" ms");
		
		/**Utilisation de variable mutée et partagée par les 2 flux lors du calcul de la somme en parallel***/
		System.out.println("parallel sum with side effect :" +measureSumPerf(ParalleleStream::sideEffectSum, 10000000)+" ms");	
		
		System.out.println("parallel sum with fork/join framework of java 7 :" +measureSumPerf(ParalleleStream::forkJoinSum, 10000000)+" ms");
		
	}
	/**
	 * Permet de faire la somme des n premiers nombres de facon séquentielle
	 * 
	 * @param n
	 * @return
	 */
	public static long sequentialSum(long n) {
		return Stream.iterate(1L, i -> i + 1).limit(n).reduce(0L, Long::sum);
	}
	
	/**
	 * Permet de faire la somme des n premiers nombres de facon séquentielle avec rangeClosed
	 * 
	 * @param n
	 * @return
	 */
	public static long sequentialSumWithRangeClosed(long n) {
		return LongStream.rangeClosed(1, n).reduce(0L, Long::sum);
	}
	
	/**
	 * Permet de faire la somme des n premiers nombres de facon séquentielle avec rangeClosed
	 * 
	 * @param n
	 * @return
	 */
	public static long parallelSumWithRangeClosed(long n) {
		return LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
	}
	
	/**
	 * Permet de calculer la somme des n premiers nombres de facon parallele
	 * @param n
	 * @return
	 */
	public static long parallelSum(long n) {
		return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L, Long::sum);
	}

	/**
	 * Permet de la somme des n premiers nombre de facon iterative
	 * @param n
	 * @return
	 */
	public static long iterativeSum(long n) {
		long result = 0;
		for (long i = 1L; i <= n; i++) {
			result += i;
		}
		return result;
	}
	public static long measureSumPerf(Function<Long,Long> adder, long n){
		long fastest= Long.MAX_VALUE;
		for (int i=0; i<10;i++){
			long start =System.nanoTime();
			long sum = adder.apply(n);
			System.out.println("La somme des 1000000 premiers nombres est: "+sum);
			long duration = (System.nanoTime() - start)/1000000;
			if (duration<fastest) fastest = duration;
		}
		return fastest;
	}
	
	/**
	 * Perform a sum in parallel with side effects by using a shared accumulator
	 * @param n
	 * @return
	 */
	public static long sideEffectSum(long n){
		Accumulator accumulator = new Accumulator();
		LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
		return accumulator.total;		
	}
	
	public static long forkJoinSum(long n){
		long[] numbers = LongStream.rangeClosed(1, n).toArray();
		ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
		return new ForkJoinPool().invoke(task);
	}
	
	
}

package stream.methodes;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParalleleStream {

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
			//System.out.println("La somme des 1000000 premiers nombres est: "+sum);
			long duration = (System.nanoTime() - start)/1000000;
			if (duration<fastest) fastest = duration;
		}
		return fastest;
	}
	
	
}

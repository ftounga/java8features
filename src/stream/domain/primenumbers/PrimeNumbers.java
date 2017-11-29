package stream.domain.primenumbers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("La liste des nombres premiers et non premiers allant qu'à 50" + partitionPrimes(50));
		System.out.println("La liste des nombres premiers et non premiers allant qu'à 50 avec un collecteur customisé" + partitionPrimesWithCustomCollector(50));
		
		System.out.println(" La fonction partitionPrime s'execute en: " +getFastestExecutionPartitionPrimes() +" ms");
		System.out.println(" La fonction partitionPrimeWithCustomCollector s'execute en: " +getFastestExecutionPartitionPrimesWithCustomizeCollector() +" ms");
		
	}
	
	static boolean  isPrime(int candidate) {
		int candidateRoot = (int) Math.sqrt((double) candidate);
		return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i ==0);
	}
	
	
	
	static Map<Boolean, List<Integer>> partitionPrimes(int n){
		return IntStream.rangeClosed(2, n).boxed().collect(Collectors.partitioningBy(candidate -> isPrime(candidate)));
	}
	
	static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n){
		return IntStream.rangeClosed(2, n).boxed().collect(new PrimeNumbersCollector());
	}
	
	
	static long getFastestExecutionPartitionPrimes(){	
		long fastest = Long.MAX_VALUE;
		for (int i=0;i<10;i++){
			long start = System.nanoTime();
			partitionPrimes(1000000);
			long duration = (System.nanoTime() -start)/1000000;
			if (duration<fastest) fastest= duration;
		}
		return fastest;
	}
	
	static long getFastestExecutionPartitionPrimesWithCustomizeCollector(){	
		long fastest = Long.MAX_VALUE;
		for (int i=0;i<10;i++){
			long start = System.nanoTime();
			partitionPrimes(1000000);
			long duration = (System.nanoTime() -start)/1000000;
			if (duration<fastest) fastest= duration;
		}
		return fastest;
	}

}

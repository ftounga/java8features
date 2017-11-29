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

}

package stream.methodes;

import java.util.Spliterator;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import stream.bean.ForkJoinSumCalculator;
import stream.bean.WordCounter;
import stream.bean.WordCounterSpliterator;

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
		
		String s = "Le cameroun des grandes ambitions comme le Pa Paul, est prévu en même temps que l'émergence 2035";
		System.out.println("La phrase: --" +s +" --compte: " + countWordsIteratively(s) +" mots");
		
		Stream<Character> stream = IntStream.range(0, s.length()).mapToObj(s::charAt);
		System.out.println("La phrase: --" +s +" -- compte avec le méthode countwordStream: " + countWordStream(stream) +" mots");
		
		Stream<Character> parallelStream = IntStream.range(0, s.length()).mapToObj(s::charAt).parallel();
		System.out.println("La phrase: --" +s +" -- compte avec le méthode countwordStream: " + countWordStream(parallelStream) +" mots, calculés dans un mode parallel");
		
		Spliterator<Character> spliterator = new WordCounterSpliterator(s);
		Stream<Character> parallelStreamSpliterator = StreamSupport.stream(spliterator, true);
		System.out.println("La phrase: --" +s +" -- compte avec le méthode countwordStream: " + countWordStream(parallelStreamSpliterator) +" mots, calculés dans un mode parallel avec un splititerator");
		
	}
	/**
	 * Permet de faire la somme des n premiers nombres de facon séquentielle
	 * 
	 * @param n les n premiers entiers
	 * @return la somme
	 */
	public static long sequentialSum(long n) {
		return Stream.iterate(1L, i -> i + 1).limit(n).reduce(0L, Long::sum);
	}
	
	/**
	 * Permet de faire la somme des n premiers nombres de facon séquentielle avec rangeClosed
	 * 
	 * @param n les n premiers entiers
	 * @return la somme
	 */
	public static long sequentialSumWithRangeClosed(long n) {
		return LongStream.rangeClosed(1, n).reduce(0L, Long::sum);
	}
	
	/**
	 * Permet de faire la somme des n premiers nombres de facon séquentielle avec rangeClosed
	 * 
	 * @param n les n premiers entiers
	 * @return la somme
	 */
	public static long parallelSumWithRangeClosed(long n) {
		return LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
	}
	
	/**
	 * Permet de calculer la somme des n premiers nombres de facon parallele
	 * @param n les n premiers entiers
	 * @return la somme
	 */
	public static long parallelSum(long n) {
		return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(0L, Long::sum);
	}

	/**
	 * Permet de la somme des n premiers nombre de facon iterative
	 * @param n les n premiers entiers
	 * @return la somme
	 */
	public static long iterativeSum(long n) {
		long result = 0;
		for (long i = 1L; i <= n; i++) {
			result += i;
		}
		return result;
	}
	/**
	 * Mesure la vitesse de calcule d'une fonction d'ajout des n premiers entiers, recu en parametre
	 * @param adder function d'ajout
	 * @param n les n premiers entiers
	 * @return la duree d'execution la plus rapide
	 */
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
	 * @return la somme
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
	
	/**
	 * Cette methode compte le nombre de mot dans une phrase de façon itérative
	 * @param s
	 * @return le nombre de mot
	 */
	public static int countWordsIteratively(String s){
		int counter=0;
		boolean lastSpace = true;
		for (char c : s.toCharArray()){
			if (Character.isWhitespace(c)){
				lastSpace = true;
			} else {
				if (lastSpace) counter++;
				lastSpace = false;
			}
			
		}
		return counter;
	}
	
	/**
	 * Cette methode retourne la liste des characteres à partir d'une stream de characteres recus en parametre et grace à un bean accumulateur word counter
	 * @param stream
	 * @return le nombre de mots
	 */
	public static int countWordStream(Stream<Character> stream){
		WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
		return wordCounter.getCounter();
	}
	
}

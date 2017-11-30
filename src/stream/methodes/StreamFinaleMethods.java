package stream.methodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import stream.bean.ToListCollector;
import stream.domain.transactions.Traders;
import stream.domain.transactions.Transaction;
import sun.management.MappedMXBeanType;

public class StreamFinaleMethods {

	/**
	 * This class handle the collectors
	 * **/
	public static void main(String[] args) {
		Traders t1 = new Traders("Douala", "Francky");
		Traders t2 = new Traders("Yaounde", "Ghislain");
		Traders t3 = new Traders("Yaounde", "Raoul");
		
		Transaction trans1 =new Transaction(t1, 500, 2011);
		Transaction trans2 =new Transaction(t1, 410, 2010);
		Transaction trans3 =new Transaction(t2, 200, 2015);
		Transaction trans4 =new Transaction(t2, 700, 2014);
		Transaction trans5 =new Transaction(t2, 1010, 2017);
		Transaction trans6 =new Transaction(t1, 600, 2011);
		Transaction trans7 =new Transaction(t1, 650, 2011);
		Transaction trans8 =new Transaction(t3, 2000, 2011);
		
		List<Transaction> listTrans = Arrays.asList(trans1,trans2,trans3,trans4,trans5, trans6, trans7, trans8);
		
		/**
		 * Reducing and summarizing
		 * **/
		/*counting collectors*/
		long howManyTransactions = listTrans.stream().collect(Collectors.counting());
		System.out.println("Le nombre de transaction est: "+ howManyTransactions);
		
		/**
		 * Find maximum and minimum values
		 * 
		 */
		Comparator<Transaction> transcationComparator = Comparator.comparingInt(Transaction::getValue);
		Optional<Transaction> mostranscationValue = listTrans.stream().collect(Collectors.maxBy(transcationComparator));
		System.out.println("La transaction avec une valeur maximale est: "+ mostranscationValue.get());
		
		/**
		 * Summarization
		 * **/
		
		int totalValue = listTrans.stream().collect(Collectors.summingInt(Transaction::getValue));
		System.out.println("La somme des valeurs des transaction est: "+ totalValue);
		
		/**
		 * Joining String
		 * **/
		String allTradersNames = listTrans.stream().map(t -> t.getTrader().getName()).distinct().sorted((n1,n2)->n1.compareTo(n2)).collect(Collectors.joining(";"));	
		System.out.println("La liste des traders et triée par leur nome et concatenée: "+allTradersNames);
		
		/**
		 * Generalized summarization with reduction
		 */
		int totalReduceValue = listTrans.stream().collect(Collectors.reducing(0,Transaction::getValue, (i,j) -> i+j));
		System.out.println("La somme des valeurs des transaction est: "+ totalReduceValue);
		
		/**
		 * Grouping
		 */
		Map<String, List<Transaction>> traderCityMap = listTrans.stream().collect(Collectors.groupingBy(tr -> tr.getTrader().getCity()));
		System.out.println("La liste des transactions regroupées par ville: "+ traderCityMap.toString());
		
		/**
		 * Grouping with a personilized Key like an enum
		 */
		Map<Ville, List<Transaction>> traderCityEnum = listTrans.stream().collect(Collectors.groupingBy(tr -> {
			if(tr.getTrader().getCity().equals("Yaounde")){
				return Ville.YAOUNDE;
			}else{
				return Ville.DOUALA;
			}
		}));
		System.out.println("La liste des transactions regroupées par ville: "+ traderCityEnum.toString());
		
		/**
		 * Multi level grouping
		 * ***/
		Map<String, Map<Price, List<Transaction>>> traderCityMultiMap = listTrans.stream().collect(Collectors.groupingBy(tr -> tr.getTrader().getCity(), Collectors.groupingBy(tr -> {
			if(tr.getValue() <900){
				return Price.CHEAP;
			}else{
				return Price.EXPENSIVE;
			}
		})));
		System.out.println("La liste des transactions regroupées par ville et par value: "+ traderCityMultiMap.toString());
		
		/****
		 * Partitioning
		 * ****/
		Map<Boolean, List<Transaction>> traderCityPartition = listTrans.stream().collect(Collectors.partitioningBy(tr -> tr.getTrader().getCity().equals("Yaounde")));
		System.out.println("La liste des transactions partitionnées par ville par ville: "+ traderCityPartition.toString());
		
		/**
		 * Customized collectors ToList
		 * **/
		List<Integer> numbers = IntStream.rangeClosed(2, 50).boxed().collect(new ToListCollector<Integer>());
		System.out.println("La taille de la liste de nombre générée grace au collector ToListCollector: "+ numbers.size());
		
		/***The same result is possible without having to implemented collector from scratch. Here we assume that we have: IDENTITY_FINISH**/
		List<Integer> numbers1 = IntStream.rangeClosed(2, 50).boxed().collect(ArrayList::new, List::add, List::addAll);
		System.out.println("La taille de la liste de nombre générée grace sans collector implémenté from scratch: "+ numbers1.size());
		
	}
	
	private static enum Ville{
		DOUALA,YAOUNDE		
	}	
	
	private static enum Price{
		EXPENSIVE,CHEAP		
	}	
	
}

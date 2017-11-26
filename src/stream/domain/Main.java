package stream.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

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
		
		/***
		 * Find all transactions in 2011 sort by value
		 * **/
		List<Transaction> sortedTrans = listTrans.stream().filter(trans -> trans.getYear() ==2011).sorted((tr1,tr2)-> tr1.getValue().compareTo(tr2.getValue())).collect(Collectors.toList());	
		System.out.println("La liste des transactions effectuées en 2011 et triée par valeur: "+sortedTrans.stream().map(t -> t.getValue()).collect(Collectors.toList()).toString());
		
		/**
		 * Find all city where traders live
		 * **/
		List<String> cityList = listTrans.stream().map(t -> t.getTrader().getCity()).distinct().collect(Collectors.toList());	
		System.out.println("La liste des villes dans lesquelles les traders vivent: "+cityList.toString());
		
		/**
		 * Find all trader and sort them by name
		 * **/
		List<String> tradersNames = listTrans.stream().map(t -> t.getTrader().getName()).distinct().sorted((n1,n2)->n1.compareTo(n2)).collect(Collectors.toList());	
		System.out.println("La liste des traders et triée par leur nom: "+tradersNames.toString());
		/**
		 * Find all trader, sort them by name and concatene the output String
		 * **/
		String allTradersNames = listTrans.stream().map(t -> t.getTrader().getName()).distinct().sorted((n1,n2)->n1.compareTo(n2)).collect(Collectors.joining(";"));	
		System.out.println("La liste des traders et triée par leur nome et concatenée: "+allTradersNames);
		
		/**
		 * Is  there any trader based in Milan
		 */		
		Boolean milanBased = listTrans.stream().anyMatch(t->t.getTrader().getCity().equals("Milan"));
		System.out.println("Is there any trader based in Milan? "+milanBased);
		
		/**
		 * Print all transaction value of traders living in Douala
		 */
		listTrans.stream().filter(t->t.getTrader().getCity().equals("Douala")).map(t->t.getValue()).forEach(t->{
			System.out.println("Traders transaction values "+t);
		});
		
		/**
		 * Higthest transactions value
		 * **/
		int max=listTrans.stream().map(t->t.getValue()).reduce(0, Integer::max);
		System.out.println("Higthest transaction values "+max);
		
		/**
		 * Lowest transactions value
		 * **/
		Optional<Integer> min=listTrans.stream().map(t->t.getValue()).reduce(Integer::min);
		System.out.println("Lowest transaction values "+min.get());
		
		/**
		 * Convert to a numeric Stream
		 * **/
		IntStream intStream = listTrans.stream().mapToInt(t->t.getValue());
		int minimum=intStream.sum();
		System.out.println("Lowest transaction values "+minimum);
		
		/**
		 * Convert to an object Stream
		 * */
		Stream<Integer> objectStream = intStream.boxed();
	}

}

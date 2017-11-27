package stream.methodes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import stream.domain.Traders;
import stream.domain.Transaction;
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
		System.out.println("La liste des traders et tri�e par leur nome et concaten�e: "+allTradersNames);
		
		/**
		 * Generalized summarization with reduction
		 */
		int totalReduceValue = listTrans.stream().collect(Collectors.reducing(0,Transaction::getValue, (i,j) -> i+j));
		System.out.println("La somme des valeurs des transaction est: "+ totalReduceValue);
		
	}
	
}
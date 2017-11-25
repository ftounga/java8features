package stream.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
		
		
	}

}

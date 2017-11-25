package stream.methodes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamFilter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,6,7,8,8,4,1,9,10,11,12);
		
		
		/**
		 * Filter with a predicate n -> n % 2 == 0 for pairs numbers
		 * ***/
		List<Integer> pairNumber =list.stream().filter(n -> n%2 ==0).collect(Collectors.toList());
		System.out.println("Les liste des nombres pairs est: " +pairNumber.toString());
		
		/**
		 * Filter with a predicate and select unique element
		 */
		List<Integer> pairNumberUniqueElement =list.stream().filter(n -> n%2 ==0).distinct().collect(Collectors.toList());
		System.out.println("Les liste des nombres pairs est: " +pairNumberUniqueElement.toString());
		/**
		 * Skip,Filter with a predicate and select unique element
		 */
		List<Integer> skipPairNumberUniqueElement =list.stream().filter(n -> n%2 ==0).distinct().skip(2).collect(Collectors.toList());
		System.out.println("Les liste des nombres pairs est: " +skipPairNumberUniqueElement.toString());
	
		/***
		 * Mapping functions. It allows transforms a given element to another one. So we end up with a stream of differents elements
		 * */
		List<String> words = Arrays.asList("Francky","Lile","Ghislain","Nancy","Isaie","Philomene");
		List<Integer> sizeWords = words.stream().map(s ->s.length()).collect(Collectors.toList());
		System.out.println("Les liste des mots: " +words.toString()+" s'est transformée en la liste de leur taille: " +sizeWords.toString());
	
		/**
		 * Flatmap functions help to handle the problem of Stream<Stream<T>>. It allows to convert it into Stream<T>.
		 * */
		List<String> singleUniqueWords= words.stream().map(w -> w.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
		System.out.println("Les liste des charactères uniques contenus dans la liste des noms est: " +singleUniqueWords.toString());

	}

}

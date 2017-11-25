package stream.methodes;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamFilter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		List<Integer> listNum = Arrays.asList(1,2,3,4,5,6,6,7,8,8,4,1,9,10,11,12);
		
		
		/**
		 * Filter with a predicate n -> n % 2 == 0 for pairs numbers
		 * ***/
		List<Integer> pairNumber =listNum.stream().filter(n -> n%2 ==0).collect(Collectors.toList());
		System.out.println("Les liste des nombres pairs est: " +pairNumber.toString());
		
		/**
		 * Filter with a predicate and select unique element
		 */
		List<Integer> pairNumberUniqueElement =listNum.stream().filter(n -> n%2 ==0).distinct().collect(Collectors.toList());
		System.out.println("Les liste des nombres pairs est: " +pairNumberUniqueElement.toString());
		/**
		 * Skip,Filter with a predicate and select unique element
		 */
		List<Integer> skipPairNumberUniqueElement =listNum.stream().filter(n -> n%2 ==0).distinct().skip(2).collect(Collectors.toList());
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

		/***
		 * Match methods help us to know if some elements correspond with a given property: AnyMath, NoMatch, AllMatch 
		 ***/
		Boolean anyNameStartingWithFexist = words.stream().anyMatch(w -> w.startsWith("F"));
		System.out.println("Y a t il un nom qui commence avec la lettre F (Francky)?: "+ anyNameStartingWithFexist);
		
		Boolean allNameStartingWithFexist = words.stream().allMatch(w -> w.startsWith("F"));
		System.out.println("Tous les noms commencent t il avec la lettre F (Francky)?: "+ allNameStartingWithFexist);
	
		Boolean noNameStartingWithFexist = words.stream().noneMatch(w -> w.startsWith("F"));
		System.out.println("Aucun nom ne commence avec la lettre F (Francky)?: "+ noNameStartingWithFexist);
	
		/***
		 * Reduce helps to perform terminal operations on a collection
		 * ***/
		int sum=listNum.stream().reduce(0, (a,b) -> a+b);
		System.out.println("La somme des entiers dans la première list est: "+ sum);
		
		Optional<Integer> max = listNum.stream().reduce(Integer::max);
		System.out.println("Le nombre le plus grand dans la liste précédente est: "+ max );
		
		Optional<Integer> min = listNum.stream().reduce(Integer::min);
		System.out.println("Le nombre le plus petit dans la liste précédente est: "+ min );
	}

}

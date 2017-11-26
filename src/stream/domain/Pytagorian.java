package stream.domain;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Pytagorian {

	public static void main(String[] args) {
		
		/**
		 * Pythagorean generate a stream of tuple (a,b,c) so that a*a +b*b = c*c.
		 * 
		 * ***/
		Stream<int[]> pythagorean= IntStream.range(1, 100).boxed().flatMap(a->IntStream.range(a, 100).filter(b->Math.sqrt(a*a+b*b) %1 ==0).mapToObj(b -> new int[]{a,b, (int)Math.sqrt(a*a+b*b)}));
		pythagorean.limit(5).forEach(t -> {
			System.out.println(t[0]+ ", " + t[1]+ ", " + t[2]);
		});
	}

}

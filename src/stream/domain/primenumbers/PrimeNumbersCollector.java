package stream.domain.primenumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean,List<Integer>>, Map<Boolean, List<Integer>>>{

	@Override
	public Supplier<Map<Boolean, List<Integer>>> supplier() {
		return () -> {
			HashMap< Boolean, List<Integer>> map =new HashMap<>();
			map.put(true, new ArrayList<>());
			map.put(false, new ArrayList<>());
			return map;
		};
	}

	@Override
	public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
		return (Map<Boolean,List<Integer>> acc, Integer candidate) -> {
			acc.get(isPrime(acc.get(true),candidate)).add(candidate);
		};
	}

	@Override
	public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
		return (Map<Boolean,List<Integer>> map1, Map<Boolean,List<Integer>> map2) -> {
			map1.get(true).addAll(map2.get(true));
			map1.get(false).addAll(map2.get(false));
			return map1;
		};
	}

	@Override
	public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
		// TODO Auto-generated method stub
		return Function.identity();
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		// TODO Auto-generated method stub
		return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
	}
	
	private boolean  isPrime(List<Integer> primes,int candidate) {
		int candidateRoot = (int) Math.sqrt((double) candidate);
		//return primes.stream().noneMatch(i -> candidate % i ==0);
		return takeWhile(primes, i -> i<= candidateRoot).stream().noneMatch(p -> candidate % p ==0);
	}

	private <A> List<A>takeWhile(List<A> list, Predicate<A> p){
		
		int i = 0;
		for (A item :list){
			if (!p.test(item)){
				return list.subList(0, i);
			}
			i++;
		}
		return list;
	}
}

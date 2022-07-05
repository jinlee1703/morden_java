import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class MainClass {
	public static void main(String[] args) {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");
		
		List<Transaction> transactions = Arrays.asList(
			new Transaction(brian, 2011, 300),
			new Transaction(raoul, 2011, 1000),
			new Transaction(raoul, 2011, 400),
			new Transaction(mario, 2011, 710),
			new Transaction(mario, 2011, 700),
			new Transaction(alan, 2011, 900)
		);
		
		// 1 : 2011년에 일어난 모든 트랜잭션을 값을 기준으로 오름차순 정렬
		List<Transaction> transaction2011 = transactions.stream()
				.filter(t -> t.getYear() == 2011)
				.sorted(comparing(Transaction::getValue))
				.collect(toList());
		
//		System.out.println(transaction2011);
		
		// 2 : 거래자가 근무하는 모든 도시를 중복 없이 출력
		List<String> citys = transactions.stream()
				.map(t -> t.getTrader().getCity())
				.distinct()
				.collect(toList());
		
//		System.out.println(citys);
		
		// 3 : 케임브리지에서 근무하는 모든 거래자를 찾아 이름순으로 정렬
		List<Trader> cambridgeTraders = transactions.stream()
				.map(t -> t.getTrader())
				.filter(t -> t.getCity().equals("Cambridge"))
				.sorted(comparing(Trader::getName))
				.collect(toList());
		
//		System.out.println(cambridgeTraders);
		
		// 4 : 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오.
		List<String> traders = transactions.stream()
				.map(t -> t.getTrader().getName())
				.distinct()
				.collect(toList());
		
//		System.out.println(traders);
		
		// 5 : 밀라노에 거래자가 있는가?
		boolean isMilanTrader = transactions.stream()
				.anyMatch(c -> c.getTrader().getCity().equals("Milan"));
		
//		System.out.println(isMilanTrader);
		
		// 6 : 케임브리지에 거주하는 거래자의 모든 트랜잭션값 출력
		List<Transaction> cambridgeTransactions = transactions.stream()
				.filter(t -> t.getTrader().getCity().equals("Cambridge"))
				.collect(toList());
		
//		System.out.println(cambridgeTransactions);
		
		// 7 : 전체 트랜잭션 중 최대값
		Optional<Integer> maxValue = transactions.stream()
				.map(t -> t.getValue())
				.reduce(Integer::max);
		
//		System.out.println(maxValue);
		
		// 7 : 전체 트랜잭션 중 최소값
		Optional<Integer> minValue = transactions.stream()
				.map(t -> t.getValue())
				.reduce(Integer::min);
				
//		System.out.println(minValue);
	}
}

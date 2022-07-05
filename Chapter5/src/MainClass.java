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
		
		// 1 : 2011�⿡ �Ͼ ��� Ʈ������� ���� �������� �������� ����
		List<Transaction> transaction2011 = transactions.stream()
				.filter(t -> t.getYear() == 2011)
				.sorted(comparing(Transaction::getValue))
				.collect(toList());
		
//		System.out.println(transaction2011);
		
		// 2 : �ŷ��ڰ� �ٹ��ϴ� ��� ���ø� �ߺ� ���� ���
		List<String> citys = transactions.stream()
				.map(t -> t.getTrader().getCity())
				.distinct()
				.collect(toList());
		
//		System.out.println(citys);
		
		// 3 : ���Ӻ긮������ �ٹ��ϴ� ��� �ŷ��ڸ� ã�� �̸������� ����
		List<Trader> cambridgeTraders = transactions.stream()
				.map(t -> t.getTrader())
				.filter(t -> t.getCity().equals("Cambridge"))
				.sorted(comparing(Trader::getName))
				.collect(toList());
		
//		System.out.println(cambridgeTraders);
		
		// 4 : ��� �ŷ����� �̸��� ���ĺ������� �����ؼ� ��ȯ�Ͻÿ�.
		List<String> traders = transactions.stream()
				.map(t -> t.getTrader().getName())
				.distinct()
				.collect(toList());
		
//		System.out.println(traders);
		
		// 5 : �ж�뿡 �ŷ��ڰ� �ִ°�?
		boolean isMilanTrader = transactions.stream()
				.anyMatch(c -> c.getTrader().getCity().equals("Milan"));
		
//		System.out.println(isMilanTrader);
		
		// 6 : ���Ӻ긮���� �����ϴ� �ŷ����� ��� Ʈ����ǰ� ���
		List<Transaction> cambridgeTransactions = transactions.stream()
				.filter(t -> t.getTrader().getCity().equals("Cambridge"))
				.collect(toList());
		
//		System.out.println(cambridgeTransactions);
		
		// 7 : ��ü Ʈ����� �� �ִ밪
		Optional<Integer> maxValue = transactions.stream()
				.map(t -> t.getValue())
				.reduce(Integer::max);
		
//		System.out.println(maxValue);
		
		// 7 : ��ü Ʈ����� �� �ּҰ�
		Optional<Integer> minValue = transactions.stream()
				.map(t -> t.getValue())
				.reduce(Integer::min);
				
//		System.out.println(minValue);
	}
}
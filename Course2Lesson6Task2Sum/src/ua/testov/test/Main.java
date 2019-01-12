package ua.testov.test; 

public class Main {

	public static void main(String[] args) {
		int[] array = new int[20000000];
		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * 9);
		}
		
		long startOneFlow = System.currentTimeMillis();
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		long finishOneFlow = System.currentTimeMillis();
		long resultOne = finishOneFlow - startOneFlow;
		
		long startMultiFlow = System.currentTimeMillis();
		ThreadSum thrSum1 = new ThreadSum(array, 0, 5000000);
		ThreadSum thrSum2 = new ThreadSum(array, 5000000, 10000000);
		ThreadSum thrSum3 = new ThreadSum(array, 10000000, 15000000);
		ThreadSum thrSum4 = new ThreadSum(array, 15000000, 20000000);
		ThreadSum[] arrThrSum = new ThreadSum[] { thrSum1, thrSum2, thrSum3, thrSum4 };
		Thread[] arrThr = new Thread[4];
		for (int i = 0; i < arrThr.length; i++) {
			arrThr[i] = new Thread(arrThrSum[i]);
		}
		for (int i = 0; i < arrThr.length; i++) {
			arrThr[i].start();
		}
		try {
			for (int i = 0; i < arrThr.length; i++) {
				arrThr[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int summa = 0;
		for (int i = 0; i < arrThr.length; i++) {
			summa += arrThrSum[i].getSum();
		}
		long finishMultiFlow = System.currentTimeMillis();
		long resultTwo = finishMultiFlow - startMultiFlow;
				
		System.out.println(sum);
		System.out.println(summa);
		System.out.println("Speed of one flow: "+resultOne + "ms");
		System.out.println("Speed of multi flow: "+resultTwo + "ms");
	}
}

package zaytsev;

import java.util.ArrayList;
import java.util.List;


public class Main {

	public static void main(String[] args) throws InterruptedException {
		//Кол-во потоков.
		int threadsCount = 8;

		//Получаем параметры с которых запущена программа.
		Long start = Long.parseLong(args[0]);
		Long end = Long.parseLong(args[1]);

		long difference = end - start;

		List<ThreadСounting> threads = new ArrayList<ThreadСounting>();
		
		//Узнаём сколько чисел можно обработать в одном потоке.
		long count = difference / threadsCount;
		
		//Не забываем про остаток, потом этот остаток нужно добавить в последний поток.
		long spare = difference % threadsCount;
		
		//С какого числа будет стартовать поток.
		long startThread = start;
		
		for (int i = 0; i < threadsCount; i++) {
			if (!(i == (threadsCount - 1))) {
				//Создаем поток указывая начало и конец которые он должен обработать и сохранить в list. 
				ThreadСounting thread = new ThreadСounting(startThread, count);
				threads.add(thread);
				//Задаём с какого числа будет стартовать следующий поток.
				startThread = startThread + count;
			} else {
				//Последний поток в который заносим остаток.
				ThreadСounting thread = new ThreadСounting(startThread, count + spare + 1);
				threads.add(thread);
			}
		}

		System.out.println("Loading...");
		//Запускаем потоки.
		for (Thread thread : threads) {
			thread.start();
		}
		//Ожидаем работу потоков.
		for (Thread thread : threads) {
			thread.join();
		}
		
		//Выводим на консоль результат обработки потоков.
		for (ThreadСounting thread : threads) {
			for (Long l : thread.getList()) {
				System.out.println(l);
			}
		}

	}
}

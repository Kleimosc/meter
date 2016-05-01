package zaytsev;

import java.util.ArrayList;
import java.util.List;

public class ThreadСounting extends Thread {

	private long start;
	private long end;

	public ThreadСounting(long start, long amount) {
		this.start = start;
		this.end = start + amount;
	}
	//Можно сразу задать размер листу. Что бы не тратилось время на расширение. Особо разницы не заметил
	List<Long> list = new ArrayList<Long>();

	@Override
	public void run() {
		for (long i = start; i < end; i++) {

			boolean check = true;
			
			char[] array = Long.toString(i).toCharArray();
			for (byte j = 0; j < array.length; j++) {
				char temp = array[j];
				byte count = 0;
				for (byte k = 0; k < array.length; k++) {
					if (array[k] == temp) {
						++count;
						if (count == 2) {
							check = false;
							break;

						}
					}

				}

			}
			if (check) {
				list.add(i);
			}

		}
	}

	public List<Long> getList() {
		return list;
	}
}


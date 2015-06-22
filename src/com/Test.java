package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

public class Test {
	//public static String[] args = { "-p", "10000", "-t", "1" };

	public static void main(String[] args) throws FileNotFoundException {

		Long first = System.currentTimeMillis();

		ThreadManager tm;
		boolean quiet = false;
		int number = 0;
		int threads = 0;
		FileOutputStream output = new FileOutputStream(new File("file.txt"));
		try {
			for (int i = 0; i < args.length; i++) {
				if (args[i].equals("-p")) {
					number = Integer.parseInt(args[i + 1]);
				}
				if (args[i].equals("-t")) {
					threads = Integer.parseInt(args[i + 1]);
				}
				if (args[i].equals("-o")) {
					output = new FileOutputStream(new File(args[i + 1]));
				}
				if (args[i].equals("-q")) {
					quiet = true;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		tm = new ThreadManager(threads, number);
		CalculatorThread.setQuit(quiet);
		try {
			output.write(tm.getE().getSum().toString().getBytes());
			output.flush();
			output.close();
		} catch (IOException s1) {
			// TODO Auto-generated catch block
			s1.printStackTrace();
		}
		System.out
				.println("E: "
						+ new BigDecimal(tm.getE().getSum().toString())
								.toPlainString());
		System.out.printf("Time: %s ms\n",
				String.valueOf(System.currentTimeMillis() - first));
	}

}

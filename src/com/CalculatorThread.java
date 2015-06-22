package com;

import java.math.BigInteger;

import org.apfloat.Apfloat;
import org.apfloat.ApfloatMath;

public class CalculatorThread extends Thread implements Runnable {

	private int index;
	private int threads;
	private int digits;
	private E e;
	private static final int precision = 30;
	private static int counter = 0;
	private long currentTime;
	private static boolean quit = false;

	public CalculatorThread(int index, int threads, int digits, E e, long time) {
		super();
		this.index = index;
		this.threads = threads;
		this.digits = digits;
		this.e = e;
		this.currentTime = time;
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		int interval = (int) digits / threads;
		int from = index * interval;
		int to = from + interval;
		Apfloat s = calc(from, to);
		this.e.add(s);
		if (!quit) {
			this.setStatic(threads, currentTime);
		}

	}

	public BigInteger factorial(BigInteger n) {

		 if (n.compareTo(BigInteger.ONE) <= 0) // n <= 1
	        {
	            return BigInteger.ONE;
	        } else {
	            return n.multiply(factorial(n.subtract(BigInteger.ONE)));
	        }

	}

	public Apfloat fac(Apfloat a) {
		
		Apfloat b = Apfloat.ONE;
		Apfloat c = Apfloat.ONE;
		
		while(a.compareTo(c) != 0){
			c = c.add(Apfloat.ONE);
			b = b.multiply(c);
		}
		return b;
	}

	public static boolean isQuit() {
		return quit;
	}

	public static void setQuit(boolean quit) {
		CalculatorThread.quit = quit;
	}

	private static void setStatic(int threads, long currentTime) {
		double progress = (((double) ++counter / threads)) * 100;
		final int finalProg = (int) Math.ceil(progress);
		System.out.printf("%d%% %sms\n", finalProg,
				String.valueOf(System.currentTimeMillis() - currentTime));
	}

	public Apfloat den(Apfloat k) {
		return fac(k.multiply(new Apfloat(2, precision)).add(Apfloat.ONE));
		//return aprox(k.multiply(new Apfloat(2, precision)).add(Apfloat.ONE));
	}

	public Apfloat num(Apfloat k) {
		return new Apfloat(BigInteger.valueOf(3)).subtract(k.multiply(k
				.multiply(new Apfloat(BigInteger.valueOf(4), precision))));
	}

	public Apfloat aprox(Apfloat n){
//		Apfloat c = ApfloatMath.pow(n.divide(new Apfloat(Math.E, precision)), n);
//		return ApfloatMath.pow(new Apfloat("2", precision).multiply(new Apfloat(Math.PI)).multiply(n), new Apfloat(0.5)).multiply(c);
		Apfloat pi = new Apfloat(Math.PI, precision);
		Apfloat e = new Apfloat(Math.E, precision);
		Apfloat d12 = new Apfloat("0.5", precision);
		Apfloat two =  new Apfloat("2", precision);
		Apfloat a12 = Apfloat.ONE.divide(n.multiply(new Apfloat("12", precision)));
		Apfloat a288 = Apfloat.ONE.divide(n.multiply(new Apfloat("288", precision).multiply(n)));
		Apfloat a139 = new Apfloat("-139", precision).divide(n.multiply(new Apfloat("51840", precision).multiply(n).multiply(n)));
		Apfloat a571 = new Apfloat("-571", precision).divide(n.multiply(new Apfloat("2488320", precision).multiply(n).multiply(n).multiply(n)));
		return ApfloatMath.pow(two.multiply(n).multiply(pi),d12).multiply(ApfloatMath.pow(n, n)).
				multiply(Apfloat.ONE.divide(ApfloatMath.pow(e, n)).multiply(Apfloat.ONE.add(a12).add(a288).add(a139).add(a571)));
	}
	public Apfloat calc(int from, int to) {
		Apfloat bd = new Apfloat("0", precision);
		Apfloat num1 = num(new Apfloat(from, precision));
		Apfloat den1 = den(new Apfloat(from, precision));
		Apfloat r = num1.divide(den1);
		bd = bd.add(r);
		for (int i = from + 1; i < to; i++) {
//			num1 = num1.add(new Apfloat(BigInteger.valueOf(-4 - 8 * (i - 1)),
//					precision));
//			den1 = den1.multiply(new Apfloat(BigInteger.valueOf((2 * i + 1) * 2
//					* i), precision));
			num1 =  num(new Apfloat(i, precision));
			den1 = den(new Apfloat(i, precision));
			r = num1.divide(den1);
			bd = bd.add(r);
		}
		return bd;
	}

}

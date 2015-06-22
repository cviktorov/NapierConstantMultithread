package com;

import org.apfloat.Apfloat;

public class E {
	
	private Apfloat sum;
	
	
	public E() {
		sum = new Apfloat("0", 30);
	}


	public void add(Apfloat a){
		
		sum = sum.add(a);
	}


	public Apfloat getSum() {
		
		return sum;
	}


	public void setSum(Apfloat sum) {
		this.sum = sum;
	}
	
	
}

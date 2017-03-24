package uteevbkru;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

	private final double[] accounds;
	private Lock bankLock = new ReentrantLock();
	private Condition noMoneyBank;
	
	public Bank(int n, double initBalance){
		// !!!!
		noMoneyBank = bankLock.newCondition();
		// !!!!!!!!!
		accounds = new double[n];
		for(int i = 0; i < accounds.length; i++)
			accounds[i] = initBalance;		
	}
	
	public void transfer(int from, int to, double amound){
		bankLock.lock();
		//TODO запускал пример без блокировки на 20 минут ошибки не было!!
		try{
			while(accounds[from] < amound) 
			noMoneyBank.await();
			System.out.print(Thread.currentThread());
			accounds[from] -= amound;
			System.out.printf("%10.2f from %d to %d", amound, from, to);
			accounds[to] += amound;
			System.out.printf("Total Balance:  %10.2f%n", getTotalBalance());
			noMoneyBank.signalAll();		
		}
		catch(InterruptedException e){	}
		finally{ bankLock.unlock();	}
	}
	
	public synchronized void transferAnotherWay(int from, int to, double amound) 
		throws InterruptedException
	{				
		while(accounds[from] < amound) 
			wait();
		System.out.print(Thread.currentThread());
		accounds[from] -= amound;
		System.out.printf("%10.2f from %d to %d", amound, from, to);
		accounds[to] += amound;
		System.out.printf("Total Balance:  %10.2f%n", getTotalBalance());
		notifyAll();		
	}
	
	public double getTotalBalance(){
		double sym = 0;
		for(double a : accounds)
			sym += a;
		
		return sym;
	}
	public int size(){
		return accounds.length;
	}
}

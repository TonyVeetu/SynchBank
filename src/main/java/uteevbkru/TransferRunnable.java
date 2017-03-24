package uteevbkru;

public class TransferRunnable implements Runnable{

	private Bank bank; 
	private int fromAccound;
	private double maxAmound; 
	private int DELAY = 10;
	
	public TransferRunnable(Bank b, int from, double max){
		bank = b;
		fromAccound = from;
		maxAmound = max;		
	}
	public void run(){
		try{
			while(true){
				int toAcc = (int) (bank.size()*Math.random());
				double amound = maxAmound*Math.random();
				bank.transfer(fromAccound, toAcc, amound);
				Thread.sleep( (int)(DELAY*Math.random()));
			}
		}
		catch(InterruptedException e ){			
		}
	}
}

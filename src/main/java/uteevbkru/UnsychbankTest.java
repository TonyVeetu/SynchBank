package uteevbkru;

public class UnsychbankTest {

	public static final int NAC = 100;
	public static final double INIT_BALANCE = 1000;
	
	public static void main(String []args){
		Bank bank = new Bank(NAC, INIT_BALANCE);
		for(int i = 0; i < NAC; i++){
			TransferRunnable r = new TransferRunnable(bank, i, INIT_BALANCE);
			Thread t = new Thread(r);
			t.start();
		}
	}
	
	
}

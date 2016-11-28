
class AccountDetail{
	
	private int amount;
	static int balance;
	private String name;
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	AccountDetail()
	{
		
	}
	AccountDetail(String name,int amount)
	{
		
		this.name=name;
		this.amount=amount;
		balance=amount;
	}
	public void withdraw(int amt)
	{
		synchronized(this){
			if((amt > balance)||(balance <  0))
			{
				System.out.println(Thread.currentThread().getName()+" cannot withdraw");
			}
			else
			{
		balance=balance-amt;
		System.out.println(Thread.currentThread().getName()+" going to  withdrawal ");
		System.out.println(" Updated Amount after withdrawal is "+balance);
		try
		{
			Thread.sleep(400);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		} 
		}
	}
	public void deposit(int amt)
	{
		synchronized(this){
			System.out.println(Thread.currentThread().getName()+" going to  deposit ");
		if(amt < 0)
		{
			System.out.println(Thread.currentThread().getName()+" cannot deposit ");
		}
		else
		{
			balance=balance+amt;
		System.out.println("Updated Amount after deposit is "+balance);
		try
		{
			Thread.sleep(400);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		}
		} 
	}
	
	
		
}
class MyThread1 extends Thread
{
	AccountDetail act;
	MyThread1(AccountDetail a)
	{
		this.act=a;
	}
	public void run()
	{
		for(int i=0;i<3;i++)
		{
		act.withdraw(100);
		act.deposit(200);
		}
	}
	
}
class MyThread2 extends Thread
{
	AccountDetail act;
	MyThread2(AccountDetail a)
	{
		this.act=a;
	}
	public void run()
	{
		for(int i=0;i<3;i++)
		{
		act.withdraw(200);
		act.deposit(500);
		}
	}
	
}
public class OnlineBankingSynchronisation
{
	public static void main(String[] args) {
		AccountDetail act=new AccountDetail("Divya",1000);
		MyThread1 t1=new MyThread1(act);
		MyThread2 t2=new MyThread2(act);
		t1.setName("Divya");
		t2.setName("Vipin");
		t1.start();
		t2.start();
	}
}

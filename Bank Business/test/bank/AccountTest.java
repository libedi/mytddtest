package bank;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AccountTest {
	
	private Account account;

	@Before
	public void setup() {
		account = new Account(10000);
	}
	
	@Test
	public void testAccount() throws Exception{
//		setup();
	}

	@Test
	public void testGetBalance() throws Exception {
//		setup();
		assertEquals("10000원으로 계좌 생성 후, 잔고 조회", 10000, account.getBalance());
		
		account = new Account(1000);
		assertEquals(1000, account.getBalance());
		
		account = new Account(0);
		assertEquals(0, account.getBalance());
	}
	
	@Test
	public void testDeposit() throws Exception {
//		setup();
		account.deposit(1000);
		assertEquals(11000, account.getBalance());
	}
	
	@Test
	public void testWithdraw() throws Exception {
//		setup();
		account.withdraw(1000);
		assertEquals(9000, account.getBalance());
	}
	
}

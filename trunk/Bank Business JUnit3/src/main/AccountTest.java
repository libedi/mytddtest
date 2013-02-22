package main;

import junit.framework.TestCase;

public class AccountTest extends TestCase {		// TestCase 를 상속.

	private Account account;			// 텍스트 픽스쳐 정의

	public void testAccount() {
	}
	
	@Override
	protected void setUp() throws Exception{
		account = new Account(10000);			// setUp() 메서드를 사용해 픽스쳐 상태 초기화
	}

	public void testGetBalance() {			// 이름이 test로 시작하는 테스트 케이스 작성
		assertEquals(10000, account.getBalance());
		
		account = new Account(20000);
		assertEquals(20000, account.getBalance());
		
		account = new Account(0);
		assertEquals(0, account.getBalance());
	}

	public void testDeposit() {
		account.deposit(1000);
		assertEquals(11000, account.getBalance());
	}

	public void testWithdraw() {
		account.withdraw(1000);
		assertEquals(9000, account.getBalance());
	}

	@Override
	protected void tearDown() throws Exception {		// tearDown() : 테스트를 마친 다음 픽스처를 정리할 코드 작성.
		super.tearDown();
	}
	
	

}

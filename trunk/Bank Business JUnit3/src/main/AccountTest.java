package main;

import junit.framework.TestCase;

public class AccountTest extends TestCase {		// TestCase �� ���.

	private Account account;			// �ؽ�Ʈ �Ƚ��� ����

	public void testAccount() {
	}
	
	@Override
	protected void setUp() throws Exception{
		account = new Account(10000);			// setUp() �޼��带 ����� �Ƚ��� ���� �ʱ�ȭ
	}

	public void testGetBalance() {			// �̸��� test�� �����ϴ� �׽�Ʈ ���̽� �ۼ�
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
	protected void tearDown() throws Exception {		// tearDown() : �׽�Ʈ�� ��ģ ���� �Ƚ�ó�� ������ �ڵ� �ۼ�.
		super.tearDown();
	}
	
	

}

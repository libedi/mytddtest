package shop;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {
	@Test
	public void testAddCoupon() throws Exception {
		User user = new User("libedi");
		assertEquals("���� ���� ��", 0, user.getTotalCouponCount());
		
		ICoupon coupon = new DummyCoupon();
		
		user.addCoupon(coupon);
		assertEquals("���� ���� ��", 1, user.getTotalCouponCount());
	}
	
	@Test
	public void testGetLastOccupiedCoupon() throws Exception {
		User user = new User("libedi");
		ICoupon coupon = new StubCoupon();
		user.addCoupon(coupon);
		ICoupon lastCoupon = user.getLastOccupiedCoupon();
		
		assertEquals("���� ������", 7, lastCoupon.getDiscountPercent());
		assertEquals("���� �̸�", "VIP �� �Ѱ��� ��������", lastCoupon.getName());
	}
	
	@Test
	public void testGetOrderPrice_discountableItem() throws Exception {
		PriceCalculator calculator = new PriceCalculator();
		// new Item(�̸�, ī�װ�, ����)
		Item item = new Item("LightSavor", "�ξ�Į", 100000);
		ICoupon coupon = new StubCoupon();
		
		assertEquals("�������� ���� ���ε� ����", 93000, calculator.getOrderPrice(item, coupon));
		
		ICoupon otherCoupon = new FakeCoupon();
		
		assertEquals("�������� ���� ���ε� ����", 93000, calculator.getOrderPrice(item, otherCoupon));
	}
	
	@Test
	public void testGetOrderPrice_undiscountableItem() throws Exception {
		PriceCalculator calculator = new PriceCalculator();
		Item item = new Item("R2D2", "�˶��ð�", 20000);
		ICoupon coupon = new StubCoupon();
		
		assertEquals("���� ���� �ȵǴ� ������", 20000, calculator.getOrderPrice(item, coupon));
		
		ICoupon otherCoupon = new FakeCoupon();
		
		assertEquals("���� ���� �ȵǴ� ������", 20000, calculator.getOrderPrice(item, otherCoupon));
	}
	
}

package shop;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {
	@Test
	public void testAddCoupon() throws Exception {
		User user = new User("libedi");
		assertEquals("쿠폰 수령 전", 0, user.getTotalCouponCount());
		
		ICoupon coupon = new DummyCoupon();
		
		user.addCoupon(coupon);
		assertEquals("쿠폰 수령 후", 1, user.getTotalCouponCount());
	}
	
	@Test
	public void testGetLastOccupiedCoupon() throws Exception {
		User user = new User("libedi");
		ICoupon coupon = new StubCoupon();
		user.addCoupon(coupon);
		ICoupon lastCoupon = user.getLastOccupiedCoupon();
		
		assertEquals("쿠폰 할인율", 7, lastCoupon.getDiscountPercent());
		assertEquals("쿠폰 이름", "VIP 고객 한가위 감사쿠폰", lastCoupon.getName());
	}
	
	@Test
	public void testGetOrderPrice_discountableItem() throws Exception {
		PriceCalculator calculator = new PriceCalculator();
		// new Item(�̸�, ī�װ?, ����)
		Item item = new Item("LightSavor", "부엌칼", 100000);
		ICoupon coupon = new StubCoupon();
		
		assertEquals("쿠폰으로 인해 할인된 가격", 93000, calculator.getOrderPrice(item, coupon));
		
		ICoupon otherCoupon = new FakeCoupon();
		
		assertEquals("쿠폰으로 인해 할인된 가격", 93000, calculator.getOrderPrice(item, otherCoupon));
	}
	
	@Test
	public void testGetOrderPrice_undiscountableItem() throws Exception {
		PriceCalculator calculator = new PriceCalculator();
		Item item = new Item("R2D2", "알람시계", 20000);
		ICoupon coupon = new StubCoupon();
		
		assertEquals("쿠폰 적용 안되는 아이템", 20000, calculator.getOrderPrice(item, coupon));
		
		ICoupon otherCoupon = new FakeCoupon();
		
		assertEquals("쿠폰 적용 안되는 아이템", 20000, calculator.getOrderPrice(item, otherCoupon));
	}
	
}

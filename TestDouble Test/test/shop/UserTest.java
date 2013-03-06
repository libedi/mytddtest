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
		// new Item(이름, 카테고리, 가격)
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
	
	@Test
	public void testGetOrderPrice_discountableItem_usingTestSpy() throws Exception {
		PriceCalculator calculator = new PriceCalculator();
		// new Item(이름, 카테고리, 가격)
		Item item = new Item("LightSavor", "부엌칼", 100000);
		
		ICoupon coupon = new SpyCoupon();
		assertEquals("쿠폰으로 인해 할인된 가격", 93000, calculator.getOrderPrice(item, coupon));
		
		int methodCallCount = ((SpyCoupon)coupon).getIsAppliableCallCount();
		assertEquals("coupon.isAppliable 메서드 호출 횟수", 1, methodCallCount);
	}
	
	@Test
	public void testAddCoupon_usingAnonymousClass() throws Exception {
		User user = new User("libedi");
		assertEquals("최초 쿠폰 수", 0, user.getTotalCouponCount());
		
		// 공통적으로 계속 사용될 경우, 필요하다면 coupon을 필드로 뽑아내고, 익명 클래스 부분을 setUp() 메서드로 이동시켜도 된다.
		ICoupon coupon = new ICoupon() {

			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isValid() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public int getDiscountPercent() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public boolean isAppliable(Item item) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void doExpire() {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		user.addCoupon(coupon);
		assertEquals("쿠폰 수령 후", 1, user.getTotalCouponCount());
	}
	
}

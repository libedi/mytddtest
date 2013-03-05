package shop;

public class PriceCalculator {

	public int getOrderPrice(Item item, ICoupon coupon) {
		// 쿠폰이 유효하고 적용가능하면
		if(coupon.isValid() && coupon.isAppliable(item)) {
			return (int) (item.getPrice() * getDiscountRate(coupon.getDiscountPercent()));		// 쿠폰의 할인율을 적용한다.
		}
		
		return item.getPrice();
	}

	private double getDiscountRate(int discountPercent) {
		// TODO Auto-generated method stub
		return (100 - discountPercent) / 100d;		// int 연산이 안되도록 d를 붙임
	}

}

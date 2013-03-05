package shop;

public class User {
	
	private int couponCount;
	private ICoupon coupon;

	public User(String string) {
		// TODO Auto-generated constructor stub
		couponCount = 0;
	}

	public int getTotalCouponCount() {
		// TODO Auto-generated method stub
		return couponCount;
	}

	public void addCoupon(ICoupon coupon) {
		// TODO Auto-generated method stub
		this.coupon = coupon;
		couponCount ++;
	}

	public ICoupon getLastOccupiedCoupon() {
		// TODO Auto-generated method stub
		return coupon;
	}

}

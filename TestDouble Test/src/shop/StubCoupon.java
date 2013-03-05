/**
 * Test Stub
 * - 더미 객체가 마치 실제로 동작하는 것처럼 보이게 만들어놓은 객체다.
 * - 실제로 스텁을 사용할 때는 테스트에 필요한 메서드 부분만 하드코딩하면 된다.
 */
package shop;

public class StubCoupon implements ICoupon {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "VIP 고객 한가위 감사쿠폰";
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getDiscountPercent() {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public boolean isAppliable(Item item) {
		if(item.getCategory().equals("부엌칼")) {
			return true;
		} else if(item.getCategory().equals("알람시계")) {
			return false;
		}
		return true;
	}

	@Override
	public void doExpire() {
		// TODO Auto-generated method stub

	}

}

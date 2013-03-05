/**
 * Dummy Object
 * - 오로지 인스턴스화 될 수 있는 수준으로만 구현한 객체.
 * - 단지 인스턴스화 된 객체가 필요할 뿐, 해당 객체의 기능까지는 필요하지 않은 경우에 사용.
 */
package shop;

public class DummyCoupon implements ICoupon {

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

}

/**
 * Dummy Object
 * - ������ �ν��Ͻ�ȭ �� �� �ִ� �������θ� ������ ��ü.
 * - ���� �ν��Ͻ�ȭ �� ��ü�� �ʿ��� ��, �ش� ��ü�� ��ɱ����� �ʿ����� ���� ��쿡 ���.
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

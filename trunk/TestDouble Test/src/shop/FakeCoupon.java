/**
 * Fake Object
 * - ����ũ ��ü�� ���� ���� �ν��Ͻ��� ��ǥ�� �� �ִ� ����̰ų�,
 * - �� �� ������ ������ �� �ִ� ��ü�� ��Ī�Ѵ�.
 * - ���� ���ο� ����Ʈ�� ���� �̿��ؼ� DB ���� �ܺ� ���� ȯ���� ��ü�Ѵ�.
 * 
 * - ����ũ ��ü�� ����ġ�� �������� ���, ������ ���ؿ��� ������ ����,
 * - Mock �����ӿ�ũ ���� ����ϰų�,
 * - ���� ��ü�� ���� �����ͼ� �׽�Ʈ ���̽� �ۼ��� ����� ���� �����Ѵ�.
 */
package shop;

import java.util.ArrayList;
import java.util.List;

public class FakeCoupon implements ICoupon {

	List<String> categoryList = new ArrayList<String>();		// ���ο����� ����� ���
	
	// ��ġ DB���� ������ ������ó�� ������ �Ѵ�.
	public FakeCoupon() {
		categoryList.add("�ξ�Į");
		categoryList.add("�Ƶ� �峭��");
		categoryList.add("�����ⱸ");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "VIP �� �Ѱ��� ��������";
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
		if(this.categoryList.contains(item.getCategory())) {
			return true;
		}
		return false;
	}

	@Override
	public void doExpire() {
		// TODO Auto-generated method stub

	}

}

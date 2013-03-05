package shop;

public class PriceCalculator {

	public int getOrderPrice(Item item, ICoupon coupon) {
		// ������ ��ȿ�ϰ� ���밡���ϸ�
		if(coupon.isValid() && coupon.isAppliable(item)) {
			return (int) (item.getPrice() * getDiscountRate(coupon.getDiscountPercent()));		// ������ �������� �����Ѵ�.
		}
		
		return item.getPrice();
	}

	private double getDiscountRate(int discountPercent) {
		// TODO Auto-generated method stub
		return (100 - discountPercent) / 100d;		// int ������ �ȵǵ��� d�� ����
	}

}

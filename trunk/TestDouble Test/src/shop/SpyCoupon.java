/**
 * Test Spy
 * - Test Double 가 정상적으로 사용되었는지,
 * - 그 객체의 예상된 메서드가 정상적으로 호출되었는지 확인하기 위한 기법.
 * - 보통 호출 여부를 감시 및 기록.
 */
package shop;

import java.util.ArrayList;
import java.util.List;

public class SpyCoupon implements ICoupon {

	List<String> categoryList = new ArrayList<String>();
	private int isAppliableCallCount;			// isAppliable() 메서드의 호출 여부를 확인하기 위한 변수.
	
	public SpyCoupon() {
		categoryList.add("부엌칼");
		categoryList.add("아동 장난감");
		categoryList.add("조리기구");
	}
	
	@Override
	public boolean isAppliable(Item item) {
		this.isAppliableCallCount++;			// 호출되면 증가
		if(this.categoryList.contains(item.getCategory())) {
			return true;
		}
		return false;
	}
	
	public int getIsAppliableCallCount() {		// 몇 번 호출되었는가?
		return this.isAppliableCallCount;
	}

	@Override
	public String getName() {
		return "VIP 고객 한가위 감사쿠폰";
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public int getDiscountPercent() {
		return 7;
	}

	@Override
	public void doExpire() {

	}

}

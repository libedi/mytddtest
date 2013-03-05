/**
 * Fake Object
 * - 페이크 객체는 여러 개의 인스턴스를 대표할 수 있는 경우이거나,
 * - 좀 더 복잡한 구현이 들어가 있는 객체를 지칭한다.
 * - 보통 내부에 리스트나 맵을 이용해서 DB 같은 외부 의존 환경을 대체한다.
 * 
 * - 페이크 객체가 지나치게 복잡해질 경우, 적절한 수준에서 구현을 접고,
 * - Mock 프레임워크 등을 사용하거나,
 * - 실제 객체를 직접 가져와서 테스트 케이스 작성에 사용할 것을 권장한다.
 */
package shop;

import java.util.ArrayList;
import java.util.List;

public class FakeCoupon implements ICoupon {

	List<String> categoryList = new ArrayList<String>();		// 내부용으로 사용할 목록
	
	// 마치 DB에서 가져온 데이터처럼 세팅을 한다.
	public FakeCoupon() {
		categoryList.add("부엌칼");
		categoryList.add("아동 장난감");
		categoryList.add("조리기구");
	}

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

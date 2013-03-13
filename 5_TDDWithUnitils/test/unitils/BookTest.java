/**
 * Reflection Assertion.
 * - 객체의 필드에 저장되어 있는 값을 알아서 비교.
 * - import static org.unitils.reflectionassert.ReflectionAssert.*;
 * - assertReflectionEquals ( 예상 객체, 실제 객체 );
 * - assertReflectionEquals ( [메시지] , 예상 객체, 실제 객체 );
 */
package unitils;

import static org.junit.Assert.assertEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertLenientEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertPropertyLenientEquals;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;
import static org.unitils.reflectionassert.ReflectionComparatorMode.IGNORE_DEFAULTS;
import static org.unitils.reflectionassert.ReflectionComparatorMode.LENIENT_DATES;
import static org.unitils.reflectionassert.ReflectionComparatorMode.LENIENT_ORDER;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class BookTest {
	
	@Test
	public void testBook() throws Exception {
		// given	: 선행조건 기술
		Book aBook = new Book("사람은 무엇으로 사는가?", "톨스토이", 9000);
		Book otherBook = new Book("사람은 무엇으로 사는가?", "톨스토이", 9000);
		
		// then	: 결과 확인
//		assertEquals(aBook, otherBook);		// fail -> 동치성은 갖지만, 동일성은 다르다.
		assertEquals(aBook.getName(), otherBook.getName());
		assertEquals(aBook.getAuthor(), otherBook.getAuthor());
		assertEquals(aBook.getPrice(), otherBook.getPrice());
		
		/*
		 * 위와 같이 객체의 상태비교를 위해 각각의 필드를 일일이 비교하면 불편하다.
		 * 실제 적용 시에 의도를 확실히 알기 위해 '메시지'를 적어주길 권장.
		 */
		assertReflectionEquals("Book 객체 필드 비교", aBook, otherBook);
	}
	
	@Test
	public void testLenientOrder() throws Exception {
		List<Integer> myList = Arrays.asList(3, 2, 1);
		
		assertReflectionEquals(Arrays.asList(1, 2, 3), myList, LENIENT_ORDER);		// 컬렉션이나 배열을 비교할 때 순서 무시.
	}
	
	@Test
	public void testIgnoreDefaults() throws Exception {
		Book expectedObject = new Book("사람은 무엇으로 사는가?", null, 9000);
		Book actualObject = new Book("사람은 무엇으로 사는가?", "톨스토이", 9000);
		
		/* 
		 * java의 타입 기본값이 할당되어 있을 경우, 동치성 판단 여부에 포함시키지 않는다.
		 * ex)
		 * - int			-> 0
		 * - Object		-> null
		 * - boolean	-> false
		 * 
		 * 주의할 점 : 필드 기본값 제외는 "예상 객체" 를 기준으로 한다!
		 * ( Fail !!! )
		 * Book expectedObject = new Book("사람은 무엇으로 사는가?", "톨스토이", 9000);
		 * Book actualObject = new Book("사람은 무엇으로 사는가?", null, 9000);
		 */
		assertReflectionEquals(expectedObject, actualObject, IGNORE_DEFAULTS);
	}
	
	@Test
	public void testLenientDates() throws Exception {
		Book expectedObject = new Book("사람은 무엇으로 사는가?", null, 9000,
				new Date(System.currentTimeMillis() + 100));
		Book actualObject = new Book("사람은 무엇으로 사는가?", null, 9000,
				new Date(System.currentTimeMillis()));

		/*
		 * 로그성으로 기록되는 날짜를 비교하지 않게 할 수 있다.
		 */
		assertReflectionEquals(expectedObject, actualObject, LENIENT_DATES);
	}
	
	@Test
	public void testAssertLenientEquals() throws Exception {
		/*
		 * assertLenientEquals();
		 * - LENIENT_ORDER, IGNORE_DEFAULTS 두 개를 적용가능.
		 */
		// 컬렉션 순서가 다른 경우
		List<Integer> bag = Arrays.asList(100, 200, 300);
		assertLenientEquals(Arrays.asList(300, 200, 100), bag);
		
		// 배열의 순서가 다른 경우
		assertLenientEquals(new String[]{"a", "b", "c"}, new String[]{"b", "c", "a"});
		
		// 필드값이 타입 기본값일 경우 비교에서 제외
		Book expectedObject = new Book("사람은 무엇으로 사는가?", null, 9000);
		Book actualObject = new Book("사람은 무엇으로 사는가?", "톨스토이", 9000);
		assertLenientEquals(expectedObject, actualObject);
	}
	
	@Test
	public void testPlayerPropertyTest() throws Exception {
//		Player player = VolleyballTeamRepository.getCaptain();		// 저장소에서 주장 캐릭터의 정보를 불러온다.
		Player player = new Player("Sangjun Park", 31, 4);

		/*
		 * assertPropertyLenientEquals( 필드명, 예상되는 필드값, 실제 객체 );
		 * - 객체의 필드값을 비교할 때 사용한다.
		 * - 객체의 getter 메서드가 없을때 필드를 비교할 떄 사용하면 편리.
		 * - JavaBeans 규약을 따르고 있기 때문에, 후일 객체에 getter 메서드가 생성되면 해당 getter 메서드를 이용해 비교. 
		 */
		assertPropertyLenientEquals("age", 31, player);
		assertPropertyLenientEquals("experienceYear", 4, player);
	}
	
}

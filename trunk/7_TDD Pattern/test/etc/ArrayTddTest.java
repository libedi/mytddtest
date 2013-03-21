/**
 * 배열 테스트
 * - JUnit 4의 assertArrayEquals()를 이용.
 * - Unitils의 assertReflectionEquals()나 assertLenientEquals()를 이용.
 * - JUnit 3의 경우, List로 변환해서 비교. 
 */
package etc;

import static org.junit.Assert.*;
import static org.unitils.reflectionassert.ReflectionAssert.*;
import static org.unitils.reflectionassert.ReflectionComparatorMode.*;

import java.util.Arrays;

import org.junit.Test;

public class ArrayTddTest {
	
	// assertArrayEquals() 사용. 순서도 비교.
	@Test
	public void testArrayEqual() throws Exception {
		String[] arrayA = new String[] { "A", "B", "C" };
		String[] arrayB = new String[] { "A", "B", "C" };

		assertArrayEquals("두 배열의 순서가 같아야 함", arrayA, arrayB);
	}

	// assertArrayEquals() 사용. 순서는 무관.
	@Test
	public void testArrayEqual_NotSorted() throws Exception {
		String[] arrayA = new String[] { "A", "B", "C" };
		String[] arrayB = new String[] { "B", "A", "C" };

		// 비교를 위해 두 배열을 같은 방식으로 정렬
		Arrays.sort(arrayA);
		Arrays.sort(arrayB);

		assertArrayEquals("두 배열의 순서는 달라도 무방", arrayA, arrayB);
	}
	
	/*
	 * Unitils의 assertReflectionEquals()나 assertLenientEquals()를 이용.
	 * Unitils 라이브러리를 다운받아 unitils-core의 jar파일을 빌드패스에 추가.
	 */
	@Test
	public void testArrayEquals_NotSortedUsingUnitils() throws Exception {
		String[] arrayA = new String[] { "A", "B", "C" };
		String[] arrayB = new String[] { "B", "A", "C" };
		
		// import static org.unitils.reflectionassert.ReflectionAssert.*;
		// import static org.unitils.reflectionassert.ReflectionComparatorMode.*;
		assertReflectionEquals("두 배열의 순서는 달라도 무방", arrayA, arrayB, LENIENT_ORDER);
		
		// import static org.unitils.reflectionassert.ReflectionAssert.*;
		assertLenientEquals("두 배열의 순서는 달라도 무방", arrayA, arrayB);
	}
	
	@Test
	public void testArrayEquals_ByJUnit3() throws Exception {
		String[] arrayA = new String[] { "A", "B", "C" };
		String[] arrayB = new String[] { "A", "B", "C" };
		
		assertEquals("List로 만들어서 비교", Arrays.asList(arrayA), Arrays.asList(arrayB));
	}
}

/**
 * 객체 동치성 테스트
 * - 객체 동치 비교 : 객체의 상태(필드값)을 비교.
 * - 내부 상태를 직접 꺼내와 각각 비교.
 * - toString을 중첩구현해(override)놓고, toString값으로 비교.
 * - equals() 메서드를 중첩구현.
 * - Unitils의 assertReflectionEquals()를 사용.
 */
package etc;

import static org.junit.Assert.*;
import static org.unitils.reflectionassert.ReflectionAssert.*;

import org.junit.Test;

public class EquivalentTest {
	
	// 동치성이란?
	@Test
	public void testEquals_case1() throws Exception {
		Music musicA = new Music("Better in time", "Leona Lewis");
		Music musicB = musicA;
		
		assertEquals( musicA, musicB );	// Pass!
	}
	@Test
	public void testEquals_case2() throws Exception {
		Music musicA = new Music("Better in time", "Leona Lewis");
		Music musicB = new Music("Better in time", "Leona Lewis");
		
		// equals() 오버라이드 전까지는 fail.
		assertEquals(musicA, musicB);	// Fail! 두 객체는 엄연히 다르다. 그러나 상태는 동일. 이것이 동치!
	}
	
	/*
	 * 내부 상태를 직접 가져와 비교.
	 * - 필드가 많지 않을 때 사용.
	 */
	@Test
	public void testEquals_상태직접비교() throws Exception {
		Music musicA = new Music("Better in time", "Leona Lewis");
		Music musicB = new Music("Better in time", "Leona Lewis");
		assertEquals( musicA.getPerformerName(), musicB.getPerformerName() );
		assertEquals( musicA.getSongName(), musicB.getSongName() );
	}

	/*
	 * 객체에 toString을 구현하여 비교.
	 * - 대상 클래스에 toString 구현이 안되어 있고,
	 * - 클래스가 확장될 가능성이 적을 경우 사용.
	 * - 일종의 트릭. 
	 */
	@Test
	public void testEquals_toString구현비교() throws Exception {
		Music musicA = new Music("Better in time", "Leona Lewis");
		Music musicB = new Music("Better in time", "Leona Lewis");
		assertEquals( musicA.toString(), musicB.toString() );
	}
	
	// assertReflectionEquals() 사용.
	@Test
	public void testEquals_usingUnitils() throws Exception {
		Music musicA = new Music("Better in time", "Leona Lewis");
		Music musicB = new Music("Better in time", "Leona Lewis");
		
		assertReflectionEquals( musicA, musicB );
	}
	
}

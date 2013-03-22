/**
 * 컬렉션 테스트
 * - 자바 기본형 또는 String 이 컬렉션에 들어있는 경우
 * - 일반 객체가 컬렉션에 들어있는 경우
 */
package etc;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CollectionTest {
	
	// 기본형, String 클래스의 경우 곧바로 비교 가능.
	@Test
	public void testListEqual_Primitive() throws Exception {
		List<String> listA = new ArrayList<String>();
		listA.add("박상준");
		listA.add("오시내");
		
		List<String> listB = new ArrayList<String>();
		listB.add("박상준");
		listB.add("오시내");
		
		assertEquals( "리스트 비교", listA, listB );
	}
	
	/*
	 * 일반 객체가 컬렉션에 들어있는 경우
	 * - assertEquals는 기대값과 실제값을 서로 equals 로 비교.
	 * - equals 비교시 대상 객체의 toString 값으로 비교.
	 * - 컬렉션의 toString은 포함하는 원소의 toString을 문자열로 만들어 반환.
	 * 
	 */
	@Test
	public void testListEqual_NotSorted() throws Exception {
		List<Employee> listA = new ArrayList<Employee>();
		listA.add( new Employee("박상준"));
		listA.add( new Employee("오시내"));
		
		List<Employee> listB = new ArrayList<Employee>();
		listB.add( new Employee("박상준"));
		listB.add( new Employee("오시내"));
		
		// 원소 객체 (Employee)의 toString은 본래 객체 ID반환.
		// 따라서, 원소 객체의 toString을 오버라이드.
		System.out.println(listA);
		System.out.println(listA.toString());
		System.out.println(listB.toString());
		
		System.out.println(listA.equals(listB));	// List 클래스의 equals는 리스트객체ID로 비교.
		System.out.println(listA.toString().equals(listB.toString()));	// 따라서, String 클래스의 equals로 비교한다!
		
//		assertEquals("리스트 비교", listA, listB);	// List 클래스의 equals는 리스트객체ID로 비교.
		assertEquals("리스트 비교", listA.toString(), listB.toString());		// 따라서, String 클래스의 equals로 비교한다!
	}
	

}

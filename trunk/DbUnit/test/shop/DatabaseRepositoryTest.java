package shop;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

public class DatabaseRepositoryTest {

	private final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private final String protocol = "jdbc:derby:";
	private final String dbName = "shopdb";

	// IDatabaseTest : DbUnit 라이브러리의 인터페이스. DB연결과 데이터셋 관련 기능 정의.
	private IDatabaseTester databaseTester; 

	/*
	 * 테스트 메서드를 수행하기 전에 seller.xml에 지정된 상태로 테이블을 초기화.
	 * -> 테스트가 수행되며 DB 테이블이 변경되는 상황을 방지.
	 */
	@Before
	public void setUp() throws Exception {
		
		/*
		 * JdbcDatabaseTester :
		 * - DriverManager를 이용해 DB 커넥션 생성.
		 * PropertiesBasedJdbcDatabaseTester :
		 * - DriverManager를 이용해 DB 커넥션 생성. 단, 연결설정을 시스템 프로퍼티로부터 읽어옴.
		 * DataSourceDatabaseTester :
		 * - javax.sql.DataSource를 이용해 DB 커넥션 생성.
		 * JndiDatabaseTester :
		 * - JNDI를 이용해 DataSource를 가져온다.
		 */
		databaseTester = new JdbcDatabaseTester(driver, protocol + dbName);

		try {
			// 데이터셋 지정.
			IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File(
					"seller.xml"));
			/* 
			 * DB 커넥션과 데이터셋을 이용해 DB에 특정 작업을 수행.
			 * CLEAN_INSERT : 데이터셋에 지정된 DB테이블 내용 모두 삭제 -> 데이터셋에 들어있는 값으로 INSERT
			 * 						: DELETE_ALL + INSERT
			 */
			DatabaseOperation.CLEAN_INSERT.execute(
					databaseTester.getConnection(), dataSet);
		} finally {
			databaseTester.getConnection().close();
		}
	}

	@Test
	public void testFindById() throws Exception {
		// given : 선행조건 기술
		Seller expectedSeller = new Seller("horichoi", "최승호",
				"megaseller@hatmail.com");

		// when : 기능 수행
		Repository repository = new DatabaseRepository();
		Seller actualSeller = repository.findById("horichoi");

		// then : 결과 확인
		assertEquals(expectedSeller.getId(), actualSeller.getId());
		assertEquals(expectedSeller.getName(), actualSeller.getName());
		assertEquals(expectedSeller.getEmail(), actualSeller.getEmail());

	}
}

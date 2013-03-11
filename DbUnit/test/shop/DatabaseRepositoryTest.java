package shop;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.SortedTable;
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
		Seller expectedSeller = new Seller("horichoi", "sh.choi",
				"megaseller@hatmail.com");

		// when : 기능 수행
		Repository repository = new DatabaseRepository();
		Seller actualSeller = repository.findById("horichoi");

		// then : 결과 확인
		assertEquals(expectedSeller.getId(), actualSeller.getId());
		assertEquals(expectedSeller.getName(), actualSeller.getName());
		assertEquals(expectedSeller.getEmail(), actualSeller.getEmail());
	}
	
	@Test
	public void testAddNewSeller_높은의존성() throws Exception {
		// given	: 선행조건 기술
		Seller newSeller = new Seller("hssm", "dw.lee", "scala@hssm.kr");
		
		// when	: 기능 수행
		Repository repository = new DatabaseRepository();
		repository.add(newSeller);		// 새로운 판매자 추가

		// then	: 결과 확인
		Seller sellerFromRepository = repository.findById("hssm");		// findById() 메서드에 의존성을 갖는다!
		
		assertEquals(newSeller.getId(), sellerFromRepository.getId());
		assertEquals(newSeller.getName(), sellerFromRepository.getName());
		assertEquals(newSeller.getEmail(), sellerFromRepository.getEmail());
	}
	
	@Test
	public void testAddNewSeller_의존성최소화() throws Exception {
		Seller newSeller = new Seller("hssm", "dw.lee", "scala@hssm.kr");

		Repository repository = new DatabaseRepository();
		repository.add(newSeller);

		/*
		 *  현재 데이터베이스 상태를 데이터셋으로 추출.
		 *  createDataSet() 파라미터로 테이블 이름 지정 가능.
		 */
		IDataSet currentDBdataSet = this.databaseTester.getConnection().createDataSet();
		// 데이터셋에서 특정 테이블(seller)을 가져온다.
		ITable actualTable = currentDBdataSet.getTable("seller");
		
		// 미리 만들어 놓은 예상 데이터셋을 읽어들인다.
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("expected_seller.xml"));
		// 예상 데이터셋 중 비교에 사용할 테이블을 읽어들인다.
		ITable expectedTable = expectedDataSet.getTable("seller");

		// DbUnit에서 제공하는 Assertion.assertEquals() 메서드를 이용.
		/*
		 * DbUnit은 테이블 추출시 PK로 정렬하여 추출한다.
		 * 파일로부터 데이터셋 추출시는 정렬하지 않는다.
		 * 따라서 둘 중 하나의 정렬상태를 맞춰줘야함!
		 * ex) ITable actualTable = this.databaseTester.getConnection().createQueryTable("seller", "SELECT * FROM SELLER");	// 정렬 안하고 그냥 추출. 
		 */
		Assertion.assertEquals(new SortedTable(expectedTable), actualTable);		// 정렬안된 테이블 정렬.
	}
	
}

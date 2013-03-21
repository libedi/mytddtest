package shop;

import static org.unitils.reflectionassert.ReflectionAssert.assertPropertyLenientEquals;

import org.dbunit.database.IDatabaseConnection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;
import org.unitils.dbunit.annotation.ExpectedDataSet;

@RunWith(UnitilsJUnit4TestClassRunner.class)		
@DataSet							
public class DatabaseRepositoryTest {

	@Test
	public void testFindById() throws Exception {
		Repository repository = new DatabaseRepository();
		Seller actualSeller = repository.findById("horichoi");

		assertPropertyLenientEquals("id", "horichoi", actualSeller);
		assertPropertyLenientEquals("name", "sh.choi", actualSeller);
		assertPropertyLenientEquals("email", "megaseller@hatmail.com", actualSeller);
	}
	
	/*
	 * 예상 데이터셋을 이용한 테스트 메서드 레벨의 결과 비교.
	 * 예상 데이터셋(expected_seller.xml) 과 결과를 비교한다.
	 */
	@Test
	@ExpectedDataSet("expected_seller.xml")
	public void testAddNewSeller() throws Exception {
		Seller newSeller = new Seller("hssm", "dw.lee", "scala@hssm.kr");
		Repository repository = new DatabaseRepository();
		repository.add(newSeller);
	}
	
}

package shop;

import static org.unitils.reflectionassert.ReflectionAssert.assertPropertyLenientEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.dbunit.annotation.DataSet;

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
	
}

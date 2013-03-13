/**
 * Repository Interface
 * - 판매자 정보를 DB에 저장하는 DatabaseRepository 클래스의 인터페이스.
 * - 향후 DB가 아닌 파일이나 다른 매체에 저장될 수 있기 때문에,
 * - 인터페이스를 만들어 좀더 유연한 구조가 되게 한다.
 */
package shop;

public interface Repository {
	public Seller findById(String id);
	public void add(Seller seller);
	public void update(Seller seller);
	public void remove(Seller seller);
}

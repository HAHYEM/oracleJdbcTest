package pro05;

import java.util.List;
import java.util.Scanner;

public class BookShopApp {

	public static void main(String[] args) {

		BookShopDao dao = new BookShopDao();
		BookVo vo = new BookVo();

		vo.setTitle("Ʈ���϶���");
		vo.setAuthorName("�����Ĵϸ��̾�");
		dao.insert(vo);
/*
		vo.setTitle("����");
		vo.setAuthorName("�����Ĵϸ��̾�");
		dao.insert(vo);

		vo.setTitle("��Ŭ����");
		vo.setAuthorName("�����Ĵϸ��̾�");
		dao.insert(vo);

		vo.setTitle("Ʈ���϶���");
		vo.setAuthorName("�����Ĵϸ��̾�");
		dao.insert(vo);

		vo.setTitle("�극��ŷ��");
		vo.setAuthorName("�����Ĵϸ��̾�");
		dao.insert(vo);

		vo.setTitle("�Ƹ���");
		vo.setAuthorName("������");
		dao.insert(vo);

		vo.setTitle("�����׵�");
		vo.setAuthorName("�赿��");
		dao.insert(vo);

		vo.setTitle("�����ϱ� û���̴�");
		vo.setAuthorName("�賭��");
		dao.insert(vo);

		vo.setTitle("��õ");
		vo.setAuthorName("õ��");
		dao.insert(vo);

		vo.setTitle("�¹���");
		vo.setAuthorName("������");
		dao.insert(vo);

		vo.setTitle("Ǯ�Ͽ콺");
		vo.setAuthorName("������");
		dao.insert(vo);*/

		Scanner key = new Scanner(System.in);
		System.out.print("�뿩 �ϰ� ���� å�� ��ȣ�� �Է��ϼ���.:");
		int num = key.nextInt();

		/*
		 * ���⿡ �Է¹��� å��ȣ�� ��ġ�ϴ� BookShopDao�� rent()�� ȣ���ϴ� �ڵ带 �ۼ��ϼ���.
		 * 
		 */

		dao.rent(num);

		displayBookInfo();
	}

	public static void displayBookInfo() {
		List<BookVo> list = BookShopDao.getListAll();
		System.out.println("*****���� ���� ����ϱ�******");
		System.out.println();
		for (BookVo vo : list) {
			System.out.println(vo);
		}

	}
}

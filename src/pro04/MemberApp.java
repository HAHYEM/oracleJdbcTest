package pro04;

import java.util.List;

public class MemberApp {
	
	public static List<MemberVo> mList ;
	public static MemberDao dao = new MemberDao();	
	
	public static void main(String[] args) {
		
	MemberVo vo = new MemberVo();
		// ���� �߰�
		
		vo.setEmail( "kim@bit.ac.kr" );
		vo.setName( "���Ʈ" );
		vo.setGender( "��" );
		vo.setPassword( "12345" );
		
		dao.insertMember( vo );
		
		// ���� ��� ����Ʈ ���
		printAllMemebrList();
		
		
		
		// �̻�� �߰�
		vo.setEmail( "lee@bit.ac.kr" );
		vo.setName( "�̺�Ʈ" );
		vo.setGender( "��" );
		vo.setPassword( "12345" );

		dao.insertMember( vo );
		
		// ���� ��� ����Ʈ ���
		printAllMemebrList();
		


		// �̻�� ��й�ȣ ����(�̸��� �ּ� ã�Ƽ� ��й�ȣ�� ����Ǿ����)
		vo.setEmail( "lee@bit.ac.kr" );
		vo.setName( "" );
		vo.setGender( "" );
		vo.setPassword( "54321" );

		dao.updatePassword( vo );
		// ���� ��� ����Ʈ ���
		printAllMemebrList();
		

		// ���� ����
		dao.deleteMember( "kim@bit.ac.kr" );
		// ���� ��� ����Ʈ ���
		printAllMemebrList();
		
		
	}
	
	public static void printAllMemebrList() {
		System.out.println( "***** ����  ��� ����Ʈ *****" );

		//����ڵ��ۼ�
		for(MemberVo vo : dao.getListAll()) {
			System.out.println(vo.getId()  + " | " + vo.getName() + " | " + vo.getEmail()  + " | " +  vo.getPassword()  + " | " + vo.getGender());
		}
		System.out.println();
	}
	

}
package or.clearn.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import or.vo.AdminVO;
import or.vo.ParentVO;

@Repository
public class LoginDao {
	
	@Autowired
	private SqlSessionTemplate ss;
	
	// Login 처리(id, pwd)
	public int userloginProcess(String id, String pw){
		ParentVO vo = new ParentVO();
		vo.setP_id(id); vo.setP_pw(pw);
		return ss.selectOne("login.userChk", vo);
	}
	
	public int adminloginProcess(String id, String pw){
		AdminVO vo = new AdminVO();
		vo.setA_id(id); vo.setA_pwd(pw);
		return ss.selectOne("login.adminChk", vo);
	}
	
	public String getAdminNick(String id){
		System.out.println(id+" admin 닉 가져오기");
		String res = ss.selectOne("login.adminNick",id);
		System.out.println(res);
		return res;
	}
	
	public String getUserNick(String id){
		System.out.println(id+" user 닉 가져오기");
		String res = ss.selectOne("login.userNick", id);
		System.out.println(res);
		return res;
	}
	
}

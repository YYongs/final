package or.clearn.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import or.clearn.dao.LoginDao;
import or.vo.ParentVO;


@Controller
public class LoginController {
	
	@Autowired
	private LoginDao loginDao;
	
	@RequestMapping(value={"/","index"})
	public String indexView(){
		return "index";
	}
	
	@RequestMapping(value = "/loginform")
	public ModelAndView loginView(){
		ModelAndView mav = new ModelAndView("loginform");
		return mav;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(String id, String pw, HttpSession session) {
		ModelAndView mav = new ModelAndView("redirect:/index");
		System.out.println("id : "+id);
		System.out.println("pw : "+pw);
		int admin_res = loginDao.adminloginProcess(id, pw);
		int parent_res = loginDao.userloginProcess(id, pw);
		String msg = "";
		if (admin_res > 0) {
			msg = "관리자 로그인 성공";
			session.setAttribute("uid", loginDao.getAdminNick(id));
		} else if(parent_res>0) {
			msg = "사용자 로그인 성공";
			session.setAttribute("uid", loginDao.getUserNick(id));
		} else {
			msg = "로그인 실패";
		}
		System.out.println("Log Message :" + msg);
		return mav;
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpSession session) {
		session.removeAttribute("uid");
		return new ModelAndView("redirect:/index");
	}
}

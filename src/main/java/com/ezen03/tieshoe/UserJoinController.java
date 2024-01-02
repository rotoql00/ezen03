package com.ezen03.tieshoe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ezen03.tieshoe.helper.RegexHelper;
import com.ezen03.tieshoe.helper.WebHelper;
import com.ezen03.tieshoe.service.UserService;

@Controller
public class UserJoinController {
	@Autowired
	WebHelper webHelper;
	
	@Autowired
	RegexHelper regexHelper;
	
	@Autowired
	UserService userSerivce;
	
	@Value("#{servletContext.contextPath}")
	String contextPath;
	
	/** 목록 페이지 */
	@RequestMapping(value="/TESTUser/list.do",method = RequestMethod.GET)
	public ModelAndView list (Model model) {
		String viewPath ="";
		return new ModelAndView(viewPath);
	}
	
	/** 상세 페이지 */
	@RequestMapping(value="/TESTUser/view.do",method = RequestMethod.GET)
	public ModelAndView view (Model model) {
		return null;
	}
	
	/** 작성 폼 페이지 */
	@RequestMapping(value="/TESTUser/add.do",method = RequestMethod.GET)
	public ModelAndView add (Model model) {	
		return null;
	}
	
	/** 작성 폼에 대한 action 페이지 */
	@RequestMapping(value="/TESTUser/add_ok.do",method = RequestMethod.POST)
	public ModelAndView add_ok (Model model) {
		return null;
	}
	
	/** 수정 폼 페이지 */
	@RequestMapping(value="/TESTUser/edit.do",method = RequestMethod.GET)
	public ModelAndView edit (Model model) {
		return null;
	}
	
	/** 수정 폼에 대한 action 페이지 */
	@RequestMapping(value="/TESTUser/edit_ok.do",method = RequestMethod.POST)
	public ModelAndView edit_ok (Model model) {
		return null;
	}
	
	/** 삭제  페이지 */
	@RequestMapping(value="/TESTUser/delete_ok.do",method = RequestMethod.GET)
	public ModelAndView delete_ok (Model model) {
		return null;
	}
}

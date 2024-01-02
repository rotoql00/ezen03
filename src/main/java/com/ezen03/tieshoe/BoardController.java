/*
 * FileName: BoardController.java
 * Description: 게시판에 대한 접근이 가능하게 하는 controller 입니다.
 * Author: 임석현
 **/
package com.ezen03.tieshoe;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

import com.ezen03.tieshoe.helper.PageData;
import com.ezen03.tieshoe.helper.MailHelper;
import com.ezen03.tieshoe.helper.RegexHelper;
import com.ezen03.tieshoe.helper.RetrofitHelper;
import com.ezen03.tieshoe.helper.WebHelper;
import com.ezen03.tieshoe.model.boardBeans;
import com.ezen03.tieshoe.model.userBeans;
import com.ezen03.tieshoe.service.BoardService;
import com.ezen03.tieshoe.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * Handles requests for the application home page.
 */
@Slf4j
@Controller
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	WebHelper webHelper;

	@Autowired
	RegexHelper regexHelper;

	@Autowired
	RetrofitHelper retrofitHelper;

	@Autowired
	MailHelper mailHelper;

	@Autowired
	UserService userService;

	@Autowired
	BoardService boardService;

	@Value("#{servletContext.contextPath}")
	String contextPath;

	/** 목록 페이지 */
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public ModelAndView board(Model model,
			@RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
			@RequestParam(value = "page", defaultValue = "1", required = false) int nowPage,
			@RequestParam(value = "totalCount", defaultValue = "0", required = false) int totalCount,
			@RequestParam(value = "listCount", defaultValue = "10", required = false) int listCount,
			@RequestParam(value = "pageCount", defaultValue = "5", required = false) int pageCount) {

		/** 2) 데이터 조회하기 */
		// 조회에 필요한 조건값(검색어)를 Beans에 담는다.
		boardBeans input = new boardBeans();
		input.setBoardTitle(keyword);
		input.setBoardContext(keyword);
		input.setBoardDate(keyword);

		List<boardBeans> output = null; // 조회결과가 저장될 객체
		PageData pageData = null; // 페이지 번호를 계산한 결과가 저장될 객체

		try {
			// 전체 게시글 수 조회
			totalCount = boardService.getBoardCount(input);
			// 페이지 번호 계산 --> 계산결과를 로그로 출력될 것이다.
			pageData = new PageData(nowPage, totalCount, listCount, pageCount);

			// SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장
			boardBeans.setOffset(pageData.getOffset());
			boardBeans.setListCount(pageData.getListCount());

			// 데이터 조회하기
			output = boardService.getBoardList(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}

		/** 3) View 처리 */
		model.addAttribute("keyword", keyword);
		model.addAttribute("output", output);
		model.addAttribute("pageData", pageData);

		String viewPath = "board/board";
		return new ModelAndView(viewPath);
	}

	/** 상세 페이지 */
	@RequestMapping(value = "/board/view", method = RequestMethod.GET)
	public ModelAndView view(Model model, @RequestParam(value = "ID", required = false) int ID) {
		/** 1) 필요한 변수값 생성 */
		// 조회할 대상에 대한 PK값
		ID = webHelper.getInt("ID");

		// 이 값이 존재하지 않는다면 데이터 조회가 불가능하므로 반드시 필수값으로 처리해야 한다.
		if (ID == 0) {
			return webHelper.redirect(null, "게시된 공지가 없습니다.");
		}

		/** 2) 데이터 조회하기 */
		// 데이터 조회에 필요한 조건값을 Beans에 저장하기
		boardBeans input = new boardBeans();
		input.setID(ID);

		// 조회결과를 저장할 객체 선언
		boardBeans output = null;

		try {
			// 데이터 조회
			output = boardService.getBoardItem(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}

		/** 3) View 처리 */
		model.addAttribute("output", output);
		return new ModelAndView("board/view");
	}

	

	/** 수정 폼 페이지 */
	@RequestMapping(value = "/board/edit", method = RequestMethod.GET)
	public ModelAndView edit(Model model, @RequestParam(value = "ID", required = false) int ID) {

		// 이 값이 존재하지 않는다면 데이터 조회가 불가능하므로 반드시 필수값으로 처리해야 한다.
		if (ID == 0) {
			return webHelper.redirect(null, "수정할 게시글이 없습니다.");
		}

		/** 2) 데이터 조회하기 */
		// 데이터 조회에 필요한 조건값을 Beans에 저장하기
		boardBeans input = new boardBeans();
		input.setID(ID);
		;

		// 조회결과를 저장할 객체 선언
		boardBeans output = null;

		try {
			output = boardService.getBoardItem(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}

		/** 3) View 처리 */
		model.addAttribute("output", output);
		return new ModelAndView("board/edit");
	}

	/** 수정 폼에 대한 action 페이지 */
	@RequestMapping(value = "/board/edit_ok", method = RequestMethod.POST)
	public ModelAndView edit_ok(Model model, @RequestParam(value = "ID", required = false) int ID,
			@RequestParam(value = "boardTitle", required = false) String boardTitle,
			@RequestParam(value = "boardContext", required = false) String boardContext) {

		if (ID == 0) {
			return webHelper.redirect(null, "수정할 게시글이 없습니다.");
		}

		if (boardTitle == null) {
			return webHelper.redirect(null, "공지글 제목이 없습니다.");
		}

		if (boardContext == null) {
			return webHelper.redirect(null, "공지 내용을 입력하세요.");
		}

		/** 2) 데이터 수정하기 */
		// 수정할 값들을 Beans에 담는다.
		boardBeans input = new boardBeans();
		input.setID(ID);
		input.setBoardTitle(boardTitle);
		input.setBoardContext(boardContext);

		try {
			// 데이터 수정
			boardService.editBoard(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}

		/** 3) 결과를 확인하기 위한 페이지 이동 */
		// 수정한 대상을 상세페이지에 알려주기 위해서 PK값을 전달해야 한다.
		String redirectUrl = contextPath + "/board/view.do?ID=" + input.getID();
		return webHelper.redirect(redirectUrl, "수정되었습니다.");
	}

	/** 공지 삭제 기능 */
	@RequestMapping(value = "/board/delete_ok", method = RequestMethod.GET)
	public ModelAndView delete_ok(Model model, @RequestParam(value = "ID", required = false) int ID) {

		// 이 값이 존재하지 않는다면 데이터 삭제가 불가능하므로 반드시 필수값으로 처리해야 한다.
		if (ID == 0) {
			return webHelper.redirect(null, "삭제할 공지가 없습니다.");
		}

		/** 2) 데이터 삭제하기 */
		// 데이터 삭제에 필요한 조건값을 Beans에 저장하기
		boardBeans input = new boardBeans();
		input.setID(ID);

		try {
			// 데이터 삭제
			boardService.deleteBoard(input);
		} catch (Exception e) {
			return webHelper.redirect(null, e.getLocalizedMessage());
		}

		/** 3) 페이지 이동 */
		// 확인할 대상이 삭제된 상태이므로 목록 페이지로 이동
		return webHelper.redirect(contextPath + "/board", "삭제되었습니다.");
	}

	@RequestMapping(value = "/faq", method = { RequestMethod.GET })
	public String faq(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);

		return "board/faq";
	}

	@RequestMapping(value = "/userGuide", method = { RequestMethod.GET })
	public String userGuide(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);

		return "board/userGuide";
	}

	/* 공지  파일 등록 */
	@RequestMapping(value = "/board/addBoard.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView addBoardInfo(Model model, HttpServletRequest request,
			@RequestParam(value = "boardTitle", required = false) String boardTitle,
			@RequestParam(value = "boardDate", required = false) String boardDate,
			@RequestParam(value = "boardContext", required = false) String boardContext,
			@RequestParam(value = "reg_date", required = false) String reg_date,
			@RequestParam(value = "edit_date", required = false) String edit_date,
			@RequestParam(value = "boardImgPath", required = false) MultipartFile boardImgPath) {
		
		/* 이미지가 존재 하지 않을 경우 Alert 합니다. */
		// 이미지가 없을 경우에 즉, 이미지값이 들어오지 않았을떄에 return 합니다.
		if (boardImgPath.getOriginalFilename().isEmpty()) {
			return webHelper.redirect(null, "이미지를 선택되지 않았습니다.");
		}
		
		// 업로드 된 파일이 저장될 경로 정보를 생성한다.
        File targetFile = new File(webHelper.getUploadDir() + "/board", boardImgPath.getOriginalFilename());
        
        // 업로드 된 파일을 지정된 경로로 복사한다.
        try {
        	boardImgPath.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
            return webHelper.redirect(null, "업로드 된 파일 저장에 실패했습니다.");
        }

        /** 2) 업로드 경로 정보 처리하기 */
        // 복사된 파일의 절대경로를 추출한다.
        // --> 운영체제 호환을 위해 역슬래시를 슬래시로 변환한다.
        String absPath = targetFile.getAbsolutePath().replace("\\", "/");
        
        // 절대경로에서 이미 root-context에 지정되어 있는 업로드 폴더 경로를 삭제한다.
        String filePath = absPath.replace(webHelper.getUploadDir(), "");

        String upload = "/upload";
    
        /** DB에 저장 */
		boardBeans input = new boardBeans();
		input.setBoardTitle(boardTitle);
		input.setBoardDate(boardDate);
		input.setBoardContext(boardContext);
		input.setReg_date(reg_date);
		input.setEdit_date(edit_date);
		input.setBoardImgPath(upload + filePath);

		try {
			boardService.addBoard(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String redirectUrl = contextPath + "/admin/announcement" ;
		return webHelper.redirect(redirectUrl, "공지가 등록 되었습니다.");
	}

}

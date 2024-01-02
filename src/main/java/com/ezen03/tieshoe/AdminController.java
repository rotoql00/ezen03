/*
 * FileName: AdminController.java
 * Description: 웹페이지의 최고 권한을 가지는 페이에 접근 가능하도록 하는 controller입니다.
 * Author: 임석현
 **/

package com.ezen03.tieshoe;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.ezen03.tieshoe.helper.*;
import com.ezen03.tieshoe.model.*;
import com.ezen03.tieshoe.service.*;
import com.ezen03.tieshoe.helper.*;

import lombok.extern.slf4j.Slf4j;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handles requests for the application home page.
 */
@Slf4j
@Controller
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    WebHelper webHelper;

    @Autowired
    RegexHelper regexHelper;

    @Autowired
    RetrofitHelper retrofitHelper;

    @Autowired
    MailHelper mailHelper;

    @Autowired
    AdminService adminService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    BoardService boardService;

    @Autowired
    OrderService orderService;

    @Value("#{servletContext.contextPath}")
    String contextPath;

    /**
     * Simply selects the home view to render by returning its name.
     */

    @RequestMapping(value = "/admin", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView admin(Locale locale, Model model) {

        int userCount = 0; // 총 회원수
        int productCount = 0; // 총 상품 수
        int orderCount = 0; // 모든 주문량
        int orderTodayCount = 0; //오늘 하루 판매갯수
        int orderTodayIncome = 0; // 오늘 총 수입
        int orderTotalIncome = 0; // 총 수입
        // 총 주문 정보
        List<orderBeans> orderOutput = null;

        Object ID = webHelper.getSession("UserAdmin", "null");

        if (!ID.equals("Y") || ID.equals(null)) {
            return webHelper.redirect(contextPath + "/error", null);
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String today = df.format(cal.getTime()); // 오늘의 날자

        try {
            // 모든 사용자들의 수
            userCount = userService.countAllUsers();
            // 모든 상품의 갯수
            productCount = productService.countAllProduct();
            // 모든 주문 정보
            orderOutput = orderService.countIncome();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < orderOutput.size(); i++) {
            orderCount += 1; //주문건수를 더 합니다
            orderBeans single = orderOutput.get(i);
            orderTotalIncome += single.getBuy_ID() + single.getSell_ID(); // 총 수입
            if (single.getReg_date() == today) {
                orderTodayCount += 1; //오늘 하루 주문건을 더 합니다
                orderTodayIncome += single.getBuy_ID() + single.getSell_ID(); //하루 수입
            }
        }

        /** 3) View 처리 */
        model.addAttribute("userCount", userCount);
        model.addAttribute("productCount", productCount);
        model.addAttribute("orderCount", orderCount);
        model.addAttribute("orderTodayCount", orderTodayCount);
        model.addAttribute("orderTotalIncome", orderTotalIncome);
        model.addAttribute("orderTodayIncome", orderTodayIncome);

        return new ModelAndView("admin/admin");
    }

    @RequestMapping(value = "/admin/addProduct", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addProduct(Locale locale, Model model) {

        Object ID = webHelper.getSession("UserAdmin", "null");

        if (!ID.equals("Y") || ID.equals(null)) {
            return webHelper.redirect(contextPath + "/error", null);
        }

        // 관리자 권한 검사
        switch ((Integer) webHelper.getSession("userLevel")) {
            case 1:
            case 2:
                break;
            default:
                return webHelper.redirect(contextPath + "/adminError", null);
        }

        //view 처리
        return new ModelAndView("admin/add_product/add_product");
    }

    @RequestMapping(value = "/admin/addProduct.do", method = RequestMethod.POST)
    public ModelAndView addProductWork(Model model, @RequestParam String productCode,
                                       @RequestParam String productNameEN,
                                       @RequestParam String productNameKR,
                                       @RequestParam String productColor,
                                       @RequestParam String productReleaseDate,
                                       @RequestParam String productReleasePrice,
                                       @RequestParam String productBrand,
                                       @RequestParam(required = false) MultipartFile image) {

        //view 처리
        return new ModelAndView("admin/add_product/add_product");
    }

    /**
     * 공지 사항 관리 페이지
     */
    @RequestMapping(value = "/admin/announcement", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView announcement(Model model,
                                     @RequestParam(value = "keyword", defaultValue = "", required = false) String keyword,
                                     @RequestParam(value = "page", defaultValue = "1", required = false) int nowPage,
                                     @RequestParam(value = "totalCount", defaultValue = "0", required = false) int totalCount,
                                     @RequestParam(value = "listCount", defaultValue = "10", required = false) int listCount,
                                     @RequestParam(value = "pageCount", defaultValue = "5", required = false) int pageCount
    ) {

        // 관리자가 아니라면 에러페이지로 리다이렉트 합니다.
        Object ID = webHelper.getSession("UserAdmin", "null");

        if (!ID.equals("Y") || ID.equals(null)) {
            return webHelper.redirect(contextPath + "/error", null);
        }

        // 관리자 권한 검사
        switch ((Integer) webHelper.getSession("userLevel")) {
            case 1:
            case 4:
                break;
            default:
                return webHelper.redirect(contextPath + "/adminError", null);
        }

        /** 2) 데이터 조회하기 */
        // 조회에 필요한 조건값(검색어)를 Beans에 담는다.
        boardBeans input = new boardBeans();
        input.setBoardTitle(keyword);
        input.setBoardContext(keyword);
        input.setBoardDate(keyword);

        List<boardBeans> output = null; // 조회결과가 저장될 객체
        PageData pageData = null;       // 페이지 번호를 계산한 결과가 저장될 객체

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

        String viewPath = "admin/announcement/announcement";
        return new ModelAndView(viewPath);
    }

    /**
     * 공지 사항 등록 action 페이지
     */
    @RequestMapping(value = "/admin/announcement_ok", method = RequestMethod.POST)
    public ModelAndView add_ok(Model model,
                               @RequestParam(value = "boardTitle", required = false) String boardTitle,
                               @RequestParam(value = "boardDate", required = false) String boardDate,
                               @RequestParam(value = "boardContext", required = false) String boardContext,
                               @RequestParam(value = "reg_date", required = false) String reg_date,
                               @RequestParam(value = "edit_date", required = false) String edit_date
    ) {

        /** 관리자가 아니라면 에러페이지로 리다이렉트 합니다. */
        Object authUser = webHelper.getSession("UserAdmin", "null");

        if (!authUser.equals("Y") || authUser.equals(null)) {
            log.info("Asd");
            return webHelper.redirect(contextPath + "/error", null);
        }

        log.info("out");

        // 위치는 미필수(null허용)이므로 입력 여부를 검사하지 않는다.
        if (boardContext == null) {
            return webHelper.redirect(null, "게시할 공지 내용을 입력하세요.");
        }

        /** 2) 데이터 저장하기 */
        // 저장할 값들을 Beans에 담는다.
        boardBeans input = new boardBeans();
        input.setBoardTitle(boardTitle);
        input.setBoardDate(boardDate);
        input.setBoardContext(boardContext);
        input.setReg_date(reg_date);
        input.setEdit_date(edit_date);

        try {
            // 데이터 저장
            // --> 데이터 저장에 성공하면 파라미터로 전달하는 input 객체에 PK값이 저장된다.
            boardService.addBoard(input);
        } catch (Exception e) {
            return webHelper.redirect(null, e.getLocalizedMessage());
        }

        /** 3) 결과를 확인하기 위한 페이지 이동 */
        // 저장 결과를 확인하기 위해서 데이터 저장시 생성된 PK값을 상세 페이지로 전달해야 한다.
        String redirectUrl = contextPath + "/board/view?ID=" + input.getID();
        return webHelper.redirect(redirectUrl, "게시글이 성공적으로 저장되었습니다.");
    }

    @RequestMapping(value = "/admin/buyStatus", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView buyStatus(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {

        // 관리자가 아니라면 에러페이지로 리다이렉트 합니다.
        Object ID = webHelper.getSession("UserAdmin", "null");

        if (!ID.equals("Y") || ID.equals(null)) {
            return webHelper.redirect(contextPath + "/error", null);
        }

        // 관리자 권한 검사
        switch ((Integer) webHelper.getSession("userLevel")) {
            case 1:
            case 3:
                break;
            default:
                return webHelper.redirect(contextPath + "/adminError", null);
        }

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate);

        return new ModelAndView("admin/buy_status/buy_status");
    }

    @RequestMapping(value = "/admin/manageOrder", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView manageOrder() {


        // 관리자가 아니라면 에러페이지로 리다이렉트 합니다.
        Object ID = webHelper.getSession("UserAdmin", "null");

        if (!ID.equals("Y") || ID.equals(null)) {
            return webHelper.redirect(contextPath + "/error", null);
        }

        // 관리자 권한 검사
        switch ((Integer) webHelper.getSession("userLevel")) {
            case 1:
            case 2:
                break;
            default:
                return webHelper.redirect(contextPath + "/adminError", null);
        }

        return new ModelAndView("admin/manage_order/manage_order");
    }

    @RequestMapping(value = "/admin/manageSellTransaction", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView manageSellTransaction() {

        // 관리자가 아니라면 에러페이지로 리다이렉트 합니다.
        Object ID = webHelper.getSession("UserAdmin", "null");

        if (!ID.equals("Y") || ID.equals(null)) {
            return webHelper.redirect(contextPath + "/error", null);
        }

        // 관리자 권한 검사
        switch ((Integer) webHelper.getSession("userLevel")) {
            case 1:
            case 3:
                break;
            default:
                return webHelper.redirect(contextPath + "/adminError", null);
        }

        return new ModelAndView("admin/manage_transaction/manage_Selltransaction");
    }

    @RequestMapping(value = "/admin/manageBuyTransaction", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView manageBuyTransaction() {

        // 관리자가 아니라면 에러페이지로 리다이렉트 합니다.
        Object ID = webHelper.getSession("UserAdmin", "null");

        if (!ID.equals("Y") || ID.equals(null)) {
            return webHelper.redirect(contextPath + "/error", null);
        }

        // 관리자 권한 검사
        switch ((Integer) webHelper.getSession("userLevel")) {
            case 1:
            case 3:
                break;
            default:
                return webHelper.redirect(contextPath + "/adminError", null);
        }

        return new ModelAndView("admin/manage_transaction/manage_Buytransaction");
    }

    @RequestMapping(value = "/admin/manageUsers", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView manageUsers(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {

        // 관리자가 아니라면 에러페이지로 리다이렉트 합니다.
        Object ID = webHelper.getSession("UserAdmin", "null");

        if (!ID.equals("Y") || ID.equals(null)) {
            return webHelper.redirect(contextPath + "/error", null);
        }

        return new ModelAndView("admin/manage_users/user_manage");
    }

    @RequestMapping(value = "/admin/sellStatus", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView sellStatus(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {

        // 관리자가 아니라면 에러페이지로 리다이렉트 합니다.
        Object ID = webHelper.getSession("UserAdmin", "null");

        if (!ID.equals("Y") || ID.equals(null)) {
            return webHelper.redirect(contextPath + "/error", null);
        }

        // 관리자 권한 검사
        switch ((Integer) webHelper.getSession("userLevel")) {
            case 1:
            case 3:
                break;
            default:
                return webHelper.redirect(contextPath + "/adminError", null);
        }

        return new ModelAndView("admin/sell_status/sell_status");
    }

    @RequestMapping(value = "/admin/sendMailTest", method = {RequestMethod.GET, RequestMethod.POST})
    public String sendMailTest(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate);

        return "admin/send_mail/send_mail_test";
    }


    @RequestMapping(value = "/admin/popProduct", method = RequestMethod.GET)
    public ModelAndView popProduct(Model model) {
        return new ModelAndView("admin/control_popular/control_popular");
    }

    /**
     * 인기상품 설정
     **/
    @RequestMapping(value = "/admin/popProduct_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView popProduct_ok(Model model,
                                      @RequestParam(value = "popular_product", required = false) String popular_product) {


        popularBeans popularInput = new popularBeans();
        popularInput.setPopular_product(popular_product);


        try {

            adminService.setPopular(popularInput);

        } catch (Exception e) {

            return webHelper.redirect(null, e.getLocalizedMessage());
        }

        return webHelper.redirect(null, "인기상품 등록이 완료되었습니다.");
    }


    @RequestMapping(value = "/admin/popSearch", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView popSearchRank(Model model) {

        // 관리자가 아니라면 에러페이지로 리다이렉트 합니다.
        Object ID = webHelper.getSession("UserAdmin", "null");

        if (!ID.equals("Y") || ID.equals(null)) {
            return webHelper.redirect(contextPath + "/error", null);
        }

        // 관리자 권한 검사
        switch ((Integer) webHelper.getSession("userLevel")) {
            case 1:
            case 4:
                break;
            default:
                return webHelper.redirect(contextPath + "/adminError", null);
        }


        return new ModelAndView("admin/control_search/control_search");
    }

    /**
     * 검색어 순위 설정
     **/
    @RequestMapping(value = "/admin/popSearch_ok.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView setPopSearchRank(Model model,
                                         @RequestParam(value = "popularFirst", required = false) String popularFirst,
                                         @RequestParam(value = "popularSecond", required = false) String popularSecond,
                                         @RequestParam(value = "popularThird", required = false) String popularThird,
                                         @RequestParam(value = "popularFourth", required = false) String popularFourth,
                                         @RequestParam(value = "popularFifth", required = false) String popularFifth,
                                         @RequestParam(value = "popularSixth", required = false) String popularSixth,
                                         @RequestParam(value = "popularSeventh", required = false) String popularSeventh) {


        rankBeans rankInput = new rankBeans();
        rankInput.setPopularFirst(popularFirst);
        rankInput.setPopularSecond(popularSecond);
        rankInput.setPopularThird(popularThird);
        rankInput.setPopularFourth(popularFourth);
        rankInput.setPopularFifth(popularFifth);
        rankInput.setPopularSixth(popularSixth);
        rankInput.setPopularSeventh(popularSeventh);

        try {

            adminService.setRank(rankInput);

        } catch (Exception e) {

            return webHelper.redirect(null, e.getLocalizedMessage());
        }

        return webHelper.redirect(null, "검색어 순위 등록이 완료되었습니다.");
    }


    @RequestMapping(value = "/admin/productList", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView productList() {

        // 관리자가 아니라면 에러페이지로 리다이렉트 합니다.
        Object ID = webHelper.getSession("UserAdmin", "null");

        if (!ID.equals("Y") || ID.equals(null)) {
            return webHelper.redirect(contextPath + "/error", null);
        }

        return new ModelAndView("admin/productList/productList");
    }

    @RequestMapping(value = "/admin/adminList", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView adminList() {

        // 관리자가 아니라면 에러페이지로 리다이렉트 합니다.
        Object ID = webHelper.getSession("UserAdmin", "null");

        if (!ID.equals("Y") || ID.equals(null)) {
            return webHelper.redirect(contextPath + "/error", null);
        }

        // 관리자 권한 검사
        if ((Integer) webHelper.getSession("userLevel") != 1) {
            return webHelper.redirect(contextPath + "/adminError", null);
        }

        return new ModelAndView("admin/manage_admin/admin_list");
    }

    @RequestMapping(value = "/admin/control", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView adminControl() {

        // 관리자가 아니라면 에러페이지로 리다이렉트 합니다.
        Object ID = webHelper.getSession("UserAdmin", "null");

        if (!ID.equals("Y") || ID.equals(null)) {
            return webHelper.redirect(contextPath + "/error", null);
        }
        // 관리자 권한 검사
        if ((Integer) webHelper.getSession("userLevel") != 1) {
            return webHelper.redirect(contextPath + "/adminError", null);
        }
        return new ModelAndView("admin/manage_admin/manage_admin");
    }

    @RequestMapping(value = "/admin/superUser", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView superUser(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {

        // 관리자가 아니라면 에러페이지로 리다이렉트 합니다.
        Object ID = webHelper.getSession("UserAdmin", "null");

        if (!ID.equals("Y") || ID.equals(null)) {
            return webHelper.redirect(contextPath + "/error", null);
        }

        if ((Integer) webHelper.getSession("userLevel") != 1) {
            return webHelper.redirect(contextPath + "/adminError", null);
        }

        return new ModelAndView("admin/manage_all/manage_all");
    }

}

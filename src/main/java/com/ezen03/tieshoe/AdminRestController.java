package com.ezen03.tieshoe;

import com.ezen03.tieshoe.helper.*;
import com.ezen03.tieshoe.model.*;
import com.ezen03.tieshoe.service.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
public class AdminRestController {

    @Autowired
    WebHelper webHelper;

    @Autowired
    RegexHelper regexHelper;

    @Autowired
    MailHelper mailHelper;

    @Autowired
    RetrofitHelper retrofitHelper;

    @Autowired
    ProductService productService;

    @Autowired
    SellService sellService;

    @Autowired
    BuyService buyService;

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Autowired
    MypageService mypageService;

    @Autowired
    AdminService adminService;

    @Autowired
    indexService indexService;


    @Value("#{servletContext.contextPath}")
    String contextPath;

    //회원관리
    @RequestMapping(value = "/admin/adminAll.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> admin(Model model,
                                     @RequestParam(required = false) String key,
                                     @RequestParam(required = true) int option,
                                     // nowPage는 파라미터에서 받아오며 페이지 번호입니다. 값이 없다면 1페이지로 설정합니다.
                                     @RequestParam(required = false) int nowPage) {

        //전체 게시글 수
        int totalCount = 0;
        // 한 페이지당 표시할 목록의 수
        int listCount = 15;
        // 한 그룹당 표시할 페이지 번호 수
        int pageCount = 5;

        //order 자바빈즈 객체 생성
        userBeans input = new userBeans();

        // 검색 또는 모든 주문건을 가져옵니다.
        List<userBeans> all = null;

        //페이지 번호를 계산한 결과가 저장될 객체
        PageData pageData = null;

    /*
    0 = 아이디
    1 = 이름
    2 = 상태
    3 = 전화번호
    */

        //검색어를 빈즈에 삽입합니다.
        input.setSearch(key);
        // 드롭다운 결과를 빈즈에 삽입합니다.
        input.setCategory(option);

        try {
            //총 몇개의 원소가 있는지 확인해 return 합니다. (int)
            totalCount = adminService.adminCount(input);

            // 페이지 번호 계산 --> 계산결과를 로그로 출력됩니다.
            pageData = new PageData(nowPage, totalCount, listCount, pageCount);

            // SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장
            userBeans.setOffset(pageData.getOffset());
            userBeans.setListCount(pageData.getListCount());

            //데이터를 조회합니다.
            all = adminService.adminStatus(input);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }


        Map<String, Object> data = new HashMap<String, Object>();
        data.put("info", all);
        data.put("page", pageData);
        return webHelper.getJsonData(data);
    }


    //sell 리스트
    @RequestMapping(value = "/admin/SellPayOrder", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> adminSellPayList(Model model,
                                                @RequestParam(required = false) String key,
                                                @RequestParam(required = true) int option,
                                                // nowPage는 파라미터에서 받아오며 페이지 번호입니다. 값이 없다면 1페이지로 설정합니다.
                                                @RequestParam(required = false) int nowPage) {

        //전체 게시글 수
        int totalCount = 0;
        // 한 페이지당 표시할 목록의 수
        int listCount = 15;
        // 한 그룹당 표시할 페이지 번호 수
        int pageCount = 5;

        //order 자바빈즈 객체 생성
        orderBeans input = new orderBeans();

        // 검색 또는 모든 주문건을 가져옵니다.
        List<orderBeans> all = null;

        //페이지 번호를 계산한 결과가 저장될 객체
        PageData pageData = null;

    /*
    0 = 전체
    1 = 아이디
    2 = 이름
    */

        //검색어를 빈즈에 삽입합니다.
        input.setSearch(key);
        // 드롭다운 결과를 빈즈에 삽입합니다.
        input.setCategory(option);

        try {
            //총 몇개의 원소가 있는지 확인해 return 합니다. (int)
            totalCount = adminService.sellPayListCount(input);

            // 페이지 번호 계산 --> 계산결과를 로그로 출력됩니다.
            pageData = new PageData(nowPage, totalCount, listCount, pageCount);

            // SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장

            orderBeans.setOffset(pageData.getOffset());
            orderBeans.setListCount(pageData.getListCount());

            //데이터를 조회합니다.
            all = adminService.sellPayList(input);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("info", all);
        data.put("page", pageData);
        return webHelper.getJsonData(data);
    }

    @RequestMapping(value = "/admin/orderSellUpdate.do", method = {RequestMethod.GET, RequestMethod.POST})
    public int updateOrderPaySell(
            @RequestParam(required = true) int ID) {

        //order 자바빈즈 객체 생성
        orderBeans input = new orderBeans();

        int update = 0;

        //orderID를 빈즈에 삽입합니다.
        input.setID(ID);

        //데이터를 조회합니다.
        try {
            update = adminService.updateOrderSellBuy(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return update;
    }

    @RequestMapping(value = "/admin/orderBuyPayUpdate.do", method = {RequestMethod.GET, RequestMethod.POST})
    public int updateOrderPayBuy(
            @RequestParam(required = true) int ID) {

        //order 자바빈즈 객체 생성
        orderBeans input = new orderBeans();

        int update = 0;

        //orderID를 빈즈에 삽입합니다.
        input.setID(ID);

        //데이터를 조회합니다.
        try {
            update = adminService.updateOrderPayBuy(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return update;
    }

    @RequestMapping(value = "/admin/orderUpdateBoth.do", method = {RequestMethod.GET, RequestMethod.POST})
    public int updateOrderBoth(
            @RequestParam(required = true) int ID) {

        //order 자바빈즈 객체 생성
        orderBeans input = new orderBeans();

        int update = 0;

        //orderID를 빈즈에 삽입합니다.
        input.setID(ID);

        //데이터를 조회합니다.
        try {
            update = adminService.updateOrderStatusBoth(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return update;
    }

    //sell 리스트
    @RequestMapping(value = "/admin/buyPayOrder.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> adminBuyPayList(Model model,
                                               @RequestParam(required = false) String key,
                                               @RequestParam(required = true) int option,
                                               // nowPage는 파라미터에서 받아오며 페이지 번호입니다. 값이 없다면 1페이지로 설정합니다.
                                               @RequestParam(required = false) int nowPage) {

        //전체 게시글 수
        int totalCount = 0;
        // 한 페이지당 표시할 목록의 수
        int listCount = 15;
        // 한 그룹당 표시할 페이지 번호 수
        int pageCount = 5;

        //order 자바빈즈 객체 생성
        orderBeans input = new orderBeans();

        // 검색 또는 모든 주문건을 가져옵니다.
        List<orderBeans> all = null;

        //페이지 번호를 계산한 결과가 저장될 객체
        PageData pageData = null;

    /*
    0 = 전체
    1 = 아이디
    2 = 이름
    */

        //검색어를 빈즈에 삽입합니다.
        input.setSearch(key);
        // 드롭다운 결과를 빈즈에 삽입합니다.
        input.setCategory(option);

        try {
            //총 몇개의 원소가 있는지 확인해 return 합니다. (int)
            totalCount = adminService.buyPayListCount(input);

            // 페이지 번호 계산 --> 계산결과를 로그로 출력됩니다.
            pageData = new PageData(nowPage, totalCount, listCount, pageCount);

            // SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장

            orderBeans.setOffset(pageData.getOffset());
            orderBeans.setListCount(pageData.getListCount());

            //데이터를 조회합니다.
            all = adminService.buyPayList(input);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("info", all);
        data.put("page", pageData);
        return webHelper.getJsonData(data);
    }

    @RequestMapping(value = "/admin/updateOrderStatusSell.do", method = {RequestMethod.GET, RequestMethod.POST})
    public int updateOrderStatusSell(
            @RequestParam(required = true) int ID,
            @RequestParam(required = true) int status) {

        //order 자바빈즈 객체 생성
        orderBeans input = new orderBeans();

        int update = 0;

        //orderID를 빈즈에 삽입합니다.
        input.setID(ID);
        input.setSellOrderStatus(status);

        //데이터를 조회합니다.
        try {
            update = adminService.updateOrderStatusSell(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return update;
    }

    //sell 리스트
    @RequestMapping(value = "/admin/sellList.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> adminSellList(Model model,
                                             @RequestParam(required = false) String key,
                                             @RequestParam(required = true) int option,
                                             // nowPage는 파라미터에서 받아오며 페이지 번호입니다. 값이 없다면 1페이지로 설정합니다.
                                             @RequestParam(required = false) int nowPage) {

        //전체 게시글 수
        int totalCount = 0;
        // 한 페이지당 표시할 목록의 수
        int listCount = 15;
        // 한 그룹당 표시할 페이지 번호 수
        int pageCount = 5;

        //order 자바빈즈 객체 생성
        sellBeans input = new sellBeans();

        // 검색 또는 모든 주문건을 가져옵니다.
        List<sellBeans> all = null;

        //페이지 번호를 계산한 결과가 저장될 객체
        PageData pageData = null;

    /*
    0 = 전체
    1 = 아이디
    2 = 이름
    */

        //검색어를 빈즈에 삽입합니다.
        input.setSearch(key);
        // 드롭다운 결과를 빈즈에 삽입합니다.
        input.setCategory(option);

        try {
            //총 몇개의 원소가 있는지 확인해 return 합니다. (int)
            totalCount = adminService.sellListCount(input);

            // 페이지 번호 계산 --> 계산결과를 로그로 출력됩니다.
            pageData = new PageData(nowPage, totalCount, listCount, pageCount);

            // SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장

            sellBeans.setOffset(pageData.getOffset());
            sellBeans.setListCount(pageData.getListCount());

            //데이터를 조회합니다.
            all = adminService.getSellList(input);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("info", all);
        data.put("page", pageData);
        return webHelper.getJsonData(data);
    }

    @RequestMapping(value = "/admin/updateOrderStatus.do", method = {RequestMethod.GET, RequestMethod.POST})
    public int updateOrderStatus(
            @RequestParam(required = true) int ID,
            @RequestParam(required = true) int status) {

        //order 자바빈즈 객체 생성
        orderBeans input = new orderBeans();

        int update = 0;

        //orderID를 빈즈에 삽입합니다.
        input.setID(ID);
        input.setBuyOrderStatus(status);

        //데이터를 조회합니다.
        try {
            update = adminService.updateOrderStatus(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return update;
    }

    //Buy 리스트
    @RequestMapping(value = "/admin/buyList.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> adminBuyList(Model model,
                                            @RequestParam(required = false) String key,
                                            @RequestParam(required = true) int option,
                                            // nowPage는 파라미터에서 받아오며 페이지 번호입니다. 값이 없다면 1페이지로 설정합니다.
                                            @RequestParam(required = false) int nowPage) {

        //전체 게시글 수
        int totalCount = 0;
        // 한 페이지당 표시할 목록의 수
        int listCount = 15;
        // 한 그룹당 표시할 페이지 번호 수
        int pageCount = 5;

        //order 자바빈즈 객체 생성
        buyBeans input = new buyBeans();

        // 검색 또는 모든 주문건을 가져옵니다.
        List<buyBeans> all = null;

        //페이지 번호를 계산한 결과가 저장될 객체
        PageData pageData = null;

    /*
    0 = 전체
    1 = 아이디
    2 = 이름
    */

        //검색어를 빈즈에 삽입합니다.
        input.setSearch(key);
        // 드롭다운 결과를 빈즈에 삽입합니다.
        input.setCategory(option);

        try {
            //총 몇개의 원소가 있는지 확인해 return 합니다. (int)
            totalCount = adminService.buyListCount(input);

            // 페이지 번호 계산 --> 계산결과를 로그로 출력됩니다.
            pageData = new PageData(nowPage, totalCount, listCount, pageCount);

            // SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장

            buyBeans.setOffset(pageData.getOffset());
            buyBeans.setListCount(pageData.getListCount());

            //데이터를 조회합니다.
            all = adminService.getBuyList(input);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }


        Map<String, Object> data = new HashMap<String, Object>();
        data.put("info", all);
        data.put("page", pageData);
        return webHelper.getJsonData(data);
    }

    //회원관리
    @RequestMapping(value = "/admin/productList.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> adminProductList(Model model,
                                                @RequestParam(required = false) String key,
                                                @RequestParam(required = true) int option,
                                                // nowPage는 파라미터에서 받아오며 페이지 번호입니다. 값이 없다면 1페이지로 설정합니다.
                                                @RequestParam(required = false) int nowPage) {

        //전체 게시글 수
        int totalCount = 0;
        // 한 페이지당 표시할 목록의 수
        int listCount = 15;
        // 한 그룹당 표시할 페이지 번호 수
        int pageCount = 5;

        //order 자바빈즈 객체 생성
        productBeans input = new productBeans();

        // 검색 또는 모든 주문건을 가져옵니다.
        List<productBeans> all = null;

        //페이지 번호를 계산한 결과가 저장될 객체
        PageData pageData = null;

    /*
    0 = 전체
    1 = 상품코드
    2 = 상품명
    3 = 브랜드
    */

        //검색어를 빈즈에 삽입합니다.
        input.setSearch(key);
        // 드롭다운 결과를 빈즈에 삽입합니다.
        input.setCategory(option);

        try {
            //총 몇개의 원소가 있는지 확인해 return 합니다. (int)
            totalCount = adminService.productListCount(input);

            // 페이지 번호 계산 --> 계산결과를 로그로 출력됩니다.
            pageData = new PageData(nowPage, totalCount, listCount, pageCount);

            // SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장

            productBeans.setOffset(pageData.getOffset());
            productBeans.setListCount(pageData.getListCount());

            //데이터를 조회합니다.
            all = adminService.getProductList(input);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }


        Map<String, Object> data = new HashMap<String, Object>();
        data.put("info", all);
        data.put("page", pageData);
        return webHelper.getJsonData(data);
    }

    //회원관리
    @RequestMapping(value = "/admin/user.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> adminUser(Model model,
                                         @RequestParam(required = false) String key,
                                         @RequestParam(required = true) int option,
                                         // nowPage는 파라미터에서 받아오며 페이지 번호입니다. 값이 없다면 1페이지로 설정합니다.
                                         @RequestParam(required = false) int nowPage) {

        //전체 게시글 수
        int totalCount = 0;
        // 한 페이지당 표시할 목록의 수
        int listCount = 15;
        // 한 그룹당 표시할 페이지 번호 수
        int pageCount = 5;

        //order 자바빈즈 객체 생성
        userBeans input = new userBeans();

        // 검색 또는 모든 주문건을 가져옵니다.
        List<userBeans> all = null;

        //페이지 번호를 계산한 결과가 저장될 객체
        PageData pageData = null;

    /*
    0 = 아이디
    1 = 이름
    2 = 상태
    3 = 전화번호
    */

        //검색어를 빈즈에 삽입합니다.
        input.setSearch(key);
        // 드롭다운 결과를 빈즈에 삽입합니다.
        input.setCategory(option);

        try {
            //총 몇개의 원소가 있는지 확인해 return 합니다. (int)
            totalCount = adminService.userCount(input);

            // 페이지 번호 계산 --> 계산결과를 로그로 출력됩니다.
            pageData = new PageData(nowPage, totalCount, listCount, pageCount);

            // SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장
            userBeans.setOffset(pageData.getOffset());
            userBeans.setListCount(pageData.getListCount());

            //데이터를 조회합니다.
            all = adminService.userStatus(input);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }


        Map<String, Object> data = new HashMap<String, Object>();
        data.put("info", all);
        data.put("page", pageData);
        return webHelper.getJsonData(data);
    }

    //회원 탈퇴
    @RequestMapping(value = "/admin/dropUser.do", method = {RequestMethod.GET, RequestMethod.POST})
    public int dropUser(Model model,
                        @RequestParam(required = true) int ID) {

        //order 자바빈즈 객체 생성
        userBeans input = new userBeans();

        int update = 0;

        //orderID를 빈즈에 삽입합니다.
        input.setID(ID);

        //데이터를 조회합니다.
        try {
            update = userService.dropUser(input);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return update;
    }

    //상품 삭제/복구
    @RequestMapping(value = "/admin/dropProduct.do", method = {RequestMethod.GET, RequestMethod.POST})
    public int dropProduct(Model model,
                           @RequestParam(required = true) int ID,
                           @RequestParam(required = true) String drop) {

        //order 자바빈즈 객체 생성
        productBeans input = new productBeans();

        int update = 0;

        //orderID를 빈즈에 삽입합니다.
        input.setID(ID);
        input.setProductDrop(drop);

        //데이터를 조회합니다.
        try {
            update = productService.dropProduct(input);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return update;
    }

    @RequestMapping(value = "/admin/order.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> adminOrder(Model model,
                                          @RequestParam(required = false) String key,
                                          @RequestParam(required = true) int option,
                                          // nowPage는 파라미터에서 받아오며 페이지 번호입니다. 값이 없다면 1페이지로 설정합니다.
                                          @RequestParam(required = false) int nowPage) {

        //전체 게시글 수
        int totalCount = 0;
        // 한 페이지당 표시할 목록의 수
        int listCount = 15;
        // 한 그룹당 표시할 페이지 번호 수
        int pageCount = 5;

        //order 자바빈즈 객체 생성
        orderBeans input = new orderBeans();

        // 검색 또는 모든 주문건을 가져옵니다.
        List<orderBeans> all = null;

        //페이지 번호를 계산한 결과가 저장될 객체
        PageData pageData = null;

        /*
        0 = 전체
        1 = 주문번호
        2 = 판매자
        3 = 구매자
         */

        //검색어를 빈즈에 삽입합니다.
        input.setSearch(key);
        // 드롭다운 결과를 빈즈에 삽입합니다.
        input.setCategory(option);

        try {
            //총 몇개의 원소가 있는지 확인해 return 합니다. (int)
            totalCount = adminService.orderStatusCount(input);

            // 페이지 번호 계산 --> 계산결과를 로그로 출력됩니다.
            pageData = new PageData(nowPage, totalCount, listCount, pageCount);

            // SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장
            orderBeans.setOffset(pageData.getOffset());
            orderBeans.setListCount(pageData.getListCount());

            //데이터를 조회합니다.
            all = adminService.orderStatus(input);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("info", all);
        data.put("page", pageData);
        return webHelper.getJsonData(data);
    }

    @RequestMapping(value = "/admin/orderUpdate.do", method = {RequestMethod.GET, RequestMethod.POST})
    public int adminOrderUpdate(Model model,
                                @RequestParam(required = true) int ID) {

        //order 자바빈즈 객체 생성
        orderBeans input = new orderBeans();

        int update = 0;

        //orderID를 빈즈에 삽입합니다.
        input.setID(ID);

        //데이터를 조회합니다.
        try {
            update = adminService.orderUpdate(input);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return update;
    }

    @RequestMapping(value = "/admin/popProductList", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> getPopProductList(
            @RequestParam(value = "search", defaultValue = "1", required = false) String search) {


        /** 데이터 조회하기 */
        // 조회에 필요한 조건값(검색어)를 Beans에 담는다.
        productBeans input = new productBeans();
        input.setSearch(search);

        List<productBeans> output = null; // 조회결과가 저장될 객체

        try {
            // 데이터 조회하기
            output = adminService.getProductCode(input);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }

        /** 3) JSON 출력하기 */
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("search", search);
        data.put("item", output);

        return webHelper.getJsonData(data);
    }

    //회원 탈퇴
    @RequestMapping(value = "/admin/dropAdmin.do", method = {RequestMethod.GET, RequestMethod.POST})
    public int dropAdmin(Model model,
                         @RequestParam(required = true) int ID) {

        //order 자바빈즈 객체 생성
        userBeans input = new userBeans();

        int update = 0;

        //orderID를 빈즈에 삽입합니다.
        input.setID(ID);

        //데이터를 조회합니다.
        try {
            update = userService.dropAdmin(input);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return update;
    }

    // 상품 등록
    @RequestMapping(value = "/admin/adminAddProduct.do", method = {RequestMethod.GET, RequestMethod.POST})
    public int addProductInfo(Model model, HttpServletRequest request,
                              @RequestParam(required = false) String productNameKR,
                              @RequestParam(required = false) String productNameEN,
                              @RequestParam(required = false) String productCode,
                              @RequestParam(required = false) String productColor,
                              @RequestParam(required = false) String productReleaseDate,
                              @RequestParam(required = false) String productReleasePrice,
                              @RequestParam(required = false) String productBrand,
                              @RequestParam(required = false) String productUnit,
                              @RequestParam(required = false) MultipartFile image) {
        /* 이미지가 존재 하지 않을 경우 Alert 합니다.*/
        //이미지가 없을 경우에 즉, 이미지값이 들어오지 않았을떄에 return 합니다.
        if (image.getOriginalFilename().isEmpty()) {
            webHelper.redirect(null, "이미지를 선택되지 않았습니다.");
            return 0; //성공하지 못했을 경우 0을 리턴합니다.
        }

        String fileName = image.getOriginalFilename();              // 전체 경로에서 파일 이름만 분리
        int p = fileName.lastIndexOf(".");          // 파일이름에서 마지막 점(.)의 위치
        String ext = fileName.substring(p + 1);         // 확장자 분리 --> 파일이름에서 마지막 점위 위치 다음부터 끝까지

        String file = productNameEN.replace(" ", "_");
        String originalFile = file + "." + ext;
        File targetFile = new File(webHelper.getUploadDir(), originalFile);

        /** 이미지를 업로드합니다. */
        try {
            image.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String absPath = targetFile.getAbsolutePath().replace("\\", "/");
        String filePath = absPath.replace(webHelper.getUploadDir(), "");

        /** 인덱스 썸네일을 만듭니다. (364 * 364) */

        /*
        0. upload/index
        1. upload/thumb
         */
        String index = null;
        try {
            index = webHelper.createThumbnail(filePath, 364, 364, true, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /** 인덱스 썸네일을 만듭니다. (130 * 130) */

        /*
        0. upload/index
        1. upload/thumb
         */
        String thumb = null;
        try {
            thumb = webHelper.createThumbnail(filePath, 130, 130, true, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /** DB에 저장 */
        productBeans input = new productBeans();
        String productBrandKR = null;
        if (productBrand == "Nike") {
            productBrandKR = "나이키";
        } else if (productBrand == "Adidas") {
            productBrandKR = "아디다스";
        } else if (productBrand == "Jordan") {
            productBrandKR = "조던";
        } else if (productBrand == "Vans") {
            productBrandKR = "반스";
        } else if (productBrand == "Converse") {
            productBrandKR = "컨버스";
        } else {
            productBrandKR = "기타";
        }

        // 가격의 반점 없애기
        String price = productReleasePrice.replace(",", "");
        int productPrice = Integer.parseInt(price);

        String upload = "/upload";

        input.setProductCode(productCode);
        input.setProductNameEN(productNameEN);
        input.setProductNameKR(productNameKR);
        input.setProductColor(productColor);
        input.setProductReleaseDate(productReleaseDate);
        input.setProductBrandKR(productBrandKR);
        input.setProductBrandEN(productBrand);
        input.setProductUnit(productUnit);
        input.setProductImgPath(upload + filePath);
        input.setProduct_thumb(upload + thumb);
        input.setIndex_thumb(upload + index);

        int insert = 0;

        try {
            insert = adminService.addProduct(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insert;
    }

    //회원 탈퇴
    @RequestMapping(value = "/admin/completeOrderStatus.do", method = {RequestMethod.GET, RequestMethod.POST})
    public int complete(Model model,
                        @RequestParam(required = true) int ID) {

        orderBeans input = new orderBeans();

        input.setID(ID);
        int update = 0; //주문 상태를 전부 7로 교체합니다.
        //데이터를 조회합니다.
        try {
            update = orderService.complete(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return update;
    }

    @RequestMapping(value = "/dailyIncomeGraph.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> dailyIncome(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {

        List<orderBeans> output = null;

        try {
            output = orderService.dailyIncome();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("info", output);
        return webHelper.getJsonData(data);
    }

    @RequestMapping(value = "/interestGraph.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> interest(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {

        List<reviewBeans> output = null;

        try {
            output = adminService.interestGraph();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("info", output);
        return webHelper.getJsonData(data);
    }

    @RequestMapping(value = "/brandCountGraph.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> brandCount(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {

        List<recentCheck> first = null;
        List<recentCheck> second = null;
        List<recentCheck> third = null;


        try {
            first = adminService.recentFirst();
            second = adminService.recentSecond();
            third = adminService.recentThird();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("first", first);
        data.put("second", second);
        data.put("third", third);

        return webHelper.getJsonData(data);
    }
}
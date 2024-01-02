package com.ezen03.tieshoe;

import com.ezen03.tieshoe.helper.*;
import com.ezen03.tieshoe.model.*;
import com.ezen03.tieshoe.service.*;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class RestAPIController {

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
    checkService checkService;

    @Autowired
    indexService indexService;


    @Value("#{servletContext.contextPath}")
    String contextPath;

    @RequestMapping(value = "/rank.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> rank(Model model) {

        rankBeans input = new rankBeans();
        rankBeans output = null;
        try {
            output = indexService.rank(input);
        } catch (
                Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("rank", output);
        return webHelper.getJsonData(data);
    }

    @RequestMapping(value = "/sellPopupCurrent.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> sellPopupCurrent(Model model,
                                                @RequestParam(required = false) int productNum,
                                                @RequestParam(required = false) int page,
                                                @RequestParam(required = false) int size) {
        int totalCount = 0; // 전체 게시글 수
        int listCount = 8; // 한 페이지당 표시할 목록 수
        int pageCount = 5; // 한 그룹당 표시할 페이지 번호 수
        sellBeans input = new sellBeans();
        input.setProductNum(productNum);
        input.setSellSize(size);

        List<sellBeans> output = null;
        PageData pageData = null;

        try {
            // 전체 게시글 수 조회
            totalCount = sellService.getSellCount(input);
            // 페이지 번호 계산 --> 계산결과를 로그로 출력될 것입니다.
            pageData = new PageData(page, totalCount, listCount, pageCount);

            //SQL의 LIMIT절에서 사용될  값을 Beans의 static 변수에 저장
            sellBeans.setOffset(pageData.getOffset());
            sellBeans.setListCount(pageData.getListCount());

            //데이터 조회
            output = sellService.getSellAllPrice(input);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("sellAllPrice", output);
        data.put("page", pageData);
        return webHelper.getJsonData(data);
    }

    @RequestMapping(value = "/buyPopupCurrent.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> buyPopupCurrent(Model model,
                                               @RequestParam(required = false) int productNum,
                                               @RequestParam(defaultValue = "1") int page,
                                               @RequestParam(required = false) int size) {
        int totalCount = 0; // 전체 게시글 수
        int listCount = 8; // 한 페이지당 표시할 목록 수
        int pageCount = 5; // 한 그룹당 표시할 페이지 번호 수


        buyBeans input = new buyBeans();
        input.setProductNum(productNum);
        input.setBuySize(size);

        List<buyBeans> output = null;
        PageData pageData = null;

        try {
            // 전체 게시글 수 조회
            totalCount = buyService.getBuyCount(input);
            // 페이지 번호 계산 --> 계산결과를 로그로 출력될 것입니다.
            pageData = new PageData(page, totalCount, listCount, pageCount);

            //SQL의 LIMIT절에서 사용될  값을 Beans의 static 변수에 저장
            buyBeans.setOffset(pageData.getOffset());
            buyBeans.setListCount(pageData.getListCount());

            //데이터 조회
            output = buyService.getBuyAllPrice(input);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("buyAllPrice", output);
        data.put("page", pageData);
        return webHelper.getJsonData(data);
    }

    @RequestMapping(value = "/orderPopupCurrent.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> orderPopupCurrent(Model model,
                                                 @RequestParam(required = false) int productNum,
                                                 @RequestParam(required = false) int page,
                                                 @RequestParam(required = false) int size) {
        int totalCount = 0; // 전체 게시글 수
        int listCount = 8; // 한 페이지당 표시할 목록 수
        int pageCount = 5; // 한 그룹당 표시할 페이지 번호 수


        orderBeans input = new orderBeans();
        input.setOrderProductNum(productNum);
        input.setOrderSize(size);

        List<orderBeans> output = null;
        PageData pageData = null;

        try {
            // 전체 게시글 수 조회
            totalCount = orderService.orderCount(input);
            // 페이지 번호 계산 --> 계산결과를 로그로 출력될 것입니다.
            pageData = new PageData(page, totalCount, listCount, pageCount);

            //SQL의 LIMIT절에서 사용될  값을 Beans의 static 변수에 저장
            orderBeans.setOffset(pageData.getOffset());
            orderBeans.setListCount(pageData.getListCount());

            //데이터 조회
            output = orderService.getOrderInfo(input);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("orderAllPrice", output);
        data.put("page", pageData);
        return webHelper.getJsonData(data);
    }

    @RequestMapping(value = "/resend.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> resend(Model model, HttpSession session, HttpServletRequest request,
                                      @RequestParam(required = true) int ID,
                                      @RequestParam(required = true) String Email,
                                      @RequestParam(required = true) String user_ID) {

        int success = 0; // 기본값 세팅

        // 같은 interface를 사용하기 위해 Beans 사용
        userReset inputReset = new userReset();


        // Key 값 생성 하고 INSERT
        long timeStamp = System.currentTimeMillis();
        byte[] array = new byte[8]; // length is bounded by 8
        new Random().nextBytes(array);
        String randomCode = createRandomCode(20, "qwertyuiopasdfghjklzxcvbnm1234567890");
        String randomString = ID + "T" + randomCode;
        //Beans에 set
        inputReset.setUser_ID(ID);
        inputReset.setEmail_key(randomString);


        try {
            success = userService.insertKey(inputReset);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 로컬 호스트 (로컬 서버 실행을 위한 String 입니다.
        String localHost = "http://localhost:8080";

        //링크 경로 가져오기
        String contextPath = request.getContextPath();

        String subject = "TIE SHOE || 비밀번호 변경";
        //비밀번호 변경 주소 페이지
        String link = localHost + contextPath + "/changePW?auth=" + randomString;

        String emailContent = emailForm(link, user_ID);
        // 이메일을 주소를 포함해서 전송합니다.
        try {
            mailHelper.sendMail(Email, subject, emailContent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("success", "yes");
        return webHelper.getJsonData(data);
    }

    @RequestMapping(value = "/getBuyInfo.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> myPageBuy(Model model, HttpSession session, HttpServletRequest request,
                                         @RequestParam(required = true) int ID,
                                         @RequestParam(required = false) int Page,
                                         @RequestParam(required = false) String start,
                                         @RequestParam(required = false) String end) {
        int totalCount = 0;
        int listCount = 10;
        int pageCount = 10;
        userBeans userInput = new userBeans();
        buyBeans buyInput = new buyBeans();
        PageData pageData = null;

        //리스트를 담을 객체
        List<buyBeans> buyList = null;

        userInput.setID(ID);
        buyInput.setUser_ID(ID);
        buyInput.setRangeStart(start);
        buyInput.setRangeEnd(end);

        try {

            totalCount = buyService.getBuyListCount(buyInput);

            pageData = new PageData(Page, totalCount, listCount, pageCount);

            // SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장
            buyBeans.setOffset(pageData.getOffset());
            buyBeans.setListCount(pageData.getListCount());

            //Data 가져오기
            userInput = mypageService.getbuyInfo(userInput);
            buyList = mypageService.getBuyList(buyInput);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Map<String, Object> data = new HashMap<String, Object>();
        data.put("userInfo", userInput);
        data.put("buyList", buyList);
        data.put("page", pageData);

        return webHelper.getJsonData(data);
    }

    @RequestMapping(value = "/getBuyProcessInfo.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> myPageProcessBuy(Model model, HttpSession session, HttpServletRequest request,
                                                @RequestParam(required = true) int ID,
                                                @RequestParam(required = false) int Page,
                                                @RequestParam(required = false) String start,
                                                @RequestParam(required = false) String end) {
        int totalCount = 0;
        int listCount = 10;
        int pageCount = 10;
        buyBeans buyInput = new buyBeans();
        PageData pageData = null;

        //리스트를 담을 객체
        List<buyBeans> buyList = null;

        buyInput.setUser_ID(ID);
        buyInput.setRangeStart(start);
        buyInput.setRangeEnd(end);

        try {

            totalCount = buyService.getBuyProcessListCount(buyInput);

            pageData = new PageData(Page, totalCount, listCount, pageCount);

            // SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장
            buyBeans.setOffset(pageData.getOffset());
            buyBeans.setListCount(pageData.getListCount());

            //Data 가져오기
            buyList = mypageService.getBuyProcessList(buyInput);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Map<String, Object> data = new HashMap<String, Object>();
        data.put("buyList", buyList);
        data.put("page", pageData);

        return webHelper.getJsonData(data);
    }

    @RequestMapping(value = "/getSellProcessInfo.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> myPageProcessSell(Model model, HttpSession session, HttpServletRequest request,
                                                 @RequestParam(required = true) int ID,
                                                 @RequestParam(required = false) int Page,
                                                 @RequestParam(required = false) String start,
                                                 @RequestParam(required = false) String end) {
        int totalCount = 0;
        int listCount = 10;
        int pageCount = 10;
        sellBeans sellInput = new sellBeans();
        PageData pageData = null;

        //리스트를 담을 객체
        List<sellBeans> sellList = null;

        sellInput.setUser_ID(ID);
        sellInput.setRangeStart(start);
        sellInput.setRangeEnd(end);


        try {

            totalCount = sellService.getSellProcessListCount(sellInput);

            pageData = new PageData(Page, totalCount, listCount, pageCount);

            // SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장
            sellBeans.setOffset(pageData.getOffset());
            sellBeans.setListCount(pageData.getListCount());

            //Data 가져오기
            sellList = mypageService.getSellProcessList(sellInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("sellList", sellList);
        data.put("page", pageData);
        return webHelper.getJsonData(data);
    }


    @RequestMapping(value = "/getSellInfo.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> myPageSell(Model model, HttpSession session, HttpServletRequest request,
                                          @RequestParam(required = true) int ID,
                                          @RequestParam(required = false) int Page,
                                          @RequestParam(required = false) String start,
                                          @RequestParam(required = false) String end) {
        int totalCount = 0;
        int listCount = 10;
        int pageCount = 10;
        userBeans userInput = new userBeans();
        sellBeans sellInput = new sellBeans();
        PageData pageData = null;

        //리스트를 담을 객체
        List<sellBeans> sellList = null;

        userInput.setID(ID);
        sellInput.setUser_ID(ID);
        sellInput.setRangeStart(start);
        sellInput.setRangeEnd(end);

        try {

            totalCount = sellService.getSellListCount(sellInput);

            pageData = new PageData(Page, totalCount, listCount, pageCount);

            // SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장
            sellBeans.setOffset(pageData.getOffset());
            sellBeans.setListCount(pageData.getListCount());

            //Data 가져오기
            userInput = mypageService.getSellInfo(userInput);
            sellList = mypageService.getSellList(sellInput);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Map<String, Object> data = new HashMap<String, Object>();
        data.put("userInfo", userInput);
        data.put("sellList", sellList);
        data.put("page", pageData);

        return webHelper.getJsonData(data);
    }

    @RequestMapping(value = "/search.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> search(Model model, HttpSession session, HttpServletRequest request,
                                      @RequestParam(value = "search", required = false) String search,
                                      @RequestParam int page) {

        int totalcount = 0;                                     // 전체 게시글 수
        int listCount = 16;                                    // 한 페이지당 표시할 목록 수
        int pageCount = 6;                                     // 한 그룹당 표시할 페이지 번호 수
        productBeans input = new productBeans();

        input.setSearch(search);

        List<productBeans> product = null; //제품의 정보를 가져오기위한 객체
        PageData pageData = null;       // 페이지 번호를 계산한 결과가 저장될 객체


        try {
            totalcount = productService.searchAllCount(input);
            // 페이지 번호 계산 --> 계산결과를 로그로 출력될 것이다.
            pageData = new PageData(page, totalcount, listCount, pageCount);

            // SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장
            productBeans.setOffset(pageData.getOffset());
            productBeans.setListCount(pageData.getListCount());

            product = productService.searchAll(input);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("page", pageData);
        data.put("product", product);
        data.put("count", totalcount);

        return webHelper.getJsonData(data);
    }

    @RequestMapping(value = "/detailSearch_ok", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> detailSearch_ok(Model model, HttpSession session, HttpServletRequest request,
                                               @RequestParam(value = "brand", required = false) String brand,
                                               @RequestParam(value = "year", required = false) String year,
                                               @RequestParam(value = "size", required = false) String size,
                                               @RequestParam(value = "price", required = false) String price,
                                               @RequestParam(value = "minPrice", required = false) String minPrice,
                                               @RequestParam(value = "maxPrice", required = false) String maxPrice,
                                               @RequestParam(value = "page", defaultValue = "1") int nowPage) {

        int totalCount = 0;                                     // 전체 게시글 수
        int listCount = 10;                                    // 한 페이지당 표시할 목록 수
        int pageCount = 5;// 한 그룹당 표시할 페이지 번호 수
        int count = 0;


        List<String> brandList = new ArrayList<String>();
        List<String> yearList = new ArrayList<String>();
        List<String> sizeList = new ArrayList<String>();
        List<String> priceList = new ArrayList<String>();
        Map<String, Object> map = new HashMap<String, Object>();
        List<productBeans> product = null; //제품의 정보를 가져오기위한 객체
        PageData pageData = null;       // 페이지 번호를 계산한 결과가 저장될 객체

        if (brand == "" || brand == null) {
            brand = "nike,adidas,jordan,converse,vans";
        }

        if (year == "" || year == null) {
            year = "2015,2016,2017,2018,2019,2020";
        }

        if (size == "" || size == null) {
            size = "0";
        }

        if (price == "" || price == null) {
            price = "0";
        }

        try {
            String[] brandArray = brand.split(",");
            String[] yearArray = year.split(",");
            String[] sizeArray = size.split(",");
            String[] priceArray = price.split(",");
            for (int i = 0; i < brandArray.length; i++) {
                brandList.add(brandArray[i]);
            }
            for (int j = 0; j < yearArray.length; j++) {
                yearList.add(yearArray[j]);
            }
            for (int k = 0; k < sizeArray.length; k++) {
                sizeList.add(sizeArray[k]);
            }

            for (int l = 0; l < priceArray.length; l++) {
                priceList.add(priceArray[l]);
            }

            map.put("brand", brandList);
            map.put("year", yearList);
            map.put("size", sizeList);
            map.put("price", priceList);
            map.put("minPrice", minPrice);
            map.put("maxPrice", maxPrice);

            count = productService.detailSearchCount(map);
            product = productService.detailSearch(map);

            pageData = new PageData(nowPage, totalCount, listCount, pageCount);
            productBeans.setOffset(pageData.getOffset());
            productBeans.setListCount(pageData.getListCount());

        } catch (Exception e) {

            return webHelper.getJsonError(e.getLocalizedMessage());
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("count", count);
        data.put("product", product);
        data.put("pageData", pageData);

        return webHelper.getJsonData(data);
    }


    @RequestMapping(value = "/buyButton.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> myPageBuy(Model model, HttpSession session, HttpServletRequest request,
                                         @RequestParam(required = true) int ID) {

        complexBeans input = new complexBeans();

        input.setProductNum(ID);

        List<complexBeans> sell = null; //제품의 정보를 가져오기위한 객체
        List<complexBeans> order = null;
        List<complexBeans> buy = null;

        //List 생성
        List<complexBeans> complexList = new ArrayList<complexBeans>();

        try {
            //Data 가져오기
            sell = sellService.buyButton(input);
            buy = buyService.buyButton(input);
            order = orderService.buyButton(input);

            for (int z = 220; z <= 300; z += 5) {
                complexBeans complexSingle = new complexBeans();
                complexSingle.setProductNum(ID);
                complexSingle.setSize(z);

                // 최고 입찰가
                for (int i = 0; i < sell.size(); i++) {
                    complexBeans sellItem = sell.get(i);
                    //사이즈가 없다면 0으로 세팅 (최고 입찰가)
                    if (sellItem.getSize() == z) {
                        complexSingle.setBuyLowPrice(sellItem.getBuyLowPrice());
                        break;
                    } else {
                        complexSingle.setBuyLowPrice(0);
                    }
                }

                //최저 판매가
                for (int k = 0; k < buy.size(); k++) {
                    complexBeans buyItem = buy.get(k);
                    //사이즈가 없다면 0으로 세팅 (최저 판매가)
                    if (buyItem.getSize() == z) {
                        complexSingle.setBuyHighPrice(buyItem.getBuyHighPrice());
                        break;
                    } else {
                        complexSingle.setBuyHighPrice(0);
                    }
                }

                //최근 거래가
                for (int j = 0; j < order.size(); j++) {
                    complexBeans orderItem = order.get(j);
                    //사이즈가 없다면 0으로 세팅 (최근 거래가)
                    if (orderItem.getSize() == z) {
                        complexSingle.setBuyRecentPrice(orderItem.getBuyRecentPrice());
                        break;
                    } else {
                        complexSingle.setBuyRecentPrice(0);
                    }
                }

                complexList.add(complexSingle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //리스트 합치기
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("complex", complexList);
        return webHelper.getJsonData(data);
    }
    /*
     * 여기서부터는 일반 함수입니다. (NOT A MAPPER)---------------------------------------------------------
     * */

    //랜덤키 만들기
    public String createRandomCode(int codeLength, String id) {
        List<Character> temp = id.chars()
                .mapToObj(i -> (char) i)
                .collect(Collectors.toList());
        Collections.shuffle(temp, new SecureRandom());
        return temp.stream()
                .map(Object::toString)
                .limit(codeLength)
                .collect(Collectors.joining());
    }

    public String emailForm(String link, String ID) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/uikit@3.2.6/dist/css/uikit.min.css\"/>\n" +
                "<style>\n" +
                "    body {\n" +
                "        width: 70%;\n" +
                "        margin: auto;\n" +
                "        height: 1000px;\n" +
                "    }\n" +
                "\n" +
                "    .title {\n" +
                "        font-size: 50px;\n" +
                "        color: black;\n" +
                "        text-align: center;\n" +
                "    }\n" +
                "\n" +
                "    .sub_title {\n" +
                "        text-align: center;\n" +
                "        color: black;\n" +
                "        font-size: 20px;\n" +
                "        margin-bottom: 30px;\n" +
                "    }\n" +
                "\n" +
                "    .context {\n" +
                "        margin-bottom: 70px;\n" +
                "    }\n" +
                "\n" +
                "    .space {\n" +
                "        padding: 40px;\n" +
                "    }\n" +
                "\n" +
                "    p {\n" +
                "        word-break: keep-all;\n" +
                "    }\n" +
                "\n" +
                "    .center {\n" +
                "        text-align: center;\n" +
                "    }\n" +
                "\n" +
                "</style>\n" +
                "\n" +
                "<body>\n" +
                "<div class=\"header\">\n" +
                "    <div class=\"title\">TIE SHOE</div>\n" +
                "    <div class=\"sub_title\">Connect C2C</div>\n" +
                "</div>\n" +
                "<div class=\"container\">\n" +
                "    <div class=\"uk-child-width-1-1@s uk-grid-match\" uk-grid>\n" +
                "        <div class=\"uk-card uk-card-secondary uk-card-hover uk-card-body uk-light\">\n" +
                "            <h3 class=\"uk-card-title\">" + ID + "님, 비밀번호를 분실하셨나요?</h3>\n" +
                "            <p class=\"context\">아래있는 버튼을 클릭해서 비밀번호를 변경해주세요.</p>\n" +
                "            <a href=\"" + link + " \"><button class=\"uk-width-1-3 uk-button uk-button-default\">비밀번호 변경</button></a>\n" +
                "            <div class=\"space\"></div>\n" +
                "            <p>" + ID + "님이 아니신가요? <a href=\"\">여기를 클릭해주세요</a></p>\n" +
                "            <div>비밀번호를 신청하신적이 없으시다면 비밀번호를 변경후 문의주세요.</div>\n" +
                "\n" +
                "        </div>\n" +
                "    </div>\n" +
                "\n" +
                "\n" +
                "    <div class=\"uk-child-width-1-1@s uk-grid-match\" uk-grid>\n" +
                "        <div class=\"uk-card uk-card-secondary uk-card-hover uk-card-body uk-light center\">\n" +
                "            <p>Tieshoe 타이슈 서울특별시 서초구,1303-37 서초 W타워 13층  |  고객센터 02-123-5678  |  tieshoec2c@gmail.com</p>\n" +
                "            <div>Copyright 2020©. tieshoe, All rights reserved.</div>\n" +
                "\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "<!-- UIkit JS -->\n" +
                "<script src=\"https://cdn.jsdelivr.net/npm/uikit@3.3.1/dist/js/uikit.min.js\"></script>\n" +
                "<script src=\"https://cdn.jsdelivr.net/npm/uikit@3.3.1/dist/js/uikit-icons.min.js\"></script>\n" +
                "</body>\n" +
                "</html>";
    }


    /**
     * 회원정보의 불러오기 .
     */
    @RequestMapping(value = "/callUserInfo.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> callUserAddr(
            @RequestParam(value = "ID", defaultValue = "0", required = true) int ID
    ) {

        // 이 값이 존재하지 않는다면 데이터 조회가 불가능하므로 반드시 필수값으로 처리해야 한다.
        if (ID == 0) {
            return webHelper.getJsonWarning("회원번호가 없습니다.");
        }

        /** 2) 데이터 조회하기 */
        // 데이터 조회에 필요한 조건값을 Beans에 저장하기
        userBeans input = new userBeans();
        input.setID(ID);

        // 조회결과를 저장할 객체 선언
        userBeans output = null;

        try {
            output = userService.getUserItem(input);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }

        /** 3) View 처리 결과값을 "output에 담아 view단에 전달" */
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("output", output);

        return webHelper.getJsonData(data);

    }


    /**
     * 회원정보의 수정하는 action 페이지 입니다.
     */
    @RequestMapping(value = "/buy/editUserAddr_ok", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> editUserAddr_ok(Model model,
                                               @RequestParam(value = "ID", required = false) int ID,
                                               @RequestParam(value = "userZipcode", required = false) String userZipcode,
                                               @RequestParam(value = "userAddress1", required = false) String userAddress1,
                                               @RequestParam(value = "userAddress2", required = false) String userAddress2
    ) {

        if (userZipcode == "") {
            return webHelper.getJsonWarning("집주소(우편번호)를 입력하세요.");
        }

        if (userAddress1 == "") {
            return webHelper.getJsonWarning("집주소(도로명주소)를 입력하세요.");
        }

        if (userAddress2 == "") {
            return webHelper.getJsonWarning("집주소(상세주소)를 입력하세요.");
        }

        userBeans input = new userBeans();
        input.setID(ID);
        input.setUserZipcode(userZipcode);
        input.setUserAddress1(userAddress1);
        input.setUserAddress2(userAddress2);

        userBeans output = null;

        try {
            userService.editUserAddr(input);
            output = userService.getUserItem(input);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("output", output);

        return webHelper.getJsonData(data);

    }

    // 상품을 구매할때 생기는 세션
    @RequestMapping(value = "/setBuySession.do", method = {RequestMethod.GET, RequestMethod.POST})
    public int setBuySession(Model model, HttpServletRequest request) {
        webHelper.setCookie("buyAuthorization", "Y", 20);
        return 1;
    }

    // 입찰건 삭제
    @RequestMapping(value = "/deleteBuy.do", method = {RequestMethod.GET, RequestMethod.POST})
    public int deleteBuy(Model model, @RequestParam(required = false) int ID) {

        buyBeans input = new buyBeans();
        input.setID(ID);
        int result = 0;

        try {
            result = buyService.deleteBuy(input);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    // 판매건 삭제
    @RequestMapping(value = "/deleteSell.do", method = {RequestMethod.GET, RequestMethod.POST})
    public int deleteSell(Model model, @RequestParam(required = false) int ID) {

        sellBeans input = new sellBeans();
        input.setID(ID);
        int result = 0;

        try {
            result = sellService.deleteBuy(input);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    // 최근 판매건을 가져오기 위한 REST API
    @RequestMapping(value = "/productGraph.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> graph(Model model,
                                     @RequestParam int productNum) {

        orderBeans input = new orderBeans();
        input.setOrderProductNum(productNum);

        List<orderBeans> output = null;

        try {
            // 그래프를 그리기 위한 데이터를 가져옵니다
            output = orderService.getGraph(input);
        } catch (Exception e) {
            return webHelper.getJsonError(e.getLocalizedMessage());
        }

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("chartData", output);
        return webHelper.getJsonData(data);
    }


    // 최근 본 상품
    @RequestMapping(value = "/recentProduct.do", method = {RequestMethod.GET, RequestMethod.POST})
    public Map<String, Object> recentProduct(Model model) {

        int ID = 0;
        if (webHelper.getSession("loginID") != null) {
            ID = (Integer) webHelper.getSession("loginID");
        } else {
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("first", "1");
            return webHelper.getJsonData(data);
        }

        recentCheck checkInput = new recentCheck();
        productBeans productInput = new productBeans();
        checkInput.setUser_ID(ID);

        productBeans first = null;
        productBeans second = null;
        productBeans third = null;
        recentCheck check = null;

        try {
            check = checkService.getCheck(checkInput);

            productInput.setID(check.getFirst());
            first = productService.getRecentProduct(productInput);
            productInput.setID(check.getSecond());
            second = productService.getRecentProduct(productInput);
            productInput.setID(check.getThird());
            third = productService.getRecentProduct(productInput);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("first", first);
        data.put("second", second);
        data.put("third", third);
        return webHelper.getJsonData(data);
    }

    // 입찰자 철회
    @RequestMapping(value = "/deleteBuyerOrder.do", method = {RequestMethod.GET, RequestMethod.POST})
    public int deleteBuyerOrder(Model model,
                                @RequestParam int orderNum) {

        int ID = (Integer) webHelper.getSession("loginID");

        orderBeans orderInput = new orderBeans();
        buyBeans buyInput = new buyBeans();
        sellBeans sellInput = new sellBeans();
        userBeans userInput = new userBeans();
        userBeans userInput1 = new userBeans();
        productBeans productInput = new productBeans();
        orderBeans orderOutput = null;
        userBeans userOutput = null;
        userBeans userOutput1 = null;
        productBeans productOutput = null;
        int orderStatusUpdate = 0; // 주문 상채를 철회 상태로 업데이트합니다

        orderInput.setID(orderNum);

        /** 주문 정보를 불러옴과 동시에 주문상태를 철회상태로 (8)로 업데이트 합니다.*/
        try {
            orderOutput = orderService.selectOrder(orderInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /**백엔드에서 사용자의 유효성을 검사합니다*/
        //만약 입찰자(철회자)가 주문에 있는 아이디와 다를경우 에러입니다.
        if (orderOutput.getBuy_user_ID() != ID) {
            return 0;
        }

        /** 8로 업데이트 합니다.*/
        try {
            orderStatusUpdate = orderService.orderTerminate(orderInput);
        } catch (Exception e) {
            e.printStackTrace();
        }


        // 모든 주문 정보를 불러옵니다.
        int buyer = orderOutput.getBuy_user_ID(); //구매자 아이디
        int seller = orderOutput.getSell_user_ID(); //판매자 아이디
        int sell = orderOutput.getSell_ID(); //판매 번호
        int buy = orderOutput.getBuy_ID(); // 구매번호
        int productNum = orderOutput.getOrderProductNum(); // 상품 번호
        String orderNumber = orderOutput.getOrderNumber(); // 주문번호

        /**구매자 구매 상태를 철회로 업데이트 합니다 (B)
         * 판매자의 판매 상태를 철회로 업데이트 합니다 (A)
         * */
        int buyTerminate = 0;
        int sellTerminate = 0;

        buyInput.setID(buy);
        buyInput.setBuyProcess("B");
        sellInput.setID(sell);
        sellInput.setSellProcess("A");

        try {
            // 입찰자 철회
            buyTerminate = buyService.buyTerminate(buyInput);
            // 판매자 철회
            sellTerminate = sellService.sellTerminate(sellInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /** 입찰자에게 패널티를 부여하고 (15일) 패널티로 Y로 변경하고 이메일 주소를 가져옵니다*/

        // 15일후...
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 15);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String penaltyDate = df.format(cal.getTime()); // 15일 후 값

        int penaltyCheck = 0;

        userInput.setID(buyer);
        userInput.setUser_penalty("Y");
        userInput.setPenaltyDate(penaltyDate);

        try {
            userOutput = userService.getUserEmail(userInput);
            penaltyCheck = userService.terminate(userInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String buyerEmail = userOutput.getUserEmail(); // 입찰자 본인의 이메일 주소
        String buyerName = userOutput.getUserName(); // 입찰자 본인의 이름

        /** 판매자의 이메일을 가져옵니다*/

        userInput1.setID(seller);

        try {
            userOutput1 = userService.getUserEmail(userInput1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sellerEmail = userOutput1.getUserEmail(); // 판매자의 이메일 주소
        String sellerName = userOutput1.getUserName(); //판매자의 이름

        /** 이메일을 보내기 위해 상품 정보를 가져옵니다*/
        productInput.setID(productNum);

        try {
            productOutput = productService.getBuyProductInfo(productInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String productImg = productOutput.getProductImgPath();
        String productName = productOutput.getProductNameEN();
        int size = orderOutput.getOrderSize();

        /**입찰자와 판매자 각각 이메일을 전송합니다*/

        String title = "TIESHOE || 주문이 철회되었습니다";
        String sellContext = "구매자가 주문을 일방적으로 철회하였습니다.<br> 상품을 발송하셨다면 상품을 TIESHOE에서 반송하며 배송비는 TIESHOE에서 부담합니다";
        String buyContext = "주문 철회가 완료되었습니다.<br> 배송비는 구매자 본인이 부담하여야 하며 15일간 활동 정지가 됩니다 판매 또는 입찰하지 못하며 주문철회가 많아지면 회원 탈퇴 될 수 있습니다";

        //입찰자의 이메일을 String으로 저장합니다
        String bEmail = email(buyerName, productName, productImg, size, buyContext);
        String sEmail = email(sellerName, productName, productImg, size, sellContext);

        try {
            mailHelper.sendMail(buyerEmail, title, bEmail);
            mailHelper.sendMail(sellerEmail, title, sEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    @RequestMapping(value = "/deleteAdminOrder.do", method = {RequestMethod.GET, RequestMethod.POST})
    public int deleteAdminOrder(Model model,
                                @RequestParam int orderNum) {


        orderBeans orderInput = new orderBeans();
        buyBeans buyInput = new buyBeans();
        sellBeans sellInput = new sellBeans();
        userBeans userInput = new userBeans();
        productBeans productInput = new productBeans();
        orderBeans orderOutput = null;
        userBeans userOutput = null;
        productBeans productOutput = null;
        int orderStatusUpdate = 0; // 주문 상채를 철회 상태로 업데이트합니다

        orderInput.setID(orderNum);

        /** 주문 정보를 불러옴과 동시에 주문상태를 철회상태로 (8)로 업데이트 합니다.*/
        try {
            orderOutput = orderService.selectOrder(orderInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**확인이 된다면 8로 업데이트*/
        try {
            orderStatusUpdate = orderService.orderTerminate(orderInput);
        } catch (Exception e) {
            e.printStackTrace();
        }


        // 모든 주문 정보를 불러옵니다.
        int buyer = orderOutput.getBuy_user_ID(); //구매자 아이디
        int seller = orderOutput.getSell_user_ID(); //판매자 아이디
        int sell = orderOutput.getSell_ID(); //판매 번호
        int buy = orderOutput.getBuy_ID(); // 구매번호
        int productNum = orderOutput.getOrderProductNum(); // 상품 번호
        String orderNumber = orderOutput.getOrderNumber(); // 주문번호

        /**구매자 구매 상태를 철회로 업데이트 합니다 (A)
         * 판매자의 판매 상태를 철회로 업데이트 합니다 (A)
         * */
        int buyTerminate = 0;
        int sellTerminate = 0;

        buyInput.setID(buy);
        buyInput.setBuyProcess("A");
        sellInput.setID(sell);
        sellInput.setSellProcess("A");

        try {
            // 입찰자 철회
            buyTerminate = buyService.buyTerminate(buyInput);
            // 판매자 철회
            sellTerminate = sellService.sellTerminate(sellInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        userInput.setID(seller);

        try {
            userOutput = userService.getUserEmail(userInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String buyerEmail = userOutput.getUserEmail(); // 입찰자 본인의 이메일 주소
        String buyerName = userOutput.getUserName(); // 입찰자 본인의 이름

        /** 판매자의 이메일을 가져옵니다*/

        userInput.setID(seller);

        try {
            userOutput = userService.getUserEmail(userInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sellerEmail = userOutput.getUserEmail(); // 판매자의 이메일 주소
        String sellerName = userOutput.getUserName(); //판매자의 이름

        /** 이메일을 보내기 위해 상품 정보를 가져옵니다*/
        productInput.setID(productNum);

        try {
            productOutput = productService.getBuyProductInfo(productInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String productImg = productOutput.getProductImgPath();
        String productName = productOutput.getProductNameEN();
        int size = orderOutput.getOrderSize();

        /**입찰자와 판매자 각각 이메일을 전송합니다*/

        String title = "TIESHOE || 주문이 철회되었습니다";
        String buyContext = "관리자가 주문을 철회하였습니다";
        String sellContext = "주문이 철회되었습니다.<br> 관리자가 거래를 검토하던중 문제가 발생해 일방적으로 주문을 철회하였습니다";

        //입찰자의 이메일을 String으로 저장합니다
        String bEmail = email(buyerName, productName, productImg, size, buyContext);
        String sEmail = email(sellerName, productName, productImg, size, sellContext);

        try {
            mailHelper.sendMail(buyerEmail, title, bEmail);
            mailHelper.sendMail(sellerEmail, title, sEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    @RequestMapping(value = "/deleteSellerOrder.do", method = {RequestMethod.GET, RequestMethod.POST})
    public int deleteSellerOrder(Model model,
                                 @RequestParam int orderNum) {

        int ID = (Integer) webHelper.getSession("loginID");

        orderBeans orderInput = new orderBeans();
        buyBeans buyInput = new buyBeans();
        sellBeans sellInput = new sellBeans();
        userBeans userInput = new userBeans();
        productBeans productInput = new productBeans();
        orderBeans orderOutput = null;
        userBeans userOutput = null;
        productBeans productOutput = null;
        int orderStatusUpdate = 0; // 주문 상채를 철회 상태로 업데이트합니다

        orderInput.setID(orderNum);

        /** 주문 정보를 불러옴과 동시에 주문상태를 철회상태로 (8)로 업데이트 합니다.*/
        try {
            orderOutput = orderService.selectOrder(orderInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**백엔드에서 사용자의 유효성을 검사합니다*/
        //만약 판매자(철회자)가 주문에 있는 아이디와 다를경우 에러입니다.
        if (orderOutput.getSell_user_ID() != ID) {
            return 0;
        }

        /**확인이 된다면 8로 업데이트*/
        try {
            orderStatusUpdate = orderService.orderTerminate(orderInput);
        } catch (Exception e) {
            e.printStackTrace();
        }


        // 모든 주문 정보를 불러옵니다.
        int buyer = orderOutput.getBuy_user_ID(); //구매자 아이디
        int seller = orderOutput.getSell_user_ID(); //판매자 아이디
        int sell = orderOutput.getSell_ID(); //판매 번호
        int buy = orderOutput.getBuy_ID(); // 구매번호
        int productNum = orderOutput.getOrderProductNum(); // 상품 번호
        String orderNumber = orderOutput.getOrderNumber(); // 주문번호

        /**구매자 구매 상태를 철회로 업데이트 합니다 (A)
         * 판매자의 판매 상태를 철회로 업데이트 합니다 (B)
         * */
        int buyTerminate = 0;
        int sellTerminate = 0;

        buyInput.setID(buy);
        buyInput.setBuyProcess("A");
        sellInput.setID(sell);
        sellInput.setSellProcess("B");

        try {
            // 입찰자 철회
            buyTerminate = buyService.buyTerminate(buyInput);
            // 판매자 철회
            sellTerminate = sellService.sellTerminate(sellInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /** 판매자에게 패널티를 부여하고 (15일) 패널티로 Y로 변경하고 이메일 주소를 가져옵니다*/

        // 15일후...
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 15);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String penaltyDate = df.format(cal.getTime()); // 15일 후 값

        int penaltyCheck = 0;

        userInput.setID(seller);
        userInput.setUser_penalty("Y");
        userInput.setPenaltyDate(penaltyDate);

        try {
            userOutput = userService.getUserEmail(userInput);
            penaltyCheck = userService.terminate(userInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String buyerEmail = userOutput.getUserEmail(); // 입찰자 본인의 이메일 주소
        String buyerName = userOutput.getUserName(); // 입찰자 본인의 이름

        /** 판매자의 이메일을 가져옵니다*/

        userInput.setID(seller);

        try {
            userOutput = userService.getUserEmail(userInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sellerEmail = userOutput.getUserEmail(); // 판매자의 이메일 주소
        String sellerName = userOutput.getUserName(); //판매자의 이름

        /** 이메일을 보내기 위해 상품 정보를 가져옵니다*/
        productInput.setID(productNum);

        try {
            productOutput = productService.getBuyProductInfo(productInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String productImg = productOutput.getProductImgPath();
        String productName = productOutput.getProductNameEN();
        int size = orderOutput.getOrderSize();

        /**입찰자와 판매자 각각 이메일을 전송합니다*/

        String title = "TIESHOE || 주문이 철회되었습니다";
        String buyContext = "판매자가 주문을 일방적으로 철회하였습니다.<br> 입금을 완료하셨다면 3일 이내에 환불조치 됩니다.";
        String sellContext = "주문 철회가 완료되었습니다.<br> 배송비는 판매자 본인이 부담하여야 하며 15일간 활동 정지가 됩니다 판매 또는 입찰하지 못하며 주문철회가 많아지면 회원 탈퇴 될 수 있습니다";

        //입찰자의 이메일을 String으로 저장합니다
        String bEmail = email(buyerName, productName, productImg, size, buyContext);
        String sEmail = email(sellerName, productName, productImg, size, sellContext);

        try {
            mailHelper.sendMail(buyerEmail, title, bEmail);
            mailHelper.sendMail(sellerEmail, title, sEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }


    public String email(String name, String productName, String path, int size, String context) {
        return "<div class=\"\">\n" +
                "    <div class=\"aHl\"></div>\n" +
                "    <div id=\":qw\" tabindex=\"-1\"></div>\n" +
                "    <div id=\":ql\" class=\"ii gt\">\n" +
                "        <div id=\":qk\" class=\"a3s aXjCH \">\n" +
                "            <div class=\"adM\">\n" +
                "\n" +
                "\n" +
                "            </div>\n" +
                "            <div>\n" +
                "                <div class=\"adM\">\n" +
                "                </div>\n" +
                "                <div class=\"adM\">\n" +
                "                </div>\n" +
                "                <div style=\"padding-top:90px;padding-bottom:70px;text-align:center\">\n" +
                "                    <div class=\"adM\">\n" +
                "                    </div>\n" +
                "                    <div style=\"width:375px;min-height:100px;margin-right:auto;margin-left:auto;font-family:'Nanum Gothic',sans-serif\">\n" +
                "                        <div class=\"adM\">\n" +
                "                        </div>\n" +
                "                        <div style=\"margin-bottom:6px;color:#000;font-size:27px;line-height:37px;text-align:left\">\n" +
                "                            <div class=\"adM\">\n" +
                "                            </div>\n" +
                "                            <div style=\"display:inline-table\">\n" +
                "                                <div class=\"adM\">\n" +
                "                                </div>\n" +
                "                                <div id=\"m_-8527723984461195389xxblue-email-name\" style=\"display:inline-block\">" + name + "</div>\n" +
                "                                <div style=\"display:inline-block\">님,</div>\n" +
                "                            </div>\n" +
                "                            <div>\n" +
                "                                <div style=\"display:inline-block\">상품이</div>\n" +
                "                                <div style=\"display:inline-block;color:#e72525\">철회</div>\n" +
                "                                <div style=\"display:inline-block\">되었습니다.</div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <div style=\"color:#a3a3a3;font-size:11px;line-height:15px;text-align:left\">\n" +
                "                            <div>\n" +
                "                                <div style=\"display:inline-block\"></div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <div style=\"margin-top:50px\">\n" +
                "                            <div style=\"display:grid;margin-bottom:20px\">\n" +
                "                                <div id=\"m_-8527723984461195389w-node-7f9f85ff6c2f-70d2219d\"\n" +
                "                                     style=\"margin-bottom:32px;padding-bottom:9px;border-bottom:2px solid #000;color:#000;font-size:15px;line-height:17px;font-weight:700;text-align:left\">\n" +
                "                                    상품정보\n" +
                "                                </div>\n" +
                "                                <div style=\"margin-bottom:12px;font-family:Roboto,sans-serif;color:#000;font-size:13px;line-height:16px;text-align:left\">\n" +
                "                                    " + productName + "\n" +
                "                                </div>\n" +
                "                                <div>\n" +
                "                                    <img src=\"" + path + "\"\n" +
                "                                         alt=\"\" style=\"max-width:100%\" class=\"CToWUd a6T\" tabindex=\"0\">\n" +
                "                                    <div class=\"a6S\" dir=\"ltr\" style=\"opacity: 0.01; left: 891.5px; top: 715px;\">\n" +
                "                                        <div id=\":ro\" class=\"T-I J-J5-Ji aQv T-I-ax7 L3 a5q\" role=\"button\" tabindex=\"0\"\n" +
                "                                             aria-label=\"첨부파일() 다운로드\" data-tooltip-class=\"a1V\" data-tooltip=\"다운로드\">\n" +
                "                                            <div class=\"aSK J-J5-Ji aYr\"></div>\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                    <div style=\"background-color:rgba(157,160,162,0.09)\"></div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                            <div style=\"display:grid\">\n" +
                "                                <div style=\"margin-bottom:11.5px;padding-bottom:12px;border-bottom:2px solid #000;text-align:left\">\n" +
                "                                    <div style=\"display:inline-block;font-size:13px\">옵션</div>\n" +
                "                                    <div style=\"display:inline-block;margin-left:15px;font-family:Roboto,sans-serif;font-size:22px;line-height:29px\">\n" +
                "                                        " + size + "\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                            <div style=\"margin-bottom:25px;padding-bottom:13.5px;border-bottom:1px solid #e8e8e8\">\n" +
                "                                " + context + "\n " +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <a href=\"https://xxblue.com\"\n" +
                "                       style=\"width:100%;margin-top:45px;padding-top:12px;padding-bottom:12px;border-radius:4px;background-color:#000;font-size:13px;line-height:15px;text-decoration:none;color:white;display:inline-block;border:0\"\n" +
                "                       target=\"_blank\"\n" +
                "                       data-saferedirecturl=\"https://www.google.com/url?q=https://xxblue.com&amp;source=gmail&amp;ust=1584163709966000&amp;usg=AFQjCNFwsizl76Ka36Oy6MIQN_x1TeD8fg\"><span\n" +
                "                            class=\"il\">TIE</span>&nbsp;<span class=\"il\">SHOE</span> 바로가기</a></div>\n" +
                "            </div>\n" +
                "            <div style=\"font-family:'Nanum Gothic',sans-serif\">\n" +
                "                <div style=\"width:100%;min-height:100px;margin-right:auto;margin-left:auto;padding:26px 12px 20px;background-color:#f5f5f5;font-family:'Nanum Gothic',sans-serif\">\n" +
                "                    <p style=\"color:#999;font-size:9px;line-height:14px\">본 메일은 발신전용메일이며 문의에 대한 회신이 되지 않습니다. <br>궁금한점은\n" +
                "                        고객센터로 문의해 주세요.</p>\n" +
                "\n" +
                "                    <p style=\"color:#999;font-size:9px;line-height:14px\">서울특별시 서초구, 1303-37 서초W타워 13층 &nbsp;| &nbsp;대표이사\n" +
                "                        홍길동<br>사업자 등록번호 123-45-67890 &nbsp; | &nbsp;통신판매업 신고 2019-서울강남-03088<br>문의전화 02-123-4567\n" +
                "                        &nbsp;| &nbsp;개인정보관리책임: 홍길동<br></p>\n" +
                "                    <div class=\"yj6qo\"></div>\n" +
                "                    <div class=\"adL\">\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                <div class=\"adL\">\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"adL\">\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"adL\">\n" +
                "\n" +
                "\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "<div id=\":r1\" class=\"ii gt\" style=\"display:none\">\n" +
                "    <div id=\":r0\" class=\"a3s aXjCH undefined\"></div>\n" +
                "</div>\n" +
                "<div class=\"hi\"></div>\n" +
                "</div>";
    }
    
    
    /**
     * 회원탈퇴 페이지
     */
    @RequestMapping(value = "/dropUser", method = RequestMethod.POST)
    public Map<String, Object> dropUser(
    		
                                 @RequestParam(value = "ID", required = false) int ID,
                                 @RequestParam(value = "userdropTF", required = false) String userdropTF
    ) {

        userBeans input = new userBeans();
        input.setID(ID);
        input.setUserdropTF(userdropTF);
        
        buyBeans buy = new buyBeans();
        int findBuy = 0;
        sellBeans sell = new sellBeans();
        int findSell = 0;
        
        buy.setUser_ID(input.getID());
        sell.setUser_ID(input.getID());
        
        int output = 0;
        

        try {        	
        	findBuy = buyService.buyUserCount(buy);
        	findSell = sellService.sellUserCount(sell);
        	if(findBuy == 0  && findSell == 0) {
        		output = userService.dropUser(input);
        	} 
			/*
			 * else { return webHelper.getJsonWarning("회원님께서는 현재 거래중인 상품이 있어 탈퇴가 불가능 합니다.");
			 * }
			 */
        } catch (Exception e) {
        	return webHelper.getJsonWarning(e.getLocalizedMessage()+"시스템 에러 발생.");
        }

        	
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("findBuy", findBuy);
        map.put("findSell", findSell);
        map.put("output",output);
        return webHelper.getJsonData(map);
    }
    
    
}
/*
 * FileName: ProductController.java
 * Description: 상품에 대한 접근이 가능하게 하는 controller 입니다.
 * Author: 임석현
 **/

package com.ezen03.tieshoe;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.ezen03.tieshoe.helper.MailHelper;
import com.ezen03.tieshoe.helper.PageData;
import com.ezen03.tieshoe.helper.RegexHelper;
import com.ezen03.tieshoe.helper.RetrofitHelper;
import com.ezen03.tieshoe.helper.WebHelper;
import com.ezen03.tieshoe.model.*;
import com.ezen03.tieshoe.service.*;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Handles requests for the application home page.
 */
@Slf4j
@Controller
public class ProductController {

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
    ReviewService reviewService;

    @Autowired
    MypageService mypageService;

    @Autowired
    OrderService orderService;

    @Autowired
    checkService checkService;

    @Value("#{servletContext.contextPath}")
    String contextPath;


    /**
     * Simply selects the home view to render by returning its name.
     */

    //구매 페이지
    @RequestMapping(value = "/buy", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView buy(Model model, HttpServletRequest request, HttpServletResponse response,
                            @RequestParam int productNum,
                            @RequestParam int size,
                            @RequestParam(value = "ID", defaultValue = "0", required = false) int ID,
                            @RequestParam(required = false, defaultValue = "0") int buyEdit_ID,
                            @RequestParam(required = false, defaultValue = "0") int price,
                            @RequestParam(required = false, defaultValue = "0") int fee
    ) {


        ID = (Integer) webHelper.getSession("loginID");

        sellBeans check = new sellBeans(); //확인을 위한 빈즈
        check.setSellSize(size);
        check.setProductNum(productNum);
        check.setUser_ID(ID);

        sellBeans checkStatus = null;

        try {
            checkStatus = sellService.check(check);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (checkStatus != null) {
            return webHelper.redirect(null, "해당 상품에 판매 내역이 존재합니다");
        }

        // 이 값이 존재하지 않는다면 구매가 불가능하므로 반드시 필수값으로 처리해야 한다.
        if (ID == 0) {
            return webHelper.redirect("/error", null);
        }

        webHelper.setCookie("authAgain", "auth", 1000);

        productBeans input = new productBeans();
        complexBeans inputNum = new complexBeans();
        couponBeans couponInput = new couponBeans();
        input.setID(productNum);
        inputNum.setProductNum(productNum);
        inputNum.setSize(size);
        //사용자의 정보 세션을 가져와 빈즈에 삽입합니다.
        couponInput.setUser_ID((Integer) webHelper.getSession("loginID"));

        userBeans User = new userBeans();
        User.setID(ID);

        productBeans output = null;
        complexBeans outputHigh = null;
        complexBeans outputLow = null;
        //배송쿠폰을 가져옵니다.
        couponBeans coupon = null;
        userBeans UserInfo = null;
        buyBeans buyInfo = new buyBeans();

        try {
            output = productService.getBuyProductInfo(input);
            outputHigh = buyService.highPrice(inputNum);
            outputLow = buyService.lowPrice(inputNum);
            coupon = userService.checkCoupon(couponInput);
            UserInfo = userService.getUserItem(User);

        } catch (Exception e) {
            e.printStackTrace();
        }

        buyInfo.setID(buyEdit_ID);
        buyInfo.setBuyPrice(price);
        buyInfo.setBuyFee(fee);

        model.addAttribute("product", output);
        model.addAttribute("highest", outputHigh);
        model.addAttribute("lowest", outputLow);
        model.addAttribute("coupon", coupon);
        model.addAttribute("UserInfo", UserInfo);
        model.addAttribute("buyInfo", buyInfo);

        return new ModelAndView("product/buy");
    }


    @RequestMapping(value = "/sell", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView sell(Model model,
                             @RequestParam(required = true) int productNum,
                             @RequestParam(required = true) int size,
                             @RequestParam(required = false, defaultValue = "0") int sellEdit_ID,
                             @RequestParam(required = false, defaultValue = "0") int price,
                             @RequestParam(required = false, defaultValue = "0") int fee) {

        int ID = (Integer) webHelper.getSession("loginID");

        buyBeans check = new buyBeans(); //확인을 위한 빈즈
        check.setBuySize(size);
        check.setProductNum(productNum);
        check.setUser_ID(ID);

        buyBeans checkStatus = null;

        try {
            checkStatus = buyService.check(check);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (checkStatus != null) {
            return webHelper.redirect(null, "해당 상품에 입찰 내역이 존재합니다");
        }

        // 이 값이 존재하지 않는다면 구매가 불가능하므로 반드시 필수값으로 처리해야 한다.
        if (ID == 0) {
            return webHelper.redirect("/error", null);
        }

        productBeans input = new productBeans();
        complexBeans inputNum = new complexBeans();
        userBeans userInput = new userBeans();
        sellBeans sellInfo = new sellBeans();

        input.setID(productNum);
        inputNum.setProductNum(productNum);
        inputNum.setSize(size);
        //사용자의 정보 세션을 가져와 빈즈에 삽입합니다.
        userInput.setID((Integer) webHelper.getSession("loginID"));

        userBeans User = new userBeans();
        User.setID(ID);

        productBeans output = null;
        complexBeans outputHigh = null;
        complexBeans outputLow = null;
        //배송쿠폰을 가져옵니다.
        userBeans UserInfo = null;

        try {
            output = productService.getBuyProductInfo(input);
            outputHigh = buyService.highPrice(inputNum);
            outputLow = buyService.lowPrice(inputNum);
            UserInfo = userService.getUserItem(User);

        } catch (Exception e) {
            e.printStackTrace();
        }

        sellInfo.setID(sellEdit_ID);
        sellInfo.setSellPrice(price);
        sellInfo.setSellFee(fee);

        model.addAttribute("product", output);
        model.addAttribute("highest", outputHigh);
        model.addAttribute("lowest", outputLow);
        model.addAttribute("user", UserInfo);
        model.addAttribute("sellInfo", sellInfo);

        return new ModelAndView("product/sell");
    }

    @RequestMapping(value = "/buyProcess", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView buyProcess(Model model, HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam int ID
    ) {
        int userID = (Integer) webHelper.getSession("loginID");
        int auth = (Integer) webHelper.getSession("auth");
        // 이 값이 존재하지 않는다면 구매가 불가능하므로 반드시 필수값으로 처리해야 한다.
        if (userID == 0 || auth != userID) {
            return webHelper.redirect("/error", null);
        }

        buyBeans buyInput = new buyBeans();
        productBeans productInput = new productBeans();
        userBeans userInput = new userBeans();

        buyBeans finalResult = null;
        productBeans product = null;
        userBeans user = null;

        buyInput.setID(ID);
        try {
            //데이터를 조회합니다.
            finalResult = buyService.selectOne(buyInput);

            productInput.setID(finalResult.getProductNum());
            product = productService.getProduct(productInput);

            userInput.setID(finalResult.getUser_ID());
            user = userService.getUserItem(userInput);

        } catch (Exception e) {
            e.printStackTrace();
        }

        webHelper.removeSession("auth");

        model.addAttribute("checkBuy", finalResult);
        model.addAttribute("product", product);
        model.addAttribute("user", user);

        return new ModelAndView("product/buyProcess");
    }

    @RequestMapping(value = "/sellProcess", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView sellProcess(Model model, HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam int ID
    ) {
        int userID = (Integer) webHelper.getSession("loginID");

        int auth = (Integer) webHelper.getSession("auth");
        // 이 값이 존재하지 않는다면 구매가 불가능하므로 반드시 필수값으로 처리해야 한다.
        if (userID == 0 || auth != userID) {
            return webHelper.redirect("/error", null);
        }

        sellBeans sellInput = new sellBeans();
        productBeans productInput = new productBeans();
        userBeans userInput = new userBeans();

        sellBeans finalResult = null;
        productBeans product = null;
        userBeans user = null;

        sellInput.setID(ID);

        try {
            //데이터를 조회합니다.
            finalResult = sellService.getSellInfo(sellInput);

            productInput.setID(finalResult.getProductNum());
            product = productService.getProduct(productInput);

            userInput.setID(finalResult.getUser_ID());
            user = userService.getUserItem(userInput);

        } catch (Exception e) {
            e.printStackTrace();
        }

        webHelper.removeSession("auth");

        model.addAttribute("checkSell", finalResult);
        model.addAttribute("product", product);
        model.addAttribute("user", user);

        return new ModelAndView("product/sellProcess");
    }


    @RequestMapping(value = "/sellProcess.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView sellProcess(Model model,
                                    @RequestParam String user_price,
                                    @RequestParam int bidDate,
                                    @RequestParam int size,
                                    @RequestParam int productNum,
                                    @RequestParam(required = false, defaultValue = "0") int myPagePrice,
                                    @RequestParam(required = false, defaultValue = "0") int myPageID) {

        int ID = (Integer) webHelper.getSession("loginID");

        // 이 값이 존재하지 않는다면 구매가 불가능하므로 반드시 필수값으로 처리해야 한다.
        if (ID == 0) {
            return webHelper.redirect("/error", null);
        }

        // 가격정보를 INTEGER 타입으로 변환합니다.
        int userInput = Integer.parseInt(user_price.replace(",", ""));
        int fee = 0; // 거래수수료
        int total = 0; // 총 거래액
        int check = 0; //체크를 확인하기 위해 빈 값을 지정
        int instanceSell = 0; // 즉시 판매가격
        int level = 0; // 판매자의 레벨
        int buyer = 0; // 만약 판매자가 존재한다면 판매자의 ID를 저장할 변수
        int buyID = 0; // 만약 판매자가 존재한다면 판매 번호를 가져옵니다.'
        String sellerEndDate = null; // 만약 판매자가 존재한다면 판매자가 배송해야하는 날자를 저장할 변수
        String buyerEndDate = null; // 바로구매를 했을경우 입금 날자를 저장할 변수
        String standardBuy = null; //입찰자 입찰 기간
        String order_Number_element = null; // 주문번호 조합을 위한 VALUE
        String order_Number_element2 = null; // 주문번호 조합을 위한 VALUE
        int orderInfo = 0; // 바로 구매가격이 입력이된다면 주문건을 생성시킵니다.

        /** 벡엔드 체크를 시작합니다. (validating again) */
        complexBeans inputNum = new complexBeans();
        buyBeans inputBuy = new buyBeans();
        sellBeans finalResult = new sellBeans();
        sellBeans inputSell = new sellBeans();
        productBeans inputProduct = new productBeans();
        userBeans sellerInput = new userBeans();
        userBeans buyerInput = new userBeans();
        orderBeans orderInput = new orderBeans();

        complexBeans instanceSellBeans = null; //바로 구매가격 (Uninitialized)
        productBeans product = null; // 현재 상품의 정보를 가져옵니다.
        userBeans sellerInfo = null; // 판매자의 정보를 가져옵니다.
        userBeans buyerInfo = null; // 입찰자의 정보를 가져옵니다.
        buyBeans buyInfo = null; // 해당 ID의 buy정보를 가져옵니다.

        // 상품을 찾기위해 정보를 빈즈에 저장합니다
        inputProduct.setID(productNum);

        // 해당 상품의 정보를 가져옵니다.
        try {
            product = productService.getProduct(inputProduct);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /** 입력된 가격의 유효성을 체크합니다. */

        inputNum.setProductNum(productNum);
        inputNum.setSize(size);
        try {
            instanceSellBeans = buyService.highPrice(inputNum);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (instanceSellBeans != null) {
            instanceSell = instanceSellBeans.getBuyHighPrice();
            buyID = instanceSellBeans.getID();
            buyer = instanceSellBeans.getUser_ID();

            //판매자가 있다면 판매자의 상품을 받아야하는 날짜를 가져옵니다 (7일)
            Calendar cal = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            Calendar orderNumEle = Calendar.getInstance();

            cal.setTime(new Date());
            cal2.setTime(new Date());
            orderNumEle.setTime(new Date());
            cal.add(Calendar.DATE, 7);
            cal2.add(Calendar.DATE, 3);

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat order_df = new SimpleDateFormat("yyyyMMdd");
            DateFormat order_df_second = new SimpleDateFormat("HHmmss");

            sellerEndDate = df.format(cal.getTime()); // 현재 시간에서 7일을 더한 값 (상품을 받아야 하는 LIMIT)
            buyerEndDate = df.format(cal2.getTime()); // 현재 시간에서 3일을 더한 값 (입금을 받아야 하는 LIMIT)
            order_Number_element = order_df.format(orderNumEle.getTime()); //현재시간을 다른 포맷으로 저장(orderNumber을 생성하기 위한 ELEMENT)
            order_Number_element2 = order_df_second.format(orderNumEle.getTime());
        } else {
            instanceSell = 0; //데이터가 없다면 0으로 설정합니다
        }

        // 만약 판매 내역이 없다면
        // 만약 입력된 값이 바로 구매가격보다 높다면 에러페이지를 내보냅니다.
        if (instanceSell < 1000) {
            instanceSell = 0;
        } else if (userInput > instanceSell) {
            //에러가 났다면 FrontEnd 에러이므로 로그를 찍습니다.
            log.debug(String.format("[Error] InstantBuy: %d, UserInput: u%d The result has been modified", instanceSell, userInput));
            return webHelper.redirect(contextPath + "/error", null);
        }

        /** 이메일을 발송하기 (바로판매 가격일 경우 판매자와 구매자 동시에 보내기) */

        // 판매자와 구매자의 정보를 DB에서 불러옵니다
        buyerInput.setID(buyer); //구매자 정보
        sellerInput.setID((Integer) webHelper.getSession("loginID")); //판매자 정보

        try {
            sellerInfo = userService.getUserItem(sellerInput); // 판매자 본인에 대한 정보
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 판매자의 레벨을 가져옵니다
        level = sellerInfo.getUserLevel();

        switch (level) {
            case 5:
                fee = (int) Math.ceil(((userInput * 0.13 + 10) / 100)) * 100;
                break;

            case 4:
                fee = (int) Math.ceil(((userInput * 0.12 + 10) / 100)) * 100;
                break;

            case 3:
                fee = (int) Math.ceil(((userInput * 0.11 + 10) / 100)) * 100;
                break;

            case 2:
                fee = (int) Math.ceil(((userInput * 0.1 + 10) / 100)) * 100;
                break;

            case 1:
                fee = (int) Math.ceil(((userInput * 0.09 + 10) / 100)) * 100;
                break;

        }
        // 금액의 총합
        int totalPrice = userInput + fee;


        // 해당 아이디로 판매을 하기위해 userID 빈즈에 저장합니다
        // 필요한 모든 데이터를 DB에 저장합니다
        inputSell.setUser_ID((Integer) webHelper.getSession("loginID"));
        inputSell.setSellSize(size);
        inputSell.setSellEnd(bidDate);
        inputSell.setProductNum(productNum);
        inputSell.setSellFee(fee);
        inputSell.setSellPrice(userInput);
        inputSell.setID(myPageID);
        inputBuy.setID(buyID);
        inputBuy.setUser_ID(buyer);


        /** 이메일에 필요한 요소들 */
        String sellerEmail = sellerInfo.getUserEmail(); // 입찰자의 이메일


        // TIE SHOE HOME PAGE
        String localHost = "http://localhost:8080";
        String link = localHost + contextPath; // INDEX PAGE

        // 메일에 들어갈 String

        // 주문 정보
        String buyStatus = "판매";
        String orderStatus = "거래";

        String buyStatusNoti = "판매정보가 성공적으로 등록되었습니다.<br>";
        String buyOrderStatusNoti = "거래가 성공적으로 성사되었습니다.<br>3일 안에 입금이 되지 않으면 패널티가 부여되며 거래가 취소됩니다.";
        String sellOrderStatusNoti = "거래가 성공적으로 성사되었습니다.<br>7일 안에 배송되지 않으면 패널티가 부여되면 거래가 취소됩니다";


        // 이메일에 필요한 요소들을 DB에서 가져옵니다.
        String sellerName = sellerInfo.getUserName(); //입찰자 이름
        String productName = product.getProductBrandEN(); // 상품 이름
        String path = product.getProductImgPath(); //상품 이미지
        String phoneNum = sellerInfo.getUserPhonenum();
        String sellTitle = "TIESHOE || 판매가 진행중입니다.";
        String orderTitle = "TIESHOE || 거래가 진행되었습니다.";
        String sellTextNoti = "판매마감일";
        String orderBuyTextNoti = "입금마감일";
        String orderSellTextNoti = "상품배송마감일";
        String tieshoeAddr = "서울특별시 서초구, 1303-37 서초W타워 13층";
        /** SELL BID DATE*/
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, bidDate);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        standardBuy = df.format(cal.getTime()); // 현재 시간에서 7일을 더한 값 (상품을 받아야 하는 LIMIT)

        // INT TO STRING WITH COMMA
        DecimalFormat comma = new DecimalFormat("###,###");

        // check 변수를 재사용합니다.
        check = 0;

        // 바로구매 가격일 경우 BUY에 진행중으로 바꾸어 저장합니다. 또한 판매자의 판매 상태도 Y로 업데이트합니다.
        if (userInput == instanceSell) {

            //바로 판매이기 때문에 바로 거래성사가 되었습니다. 확인이 필요 없기 때문에 Y가 아닌 D로 업데이트 합니다.
            inputSell.setSellProcess("D");
            inputBuy.setBuyProcess("Y");

            // DB에 저장합니다.
            try {
                //판매자의 정보를 가져옵니다.
                buyerInfo = userService.getUserItem(buyerInput);
                // BUY UPDATE TO "Y"
                check = buyService.updateBuy(inputBuy);
                // SELL INSERT
                if (myPageID == 0) {
                    check = sellService.addSell(inputSell);
                } else {
                    check = sellService.updateSellPrice(inputSell);
                }
                // GET BUY INFO
                buyInfo = buyService.selectOne(inputBuy);

            } catch (Exception e) {
                e.printStackTrace();
            }

            String buyerName = buyerInfo.getUserName(); //판매자 이름

            // 입찰자가 존재 함으로 입찰자의 이메일을 가져옵니다.
            String buyerEmail = buyerInfo.getUserEmail();

            // 입찰자가 존재 함으로 입찰자의 주소를 가져옵니다.
            String buyerAddress = buyerInfo.getUserAddress1() + " " + buyerInfo.getUserAddress2(); // 입찰자의 주소

            // 입찰자의 총합
            int totalbuy = buyInfo.getBuyPrice() + buyInfo.getBuyFee();

            // 입찰자의 배송비를 확인합니다
            int shipping = 0;
            if (buyInfo.getBuyFee() == 5000) {
                shipping = 3000;
            } else {
                shipping = 0;
            }

            // 확인 이메일을 입찰자와 판매자에게 전송합니다. (바로구매)
            String buyerMail = emailForm(buyerName, orderStatus, buyOrderStatusNoti, productName, path,
                    size, user_price, comma.format(shipping), "5,000", comma.format(totalbuy), 3, buyerEndDate,
                    buyerAddress, phoneNum, link, orderBuyTextNoti, "배송비");

            String sellerMail = emailForm(sellerName, orderStatus, sellOrderStatusNoti, productName, path,
                    size, user_price, comma.format(fee), "0", comma.format(totalPrice), 7, sellerEndDate,
                    tieshoeAddr, "02-123-1234", link, orderSellTextNoti, "수수료");

            try {
                // 입찰자와 판메자에게 메일을 전송합니다.
                mailHelper.sendMail(buyerEmail, orderTitle, buyerMail);
                mailHelper.sendMail(sellerEmail, orderTitle, sellerMail);
            } catch (Exception e) {
                e.printStackTrace();
            }

            /** 주문 생성 */
            // 주문건을 생성하기 위한 변수들
            String orderNumber = order_Number_element + " - " + productNum + sellerInfo.getID() + buyerInfo.getID() + order_Number_element2;

            orderInput.setOrderProductNum(productNum);
            orderInput.setOrderNumber(orderNumber);
            orderInput.setOrderPrice(userInput);
            orderInput.setOrderSize(size);
            orderInput.setSell_user_ID(sellerInfo.getID());
            orderInput.setBuy_user_ID(buyerInfo.getID());
            orderInput.setSell_ID(inputSell.getID());
            orderInput.setBuy_ID(buyID);

            // INSERT ORDERNUM
            try {
                orderInfo = orderService.createOrder(orderInput);
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {
            // 일반 판매이기 때문에 N으로 INSERT 합니다.
            inputSell.setSellProcess("N");
            //DB에 저장합니다
            try {
                // SELL INSERT
                check = sellService.addSell(inputSell);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 확인 이메일을 입찰자에게 전송합니다.
            String buyerMail = emailForm(sellerName, buyStatus, buyStatusNoti, productName, path,
                    size, user_price, comma.format(fee), "0", comma.format(totalPrice), bidDate, standardBuy,
                    tieshoeAddr, "02-123-1234", link, sellTextNoti, "수수료");

            try {
                mailHelper.sendMail(sellerEmail, sellTitle, buyerMail);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        // 현재 check변수는 업데이트가 된 변수입니다.
        /** 데이터를 조회에 view에 뿌려줍니다. */

        // 다시 업데이트 된 정보를 불러오기위해 ID를 업데이트 합니다.

        webHelper.setSession("auth", sellerInfo.getID());

        // 현재 check변수는 업데이트가 된 변수입니다.
        if (myPageID == 0) {
            // 현재 check변수는 업데이트가 된 변수입니다.
            return webHelper.redirect(contextPath + "/sellProcess?ID=" + inputSell.getID(), null);
        } else {
            // 현재 check변수는 업데이트가 된 변수입니다.
            return webHelper.redirect(contextPath + "/sellProcess?ID=" + inputSell.getID() + "&edit=1", null);
        }
    }

    @RequestMapping(value = "/buyProcess.do", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView buyProcess(Model model,
                                   @RequestParam String user_price,
                                   @RequestParam int bidDate,
                                   @RequestParam int size,
                                   @RequestParam int productNum,
                                   @RequestParam(required = false, defaultValue = "0") int myPagePrice,
                                   @RequestParam(required = false, defaultValue = "0") int myPageID) {

        int ID = (Integer) webHelper.getSession("loginID");

        // 이 값이 존재하지 않는다면 구매가 불가능하므로 반드시 필수값으로 처리해야 한다.
        if (ID == 0) {
            return webHelper.redirect("/error", null);
        }

        // 가격정보를 INTEGER 타입으로 변환합니다.
        int userInput = Integer.parseInt(user_price.replace(",", ""));
        int checkPrice = 5000; // 제품 판정비용 (고정값)
        int shipping = 3000; // 배송비 (고정값)
        int check = 0; //체크를 확인하기 위해 빈 값을 지정
        int instanceBuy = 0; // 즉시 판매가격
        int couponCheck = 0; //쿠폰 존재여부 확인
        int seller = 0; // 만약 판매자가 존재한다면 판매자의 ID를 저장할 변수
        int sellID = 0; // 만약 판매자가 존재한다면 판매 번호를 가져옵니다.'
        String sellerEndDate = null; // 만약 판매자가 존재한다면 판매자가 배송해야하는 날자를 저장할 변수
        String buyerEndDate = null; // 바로구매를 했을경우 입금 날자를 저장할 변수
        String standardBuy = null; //입찰자 입찰 기간
        String order_Number_element = null; // 주문번호 조합을 위한 VALUE
        String order_Number_element2 = null; // 주문번호 조합을 위한 VALUE
        int orderInfo = 0; // 바로 구매가격이 입력이된다면 주문건을 생성시킵니다.

        /** 벡엔드 체크를 시작합니다. (validating again) */
        complexBeans inputNum = new complexBeans();
        couponBeans inputCoupon = new couponBeans();
        buyBeans inputBuy = new buyBeans();
        sellBeans inputSell = new sellBeans();
        productBeans inputProduct = new productBeans();
        userBeans sellerInput = new userBeans();
        userBeans buyerInput = new userBeans();
        orderBeans orderInput = new orderBeans();

        complexBeans instanceBuyBeans = null; //바로 구매가격 (Uninitialized)
        couponBeans coupon = null; //쿠폰존재 여부 확인
        productBeans product = null; // 현재 상품의 정보를 가져옵니다.
        userBeans sellerInfo = null; // 판매자의 정보를 가져옵니다.
        userBeans buyerInfo = null; // 입찰자의 정보를 가져옵니다.
        sellBeans sellInfo = null; // 해당 ID의 buy정보를 가져옵니다.


        inputProduct.setID(productNum);

        // 해당 상품의 정보를 가져옵니다.
        try {
            product = productService.getProduct(inputProduct);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /** 입력된 가격의 유효성을 체크합니다. */

        inputNum.setProductNum(productNum);
        inputNum.setSize(size);

        try {
            instanceBuyBeans = buyService.lowPrice(inputNum);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (instanceBuyBeans != null) {
            instanceBuy = instanceBuyBeans.getBuyLowPrice(); //바로 구매가격 (Initialized)
            seller = instanceBuyBeans.getUser_ID(); //판매자의 ID
            sellID = instanceBuyBeans.getID();

            //판매자가 있다면 판매자의 상품을 받아야하는 날짜를 가져옵니다 (7일)
            Calendar cal = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            Calendar orderNumEle = Calendar.getInstance();

            cal.setTime(new Date());
            cal2.setTime(new Date());
            orderNumEle.setTime(new Date());
            cal.add(Calendar.DATE, 7);
            cal2.add(Calendar.DATE, 3);

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat order_df = new SimpleDateFormat("yyyyMMdd");
            DateFormat order_df_second = new SimpleDateFormat("HHmmss");

            sellerEndDate = df.format(cal.getTime()); // 현재 시간에서 7일을 더한 값 (상품을 받아야 하는 LIMIT)
            buyerEndDate = df.format(cal2.getTime()); // 현재 시간에서 3일을 더한 값 (입금을 받아야 하는 LIMIT)
            order_Number_element = order_df.format(orderNumEle.getTime()); //현재시간을 다른 포맷으로 저장(orderNumber을 생성하기 위한 ELEMENT)
            order_Number_element2 = order_df_second.format(orderNumEle.getTime());

        } else {
            instanceBuy = 0; //데이터가 없다면 0으로 설정합니다
        }

        // 만약 판매 내역이 없다면
        // 만약 입력된 값이 바로 구매가격보다 높다면 에러페이지를 내보냅니다.
        if (instanceBuy < 1000) {
            instanceBuy = 0;
        } else if (userInput > instanceBuy) {
            //에러가 났다면 FrontEnd 에러이므로 로그를 찍습니다.
            log.debug(String.format("[Error] InstantBuy: %d, UserInput: u%d The result has been modified", instanceBuy, userInput));
            return webHelper.redirect(contextPath + "/error", null);
        }

        /** 쿠폰을 확인해 있다면 배송비를 절감하고 없다면 3000원을 추가합니다.*/
        // 해당 아이디의 쿠폰을 확인하기위해 userID를 빈즈에 저장합니다.
        inputCoupon.setUser_ID((Integer) webHelper.getSession("loginID"));

        try {
            coupon = userService.checkCoupon2(inputCoupon);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (coupon != null) {
            //쿠폰 아이디를 가져옵니다.
            couponCheck = coupon.getID();
        }

        // 쿠폰이 하나 이상이라면 (즉, 쿠폰이 있다면) 배송비용을 0으로 세팅합니다.
        if (couponCheck >= 1) {
            shipping = 0;
            try {
                check = userService.updateCoupon(inputCoupon);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /** 이메일을 발송하기 (바로입찰 가격일 경우 판매자와 구매자 동시에 보내기) */

        // 판매자와 구매자의 정보를 DB에서 불러옵니다
        sellerInput.setID(seller);
        buyerInput.setID((Integer) webHelper.getSession("loginID"));

        try {
            buyerInfo = userService.getUserItem(buyerInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 금액의 총합
        int totalPrice = userInput + shipping + checkPrice;

        // 해당 아이디로 입찰을 하기위해 userID 빈즈에 저장합니다
        // 필요한 모든 데이터를 DB에 저장합니다
        inputBuy.setUser_ID((Integer) webHelper.getSession("loginID"));
        inputBuy.setBuySize(size);
        inputBuy.setBuyEnd(bidDate);
        inputBuy.setProductNum(productNum);
        inputBuy.setBuyFee(checkPrice + shipping);
        inputBuy.setBuyPrice(userInput);
        inputBuy.setID(myPageID);
        inputSell.setID(sellID);
        inputSell.setUser_ID(seller);


        /** 이메일에 필요한 요소들 */
        String buyerEmail = buyerInfo.getUserEmail(); // 입찰자의 이메일


        // TIE SHOE HOME PAGE
        String localHost = "http://localhost:8080";
        String link = localHost + contextPath; // INDEX PAGE

        // 메일에 들어갈 String

        // 주문 정보
        String buyStatus = "입찰";
        String orderStatus = "거래";

        String buyStatusNoti = "입찰정보가 성공적으로 등록되었습니다.<br>";
        String buyOrderStatusNoti = "거래가 성공적으로 성사되었습니다.<br>3일 안에 입금이 되지 않으면 패널티가 부여되며 거래가 취소됩니다.";
        String sellOrderStatusNoti = "거래가 성공적으로 성사되었습니다.<br>7일 안에 배송되지 않으면 패널티가 부여되면 거래가 취소됩니다";

        // 이메일에 필요한 요소들을 DB에서 가져옵니다.
        String buyerName = buyerInfo.getUserName(); //입찰자 이름
        String productName = product.getProductBrandEN(); // 상품 이름
        String path = product.getProductImgPath(); //상품 이미지
        String buyerAddress = buyerInfo.getUserAddress1() + " " + buyerInfo.getUserAddress2(); // 입찰자의 주소
        String phoneNum = buyerInfo.getUserPhonenum();
        String buyTitle = "TIESHOE || 입찰이 진행중입니다.";
        String orderTitle = "TIESHOE || 거래가 진행되었습니다.";
        String buyTextNoti = "입찰마감일";
        String orderBuyTextNoti = "입금마감일";
        String orderSellTextNoti = "상품배송마감일";
        String tieshoeAddr = "서울특별시 서초구, 1303-37 서초W타워 13층";
        /** BUY BID DATE*/
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, bidDate);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        standardBuy = df.format(cal.getTime()); // 현재 시간에서 7일을 더한 값 (상품을 받아야 하는 LIMIT)

        // INT TO STRING WITH COMMA
        DecimalFormat comma = new DecimalFormat("###,###");

        // check 변수를 재사용합니다.
        check = 0;

        // 바로구매 가격일 경우 BUY에 진행중으로 바꾸어 저장합니다. 또한 판매자의 판매 상태도 Y로 업데이트합니다.
        if (userInput == instanceBuy) {

            //바로 구매이기 때문에 바로 거래성사가 되었습니다. 확인이 필요 없기 때문에 Y가 아닌 D로 업데이트 합니다.
            inputBuy.setBuyProcess("D");
            inputSell.setSellProcess("Y");


            // DB에 저장합니다.
            try {
                //판매자의 정보를 가져옵니다.
                sellerInfo = userService.getUserItem(sellerInput);
                // SELL UPDATE TO "Y"
                check = sellService.updateSell(inputSell);
                // BUY INSERT
                if (myPageID == 0) {
                    check = buyService.addBuy(inputBuy);
                } else {
                    check = buyService.updateBuyPrice(inputBuy);
                }
                // GET SELL INFO
                sellInfo = sellService.getSellInfo(inputSell);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 판매자가 존재 함으로 판매자의 이메일과 이름을 가져옵니다.
            String sellerName = sellerInfo.getUserName(); //판매자 이름
            String sellerEmail = sellerInfo.getUserEmail();

            // 판매자의 총합
            int totalSell = sellInfo.getSellPrice() + sellInfo.getSellFee();

            // 확인 이메일을 입찰자와 판매자에게 전송합니다. (바로구매)
            String buyerMail = emailForm(buyerName, orderStatus, buyOrderStatusNoti, productName, path,
                    size, user_price, comma.format(shipping), "5,000", comma.format(totalSell), 3, buyerEndDate,
                    buyerAddress, phoneNum, link, orderBuyTextNoti, "배송비");

            String sellerMail = emailForm(sellerName, orderStatus, sellOrderStatusNoti, productName, path,
                    size, user_price, "0", "5,000", comma.format(totalPrice), 7, sellerEndDate,
                    tieshoeAddr, "02-123-1234", link, orderSellTextNoti, "수수료");

            try {
                // 입찰자와 판메자에게 메일을 전송합니다.
                mailHelper.sendMail(buyerEmail, orderTitle, buyerMail);
                mailHelper.sendMail(sellerEmail, orderTitle, sellerMail);
            } catch (Exception e) {
                e.printStackTrace();
            }

            /** 주문 생성 */

            // 주문건을 생성하기 위한 변수들
            String orderNumber = order_Number_element + " - " + productNum + sellerInfo.getID() + buyerInfo.getID() + order_Number_element2;

            orderInput.setOrderProductNum(productNum);
            orderInput.setOrderNumber(orderNumber);
            orderInput.setOrderPrice(userInput);
            orderInput.setOrderSize(size);
            orderInput.setSell_user_ID(sellerInfo.getID());
            orderInput.setBuy_user_ID(buyerInfo.getID());
            orderInput.setSell_ID(sellID);
            orderInput.setBuy_ID(inputBuy.getID());

            // INSERT ORDERNUM
            try {
                orderInfo = orderService.createOrder(orderInput);
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {
            // 일반 입찰이기 때문에 N으로 INSERT 합니다.
            inputBuy.setBuyProcess("N");

            //DB에 저장합니다
            // BUY INSERT
            try {
                // BUY INSERT
                if (myPageID == 0) {
                    check = buyService.addBuy(inputBuy);
                } else {
                    check = buyService.updateBuyPrice(inputBuy);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 확인 이메일을 입찰자에게 전송합니다.
            String buyerMail = emailForm(buyerName, buyStatus, buyStatusNoti, productName, path,
                    size, user_price, comma.format(shipping), "5,000", comma.format(totalPrice), bidDate, standardBuy,
                    buyerAddress, phoneNum, link, buyTextNoti, "배송비");

            try {
                mailHelper.sendMail(buyerEmail, buyTitle, buyerMail);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        webHelper.setSession("auth", buyerInfo.getID());

        if (myPageID == 0) {
            // 현재 check변수는 업데이트가 된 변수입니다.
            return webHelper.redirect(contextPath + "/buyProcess?ID=" + inputBuy.getID(), null);
        } else {
            // 현재 check변수는 업데이트가 된 변수입니다.
            return webHelper.redirect(contextPath + "/buyProcess?ID=" + inputBuy.getID() + "&edit=1", null);
        }
    }


    @RequestMapping(value = "/product", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView getProduct(Model model, @RequestParam(required = false) int productNum,
                                   @RequestParam(value = "page", defaultValue = "1", required = false) int nowPage,    // 페이지 번호 (기본값 1)
                                   @RequestParam(value = "totalCount", defaultValue = "0", required = false) int totalCount,    // 전체 게시글수
                                   @RequestParam(value = "listCount", defaultValue = "5", required = false) int listCount,    //한 페이지당 표시할 목록수
                                   @RequestParam(value = "pageCount", defaultValue = "5", required = false) int pageCount    // 한그룹당 표시할 페이지 번호수
    ) {

        // 이 값이 존재하지 않는다면 데이터 조회가 불가능하므로 반드시 필수값으로 처리해야 한다.
        if (productNum == 0) {
            return webHelper.redirect(null, "상품정보를 가져올수 없습니다.");
        }
        int ID = 0;
        if (webHelper.getSession("loginID") != null) {
            ID = (Integer) webHelper.getSession("loginID");
        }

        // 최근 본 상품
        if (ID != 0) {
            //객체를 생성합니다
            recentCheck checkInput = new recentCheck();
            productBeans productInput = new productBeans();
            checkInput.setFirst(productNum);
            checkInput.setUser_ID(ID);
            checkInput.setFirst(productNum);

            int success = 0;

            recentCheck check = null;

            try {
                /** 조회했다면 업데이트 합니다*/
                // 조회를 먼저 합니다
                check = checkService.getCheck(checkInput);
                // 만약 조회 결과가 없다면
                if (check == null) {
                    //하나만 삽입합니다
                    success = checkService.insert(checkInput);
                } else {
                    //만약 두번쨰 항목을 다시 본다면 첫번째와 두번쨰의 위치를 바꾼다
                    if (check.getSecond() == productNum) {
                        checkInput.setSecond(check.getFirst());
                        checkInput.setThird(check.getThird());
                        success = checkService.update(checkInput);
                    } else if (check.getFirst() == productNum) {
                        //Do nothing
                    } else {
                        //3번째가 최근으로 온다고 해도 두개만 밀어줍니다
                        //아니라면 최근 두개만 가져와 밀어줍니다
                        checkInput.setSecond(check.getFirst());
                        checkInput.setThird(check.getSecond());
                        success = checkService.update(checkInput);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /** 2) 데이터 조회하기 */
        // 데이터 조회에 필요한 조건값을 Beans에 저장하기
        productBeans productInput = new productBeans();
        sellBeans sellInput = new sellBeans();
        buyBeans buyInput = new buyBeans();
        orderBeans orderInput = new orderBeans();
        reviewBeans reviewInput = new reviewBeans();

        productInput.setID(productNum);
        sellInput.setProductNum(productNum);
        buyInput.setProductNum(productNum);
        orderInput.setOrderProductNum(productNum);
        reviewInput.setProduct_ID(productNum);

        // 조회결과를 저장할 객체 선언
        productBeans productOutput = null;
        orderBeans lowestPrice = null;
        orderBeans highestPrice = null;
        sellBeans sellAllLowPrice260 = null;
        sellBeans sellAllLowPrice265 = null;
        sellBeans sellAllLowPrice270 = null;
        buyBeans buyAllHighPrice260 = null;
        buyBeans buyAllHighPrice265 = null;
        buyBeans buyAllHighPrice270 = null;
        orderBeans recent260 = null;
        orderBeans recent265 = null;
        orderBeans recent270 = null;

        List<reviewBeans> reviewOutput = null; // 조회결과가 저장될 객체
        PageData pageData = null;       // 페이지 번호를 계산한 결과가 저장될 객체


        try {
            lowestPrice = productService.getRecentLow(orderInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            highestPrice = productService.getRecentHigh(orderInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            sellAllLowPrice260 = sellService.getLowPrice260(sellInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            sellAllLowPrice265 = sellService.getLowPrice265(sellInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            sellAllLowPrice270 = sellService.getLowPrice270(sellInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            buyAllHighPrice260 = buyService.getHighPrice260(buyInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            buyAllHighPrice265 = buyService.getHighPrice265(buyInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            buyAllHighPrice270 = buyService.getHighPrice270(buyInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            recent260 = productService.getRecent260(orderInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            recent265 = productService.getRecent265(orderInput);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            recent270 = productService.getRecent270(orderInput);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            // 데이터 조회
            productOutput = productService.getProduct(productInput);
        } catch (Exception e) {
            return webHelper.redirect(null, e.getLocalizedMessage());
        }

        try {
            // 전체 게시글 수 조회
            totalCount = reviewService.getReviewProductCount(reviewInput);
            // 페이지 번호 계산 --> 계산결과를 로그로 출력될 것이다.
            pageData = new PageData(nowPage, totalCount, listCount, pageCount);

            // SQL의 LIMIT절에서 사용될 값을 Beans의 static 변수에 저장
            reviewBeans.setOffset(pageData.getOffset());
            reviewBeans.setListCount(pageData.getListCount());

            // 데이터 조회하기
            reviewOutput = reviewService.getReviewProductList(reviewInput);
        } catch (Exception e) {
            return webHelper.redirect(null, e.getLocalizedMessage());
        }


        /** 3) View 처리 */
        model.addAttribute("productOutput", productOutput);
        model.addAttribute("sellLowPrice260", sellAllLowPrice260);
        model.addAttribute("sellLowPrice265", sellAllLowPrice265);
        model.addAttribute("sellLowPrice270", sellAllLowPrice270);
        model.addAttribute("buyHighPrice260", buyAllHighPrice260);
        model.addAttribute("buyHighPrice265", buyAllHighPrice265);
        model.addAttribute("buyHighPrice270", buyAllHighPrice270);
        model.addAttribute("recent260", recent260);
        model.addAttribute("recent265", recent265);
        model.addAttribute("recent270", recent270);
        model.addAttribute("highestPrice", highestPrice);
        model.addAttribute("lowestPrice", lowestPrice);
        model.addAttribute("reviewOPT", reviewOutput);
        model.addAttribute("pageData", pageData);

        return new ModelAndView("product/product");
    }


    public String emailForm(String name, String status, String statusInfo, String productName, String img,
                            int size, String bidPrice, String shipping, String test, String total, int end, String date, String
                                    address, String phoneNum, String link, String endStatus, String shippingName) {
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
                "                                <div style=\"display:inline-block;color:#e72525\">" + status + "</div>\n" +
                "                                <div style=\"display:inline-block\">해주셔서 감사합니다.</div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <div style=\"color:#a3a3a3;font-size:11px;line-height:15px;text-align:left\">\n" +
                "                            <div>\n" +
                "                                <div style=\"display:inline-block\">" + statusInfo + "</div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <div style=\"margin-top:50px\">\n" +
                "                            <div style=\"display:grid;margin-bottom:20px\">\n" +
                "                                <div id=\"m_-8527723984461195389w-node-7f9f85ff6c2f-70d2219d\"\n" +
                "                                     style=\"margin-bottom:32px;padding-bottom:9px;border-bottom:2px solid #000;color:#000;font-size:15px;line-height:17px;font-weight:700;text-align:left\">\n" +
                "                                    " + status + "현황\n" +
                "                                </div>\n" +
                "                                <div style=\"margin-bottom:12px;font-family:Roboto,sans-serif;color:#000;font-size:13px;line-height:16px;text-align:left\">\n" +
                "                                   " + productName + "\n" +
                "                                </div>\n" +
                "                                <div>\n" +
                "                                    <img src=\"" + img + "\"\n" +
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
                "                                <div style=\"display:grid\">\n" +
                "                                    <div id=\"m_-8527723984461195389w-node-744936b24541-70d2219d\"\n" +
                "                                         style=\"margin-top:6.5px;margin-bottom:6.5px;margin-left:10px;color:#000;font-size:11px;line-height:12px;text-align:left\">\n" +
                "                                        " + status + "가\n" +
                "                                    </div>\n" +
                "                                    <div id=\"m_-8527723984461195389w-node-39d406fe7668-70d2219d\"\n" +
                "                                         style=\"height:25px;text-align:right\">\n" +
                "                                        <div style=\"display:inline-block;margin:6.5px 4px 6.5px 10px;color:#999;font-size:13px;line-height:15px;text-align:left\">\n" +
                "                                            ₩\n" +
                "                                        </div>\n" +
                "                                        <div style=\"display:inline-block;margin-top:6.5px;margin-right:10px;margin-bottom:6.5px;font-size:13px;line-height:15px;font-weight:700;text-align:right\">\n" +
                "                                            " + bidPrice + "\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <div style=\"display:grid\">\n" +
                "                                    <div id=\"m_-8527723984461195389w-node-5e6cd636b6f5-70d2219d\"\n" +
                "                                         style=\"margin-top:6.5px;margin-bottom:6.5px;margin-left:10px;color:#000;font-size:11px;line-height:12px;text-align:left\">\n" +
                "                                        " + shippingName + "\n" +
                "                                    </div>\n" +
                "                                    <div id=\"m_-8527723984461195389w-node-5e6cd636b6f7-70d2219d\"\n" +
                "                                         style=\"height:25px;text-align:right\">\n" +
                "                                        <div style=\"display:inline-block;margin:6.5px 4px 6.5px 10px;color:#999;font-size:13px;line-height:15px;text-align:left\">\n" +
                "                                            ₩\n" +
                "                                        </div>\n" +
                "                                        <div style=\"display:inline-block;margin-top:6.5px;margin-right:10px;margin-bottom:6.5px;font-size:13px;line-height:15px;font-weight:700;text-align:right\">\n" +
                "                                            " + shipping + "\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                                <div style=\"display:grid\">\n" +
                "                                    <div id=\"m_-8527723984461195389w-node-76fbfc4b0309-70d2219d\"\n" +
                "                                         style=\"margin-top:6.5px;margin-bottom:6.5px;margin-left:10px;color:#000;font-size:11px;line-height:12px;text-align:left\">\n" +
                "                                        정품판정비\n" +
                "                                    </div>\n" +
                "                                    <div id=\"m_-8527723984461195389w-node-76fbfc4b030b-70d2219d\"\n" +
                "                                         style=\"height:25px;text-align:right\">\n" +
                "                                        <div style=\"display:inline-block;margin-top:6.5px;margin-right:10px;margin-bottom:6.5px;font-size:13px;line-height:15px;font-weight:700;text-align:right\">\n" +
                "                                            " + test + "\n" +
                "                                        </div>\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                            <div style=\"display:grid;padding-bottom:10px;border-bottom:1px solid #000\">\n" +
                "                                <div id=\"m_-8527723984461195389w-node-21ffee2f2826-70d2219d\"\n" +
                "                                     style=\"margin-top:6.5px;margin-bottom:6.5px;margin-left:10px;font-size:15px;line-height:17px;font-weight:700;text-align:left\">\n" +
                "                                    합계\n" +
                "                                </div>\n" +
                "                                <div id=\"m_-8527723984461195389w-node-21ffee2f2828-70d2219d\"\n" +
                "                                     style=\"height:25px;text-align:right\">\n" +
                "                                    <div style=\"display:inline-block;margin:6.5px 4px 6.5px 10px;color:#000;font-size:14px;line-height:16px;text-align:left\">\n" +
                "                                        ₩\n" +
                "                                    </div>\n" +
                "                                    <div style=\"display:inline-block;margin-top:6.5px;margin-right:10px;margin-bottom:6.5px;font-size:20px;line-height:23px;font-weight:700;text-align:right;color:#e72525\">\n" +
                "                                        " + total + "\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                            <div style=\"display:grid;margin-top:27.5px;padding-bottom:7.5px;border-bottom:1px solid #e8e8e8\">\n" +
                "                                <div id=\"m_-8527723984461195389w-node-a0710a0c4c4d-70d2219d\"\n" +
                "                                     style=\"margin-top:6.5px;margin-bottom:6.5px;margin-left:10px;color:#000;font-size:11px;line-height:12px;text-align:left;font-weight:700\">\n" +
                "                                    " + endStatus + "\n" +
                "                                </div>\n" +
                "                                <div id=\"m_-8527723984461195389w-node-a0710a0c4c4f-70d2219d\"\n" +
                "                                     style=\"height:25px;text-align:right\">\n" +
                "                                    <div style=\"display:inline-block;margin-top:6.5px;margin-right:10px;margin-bottom:6.5px;font-size:13px;line-height:15px;text-align:right\">\n" +
                "                                        " + date + "일 (" + date + ")\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                            <div style=\"display:grid;margin-top:17px;margin-bottom:8px;padding-bottom:7px\">\n" +
                "                                <div id=\"m_-8527723984461195389w-node-42c7e44492d9-70d2219d\"\n" +
                "                                     style=\"margin-top:6.5px;margin-bottom:6.5px;margin-left:10px;color:#000;font-size:11px;line-height:12px;text-align:left;font-weight:700\">\n" +
                "                                    배송\n" +
                "                                </div>\n" +
                "                                <div id=\"m_-8527723984461195389w-node-42c7e44492db-70d2219d\" style=\"text-align:left\">\n" +
                "                                    <div style=\"display:inline-block;margin-top:6.5px;margin-right:10px;margin-bottom:6.5px;font-size:15px;line-height:17px\">\n" +
                "                                        " + address + "\n" +
                "                                    </div>\n" +
                "                                    <div style=\"display:inline-block;margin-top:2.5px;margin-right:10px;margin-bottom:6.5px;color:#666;font-size:13px;line-height:15px\">\n" +
                "                                        " + name + " / " + phoneNum + " /\n" +
                "                                    </div>\n" +
                "                                </div>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <a href=\"" + link + "\"\n" +
                "                           style=\"width:100%;margin-top:45px;padding-top:12px;padding-bottom:12px;border-radius:4px;background-color:#000;font-size:13px;line-height:15px;text-decoration:none;color:white;display:inline-block;border:0\"\n" +
                "                           target=\"_blank\"\n" +
                "                           data-saferedirecturl=\"https://www.google.com/url?q=https://xxblue.com&amp;source=gmail&amp;ust=1584163709966000&amp;usg=AFQjCNFwsizl76Ka36Oy6MIQN_x1TeD8fg\"><span\n" +
                "                                class=\"il\">TIE</span>&nbsp;<span class=\"il\">SHOE</span> 바로가기</a></div>\n" +
                "                </div>\n" +
                "                <div style=\"font-family:'Nanum Gothic',sans-serif\">\n" +
                "                    <div style=\"width:100%;min-height:100px;margin-right:auto;margin-left:auto;padding:26px 12px 20px;background-color:#f5f5f5;font-family:'Nanum Gothic',sans-serif\">\n" +
                "                        <p style=\"color:#999;font-size:9px;line-height:14px\">본 메일은 발신전용메일이며 문의에 대한 회신이 되지 않습니다. <br>궁금한점은\n" +
                "                            고객센터로 문의해 주세요.</p>\n" +
                "\n" +
                "                        <p style=\"color:#999;font-size:9px;line-height:14px\">서울특별시 서초구, 1303-37 서초W타워 13층 &nbsp;| &nbsp;대표이사\n" +
                "                            홍길동<br>사업자 등록번호 123-45-67890 &nbsp; | &nbsp;통신판매업 신고 2019-서울강남-03088<br>문의전화 02-123-4567\n" +
                "                            &nbsp;| &nbsp;개인정보관리책임: 홍길동<br></p>\n" +
                "                        <div class=\"yj6qo\"></div>\n" +
                "                        <div class=\"adL\">\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                    <div class=\"adL\">\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                <div class=\"adL\">\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"adL\">\n" +
                "\n" +
                "\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div id=\":r1\" class=\"ii gt\" style=\"display:none\">\n" +
                "        <div id=\":r0\" class=\"a3s aXjCH undefined\"></div>\n" +
                "    </div>\n" +
                "    <div class=\"hi\"></div>\n" +
                "</div>";
    }

}


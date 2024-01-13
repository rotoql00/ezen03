## B2C 전자정부 표준프레임워크 팀 프로젝트 포트폴리오



#### 포트폴리오 웹페이지  
 항목 | 내용 
|----|----|
|ngrok 서버|https://3eea-122-41-53-2.ngrok-free.app|
<!--|localtunnel 서버|https://plain-onions-glow.loca.lt/|-->



#### 포트폴리오 테스트 평가용 user  
 항목 | 내용 
|----|----|
|관리인 아이디|admin1, admin2, admin3 |
|일반 유저 아이디|(테스트아이디)rotoql (이외 신규가입 테스트 환영)|
|회원가입 및 활동 가능|통합 PW : 123456|


![web_login_erpPage](https://github.com/rotoql00/ezen03/assets/56528619/61363a99-b1f8-49e3-b5e8-93d5a5d37111)


### Resource information
   1000commit 달성!
    <resources mapping="/assets/**" location="/WEB-INF/views/assets/"/>
    <resources mapping="/admin/**" location="/WEB-INF/views/admin/"/>
    <resources mapping="/plugin/**" location="/WEB-INF/views/plugin/"/>
    
    
### Mapping information

#### Home Controller  
 @RequestMapping | location 
|----|----|
|/|index.jsp|
|/detailSearch|detailSearch.jsp|


#### Account Controller
 @RequestMapping | location
|----|----|
|/dropUser|account/dropUser.jsp|
|/join|account/join.jsp|
|/joinConfirm|account/join_ok.jsp|
|/login|account/login.jsp|
|/survey|account/survey.jsp|

#### Admin Controller
 @RequestMapping | location
|----|----|
|/admin|admin/admin.jsp|
|/admin/addProduct|admin/add_product/add_product.jsp|
|/admin/announcement|admin/announcement/announcement.jsp|
|/admin/buyStatus|admin/buy_status/buyStatus.jsp|
|/admin/manageKeyword|admin/manage_keyword/manage_keyword.jsp|
|/admin/manageOrder|admin/manage_order/manage_order.jsp|
|/admin/manageTransaction|admin/manage_transaction/manage_transaction.jsp|
|/admin/manageUsers|admin/manage_users/user_manage.jsp|
|/admin/sellStatus|admin/sell_status/sell_status.jsp|
|/admin/sendMail|admin/send_mail/send_mail.jsp|
|/admin/sendMailTest|admin/send_mail/send_mail_test.jsp|

#### Mypage Controller
 @RequestMapping | location 
|----|----|
|myPage|mypage/mypage.jsp|
|myPage/sell|myPage/myPage_sell.jsp|
|myPage/buy|myPage/myPage_buy.jsp|

#### Product
 @RequestMapping | location 
|----|----|
|/buy|product/buy.jsp|
|/buyProcess|product/buyProcess.jsp|
|/product|product/product.jsp|
|/sell|product/sell.jsp|
|/sellProcess|product/sellProcess.jsp|

#### Board
 @RequestMapping | location 
|----|----|
|/board|board/board.jsp|
|/faq|board/faq.jsp|
|/userGuide|board/userGuide.jsp|

#### Header&Footer (include)
    <%@ include file="/WEB-INF/views/inc/header.jsp" %>
    <%@ include file="/WEB-INF/views/inc/footer.jsp" %>
    
#### Servlet-context (resource)
        <resources mapping="/assets/**" location="/WEB-INF/views/assets/"/>
        <resources mapping="/admin/**" location="/WEB-INF/views/admin/"/>



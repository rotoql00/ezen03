## EZEN03
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


작업 마무리 => 리눅스 이전 작업중

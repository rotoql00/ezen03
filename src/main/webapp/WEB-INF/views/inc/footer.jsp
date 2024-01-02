<!--
FileName: footer.jsp
Description: footer파일이며 상호명과 SNS등등 info를 가지고 있습니다.
Author: 임석현
Additional Info: INCLUDE only
-->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>

<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/footer.css">
<!-- UItool을 사용하지 않았습니다 -->
<footer>
    <div class="introduction">
        <!-- 가장 왼쪽에 있는 info 입니다.-->
        <div class="logo_footer">
            TIE SHOE
        </div>
        <div class="information">
            <ul>
                <li>상호: 타이 슈</li>
                <li>대표이사: 임채린, 최성준, 임석현</li>
                <li>사업자등록번호:123-45-67890</li>
                <li> 통신판매업신고:2019-서울강남-12345</li>
                <li class="info_address">서울특별시 서초구,1303-37
                    <br> 서초W타워 13층
                </li>
            </ul>
        </div>
    </div>

    <!-- 오른쪽에 있는 info들을 배치하였습니다-->
    <div class="footer_info">
        <!-- Guide Menu와 help 메뉴가 있는 wrapper입니다.-->
        <div class="first_footer">
            <div class="guide_menu">
                <div class="title">GUIDE MENU</div>
                <ol>
                    <li><a href="<%=request.getContextPath()%>/board">공지사항</a></li>
                    <li><a href="<%=request.getContextPath()%>/faq">FAQ</a></li>
                    <li><a href="<%=request.getContextPath()%>/userGuide">이용안내</a></li>
                </ol>
            </div>
            <div class="help">
                <div class="title">HELP</div>
                <p>tieshoe@gmail.com</p>
                <p>02-123-4567</p>
                <p>평일 11:00~5:00 <br>
                    점심 12:00~1:00 <br>
                    토/일,공휴일 휴무</p>
            </div>
        </div>
    </div>

    <!-- SNS에 대한 두번째 메뉴 wrapper입니다.-->
    <div class="second_footer">
        <div class="sns_footer">
            <div class="title">SNS</div>
            <span><i class="fab fa-github"></i></span>
            <span><i class="fab fa-facebook"></i></span>
            <span><i class="fab fa-google-plus"></i></span>
        </div>
    </div>
</footer>

<script src="https://kit.fontawesome.com/dcf1d9fa0c.js" crossorigin="anonymous"></script>
<script src="<%=request.getContextPath()%>/assets/js/FrontConsole.js"></script>
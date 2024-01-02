<!--
@filename : closeTab.jsp
@description :소셜로그인을 할떄 창을 닫고 부모창을 제어하는 창입니다
@author : 임석현
-->

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="<%=request.getContextPath()%>/assets/img/tieshoe.png">
</head>

<body>
<script>
    let loginType = "${param.loginType}";

    if (${param.approve}) {
        opener.document.location.href = "<%=request.getContextPath()%>/login_ok?userId=${param.ID}&userPw=${param.check}"; // 부모의 창을 해당 '주소'로 바꿈
        window.close();                                    // 현재 창 닫기
    } else {
        if (loginType === "naver") {
            opener.document.location.href = "<%=request.getContextPath()%>/naverRegister"; // 부모의 창을 해당 '주소'로 바꿈 (네이버)
        } else {
            opener.document.location.href = "<%=request.getContextPath()%>/kakaoRegister"; // 부모의 창을 해당 '주소'로 바꿈 (카카오)
        }
        window.close();                                    // 현재 창 닫기
    }
</script>

</body>
</html>
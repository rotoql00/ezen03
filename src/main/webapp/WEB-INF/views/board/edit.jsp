<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Hello JSP</title>
</head>
<body>
    <h1>공지수정</h1>
    <form method="post" action="${pageContext.request.contextPath}/board/edit_ok">
        <% /* action 페이지에서 사용할 WHERE 조건값을 hidden 필드로 숨겨서 전송한다. */ %>
        <input type="hidden" name="ID" value="${output.ID}" />
        <div>
            <label for="boardTitle">공지 제목: </label>
            <input type="text" name="boardTitle" id="boardTitle" value="${output.boardTitle}" />
        </div>
        <div>
            <label for="boardContext">공지 내용: </label>
            <input type="text" name="boardContext" id="boardContext" value="${output.boardContext}" />
        </div>
        <hr />
        <button type="submit">저장하기</button>
        <button type="reset">다시작성</button>
    </form>
</body>
</html>
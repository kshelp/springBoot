<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<body>

	<div class="container">
		<table class="table table-hover table table-striped">
			<tr>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
			</tr>

			<c:forEach items="${list}" var="news">
				<tr>
					<td>${news.getTitle()}</td>
					<td>${news.journalist}</td>
					<td>${news.publisher}</td>
					<td>${news.reg_dt}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
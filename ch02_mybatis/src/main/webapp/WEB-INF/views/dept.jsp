<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<body>

	<div class="container">
		<table class="table table-hover table table-striped">
			<tr>
				<th>deptno</th>
				<th>dname</th>
				<th>loc</th>
			</tr>

			<c:forEach items="${list}" var="dept">
				<tr>
					<td>${dept.deptno}</td>
					<td>${dept.dname}</td>
					<td>${dept.loc}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
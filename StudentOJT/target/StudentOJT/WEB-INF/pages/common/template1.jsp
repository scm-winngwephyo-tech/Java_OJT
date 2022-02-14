<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <jsp:useBean id="current" scope="session" class="java.util.Date" />
    <meta charset="utf-8">
    <title><tiles:getAsString name="title" /></title>
  </head>
  <body>
    <div class="content">
      <tiles:insertAttribute name="body" />
    </div>
  </body>
  <tiles:insertAttribute name="javascript" ignore="true" />
</html>
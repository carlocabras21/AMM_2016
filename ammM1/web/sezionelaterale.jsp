<%-- 
    Document   : sezionelaterale
    Created on : 24-apr-2016, 10.52.12
    Author     : Carlo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<aside class="sezioneLateraleSinistra">
    <c:choose>
        <c:when test="${sessionScope['loggedIn'] == null}">
            <div class="content">
                    <p><a href="login.html">Login</a></p>
            </div>
        </c:when>
        <c:when test="${sessionScope['loggedIn'] == false}">
            <div class="content">
                    <p><a href="login.html">Login</a></p>
            </div>
        </c:when>
        <c:otherwise>
            <div class="content">
                Benvenuto, ${sessionScope['nome']}
            </div>
        </c:otherwise>
    </c:choose>
</aside>

<aside class="sezioneLateraleDestra">
    <h2>Link utili</h2>
    <p><a href="http://www.tennispro.it/conseils-techniques" target="_blank">Come scegliere la racchetta</a></p>
    <p><a href="http://www.focus.it/cultura/curiosita/quali-differenze-ci-sono-tra-i-terreni-dei-campi-da-tennis"  target="_blank">Differenze superfici campi</a></p>
</aside>

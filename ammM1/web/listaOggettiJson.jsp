<%-- 
    Document   : listaOggettiJson
    Created on : 8-giu-2016, 15.22.12
    Author     : Carlo
--%>


<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<json:array>
    <c:forEach var="oggetto" items="${listaOggetti}">
        <json:object>            
            <json:property name="id" value="${oggetto.getId()}"/>
            <json:property name="nome" value="${oggetto.getNome()}"/>
            <json:property name="decrizione" value="${oggetto.getDescrizione()}"/>
            <json:property name="urlimg" value="${oggetto.getUrlImmagine()}"/>
            <json:property name="disponibile" value="${oggetto.getQuantitaDisponibile()}"/>            
            <json:property name="prezzo" value="${oggetto.getPrezzo()}"/>
        </json:object>
    </c:forEach>
</json:array>

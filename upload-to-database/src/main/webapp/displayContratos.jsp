
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import= "java.sql.Connection"%>
<%@ page import= "dev.aadc.connection.ConnectionFactory"%>

<%@ page import= "dev.aadc.dao.ContratosDao"%>
<%@ page import= "dev.aadc.model.Contratos"%>

<%@ page import= "java.util.List"%>
<%@ page import= "java.util.ArrayList"%>

<%@ page import="javax.servlet.*, javax.servlet.http.*" %>


<!DOCTYPE html>

<html lang="en">

    <head>
        <meta charset="UTF-8">
        <title> Visualize os documentos: </title>

        <link rel="stylesheet" href="styles.css">
    </head>

    <body>

        <div> <a href="index.jsp" class="botao-voltar"> VOLTAR </a> </div>

        <h1> Visualize os arquivos abaixo: </h1>

        <table>
            <tr>
                <th> ID </th>
                <th> DOCUMENTO </th>
                <th> LINK PARA OUTRA ABA </th>
                <th> EMBED </th>
            </tr>

            <c:forEach var="elemento" items="${cont}">

               <tr>
                  <td> ${elemento.getIdContrato()} </td>
                  <td> ${elemento.getContratoFileName()} </td>
                  <td> <a href="${pageContext.request.contextPath}/uploaded-files/${elemento.getContratoFileName()}" target="_blank"> ver PDF em outra aba </a> </td>
                  <td> <embed src="${pageContext.request.contextPath}/uploaded-files/${elemento.getContratoFileName()}" type="application/pdf" width="600" height="400"> </td>
               </tr>

            </c:forEach>

        </table>

    </body>

</html>
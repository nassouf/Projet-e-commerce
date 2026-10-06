<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head><title>Client</title></head>
<style>
  body{font-family:Arial,sans-serif;background:#f6f8fb;color:#333;padding:24px}
  h1,h2,h3{color:#0078d4}
  .container{max-width:900px;margin:0 auto;background:#fff;padding:24px;border-radius:8px;box-shadow:0 4px 16px rgba(0,0,0,.08)}
  table{width:100%;border-collapse:collapse;margin-top:12px}
  th{background:#0078d4;color:#fff;padding:8px}
  td{padding:8px;border-bottom:1px solid #eee}
  tr:hover td{background:#f0f6ff}
  input,select{padding:8px;border:1px solid #d7dbe0;border-radius:6px;width:100%;box-sizing:border-box}
  button{background:#0078d4;color:#fff;border:none;padding:8px 14px;border-radius:6px;cursor:pointer;font-weight:600}
  button:hover{background:#005fa3}
  form p{margin:10px 0}
  label{display:block;margin-bottom:4px;font-weight:600}
  a{color:#0078d4;text-decoration:none}
  a:hover{text-decoration:underline}
  .error{color:red;font-weight:600}
  .tag-dispo{color:green;font-weight:600}
  .tag-vendu{color:gray;font-weight:600}
  hr{border:none;border-top:1px solid #eee;margin:20px 0}
</style>
<body>

<h1>Produits disponibles</h1>

<c:choose>
    <c:when test="${empty produits}">
        <p>Aucun produit disponible pour le moment.</p>
    </c:when>
    <c:otherwise>
        <table border="1">
            <tr>
                <th>#</th>
                <th>Nom</th>
                <th>Description</th>
                <th>Prix minimum</th>
                <th>État</th>
            </tr>
            <c:forEach var="p" items="${produits}" varStatus="s">
                <tr>
                    <td>${s.index + 1}</td>
                    <td><c:out value="${p.nom}"/></td>
                    <td><c:out value="${p.description}"/></td>
                    <td><c:out value="${p.prixMin}"/> DT</td>
                    <td><c:out value="${p.etat}"/></td>
                    
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
<div>
<h1>Formulaire d'encher</h1>
<form action="${pageContext.request.contextPath}/Enchere" method="post">
                <label>Id Produit:<input type="text" name="idProduit" step="0.01" required style="width:100px" /></label>
                <label>Montant :
                  <input type="number" name="montant"  step="0.01" required style="width:100px"/>
                </label>
                <input type="hidden" name="idClient" value="${client.id}"/>
                <button type="submit">Enchérir</button>
                <c:if test="${not empty msg}">
    			<p style="color:red">${msg}</p>
</c:if>
 </form>
 </div>
              

<br/>
<a href="${pageContext.request.contextPath}/Conn">Se déconnecter</a>

</body>
</html>
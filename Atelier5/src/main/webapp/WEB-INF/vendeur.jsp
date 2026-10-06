<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vendeur</title>

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
</head>

<body>

<h1>Mes produits</h1>

<h2>Ajouter un produit</h2>
<form action="${pageContext.request.contextPath}/AjouterProduit" method="post">
    <label>Nom : 
    <input type="text" name="nom" required/>
    </label><br/>
    <label>Description : 
    <input type="text" name="description"/>
    </label><br/>
    <label>Prix minimum : 
    <input type="number" name="prixMin" min="0" step="0.01" required/>
    </label><br/>
    <input type="hidden" name="idVendeur" value="${vendeur.id}"/>
    <button type="submit">Ajouter</button>
</form>

<hr/>

<h2>Historique de mes produits</h2>

<c:choose>
    <c:when test="${empty produits}">
        <p>Vous n'avez aucun produit.</p>
    </c:when>
    <c:otherwise>
        <c:forEach var="p" items="${produits}" varStatus="s">
            <h3>${s.index + 1}. <c:out value="${p.nom}"/> — État : <c:out value="${p.etat}"/></h3>
            <p>Description : <c:out value="${p.description}"/></p>
            <p>Prix minimum : <c:out value="${p.prixMin}"/> DT</p>

            <c:choose>
                <c:when test="${empty p.offres}">
                    <p>Aucune enchère pour ce produit.</p>
                </c:when>
                <c:otherwise>
                    <table border="1">
                        <tr>
                            <th>#</th>
                            <th>ID Acheteur</th>
                            <th>Montant</th>
                        </tr>
                        <c:forEach var="o" items="${p.offres}" varStatus="os">
                            <tr>
                            	<c:choose>
                					<c:when test="${not empty p.meilleureVente && o.id == p.meilleureVente.id}">
                    				
                    						<td style="color:green">${os.index + 1}</td>
                    						<td > ${o.idAcheteur} Gagnant</td>
                    						<td >${o.montant} DT</td>
                					</c:when>
                					<c:otherwise>
                   								 <td>${os.index + 1}</td>
                   								 <td>${o.idAcheteur}</td>
                   								 <td>${o.montant} DT</td>
                					</c:otherwise>
            					</c:choose>
                            </tr>
                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>
			<c:if test="${p.etat == 'DISPONIBLE' && !empty p.offres}">
            <form action="${pageContext.request.contextPath}/Vendre" method="post" style="margin-top:10px">
              <input type="hidden" name="idProduit" value="${p.id}"/>
              <button type="submit">Vendre</button>
              <c:if test="${not empty msg}">
    				<p style="color:green;"> ${msg}</p>
				</c:if>
            </form>
          </c:if>
            

            <hr/>
        </c:forEach>
    </c:otherwise>
</c:choose>

<br/>
<a href="${pageContext.request.contextPath}/Conn">Se déconnecter</a>

</body>
</html>
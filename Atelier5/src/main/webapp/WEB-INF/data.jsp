<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Test BaseDonne</title>
</head>
<body>

<h1>Test de la BaseDonne</h1>

<c:choose>
    <c:when test="${empty requestScope.bd}">
        <p><strong>Erreur :</strong> Aucune BaseDonne trouvée dans le request scope.</p>
    </c:when>

    <c:otherwise>

        <!-- ===== UTILISATEURS ===== -->
        <h2>Utilisateurs
            (total : <c:out value="${fn:length(requestScope.bd.utilisateurs)}"/>)
        </h2>

        <c:choose>
            <c:when test="${empty requestScope.bd.utilisateurs}">
                <p>Aucun utilisateur enregistré.</p>
            </c:when>
            <c:otherwise>
                <table border="1" cellpadding="5" cellspacing="0">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nom</th>
                            <th>Email</th>
                            <th>Rôle</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="u" items="${requestScope.bd.utilisateurs}" varStatus="s">
                            <tr>
                               <td><c:out value="${u.id}"/></td>
                                <td><c:out value="${u.nom}"/></td>
                                
                                <td><c:out value="${u.email}"/></td>
                                <td><c:out value="${u.role}"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>

        <br>

        <!-- ===== PRODUITS ===== -->
        <h2>Produits
            (total : <c:out value="${fn:length(requestScope.bd.produits)}"/>)
        </h2>

        <c:choose>
            <c:when test="${empty requestScope.bd.produits}">
                <p>Aucun produit enregistré.</p>
            </c:when>
            <c:otherwise>
                <table border="1" cellpadding="5" cellspacing="0">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Nom</th>
                            <th>Prix</th>
                            <th>idV</th>
                            <th>Description</th>
                            <th>Etat</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="p" items="${requestScope.bd.produits}" varStatus="s">
                            <tr>
                                <td><c:out value="${p.id}"/></td>
                                <td><c:out value="${p.nom}"/></td>
                                <td><c:out value="${p.prixMin}"/></td>
                                <td><c:out value="${p.idVendeur}"/></td>
                                <td><c:out value="${p.description}"/></td>
                                <td><c:out value="${p.etat}"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>

    </c:otherwise>
</c:choose>

</body>
</html>
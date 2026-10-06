<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Inscription</title>
    <style>
        body { font-family: Arial, sans-serif; background:#f6f8fb; color:#333; padding:40px; }
        .container { max-width:520px; margin:0 auto; background:#fff; padding:24px; border-radius:8px; box-shadow:0 6px 20px rgba(20,30,40,0.08); }
        h1 { font-size:1.4rem; margin:0 0 16px; text-align:center; }
        form p { margin:12px 0; }
        label { display:block; margin-bottom:6px; font-weight:600; color:#222; }
        input[type="text"], input[type="email"],input[type="password"], select { width:100%; padding:10px; border:1px solid #d7dbe0; border-radius:6px; box-sizing:border-box; }
        button { background:#0078d4; color:#fff; border:none; padding:10px 16px; border-radius:6px; cursor:pointer; font-weight:600; }
        button:hover { background:#005fa3; }
        a { display:block; margin-top:16px; text-align:center; color:#0078d4; text-decoration:none; }
            a:hover { text-decoration:underline; }
    </style>
</head>
<body>
<div class="container">
<h1>Formulaire d'inscription</h1>
<form action="" method="POST">
    <p>
        <label>Nom :</label>
        <input type="text" name="nom" required>
    </p>

    <p>
        <label>Email :</label>
        <input type="email" name="email" required>
    </p>

    <p>
        <label>Role :</label>
        <select name="role" required>
            <option value="">-- Choisir --</option>
            <option value="client">Client</option>
            <option value="Vendeur">Vendeur</option>
        </select>
    </p>

    <p>
        <button type="submit">Envoyer</button>
    </p>
</form>
<a href="${pageContext.request.contextPath}/Conn">Connexion</a>
</div>
</body>
</html>
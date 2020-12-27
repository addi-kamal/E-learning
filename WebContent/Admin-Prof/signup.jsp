<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<script type="text/javascript">
$('.ui.radio.checkbox')
.checkbox()
;
</script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post">
<p>
<label for="nom">nom : </label>
<input type="text" name="nom" id="nom" required />
</p>
<p>
<label for="prenom">prenom : </label>
<input type="text" name="prenom" id="penom" required />
</p>
<p>
<label for="email">email : </label>
<input type="email" name="email" id="email" required />
</p>
<p>
<label for="pass">password : </label>
<input type="password" name="pass" id="pass"required />
</p>
<p>
    <div class="field">
      <div class="ui radio checkbox">
        <input type="radio" name="isAdmin" value="yes"  tabindex="0" class="hidden">
        <label>Admin</label>
      </div>
    </div>
    <div class="field">
      <div class="ui radio checkbox">
        <input type="radio" name="isAdmin" value="no" tabindex="0" class="hidden">
        <label>Professeur</label>
      </div>
    </div>
</p>
<input type="submit"/>
</form>
</body>
</html>
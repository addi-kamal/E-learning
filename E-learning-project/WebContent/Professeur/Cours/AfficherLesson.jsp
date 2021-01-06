<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Test</title>
</head>
<body>
    		<h1>${param['lessonid']}</h1>
    		<input name="lessonid" value="${param['lessonid']}" readonly="true" type="hidden"/>
    		<div class="container">
           <!--   
                
                String text = (String) request.getAttribute("text");
                response.setContentType("text/html"); 
                PrintWriter pw=response.getWriter();
                pw.println(text); 
             %>--> 
    		</div>


</body>
</html>

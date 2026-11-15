<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Counter Application</title>
</head>
<body>
<h1>Counter Control Page</h1>

<h3>Testare GET (Incrementare & Decrementare)</h3>
<a href="CounterServlet?inc=1">CounterServlet GET (+1)</a> <br>
<a href="CounterServlet?dec=1">CounterServlet GET (-1)</a> <br><br>
<a href="CounterServlet?inc=3">CounterServlet GET (+3)</a> <br>
<a href="CounterServlet?dec=3">CounterServlet GET (-3)</a> <br><br>

<h3>Testare POST</h3>
<form action="CounterServlet" method="POST">
    inc: <input type="text" name="inc" value="1"> <br><br>
    dec: <input type="text" name="dec" value="1"> <br><br>
    <input type="submit" name="action" value="Increment POST">
    <input type="submit" name="action" value="Decrement POST">
</form>
</body>
</html>
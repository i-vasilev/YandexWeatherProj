<%--@elvariable id="cityPartName" type="java.lang.String"--%>
<%--@elvariable id="errors" type="java.lang.String"--%>
<%--@elvariable id="weather" type="ru.vasilev.weather.data.WeatherData"--%>
<%--@elvariable id="cities" type="java.util.List"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Погода</title>
</head>
<body>
<h1>Прогноз погоды</h1>

<form action="${pageContext.request.contextPath}/search" method="get">
    <p>
        <label>
            Начните вводить название города:
            <input type="text" name="cityPartName" value="${cityPartName}"/>
        </label>
    </p>
    <p>
        <button type="submit">Найти город</button>
    </p>
</form>

<c:if test="${cities ne null}">
    <form action="${pageContext.request.contextPath}/weather" method="get">
        <p>
            <select name="city">
                <c:forEach var="city" items="${cities}">
                    <option value="${city.id}">${city.name}</option>
                </c:forEach>
            </select>
        </p>
        <p>
            <button type="submit">Получить прогноз</button>
        </p>
    </form>
</c:if>

<c:if test="${weather ne null}">
    <p>
        Температура: ${weather.temperature}
    </p>
    <p>
        Скорость ветра: ${weather.windSpeed}
    </p>
    <p>
        Давление: ${weather.pressure}
    </p>
    <p>
        Осадки: ${weather.condition} <img
            src="https://yastatic.net/weather/i/icons/blueye/color/svg/${weather.icon}.svg" alt="Картинка осадков"
            style="width: 30px;"/>
    </p>
</c:if>
<c:if test="${errors ne null}">
    <p>
            ${errors}
    </p>
</c:if>
</body>
</html>
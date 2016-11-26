<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello world</title>
    <style>
        .error {
            color: red; font-weight: bold;
        }

        #myModal .modal-dialog  {width:100%;}
        /*#myModal {*/
            /*top:5%;*/
            /*right:50%;*/
            /*outline: none;*/
        /*}*/
    </style>


    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <%--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap-3.3.7-dist/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/resources/bootstrap-3.3.7-dist/js/bootstrap.js"></script>

</head>
<body>
<header>
</header>


<form:form modelAttribute="wrapper" action="/test"
           method="post">

    <c:forEach items="${wrapper.testEntityList}" varStatus="vs" var="testEntity">
        <form:label path="testEntityList[${vs.index}].name" cssErrorClass="invalid">College Name</form:label>
        <form:input path="testEntityList[${vs.index}].name" cssErrorClass="invalid " />
        <form:input path="testEntityList[${vs.index}].id" />

        <c:forEach items="${testEntity.mailsList}" varStatus="vss">
            <form:input path="testEntityList[${vs.index}].mailsList[${vss.index}].mail" />
        </c:forEach>

    </c:forEach>
    <form:checkbox path="flag"/>

<input type="submit">
</form:form>

<script type="text/javascript">
    $("#activType_id").val($("#activType_id option:first").val());

    $(function() {
        $ ('#datepick').datepicker({
            dateFormat: 'yy-mm-dd',
            changeYear: true
        });
    });
</script>

</body>
</html>

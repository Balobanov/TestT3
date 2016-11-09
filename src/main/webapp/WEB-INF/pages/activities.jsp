<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: DENNNN
  Date: 07.11.2016
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <title>Activities</title>

</head>
<body>


<table>
    <tr>
        <td>Activity</td>
        <td>Contact name</td>
    </tr>

    <tr>
        <td><label>--------------------------------------------</label></td>
    </tr>

    <c:forEach items="${contacts}" var="contact">
        <tr>
            <c:forEach items="${contact.activities}" var="activity">
                <td><label>${activity.title}</label></td>
                <td><label>${contact.firstName} ${contact.firstName}</label></td>
            </c:forEach>
        </tr>
    </c:forEach>

    <tr>
        <td><label>--------------------------------------------</label></td>
    </tr>

    <tr><td>||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||</td></tr>

    <sf:form modelAttribute="activity" method="post" action="/saveAvtivity">
        <tr>
            <td>
                <label>Activities types</label>
                <select id="activType_id" name="act_id">
                    <c:forEach items="${activityTypes}" var="at">
                        <option value="${at.id}">${at.activityType}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td>
                <label>Contacts</label>
                <select id="contact_id" name="c_id">
                    <c:forEach items="${contacts}" var="c">
                        <option value="${c.id}">${c.firstName} ${c.lastName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

            <tr>
                <td><sf:hidden path="id"></sf:hidden></td>
            </tr>

            <tr>
                <td><sf:label path="title">Title *</sf:label></td>
                <td><sf:input path="title" ></sf:input></td>
                <td><sf:errors path="title" cssClass="error"></sf:errors></td>
            </tr>

            <tr>
                <td><sf:label path="notes">Notes</sf:label></td>
                <td><sf:textarea path="notes" rows="10" cols="45"></sf:textarea></td>
                <td><sf:errors path="notes" cssClass="error"></sf:errors></td>
            </tr>

            <tr>
                <td><sf:label path="date">Date</sf:label></td>
                <td><sf:input path="date" id = "datepick"></sf:input></td>
                <td><sf:errors path="date" cssClass="error"></sf:errors></td>
            </tr>

        <tr>
            <td><input type="submit" value="Add/Edit Activity"></td>
        </tr>
</table>
</sf:form>


<script type="text/javascript">
    $("#activType_id").val($("#activType_id option:first").val());
    $("#contact_id").val($("#contact_id option:first").val());

    $(function() {
        $ ('#datepick').datepicker({
            dateFormat: 'yy-mm-dd',
            changeYear: true
        });
    });
</script>
</body>
</html>

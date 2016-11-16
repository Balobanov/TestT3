<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello world</title>
    <style>
        .error {
            color: red; font-weight: bold;
        }
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

<a href="<c:url value="/logout" />" >Logout</a><br>

<form action="/toActivityPage" method="post" id="activities" >
<input type="submit" value="Activities" >
</form>



<form action="/searchContact" method="post" id="search" >
    <input type="text" name="name" value="${searchName}"><Br>
    <input type="radio" name="type" value="first" checked>First name<Br>
    <input type="radio" name="type" value="last">Last name<Br>
    <input type="submit" value="Find" ><Br>
</form>


<sf:form method="post" action="/saveContact" modelAttribute="contact" id="saveContact">
<table>
        <tr>
            <td><sf:hidden path="contact.id"></sf:hidden></td>
        </tr>

            <tr>
                <td><sf:label path="contact.firstName">First Name *</sf:label></td>
                <td><sf:input path="contact.firstName" ></sf:input></td>
                <td><sf:errors path="contact.firstName" cssClass="error"></sf:errors></td>
            </tr>

            <tr>
                <td><sf:label path="contact.lastName">Last Name *</sf:label></td>
                <td><sf:input path="contact.lastName"></sf:input></td>
                <td><sf:errors path="contact.lastName" cssClass="error"></sf:errors></td>
            </tr>

            <tr>
                <td><sf:label path="contact.email">Email *</sf:label></td>
                <td><sf:input path="contact.email"></sf:input></td>
                <td><sf:errors path="contact.email" cssClass="error" ></sf:errors></td>
            </tr>

        <tr>
            <td><sf:label path="contact.phone">Phone</sf:label></td>
            <td><sf:input path="contact.phone"></sf:input></td>
            <td><sf:errors path="contact.phone" cssClass="error" ></sf:errors></td>
        </tr>

        <tr>
            <td><sf:label path="contact.address1">Address 1</sf:label></td>
            <td><sf:input path="contact.address1"></sf:input></td>
            <td><sf:errors path="contact.address1" cssClass="error" ></sf:errors></td>
        </tr>

        <tr>
            <td><sf:label path="contact.address2">Address 2</sf:label></td>
            <td><sf:input path="contact.address2"></sf:input></td>
            <td><sf:errors path="contact.address2" cssClass="error" ></sf:errors></td>
        </tr>

        <tr>
            <td><sf:label path="contact.city">City</sf:label></td>
            <td><sf:input path="contact.city"></sf:input></td>
            <td><sf:errors path="contact.city" cssClass="error" ></sf:errors></td>
        </tr>

        <tr>
            <td><sf:label path="contact.postCode">Post Code</sf:label></td>
            <td><sf:input path="contact.postCode"></sf:input></td>
            <td><sf:errors path="contact.postCode" cssClass="error" ></sf:errors></td>
        </tr>

            <tr>
                <td><input type="submit" value="Add contact/Edit Contact" name="save"></td>
            </tr>

</table>

    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">Название модали</h4>
                </div>
                <div class="modal-body">

                    <table class="table table-bordered" width="600px">

                        <caption>Add activity and contact</caption>

                        <tr>
                            <th>Activities types</th>
                            <th>For Contact</th>
                            <th>Title</th>
                            <th>Notes</th>
                            <th>Date</th>
                        </tr>

                        <tr>
                            <td>
                                <select id="activType_id" name="act_id">
                                    <c:forEach items="${activityTypes}" var="at">
                                        <option value="${at.id}">${at.activityType}</option>
                                    </c:forEach>
                                </select>
                            </td>

                            <td>
                                <label>${contact.contact.firstName} ${contact.contact.lastName}</label>
                            </td>

                            <td>
                                <sf:hidden path="activity.id"></sf:hidden>
                                <sf:input path="activity.title" ></sf:input>
                                <sf:errors path="activity.title" cssClass="error"></sf:errors>
                            </td>

                            <td>
                                <sf:textarea path="activity.notes" rows="10" cols="45"></sf:textarea>
                                <sf:errors path="activity.notes" cssClass="error"></sf:errors>
                            </td>

                            <td>
                                <sf:input path="activity.date" id = "datepick"></sf:input>
                                <sf:errors path="activity.date" cssClass="error"></sf:errors>
                            </td>
                        </tr>

                    </table>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                    <input type="submit" value="Add Activity" name="save">
                </div>
            </div>
        </div>
    </div>


        </sf:form>

        <tr>
            <form method="POST" action="/deleteContact?id=${(contact.contact.id == null || contact.contact.id <= 0) ? 0 : contact.contact.id}"
                  id="deleteContact" onSubmit="if(!confirm('Are you sure you want delete contact?')){return false;}">
                <td><input type="submit" value="Delete contact"></td>
            </form>
        </tr>





<table class="table table-bordered" width="600px">
        <caption>Contacts</caption>
            <tr>
                <th>Contact</th>
            </tr>

            <c:forEach var="c" items="${contacts}">
                <tr>
                    <td><a href="/editContact?id=${c.id}">${c.firstName} ${c.lastName}</a></td>
                </tr>
            </c:forEach>
        </table>

<table class="table table-bordered" width="600px">
                <caption>
                    Activities
                </caption>

                <tr>
                    <th>Title</th>
                    <th>Type</th>
                    <th>Date</th>
                </tr>

            <c:forEach items="${contact.contact.activities}" var="act">
                <tr>
                    <td> ${act.title} </td>
                    <td> ${act.activityType} </td>
                    <td> ${act.date} </td>
                </tr>
            </c:forEach>
</table>






<!-- Button trigger modal -->
<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
    Add activity
</button>



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

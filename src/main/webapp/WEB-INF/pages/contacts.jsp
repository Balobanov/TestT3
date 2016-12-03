<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello world</title>

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

<%--<form id="contactDto" name="contactDto" action="/contactsSaveAjax" method="post">--%>
    <input id="id" name="id" type="text">
    <input id="firstName" name="firstName" type="text">
    <input type="button" id="save" value="Save">
<%--</form>--%>


<label id="hello"> Hello </label>
<input type="button" id="button1" value="Append to hello">
<label id="callback">Call back: </label>
<script>
    $(document).ready(function () {

        $('#button1').click(
            function () {

                $.ajax({
                    url: '/contactsAllAjax',
                    dataType: 'json',
                    type: 'POST',
                    contentType : 'application/json',

                    success: function(data){
                        $.each(data, function (i, obj) {
                            $('#hello').append(obj.id + " " + obj.firstName + " ");
                        })
                    }
                });
            }
        ); // button1 click


        ////////////////////////////////////////////
        /////// SEND FORM PARAMETERS TO CONTROLLER //
        ////////////////////////////////////////////

        $('#save').click(function(event) {

            var id = $('#id').val();
            var firstName = $('#firstName').val();
            var json = { "id" : id, "firstName" : firstName};

            //var json = $('#contactDto').serializeObject(); // working, just another method to get json

            $.ajax({
                url: '/contactsSaveAjax',
                data: JSON.stringify(json),
                type: "POST",
                contentType : 'application/json',

                success: function(data) {

                    $("#callback").html(data.firstName);
                }
            });

        });


        $.fn.serializeObject = function()
            {
                var o = {};
                var a = this.serializeArray();
                $.each(a, function() {
                    if (o[this.name] !== undefined) {
                        if (!o[this.name].push) {
                            o[this.name] = [o[this.name]];
                        }
                        o[this.name].push(this.value || '');
                    } else {
                        o[this.name] = this.value || '';
                    }
                });
                return o;
            };
    });

</script>

</body>
</html>

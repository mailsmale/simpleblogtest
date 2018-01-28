<%@include file="../layout/taglib.jsp" %>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
      integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/additional-methods.js"></script>






<form:form commandName="user" cssClass="form-horizontal registrationForm">

    <c:if test="${param.success eq true}">
        <div class="alert alert-success">Registration successful!</div>
    </c:if>
    <div id="registration_form">
        <div class="form-group">
            <%--<label for="name" class="col-sm-2 control-label">Name:--%>
            <%--</label>--%>
            <div class="col-sm-2">
                <form:input path="name" cssClass="form-control" placeholder="Name"/>
                <form:errors path="name"/>
            </div>
        </div>
        <div class="form-group">
            <%--<label for="email" class="col-sm-2 control-label">Email:--%>
            <%--</label>--%>
            <div class="col-sm-2">
                <form:input path="email" cssClass="form-control" placeholder="Email address"/>
                <form:errors path="email"/>
            </div>
        </div>
        <div class="form-group">
            <%--<label for="password" class="col-sm-2 control-label">Password:--%>
            <%--</label>--%>
            <div class="col-sm-2">
                <form:password path="password" cssClass="form-control" id="password" placeholder="Password"/>
                <form:errors path="password"/>
            </div>
        </div>
        <div class="form-group">
            <%--<label for="password" class="col-sm-2 control-label">Password again:--%>
            <%--</label>--%>
            <div class="col-sm-2">
                <input type="password" name="passwordAgain" id="passwordAgain" class="form-control" placeholder="Password again"/>
            </div>
        </div>
        <div class="form-group">
            <div class="form-group">
                <div class="col-sm-2">
                    <input type="submit" value="Save" class="btn btn-lg btn-primary">
                </div>
            </div>
        </div>
    </div>
</form:form>

<script type="text/javascript">
    $(document).ready(function () {
        $(".registrationForm").validate({
            rules: {
                name: {
                    required: true,
                    minlength: 3,
                    remote: {
                        url: "<spring:url value="/sign_up/available.html" />",
                        type: "get",
                        data: {
                            username: function() {
                                return $("#name").val();
                            }
                        },
                    },

                },
                email: {
                    required: true,
                    email: true,
                    minlength: 3
                },
                password: {
                    required: true,
                    required: true,
                    minlength: 5
                },
                passwordAgain: {
                    required: true,
                    minlength: 5,
                    equalTo: "#password",
                },
            },
            highlight: function (element) {
                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
            },
            unhighlight : function (element) {
                $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
            },
            messages: {
                name: {
                    remote: "This user already exist!"
                }
            }

        })
        ;
    });
</script>
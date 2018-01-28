<%@include file="../layout/taglib.jsp" %>
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalLong">
    New blog
</button>

<form:form commandName="blog" cssClass="form-horizontal blog-form">
    <!-- Modal -->
    <div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">New blog</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">Name:
                        </label>
                        <div class="col-sm-2">
                            <form:input path="name" cssClass="form-control"/>
                            <form:errors path="name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="url" class="col-sm-2 control-label">URL:
                        </label>
                        <div class="col-sm-2">
                            <form:input path="url" cssClass="form-control"/>
                            <form:errors path="url"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <input type="submit" class="btn btn-primary" value="Save"/>
                </div>
            </div>
        </div>
    </div>
</form:form>
<br/>

<ul class="nav nav-pills">
    <c:forEach items="${user.blogs}" var="blog">
        <li class="nav-item">
            <a class="nav-link" href="#blog_${blog.id}" data-toggle="tab">${blog.name}</a>
            <a href="<spring:url value="/blog/remove/${blog.id}.html" />" class="btn btn-danger triggerRemove">remove
                blog</a>
        </li>
    </c:forEach>
</ul>
<div class="tab-content">
    <c:forEach items="${user.blogs}" var="blog">
        <div class="tab-pane" id="blog_${blog.id}" role="tabpanel" aria-labelledby="profile-tab">
            <h1>${blog.name}</h1>
            <h1>${blog.url}</h1>
            <table class="table table-bordered table-hover table-striped">
                <thead>
                <tr>
                    <th>Title</th>
                    <th>Link</th>
                    <th>Description</th>
                    <th>Published date</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${blog.items}" var="item">
                    <tr>
                        <strong>
                            <td>${item.title}</td>
                        </strong>
                        <strong>
                            <td>${item.link}</td>
                        </strong>
                        <td>${item.description}</td>
                        <td>${item.publishedDate}</td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>

        </div>
    </c:forEach>
</div>

<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalRemoveTitle">Remove blog</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Realy remove?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                <a href="" class="btn btn-danger removeBtn">Remove</a>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    $(document).ready(function () {
        $('.nav-pills a:first').tab('show');
        $('.triggerRemove').click(function (e) {
            e.preventDefault();
            $("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
            $("#modalRemove").modal();
        })
    });
    $(".blog-form").validate({
        rules: {
            name: {
                required: true,
                minlength: 3
            },
            url: {
                url: true,
                required: true
            },
        },
        highlight: function (element) {
            $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
        },

    });
</script>
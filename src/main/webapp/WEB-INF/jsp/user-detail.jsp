<%--
  Created by IntelliJ IDEA.
  User: Astrarium
  Date: 10/28/2017
  Time: 5:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../layout/taglib.jsp" %>
<h1>
    ${user.name}
</h1>
<!-- Button trigger modal -->




<ul class="nav nav-pills">
    <c:forEach items="${user.blogs}" var="blog">
        <li class="nav-item">
            <a class="nav-link" href="#blog_${blog.id}" data-toggle="tab">${blog.name}</a>
            <a href="<spring:url value="/blog/remove/${blog.id}.html" />" class="btn btn-danger triggerRemove">remove blog</a>
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
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${blog.items}" var="item">
                    <tr>
                        <td>${item.title}</td>
                        <td>${item.link}</td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>

        </div>
    </c:forEach>
</div>

<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
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
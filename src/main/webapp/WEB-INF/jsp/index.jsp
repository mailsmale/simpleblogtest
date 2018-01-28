<%@ include file="../layout/taglib.jsp" %>
<h1>Latest news from the Java Worlds</h1>
<div class="tab-content">
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
        <c:forEach items="${items}" var="item">
            <tr>
                <strong>
                    <td>    <b>${item.blog.name}<b/>
                            <br />
                            ${item.title}</td>
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


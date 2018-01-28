<%@include file="../layout/taglib.jsp" %>


<script type="text/javascript">
    $(document).ready(function () {
        $('.triggerRemove').click(function (e) {
            e.preventDefault();
            $("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
            $("#modalRemove").modal();
        })
    });
</script>

<%--<script>
    $(document).ready(function () {
        $("body").hide();
    });
</script>--%>

<table class="table table-bordered table-hover table-striped">
    <thead>
    <th>Name</th>
    <th>Email</th>
    <th>Enabled</th>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>
                <a href="<spring:url value="/users/${user.id}.html"></spring:url>">
                    <c:out value="${user.name}"/>
                </a>
            </td>
            <td>
                <c:out value="${user.email}"/>
            </td>
            <td>
                <c:out value="${user.enabled}"/>
            </td>
            <td>
                <a href="<spring:url value="/users/remove/${user.id}.html" />" class="btn btn-danger triggerRemove">remove
                    user</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

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

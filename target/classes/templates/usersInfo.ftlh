<#import "parts/common.ftlh" as c>
<#import "parts/book.ftlh" as b>
<@c.page>
    <div>
        <h5>Users</h5>
    </div>
    <div class="container" style="min-height: 100vh" >
        <div class="row my-3">
            <div class="col-sm">
                <h6>Login</h6>
            </div>
            <div class="col-sm">
                <h6>Email</h6>
            </div>
            <div class="col-sm">
            </div>
        </div>
        <ul class="list-group">
            <#list users as user>
                <li class="list-group-item">
                    <div class="row my-2">
                        <div class="col-sm">
                            ${user.getUsername()}
                        </div>
                        <div class="col-sm">
                            ${user.getEmail()}
                        </div>
                        <div class="col-sm">
                            <#if user.isActive()>
                                <form method="post" action="/admin/block">
                                    <input type="hidden" name="userId" value="${user.getId()}" />
                                    <button type="submit" class="btn btn-success">Block</button>
                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                </form>
                            <#else>
                                <form method="post" action="/admin/block">
                                    <input type="hidden" name="userId" value="${user.getId()}" />
                                    <button type="submit" class="btn btn-danger">Unlock</button>
                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                </form>
                            </#if>
                        </div>
                    </div>
                </li>
            </#list>
        </ul>
        </div>
</@c.page>
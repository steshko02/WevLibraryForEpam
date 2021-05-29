<#import "parts/common.ftlh" as c>
<#import "parts/book.ftlh" as b>

<@c.page>
    <div >
        <h4>Users ordered this books:</h4>
    </div>
    <div class="container" style="height: 100vh">
        <div class="row my-3">
            <div class="col-sm">
                <h6>Book</h6>
            </div>
            <div class="col-sm">
                <h6>User</h6>
            </div>
            <div class="col-sm">
                <h6>Author</h6>
            </div>
            <div class="col-sm">
            </div>
        </div>
        <ul class="list-group">
            <#list hash as user,books>
                <li class="list-group-item">
                    <div class="row my-2">
                        <div class="col-sm-3">
                            ${user.username}
                        </div>
                    </div>
                    <#list books as book>
                        <div class="row my-2">
                            <div class="col-sm">

                            </div>
                            <div class="col-sm">
                                <a href="/bookPage?bookId=${book.id}">${book.name}</a>
                            </div>
                            <div class="col-sm">
                                ${book.author}
                            </div>
                            <div class="col-sm">
                                <div class="btn-group" role="group" aria-label="Basic example">
                                    <form method="post" action="/adminOrders/allow">
                                        <input type="hidden" name="userId" value="${user.id}" />
                                        <input type="hidden" name="bookId" value="${book.id}" />
                                        <button type="submit" class="btn btn-success">Give book</button>
                                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                    </form>
                                    <form method="post" action="/adminOrders/deny">
                                        <input type="hidden" name="userId" value="${user.id}" />
                                        <input type="hidden" name="bookId" value="${book.id}" />
                                        <button type="submit" class="btn btn-danger">Deny</button>
                                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                    </form>
                                </div>

                            </div>
                        </div>
                    </#list>
                </li>
            </#list>
        </ul>

    </div>
</@c.page>
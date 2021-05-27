<#import "parts/common.ftlh" as c>
<#import "parts/book.ftlh" as b>

<@c.page>
    <div >
        <h4>Your orders</h4>
    </div>
    <div class="container" style="height: 100vh">
        <div class="row my-3">
            <div class="col-sm">
                <h6>Book</h6>
            </div>
            <div class="col-sm">
                <h6>Author</h6>
            </div>
            <div class="col-sm">
            </div>
        </div>
        <ul class="list-group">
            <#list books as book>
                <li class="list-group-item">
                <div class="row my-2">
                    <div class="col-sm">
                        <a href="/bookPage?bookId=${book.id}">${book.name}</a>

                    </div>
                    <div class="col-sm">
                        ${book.author}
                    </div>
                    <div class="col-sm">
                        <form method="post" action="/userOrders">
                            <input type="hidden" name="bookId" value="${book.id}" />
                            <button type="submit" class="btn btn-danger">Deny order</button>
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        </form>
                    </div>
                </div>
                </li>
            </#list>
        </ul>

    </div>
</@c.page>
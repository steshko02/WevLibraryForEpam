<#import "parts/common.ftlh" as c>
<#import "parts/book.ftlh" as b>

<@c.page>
    <div class="mt-2">
        <h5>Book list</h5>
    </div>
    <div class="card-columns">
        <#list books as book>

            <#if book.filename??>
                <@b.book book/>
            </#if>

        <#else>
            <h5>Library is empty.</h5>
        </#list>
    </div>
</@c.page>
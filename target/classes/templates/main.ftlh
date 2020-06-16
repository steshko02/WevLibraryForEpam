<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

<@c.page>
    <div class="jumbotron mt-5">
        <h1 class="display-4">Hello, <#if known>${user.username}<#else>Guest</#if>!</h1>
        <p class="lead">Welcome to the WebLib. Here you can order a book.</p>
        <a class="btn btn-outline-success btn-lg" href="/bookList" role="button">Get started</a>
    </div>
</@c.page>


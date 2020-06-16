<#import "login.ftlh" as l>
<#include "security.ftlh">

<nav class="navbar navbar-expand-lg navbar navbar-dark bg-dark">
    <a class="navbar-brand" href="/">WebLib</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">Home </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/bookList">Books</a>
            </li>
            <#if known>
                <#if !isAdmin>
                    <li class="nav-item">
                        <a class="nav-link" href="/userOrders">My orders</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/userLib">My lib</a>
                    </li>
                </#if>
                <#if isAdmin>
                    <li class="nav-item">
                        <a class="nav-link" href="/addBook">Add book</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/adminOrders">Orders</a>
                    </li>
                </#if>
            </#if>

        </ul>
        <#if known>
            <@l.logout/>
        </#if>
    </div>
</nav>
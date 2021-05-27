<#import "login.ftlh" as l>
<#include "security.ftlh">

<nav class="navbar navbar-expand-lg navbar navbar-dark bg-secondary ">
    <a class="navbar-brand" href="/">WebLibrary</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto" >
            <li class="nav-item active">
                <a class="nav-link" href="/">Main </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/bookList">Library</a>
            </li>
            <#if known>
                <#if !isAdmin>
                    <li class="nav-item">
                        <a class="nav-link" href="/userOrders">My orders</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/userLib">My books</a>
                    </li>
                    <li class="dropdown">
                        <a class="dropdown-toggle nav-link" data-toggle="dropdown" href="#">Profile
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="/profile/editPass">Edit password</a></li>
                            <li><a href="/profile/editEmail">Edit email</a></li>
                            <li><a href="/profile/editName">Edit name</a></li>
                        </ul>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/aboutUs">About Us</a>
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
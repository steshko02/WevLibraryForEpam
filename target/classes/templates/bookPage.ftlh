<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

<@c.page>
    <#if book.filename??>
        <div class="container mt-5">
        <div class="row">
            <div class="col-sm-4">
                <img src="/img/${book.filename}" class="card-img-top" alt="No image">

            </div>
            <div class="col-sm-8">
                <h5 class="display-4">${book.name}</h5>
                <#if book.description??>
                    <p>${book.description}</p>
                <#else>
                    <p>No description</p>
                </#if>
                <div class="mt-3">
                    <p>Author: ${book.author}</p>
                    <p>Genre: ${book.genre}</p>
                    <p>Year: ${book.year}</p>
                    <p>ISBN: ${book.isbn}</p>
                </div>
                <#if !isAdmin>
                    <#if user.orderedBook(book.id)>
                        <div class="alert alert-warning" role="alert">
                            Book has been ordered!
                        </div>
                    <#else>
                        <#if user.bookInLib(book.id)>
                            <div class="alert alert-success" role="alert">
                                Book in your lib!
                            </div>
                            <#else>
                                <form method="post" action="/bookPage">
                                    <div class="my-5">
                                        <input type="hidden" name="bookId" value="${book.id}">
                                        <button class="btn btn-outline-success">Order</button>
                                        <label>${message!}</label>
                                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                    </div>
                                </form>
                        </#if>
                    </#if>
                </#if>
            </div>
        </div>

    </div>
    </#if>

</@c.page>

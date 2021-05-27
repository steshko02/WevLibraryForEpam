<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div class="container" style="height: 100vh">
        <h5>Sign In</h5>
        <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
            <div class="alert alert-danger" role="alert">
                ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
            </div>
        </#if>
        <#if messageGood??>
            <div class="alert alert-success" role="alert">
                ${messageGood}
            </div>
        </#if>
        <#if messageBad??>
            <div class="alert alert-danger" role="alert">
                ${messageBad}
            </div>
        </#if>
        <@l.login "/login"/>
        <a href="/registration" class="btn btn-outline-primary my-2 my-sm-0" type="button">Sign Up</a>
    </div>
</@c.page>


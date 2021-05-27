<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div class="container" style="height: 100vh">
    <h5>Register new User</h5>
        <form action="/registration" method="post">
            <div class="form-group" style="width: 50%">
                <label for="usernameInput">Username:</label>
                <input class="form-control <#if usernameMessage??>is-invalid</#if>"
                       type="text" id="usernameInput" name="username" required
                />
                <#if usernameMessage??>
                <div class="invalid-feedback">
                    ${usernameMessage}
                </div>
                </#if>
            </div>
            <div style="width: 50%">
                <label for="emailInput"> Email: </label>
                <input class="form-control"
                               type="email" id="emailInput" name="email" required/>
            </div>
            <div style="width: 50%">
                <label for="passwordInput"> Password: </label>
                <input class="form-control <#if passMessage??>is-invalid</#if>"
                       type="password" id="passwordInput" name="password" required
                />
                <#if passMessage??>
                    <div class="invalid-feedback">
                        ${passMessage}
                    </div>
                </#if>
            </div>
            <div style="width: 50%">
                <label for="password2Input"> Repeat the password: </label>
                <input class="form-control <#if pass2Message??>is-invalid</#if>"
                       type="password" id="password2Input" name="password2" required
                />
                <#if pass2Message??>
                    <div class="invalid-feedback">
                        ${pass2Message}
                    </div>
                </#if>
            </div>
            <div class="my-2">
                <button type="submit" class="btn btn-outline-success my-2 my-sm-0">Submit</button>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
        </form>
    </div>
</@c.page>
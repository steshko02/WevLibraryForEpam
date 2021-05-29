<#import "parts/common.ftlh" as c>

<@c.page>
    <div style="height: 100vh">
        <#if username??><h5>${username}</h5></#if>
    <form  method="post">
        <div style="width: 30%">
            <label for="passwordInput"> Enter old password: </label>
            <input class="form-control <#if oldPassMessage??>is-invalid</#if>"
                   type="password" id="passwordInput" name="oldPassword" required
            />
            <#if oldPassMessage??>
                <div class="invalid-feedback">
                    ${oldPassMessage}
                </div>
            </#if>
        </div>
        <div style="width: 30%">
            <label for="password1Input"> Enter new password: </label>
            <input class="form-control <#if passMessage??>is-invalid</#if>"
                   type="password" id="password1Input" name="password" required
            />
            <#if passMessage??>
                <div class="invalid-feedback">
                    ${passMessage}
                </div>
            </#if>
        </div>
        <div style="width: 30%">
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
            <h6 style="font-size: smaller;color: darkgreen"> Аfter confirmation, you must activate your account.</h6>
            <button type="submit" class="btn btn-outline-success my-2 my-sm-0">Submit</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
        </div>
    </form>
    </div>
</@c.page>


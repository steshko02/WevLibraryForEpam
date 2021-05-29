<#import "parts/common.ftlh" as c>

<@c.page >
    <div style="height: 100vh">
        <#if username??><h5>${username}</h5></#if>
    <form  method="post">
        <div style="width: 30%">
            <label for="emailInput"> Enter new email: </label>
            <input class="form-control <#if emailMessage??>is-invalid</#if>"
                   type="email" id="emailInput" name="newEmail" value="<#if username??>${email}</#if>" required/>
            <#if emailMessage??>
                <div class="invalid-feedback">
                    ${emailMessage}
                </div>
            </#if>
        </div>
        <div style="width: 30%">
            <label for="passwordInput"> Enter password: </label>
            <input class="form-control <#if oldPassMessage??>is-invalid</#if>"
                   type="password" id="passwordInput" name="oldPassword" required
            />
            <#if oldPassMessage??>
                <div class="invalid-feedback">
                    ${oldPassMessage}
                </div>
            </#if>
        </div>

        <div class="my-2">
            <h6 style="font-size: smaller; color: darkgreen" > Аfter confirmation, you must activate your account.</h6>
            <button type="submit"  class="btn btn-outline-success my-2 my-sm-0">Submit</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
        </div>
    </form>
    </div>
</@c.page>


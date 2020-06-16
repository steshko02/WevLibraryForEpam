<#macro login path>
    <form action="${path}" method="post">
        <div class="form-group">
            <label for="usernameInput">Username</label>
            <input type="text" id="usernameInput" name="username" required/>
        </div>
        <div>
            <label for="passwordInput"> Password: </label>
            <input type="password" name="password" required/>
        </div>
        <div class="my-2">
        <button type="submit" class="btn btn-outline-success my-2 my-sm-0">Submit</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <button type="submit" class="btn btn-danger">Log out</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</#macro>
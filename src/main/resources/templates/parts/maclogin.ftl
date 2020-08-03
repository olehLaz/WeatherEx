<#macro maclogin path>
    <div>
        <form action="${path}" method="post">
            <div><label> User name: <input type="text" name="username"/></label></div>
            <div><label> Password: <input type="pasword" name="password"/></label></div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div><input type="submit" value="Sign in "/></div>
        </form>
    </div>
</#macro>
<#macro logout>
    <div>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="submit" value=" Sing Out "/>
        </form>
    </div>

</#macro>
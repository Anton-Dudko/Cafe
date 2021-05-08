<#macro login path isRegisterForm>
<main class="form-signin">
    <form action="${path}" method="post">

        <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

        <div class="form-floating">
            <input type="text" name="username" class="form-control" id="floatingInput" placeholder="name@example.com">
        </div>
        <br>
        <div class="form-floating">
            <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />

        <button class="w-100 btn btn-lg btn-primary" type="submit"><#if isRegisterForm>Зарегестрироваться<#else>Войти</#if></button>
        <#if !isRegisterForm><a href="/registration">Регистрация</a></#if>
    </form>
</main>
</#macro>

<#macro logout>
<form action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <button class="btn btn-primary" type="submit"><#if user??>Выйти<#else>Войти</#if></button>
</form>
</#macro>

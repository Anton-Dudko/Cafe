<#import "parts/common.ftlh" as c>

<@c.page>
<h1>Редактирование пользователя</h1>
<hr>

<form action="/user" method="post">
    <input type="email" class="form-control"" name="username" value="${user.username}">
    <#list roles as role>
    <div>
        <label class="form-check-label"><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
    </div>
</#list>
<input type="hidden" value="${user.id}" name="userId">
<input type="hidden" value="${_csrf.token}" name="_csrf">
<button class="btn btn-primary" type="submit">Save</button>
</form>
</@c.page>
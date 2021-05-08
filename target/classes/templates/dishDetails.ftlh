<#import "parts/common.ftlh" as c>

<@c.page>
<h1>Просмотр блюда</h1>
<hr>

<#list dishes as dish>
<div class="card mb-4">
    <#if dish.filename??>
        <img src="/img/${dish.filename}">
    </#if>
    <div class="p-3">
        <h3 style="color: black">${dish.name}</h3>
        <div class="dish">
            <span class="weight">${dish.weight} гр.</span>
            <span class="cost">${dish.cost} руб.</span>
        </div>
        <i style="color: black">${dish.description}</i>
    </div>

</#list>
</div>
<button class="btn btn-dangerous" type="submit"><a href="/main">Назад</a></button>
</@c.page>
<#import "parts/common.ftlh" as c>
<#include "parts/security.ftlh">

<@c.page>
<h1>Меню</h1>
<hr>


<div class="row" >
<#list dishes as dish>

    <div class="col-lg-6 " style="color: black">
        <div class="card mb-3">
            <#if dish.filename??>
                <img src="/img/${dish.filename}">
            </#if>
            <div class="p-3">
                <h3><a href="/main/${dish.id}" style="color: #2d2b2d">${dish.name}</a></h3>
                <div class="dish">
                    <span class="weight">${dish.weight} гр.</span>
                    <span class="cost">${dish.cost} руб.</span>
                </div>
                <i>${dish.description}</i>
            </div>
        <!--<form action="/card/${dish.id}" method="post">
            <div class="text-center mb-3"><button class=" btn btn-secondary button-add-to-card text-in-button" type="submit" >В корзину</button>
            </div></form>-->
        <#if isAdmin>
            <button class="btn btn-outline-primary" type="submit"><a href="/main/${dish.id}/edit" style="color:inherit">Редактировать</a></button>

            <button class="btn btn-outline-danger" type="submit"><a href="/main/${dish.id}/remove" style="color:inherit">Удалить</a></button>
        </#if>
    </div>


    </div>
    <#else>
    No dish
    </#list>

</div>




</@c.page>
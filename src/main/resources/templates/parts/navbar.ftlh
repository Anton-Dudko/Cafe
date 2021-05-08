<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-light bg-danger">
    <a class="navbar-brand" href="/">Cafe-shop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/">Главная</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main">Меню</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/location">Где мы находимся</a>
            </li>
            <!--<li class="nav-item">
                <a class="nav-link" href="/card">Корзина</a>
            </li>-->
            <#if isAdmin>
            <li class="nav-item">
                <a class="nav-link" href="/user">Пользователи</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/addDishes">Добавить блюдо</a>
            </li>
            </#if>
        </ul>

        <form method="get" action="/main" class="d-flex mr-3">
            <input class="form-control me-2" type="text" name="filter" value="${filter?ifExists}">
            <button class="btn btn-success" type="submit">Найти</button>
        </form>

        <div class="navbar-text mr-3"><#if user??>${name}<#else>Please, login</#if></div>
        <@l.logout />
    </div>
</nav>
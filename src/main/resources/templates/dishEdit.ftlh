<#import "parts/common.ftlh" as c>


<@c.page>

<h1>Редактирование блюда</h1>
<hr>
<#list dishes as dish>
<div class="container" style="background: #21262c; color: white;>
    <div class="form-group mt-3">
<form method="post" enctype="multipart/form-data">
    <div class="form-group">
        <input type="text" name="name" class="form-control" value="${dish.name}" />
    </div>
    <div class="form-group">
        <textarea class="form-control rounded-0" id="exampleFormControlTextarea1" type="text" name="description"  rows="10">${dish.description}</textarea>
    </div>
    <div class="form-group">
        <input class="form-control" type="text" name="weight" value="${dish.weight}">
    </div>
    <div class="form-group">
        <input type="text" class="form-control" name="cost" value="${dish.cost}">
    </div>
    <div class="form-group">
        <div class="custom-file">
            <input type="file" name="file" id="customFile">
            <label class="custom-file-label" for="customFile">Choose file</label>
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <div class="form-group">
        <button type="submit" class="btn btn-primary">Обновить</button>
    </div>
</form>
</div>
</div>
</#list>
</@c.page>
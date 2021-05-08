<#import "parts/common.ftlh" as c>


<@c.page>

<h1>Добавление блюда</h1>
<hr>
<div class="container" style="background: #21262c; color: white;>
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" name="name" class="form-control" placeholder="Введите название" />
            </div>
            <div class="form-group">
                <textarea class="form-control rounded-0" id="exampleFormControlTextarea1" type="text" name="description" placeholder="Описание" rows="10"></textarea>
            </div>
            <div class="form-group">
                <input class="form-control" type="text" name="weight" placeholder="Масса">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="cost" placeholder="Цена">
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Добавить</button>
            </div>
        </form>
    </div>
</div>
</@c.page>
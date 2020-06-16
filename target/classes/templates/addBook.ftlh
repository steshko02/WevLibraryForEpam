<#import "parts/common.ftlh" as c>
<#import "parts/book.ftlh" as b>

<@c.page>
    <div class="mt-4">
        <h5>Add new book</h5>
</div>
<div>
    <form method="post" enctype="multipart/form-data">
        <div class="form-group col-sm-5">
            <label for="nameInput">Book name:</label>
            <input class="form-control" type="text" name="name" placeholder="Name" id="nameInput" required>
        </div>
        <div class="form-group col-sm-5">
            <label for="authorInput">Author:</label>
            <input class="form-control" type="text" name="author" placeholder="Author" id="authorInput" required>
        </div>
        <div class="form-group col-sm-5">
            <label for="yearInput">Year of publishing:</label>
            <input class="form-control" type="number" name="year" placeholder="Year" id="yearInput" required>
        </div>
        <div class="form-group col-sm-5">
            <label for="genreInput">Genre:</label>
            <input class="form-control" type="text" name="genre" placeholder="Genre" id="genreInput" required>
        </div>
        <div class="form-group col-sm-5">
            <label for="isbnInput">ISBN:</label>
            <input class="form-control" type="number" name="isbn" placeholder="ISBN" id="isbnInput" required>
        </div>
        <div class="form-group">
            <label for="fileInput">Add an image</label>
            <input  type="file" name="file" id="fileInput" required>
        </div>
        <div class="input-group">
            <div class="input-group-prepend">
                <span class="input-group-text">Description</span>
            </div>
            <textarea name="description" class="form-control" aria-label="Description" required></textarea>
        </div>
        <div class="my-3">
            <button type="submit" class="btn btn-outline-success">Add</button>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</div>
</@c.page>
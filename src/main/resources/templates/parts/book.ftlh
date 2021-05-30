<#macro book book>
    <div class="card border-success my-2" style="width: 15rem;" >
            <img src="/img/${book.filename}" class="card-img-top" alt="No image">
            <div class="card-body">
                <h5 class="card-title">${book.name}</h5>
            </div>
            <form action="/bookPage" method="get">
                <div class="card-body">
                    <input type="hidden" name="bookId" value="${book.id}">
                    <button type="submit" class="btn btn-outline-success">More</button>
                </div>
            </form>
    </div>
</#macro>
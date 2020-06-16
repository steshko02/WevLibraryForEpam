<#macro book book>
    <div class="card border-success my-2" style="width: 18rem;">
            <img src="/img/${book.filename}" class="card-img-top" alt="No image">
            <div class="card-body">
                <h5 class="card-title">${book.name}</h5>
                <p>Author: ${book.author}</p>
                <p>Genre: ${book.genre}</p>
                <p>Year: ${book.year}</p>
                <p>ISBN: ${book.isbn}</p>
                </p>
            </div>
            <form action="/bookPage" method="get">
                <div class="card-body">
                    <input type="hidden" name="bookId" value="${book.id}">
                    <button type="submit" class="btn btn-outline-success">More</button>
                </div>
            </form>

    </div>
</#macro>
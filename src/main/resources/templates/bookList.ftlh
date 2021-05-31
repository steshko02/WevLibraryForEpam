<#import "parts/common.ftlh" as c>
<#import "parts/book.ftlh" as b>
<@c.page>
      <form method="get" class="navbar-form navbar-right" action="filter">
          <div class="input-group">
              <div class="form-outline">
                  <input name="filter" type="search" id="form1" class="form-control" placeholder="Search..."/>
                  <label class="form-label" for="form1" ></label>
              </div>
              <button type="submit" class="btn btn-primary" style="width: 60px; height: 37px" >
              </button>
          </div>
    </form>
    <div>
        <h5>Our books</h5>
    </div>
    <div class="container" style="min-height: 100vh" >
        <div class="card-columns" >
            <#list books as book>
                <#if book.filename??>
                        <@b.book book/>
                </#if>
            <#else>
                <h5>Library is empty.</h5>
            </#list>
        </div>
    </div>
</@c.page>
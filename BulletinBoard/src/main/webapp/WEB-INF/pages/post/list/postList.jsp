<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="content-wrapper">
  <section class="content">
    <div class="row">
      <div class="col-12">
        <div class="card">
          <div class="card-header">
            <h3 class="card-title">Post List</h3>
          </div>
          <div class="card-body">
            <div class="row">
              <div class="col-sm-12 col-md-12">
                <div class="search-sec">
                  <c:url var="addAction" value="/searchPost"></c:url>
                  <form:form action="${addAction}"
                    modelAttribute="postList" method="post" id="form"
                    class="searchForm-mr">
                    <label><input type="text"
                      class="form-control form-control-sm search-text-pd"
                      aria-controls="example1" name="search-input"
                      value="${searchData }"></label>
                    <input name="searchPost" type="submit"
                      value="Search" class="btn btn-secondary">
                    <a
                      href="${pageContext.request.contextPath}/createPost"
                      class="btn btn-info">Add</a>
                    <a
                      href="${pageContext.request.contextPath}/uploadCSV"
                      class="btn btn-info">Upload</a>
                    <input type="submit" class="btn btn-info"
                      value="Download" name="downloadExcel">
                  </form:form>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-sm-12">
                <table id="example1"
                  class="table table-bordered table-striped">
                  <thead>
                    <tr>
                      <th>Post Title</th>
                      <th>Post Description</th>
                      <th>Posted User</th>
                      <th>Post Date</th>
                      <th></th>
                    </tr>
                  </thead>
                  <tbody>
                    <c:forEach items="${postList}" var="post"
                      varStatus="loop">
                      <tr>
                        <td>${post.title }</td>
                        <td>${post.description }</td>
                        <td>${post.createdUserId}</td>
                        <td>${post.createdAt}</td>
                        <td class="text-right py-0 align-middle">
                          <div class="btn-group btn-group-sm">
                            <a
                              href="<c:url value='detailPost?id=${post.id}'/>"
                              class="btn btn-info"><i
                              class="fas fa-eye"></i></a> <a
                              href="${pageContext.request.contextPath}/updatePost?id=${post.id}"
                              class="btn btn-secondary"><i
                              class="fas fa-edit"></i></a> <a href="#"
                              data-toggle="modal"
                              data-href="${pageContext.request.contextPath}/deletePost?id=${post.id }"
                              data-target="#myModal"
                              class="btn btn-danger"><i
                              class="fas fa-trash"></i></a>
                          </div>
                        </td>
                      </tr>
                    </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <c:if test="${noOfPages > 0}">
      <div class="row">
        <div class="col-sm-12 col-md-6">
          <div class="dataTables_paginate paging_simple_numbers"
            id="example1_paginate">
            <ul class="pagination">
              <c:if test="${currentPage != 1}">
                <li class="page-item"><a class="page-link"
                  href="postList?recordsPerPage=${recordsPerPage}&currentPage=${currentPage - 1}">Previous</a>
                </li>
              </c:if>
              <c:forEach begin="1" end="${noOfPages }" var="i">
                <c:choose>
                  <c:when test="${currentPage eq i}">
                    <li class="page-item active"><a
                      class="page-link"> ${i} <span class="sr-only">(current)</span>
                    </a></li>
                  </c:when>
                  <c:otherwise>
                    <li class="page-item"><a class="page-link"
                      href="postList?recordsPerPage=${recordsPerPage}&currentPage=${i}">
                        ${i} </a></li>
                  </c:otherwise>
                </c:choose>
              </c:forEach>

              <c:if test="${currentPage lt noOfPages}">
                <li class="page-item"><a class="page-link"
                  href="postList?recordsPerPage=${recordsPerPage}&currentPage=${currentPage + 1}">Next</a>
                </li>
              </c:if>
            </ul>
          </div>
        </div>
      </div>
    </c:if>
  </section>
</div>

<div class="modal fade" id="myModal" role="dialog">
  <div class="modal-dialog">
    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">Delete Post Confirmation!</h4>
      </div>
      <div class="modal-body">
        <p>Are You Sure Want to Delete!</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default"
          data-dismiss="modal">Cancel</button>
        <a class="btn btn-danger btn-ok">OK</a>
      </div>
    </div>
  </div>
</div>
<script>
$('#myModal').on(
	    'show.bs.modal',
	    function (e) {
	        $(this).find('.btn-ok').attr('href',
	            $(e.relatedTarget).data('href'));
	        $('.debug-url').html(
	            'Delete URL: <strong>' +
	            $(this).find('.btn-ok').attr('href') +
	            '</strong>');
	    });
</script>
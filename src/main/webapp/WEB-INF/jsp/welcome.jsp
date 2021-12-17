<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<petclinic:layout pageName="home">

    <div class="row-auto" style="display: flex; justify-content: center;">
        <div class="col-md" style="display: flex; justify-content: center;">
            <div class="btn-group-vertical">
                <sec:authorize access="!isAuthenticated()">
                    <button type="button" class="btn btn-primary btn-lg btn-block"
                        onClick='redirectOnClickRegister()'>Register</button>
                    <button type="button" class="btn btn-primary btn-lg btn-block"
                        onClick='redirectOnClickLogin()'>Login</button>
                </sec:authorize>
            </div>
        </div>
    </div>



    <script>
        function redirectOnClickLogin() {
            document.location = "/login";
        }
    </script>
    <script>
        function redirectOnClickRegister() {
            document.location = "/usersDwarf/register";
        }
    </script>

<h2>Global Statistics</h2>
<table class="table table-striped" >
    <tr>
        <th>Global time played:</th>
        <td><c:out value="${ttp}"/></td>
    </tr>
    <tr>
        <th>Global games played:</th>
        <td><c:out value="${tgp}"/></td>
    </tr>
    <tr>
        <th>Global games won:</th>
        <td><c:out value="${tgw}"/></td>
    </tr>
    <tr>
        <th>Global iron mined:</th>
        <td><c:out value="${ti}"/></td>
    </tr>
    <tr>
        <th>Global gold mined:</th>
        <td><c:out value="${to}"/></td>
    </tr>
    <tr>
        <th>Global steel forged:</th>
        <td><c:out value="${ts}"/></td>
    </tr>
    <tr>
        <th>Global objects acquired:</th>
        <td><c:out value="${tob}"/></td>
    </tr>
    <tr>
        <th>Global medal acquired:</th>
        <td><c:out value="${tm}"/></td>
    </tr>
</table>


</petclinic:layout>
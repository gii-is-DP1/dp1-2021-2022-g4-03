<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="usersDwarf">

    <div class="bodyBackground">

        <h2>
            <c:if test="${wrapper.userDwarf['new']}">New </c:if> User
        </h2>
        <form:form modelAttribute="wrapper" var="item" class="form-horizontal" id="add-userDwarf-form">
            <div class="panel form-square">  
                <div class="form-group has-feedback">
                    <petclinic:inputField label="Username" name="userDwarf.username" />
                    <petclinic:inputField label="Password" name="userDwarf.pass" />
                    <petclinic:inputField label="Email" name="userDwarf.email" />
                    <c:choose>
                        <c:when test="${registerCheck}">
                            <input type="hidden" name="userDwarf.active" value="true" />
                        </c:when>
                        <c:otherwise>
                            <petclinic:selectField name="userDwarf.active" label="Active" names="${boolList}"
                                size="2" />
                            <div class="form-group">
                                <label class="col-sm-offset-1 col-sm-2 control-label">Role:</label>
                                <div class="col-sm-offset-1 col-sm-10">
                                    <label class="checkbox-inline">
                                        <form:checkbox path="roles" name="roles" value="player" /> Player
                                    </label>
                                    <label class="checkbox-inline">
                                        <form:checkbox path="roles" name="roles" value="moderator" /> Moderator
                                    </label>
                                    <label class="checkbox-inline">
                                        <form:checkbox path="roles" name="roles" value="admin" /> Admin
                                    </label>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>

                </div>
                <div class="form-group">
                    <div class="col-sm-12 text-center">
                        <c:choose>
                            <c:when test="${userDwarf.userDwarf['new']}">
                                <button class="btn btn-default" type="submit">Add</button>
                            </c:when>
                            <c:otherwise>
                                <button class="btn btn-default" type="submit">Update</button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </form:form>
        <div id="fondo2"></div>
    </div>

</petclinic:layout>                           
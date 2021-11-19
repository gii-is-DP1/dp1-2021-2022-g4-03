<%@ page session="false" trimDirectiveWhitespaces="true" %>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
                    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                        <%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

                            <petclinic:layout pageName="usersDwarf">
                                <h2>
                                    <c:if test="${userDwarf['new']}">New </c:if> User
                                </h2>
                                <form:form modelAttribute="userDwarf" class="form-horizontal" id="add-userDwarf-form">
                                    <div class="form-group has-feedback">
                                        <petclinic:inputField label="Username" name="username" />
                                        <petclinic:inputField label="Password" name="pass" />
                                        <petclinic:inputField label="Email" name="email" />
                                        <c:choose>
                                            <c:when test="${registerCheck}">
                                                    <input type="hidden" name="active" value="true"/>
                                            </c:when>
                                            <c:otherwise>
                                                <petclinic:selectField name="active" label="Active" names="${boolList}"
                                                    size="2" />
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label">Role:</label>
                                                    <div class="col-sm-10">
                                                        <label class="checkbox-inline">
                                                            <input type="checkbox" name="role" id="playerCheckbox" value="player"> Player
                                                        </label>
                                                        <label class="checkbox-inline">
                                                            <input type="checkbox" name="role" id="moderatorCheckbox" value="moderator"> Moderator
                                                        </label>
                                                        <label class="checkbox-inline"> 
                                                            <input type="checkbox" name="role" id="adminCheckbox" value="admin"> Admin
                                                        </label>
                                                    </div>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>

                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-offset-2 col-sm-10">
                                            <c:choose>
                                                <c:when test="${userDwarf['new']}">
                                                    <button class="btn btn-default" type="submit">Add User</button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button class="btn btn-default" type="submit">Update User</button>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </form:form>
                            </petclinic:layout>
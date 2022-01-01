<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="profile">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <h2>Logros conseguidos:</h2><br><br>

        <tr>
            <th>Earn 100 gold:</th>
            <td><div class="w3-light-grey">
                    <div class="w3-container w3-green w3-center" style="width:50%">${progress}</div>
                </div><br>
            </td>
        </tr>
        <tr>
            <th>Earn 100 iron:</th>
            <td><div class="w3-light-grey">
                    <div class="w3-container w3-green w3-center" style="width:50%">25%</div>
                </div><br>
            </td>
        </tr>

        


    </table>

    




    <br/>
    <br/>
    <br/>
</petclinic:layout>


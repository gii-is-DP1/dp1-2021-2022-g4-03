<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<petclinic:layout pageName="profile">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <h2>Logros conseguidos:</h2><br><br>


    <div class="row">
    
        <div class="col-md-2">
            <img src="${pic1}" alt="gold" class="img-responsive img-circle" style="height: 3cm;
                        width: 115px;">            
        </div>
        <div class="col-md-8">
            <tr>
                <th><h2 style="font-size: larger;">${dp1}</h2></th>
                <td><div class="w3-light-grey" style="width: 50%;">
                        <div class="w3-container w3-green w3-center">${progress1}</div>
                    </div><br>
                </td>
            </tr>
        </div>
        
    </div>

    <div class="row">
        <div class="col-md-2">
            <img src="${pic2}" alt="iron" class="img-responsive img-circle" style="height: 3cm;
                        width: 115px;">            
        </div>
        <div class="col-md-8">
            <tr>
                <th><h2 style="font-size: larger;">${dp2}</h2></th>
                <td><div class="w3-light-grey" style="width: 50%;">
                        <div class="w3-container w3-green w3-center" >${progress2}</div>
                    </div><br>
                </td>
            </tr>
        </div>
    </div>

    <div class="row">
        <div class="col-md-2">
            <img src="${pic3}" alt="iron" class="img-responsive img-circle" style="height: 3cm;
                        width: 115px;">            
        </div>
        <div class="col-md-8">
            <tr>
                <th><h2 style="font-size: larger;">${dp3}</h2></th>
                <td><div class="w3-light-grey" style="width: 50%;">
                        <div class="w3-container w3-green w3-center">${progress3}</div>
                    </div><br>
                </td>
            </tr>
        </div>
    </div>          

    <div class="row">
        <div class="col-md-2">
            <img src="${pic4}" alt="iron" class="img-responsive img-circle" style="height: 3cm;
                        width: 115px;">
        </div>
        <div class="col-md-8">
            <tr>
                <th><h2 style="font-size: larger;">${dp4}</h2></th>
                <td><div class="w3-light-grey" style="width: 50%;">
                        <div class="w3-container w3-green w3-center">${progress4}</div>
                    </div><br>
                </td>
            </tr>
        </div>
    </div>

    <div id="fondo7"></div>
</petclinic:layout>


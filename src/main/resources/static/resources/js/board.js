"use strict";

function main(){
    let form = document.getElementById("testForm");

    form.addEventListener("submit", handleClick, false);
}

function handleClick(event) {
    event.preventDefault();
    console.log("Works");
    
}

document.addEventListener("DOMContentLoaded", main, false);
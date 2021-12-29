"use strict";

function main(){
    let form = document.getElementById("testForm");

    form.addEventListener("submit", handleClick, false);

    window.addEventListener("load", loadBackground, false);

}

function handleClick(event) {
    event.preventDefault();
    console.log("Works");

}

function loadBackground(event){
    console.log("This is triggering load");
    let canvas = document.getElementById("canvas");

    let ctx = canvas.getContext("2d");
    let image = document.getElementById('boardBackground');

    ctx.drawImage(image, 0, 0, canvas.width, canvas.height);
}


document.addEventListener("DOMContentLoaded", main, false);
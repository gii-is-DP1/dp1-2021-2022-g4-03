"use strict";

function main(){
    let form = document.getElementById("testForm");
    let startButton=document.getElementById("startButton");

    startButton.addEventListener("click", startGame);
    form.addEventListener("submit", handleClick, false);

    window.addEventListener("load", loadBackground, false);

}
 function startGame(event) {
    console.log("Works");
    let test="/api/game/"+gameId;
    let game = fetch(test, {
        headers : {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        credentials: 'include'
    })
        .then(response => response.json());

    console.log(game);

}

async function handleClick(event) {
    event.preventDefault();
    //console.log("Works");
    let game = await fetch(event.action,)
        .then(response => response.json());

}

function loadBackground(event){
    console.log("This is triggering load");
    let canvas = document.getElementById("canvas");

    let ctx = canvas.getContext("2d");
    let image = document.getElementById('boardBackground');

    ctx.drawImage(image, 0, 0, canvas.width, canvas.height);
}


document.addEventListener("DOMContentLoaded", main, false);

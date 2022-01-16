"use strict";

function main(){
    let form = document.getElementById("testForm");
    let actionButton=document.getElementById("takeTurn");

    actionButton.addEventListener("click", takeTurn);
    form.addEventListener("submit", handleClick, false);

    window.addEventListener("load", loadBackground, false);

}
 function takeTurn(event) {
    /*TODO: Change this function to be instead a turn detecting function. Make new function for starting game which
     only makes aesthetic changes to the page.*/
    //console.log("Works");


    //console.log(game);

}

async function handleClick(event) {
    //console.log("Works");

    //Block of code to fetch game
    let gameURL="/api/game/"+gameId;
    let game = fetch(gameURL, {
        headers : {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        credentials: 'include'
    })
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

"use strict";

function main(){
    let form = document.getElementById("testForm");
    let cardButton=document.getElementById("card1");

    cardButton.addEventListener("click", function (e){
        sample(e, cardButton.value);
    });
    form.addEventListener("submit", handleClick, false);

    window.addEventListener("load", loadBackground, false);

}

function sample(event, cardValue){
    let gameURL="/api/game/"+gameId;
    fetch(gameURL, {
        method :'POST',
        headers : {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
        body: JSON.stringify({playerAction: cardValue})
    }).then(data=>{
        console.log(data);
    })
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

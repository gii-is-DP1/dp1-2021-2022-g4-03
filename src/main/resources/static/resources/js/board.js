"use strict";

function main(){
    let form = document.getElementById("testForm");
    let cardButton1=document.getElementById("card1");
    let cardButton2=document.getElementById("card2");
    let cardButton3=document.getElementById("card3");
    let cardButton4=document.getElementById("card4");
    let cardButton5=document.getElementById("card5");
    let cardButton6=document.getElementById("card6");
    let cardButton7=document.getElementById("card7");
    let cardButton8=document.getElementById("card8");
    let cardButton9=document.getElementById("card9");
    let cardButton10=document.getElementById("card10");
    let cardButton11=document.getElementById("card11");
    let cardButton12=document.getElementById("card12");

    cardButton1.addEventListener("click", function (e){
        sample(e, cardButton1.value);
    });
    cardButton2.addEventListener("click", function (e){
        sample(e, cardButton2.value);
    });
    cardButton3.addEventListener("click", function (e){
        sample(e, cardButton3.value);
    });
    cardButton4.addEventListener("click", function (e){
        sample(e, cardButton4.value);
    });
    cardButton5.addEventListener("click", function (e){
        sample(e, cardButton5.value);
    });
    cardButton6.addEventListener("click", function (e){
        sample(e, cardButton6.value);
    });
    cardButton7.addEventListener("click", function (e){
        sample(e, cardButton7.value);
    });
    cardButton8.addEventListener("click", function (e){
        sample(e, cardButton8.value);
    });
    cardButton9.addEventListener("click", function (e){
        sample(e, cardButton9.value);
    });
    cardButton10.addEventListener("click", function (e){
        sample(e, cardButton10.value);
    });
    cardButton11.addEventListener("click", function (e){
        sample(e, cardButton11.value);
    });
    cardButton12.addEventListener("click", function (e){
        sample(e, cardButton12.value);
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

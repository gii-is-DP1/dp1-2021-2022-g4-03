"use strict";

function main() {

    let cardButton1 = document.getElementById("card1");
    let cardButton2 = document.getElementById("card2");
    let cardButton3 = document.getElementById("card3");
    let cardButton4 = document.getElementById("card4");
    let cardButton5 = document.getElementById("card5");
    let cardButton6 = document.getElementById("card6");
    let cardButton7 = document.getElementById("card7");
    let cardButton8 = document.getElementById("card8");
    let cardButton9 = document.getElementById("card9");
    let cardButton10 = document.getElementById("card10");
    let cardButton11 = document.getElementById("card11");
    let cardButton12 = document.getElementById("card12");


    cardButton1.addEventListener("click", function (e) {
        sample(e, cardButton1.value);
    });
    cardButton2.addEventListener("click", function (e) {
        sample(e, cardButton2.value);
    });
    cardButton3.addEventListener("click", function (e) {
        sample(e, cardButton3.value);
    });
    cardButton4.addEventListener("click", function (e) {
        sample(e, cardButton4.value);
    });
    cardButton5.addEventListener("click", function (e) {
        sample(e, cardButton5.value);
    });
    cardButton6.addEventListener("click", function (e) {
        sample(e, cardButton6.value);
    });
    cardButton7.addEventListener("click", function (e) {
        sample(e, cardButton7.value);
    });
    cardButton8.addEventListener("click", function (e) {
        sample(e, cardButton8.value);
    });
    cardButton9.addEventListener("click", function (e) {
        sample(e, cardButton9.value);
    });
    cardButton10.addEventListener("click", function (e) {
        sample(e, cardButton10.value);
    });
    cardButton11.addEventListener("click", function (e) {
        sample(e, cardButton11.value);
    });
    cardButton12.addEventListener("click", function (e) {
        sample(e, cardButton12.value);
    });

    // window.addEventListener("load", loadBackground, false);
    window.addEventListener("load", loadCards);
    window.addEventListener("load", loadSpecialCards);
    wondow.addEventListener("load", function (e) {
        sample(e, -1);
    });

}

async function loadCards() {
    for (let i = 0; i < 9; i++) {
        let node = document.getElementById("cell" + i);
        let cardId = node.name;
        let card = await getCard(cardId);
        node.src = card.cardImage;
    }
}

async function loadSpecialCards() {
    for (let i = 9; i < 12; i++) {
        let node = document.getElementById("cell" + i);
        let cardId = node.name;
        let card = await getCard(cardId);
        node.src = card.cardImage;
    }
}

async function sample(event, cardValue) {
    let gameId = document.getElementById("gameId").value;
    let currentUser = document.getElementById("currentUser").value;

    let game = await getGame(gameId, cardValue, currentUser);
    let worker0 = game.playerState_0.worker0;
    let worker1 = game.playerState_0.worker1;
    let worker2 = game.playerState_0.worker2;
    let worker3 = game.playerState_0.worker3;
    let worker4 = game.playerState_1.worker0;
    let worker5 = game.playerState_1.worker1;
    let worker6 = game.playerState_1.worker2;
    let worker7 = game.playerState_1.worker3;
    let worker8 = game.playerState_2.worker0;
    let worker9 = game.playerState_2.worker1;
    let worker10 = game.playerState_2.worker2;
    let worker11 = game.playerState_2.worker3;
    let workerList = [
        worker0, worker1, worker2, worker3, worker4, worker5, worker6, worker7, worker8, worker9, worker10, worker11];
    for (let i = 0; i < 9; i++) {
        if (workerList.includes(i)) {
            let worker = document.getElementById("worker" + i);
            worker.style = "display: block";
            console.log(worker.style);
        }
    }
    console.log(game);
}

function getGame(gameId, cardValue, currentUser) {
    let gameURL = "/api/game/" + gameId;
    return fetch(gameURL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
        body: JSON.stringify({playerAction: cardValue, currentUser: currentUser})
    }).then(response =>
        response.json()
    );
}

function getCard(cardId) {
    let cardURL = "/api/card/" + cardId;
    return fetch(cardURL, {
        headers: {
            'Accept': 'application/json',
        },
        credentials: 'include'
    }).then(response => response.json());
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
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        },
        credentials: 'include'
    })
        .then(response => response.json());

}

function loadBackground(event) {
    console.log("This is triggering load");
    let canvas = document.getElementById("canvas");

    let ctx = canvas.getContext("2d");
    let image = document.getElementById('boardBackground');

    ctx.drawImage(image, 0, 0, canvas.width, canvas.height);
}


document.addEventListener("DOMContentLoaded", main, false);

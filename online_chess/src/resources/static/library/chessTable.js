import {
    getPlayerRoute,
    localGamePage,
    makeMoveRoute,
    onlineGamePage,
    puzzlePage,
    url,
    validMovesRoute
} from "../index.js";
import {makeMovePuzzle} from "./chessTablePuzzle.js";

export let isGameOver = false
export let gameId
export let username

let clickedFigure = null;
let activePlayerColor = null;
let WhiteFigures = []
let BlackFigures = []
let table
let blackPlayerName
let whitePLayerName
let gameStatus
let isWhitePlayer
const allSquare = Array.from(document.getElementsByClassName('whiteSquare')).concat(Array.from(document.getElementsByClassName('blackSquare')));

export function makeMove(from,to){
    if(isGameOver){ return;}

    let xhr = new XMLHttpRequest()
    xhr.open('GET',makeMoveRoute+"/"+gameId+"/"+from+"/"+to,true)
    xhr.onload = function(){
        if(xhr.status === 200){
            console.log("makeMove()");
            console.log("response",this.response);
            if(document.URL === puzzlePage){
                makeMovePuzzle(from,to)
                getTableUpdateForPuzzle(JSON.parse(this.response))
            }
            else if(document.URL === onlineGamePage){
                getTableUpdateForOnline(JSON.parse(this.response))
            }
            else if(document.URL === localGamePage){
                getTableUpdateForLocal(JSON.parse(this.response))
            }
        }
        else {
            console.log("Problem with makeMove request!")
        }
    }
    xhr.send()
}

export function getTableUpdateForPuzzle(data){
    console.log("getTableUpdateForPuzzle")
    console.log(data);
    setVariables(data)
    clearFlagClassesFromTable()
    displayGameNotation()
    clearTakenFiguresList()
    displayTakenFigures()
    displayFigureToTable()
    removeEventListenersFromSquares()
    addEventListenerToFiguresLocalGame()
}

export function getTableUpdateForOnline(data){
    console.log("getTableUpdate")
    console.log(data);

    setVariables(data)
    displayGameInformation()
    addEventListenerToFiguresOnlineGame()
    clearFlagClassesFromTable()
    handleTableOrientationForOnline()
    displayPlayerTurn()
    displayGameNotation()
    clearTakenFiguresList()
    displayTakenFigures()
    displayFigureToTable()
    removeEventListenersFromSquares()
    addEventListenerToFiguresOnlineGame()
}
export function getTableUpdateForLocal(data){
    console.log("getTableUpdateForLocal")
    console.log(data);

    setVariables(data)
    clearFlagClassesFromTable()
    handleTableOrientationForLocal()
    displayGameNotation()
    clearTakenFiguresList()
    displayTakenFigures()
    displayFigureToTable()
    removeEventListenersFromSquares()
    addEventListenerToFiguresLocalGame()
}

function setVariables(data) {
    table = data["table"];

    isGameOver = table["isGameOver"]
    activePlayerColor = table["activePlayerColor"]
    if(data["blackPlayer"]) {
        blackPlayerName = data["blackPlayer"]["username"];
    }
    if(data["whitePlayer"]) {
        whitePLayerName = data["whitePlayer"]["username"];
    }
    isWhitePlayer = (whitePLayerName === username);
    gameId = data.id
    gameStatus = data.status;
}

function displayGameInformation(){
    document.getElementById("gameStatusDiv").innerText = gameStatus
    document.getElementById("WhitePlayerDiv").innerText = whitePLayerName;
    document.getElementById("BlackPlayerDiv").innerText = blackPlayerName;
    document.getElementById("GameID").innerText = gameId;
}

function clearFlagClassesFromTable(){
    allSquare.forEach(square => {
        square.replaceChildren();
        square.classList.remove("BLACK")
        square.classList.remove("WHITE")
    })
    Array.from(document.getElementsByClassName("validMove")).forEach(square => {
        square.classList.remove("validMove")
    })
}

function handleTableOrientationForLocal() {
    if(activePlayerColor === "WHITE"){
        document.getElementById("chessTable").classList.remove("BlackPlayerTable")
    }
    else{
        document.getElementById("chessTable").classList.add("BlackPlayerTable")
    }
}
function handleTableOrientationForOnline() {
    if(isWhitePlayer) {
        document.getElementById("chessTable").classList.remove("BlackPlayerTable")
    }
    else {
        document.getElementById("chessTable").classList.add("BlackPlayerTable")
    }
}

function displayPlayerTurn() {
    if(isGameOver){
        document.getElementById("TurnsH1").innerText = "CheckMate "+activePlayerColor+" Win"
    }
    else{
        document.getElementById("TurnsH1").innerText = activePlayerColor +" player's turn"
    }
}

function displayGameNotation() {
    document.getElementById("gameNotation").innerText = table["gameNotation"];
}

function clearTakenFiguresList() {
    document.getElementById('takenFiguresWhite').replaceChildren();
    document.getElementById('takenFiguresBlack').replaceChildren();
}

function displayTakenFigures() {
    for (let figure of table.takenFigures) {
        const img = document.createElement("img");
        img.src = figure.imageSource

        if(figure["color"] === "WHITE"){
            document.getElementById('takenFiguresBlack').appendChild(img)
        }
        else{
            document.getElementById('takenFiguresWhite').appendChild(img)
        }
    }
}

function displayFigureToTable() {
    for (let figure in table["figures"]) {
        const img = document.createElement("img");
        const posX = table["figures"][figure]["position"]["posX"]
        const posY = table["figures"][figure]["position"]["posY"]
        const notation = PositionToNotation(posX,posY)

        img.src = table["figures"][figure]["imageSource"]
        document.getElementById(notation).replaceChildren(img)
        document.getElementById(notation).classList.add(table["figures"][figure]["color"])
    }
    WhiteFigures = Array.from(document.getElementsByClassName("WHITE"));
    BlackFigures = Array.from(document.getElementsByClassName("BLACK"));
}

function PositionToNotation(posX,posY){
    let notation = ''
    switch(posX){
        case 0: notation+='A'; break;
        case 1: notation+='B'; break;
        case 2: notation+='C'; break;
        case 3: notation+='D'; break;
        case 4: notation+='E'; break;
        case 5: notation+='F'; break;
        case 6: notation+='G'; break;
        case 7: notation+='H'; break;
    }
    switch(posY){
        case 0: notation+='8'; break;
        case 1: notation+='7'; break;
        case 2: notation+='6'; break;
        case 3: notation+='5'; break;
        case 4: notation+='4'; break;
        case 5: notation+='3'; break;
        case 6: notation+='2'; break;
        case 7: notation+='1'; break;
    }
    return notation;
}

function removeEventListenersFromSquares() {
    allSquare.forEach(square => {
        square.removeEventListener("click",ClickFigure)
        square.removeEventListener("click",ClickSquare)
    })
}

function addEventListenerToFiguresOnlineGame() {
    if(activePlayerColor === 'WHITE' && isWhitePlayer){
        WhiteFigures.forEach(figure => figure.addEventListener("click",ClickFigure,false))
    }
    else if(activePlayerColor === 'BLACK' && !isWhitePlayer){
        BlackFigures.forEach(figure => figure.addEventListener("click",ClickFigure,false))
    }
}
function addEventListenerToFiguresLocalGame() {
    if(activePlayerColor === 'WHITE'){
        WhiteFigures.forEach(figure => figure.addEventListener("click",ClickFigure,false))
    }
    else if(activePlayerColor === 'BLACK'){
        BlackFigures.forEach(figure => figure.addEventListener("click",ClickFigure,false))
    }
}

export function getValidMoves(pos){
    let xhr = new XMLHttpRequest()
    xhr.open('GET',validMovesRoute+gameId+'/'+pos ,true)
    xhr.onload = function(){
        if(xhr.status === 200) {
            console.log("getValidMoves success")
            const validMovesResponse = Array.from(JSON.parse(this.response))
            console.log("VALID ",validMovesResponse)
            clearValidMoves()
            setUpEventListenersForValidSquares(validMovesResponse)
        }
        else {
            console.log("Problem with getValidMoves request !!!")
        }
    }
    xhr.send()
}

function clearValidMoves() {
    Array.from(document.getElementsByClassName("validMove")).forEach(square => {
        square.classList.remove("validMove")
    })
}

function setUpEventListenersForValidSquares(validMoves) {
    validMoves.forEach(validMove => {
        document.getElementById(validMove).classList.add("validMove")
        document.getElementById(validMove).removeEventListener("click",ClickFigure)
        document.getElementById(validMove).addEventListener("click",ClickSquare,true)
    })
}

export function ClickFigure(event){
    if(isGameOver){ return;}
    clickedFigure = event.target.parentNode.getAttribute('id');

    allSquare.forEach(square => {
        square.removeEventListener("click",ClickSquare,true)
    })

    getValidMoves(clickedFigure)
}

export function ClickSquare(event){
    if(isGameOver){ return;}
    if (clickedFigure) {
        let index
        if (event.target.tagName === 'IMG') {
            index = event.target.parentNode.getAttribute('id')
        }
        else{
            index = event.target.getAttribute('id')
        }

        const figure = clickedFigure
        clickedFigure = undefined;
        makeMove(figure,index)
    }
}

(function getUser(){
    let xhr = new XMLHttpRequest()
    xhr.open('POST', getPlayerRoute,true)
    xhr.onload = function(){
        if(xhr.status === 200){
            console.log("getValidMoves success")
            const user = JSON.parse(this.response)
            username = user.username
        }
        else {
            console.log("Problem with GETUSER request !!!")
        }
    }
    xhr.send()
})();
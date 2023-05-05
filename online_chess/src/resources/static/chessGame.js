let clickedFigure = null;
let activePlayerColor = null;
let EmptyOrEnemySquares = []
let Whitefigures = []
let Blackfigures = []
let isGameOver = false


const allSquare = Array.from(document.getElementsByClassName('whiteSquare')).concat(Array.from(document.getElementsByClassName('blackSquare')));
const url = 'http://localhost:8081'
const makeMoveRoute = url+"/game/makeMove"
const validMovesRoute = url+"/game/validMoves/"
const newGameRoute = url+'/updateTable/newGame'
const gameStartRoute = url+"/game/start"
const connectRandomRoute = url+"/game/connectRandom"
const connectRoute = url+"/game/connect"
const dashboardRoute = url+"/dashboard"
const onlineGameRoute = url+"/onlineGame"
const getPlayerRoute = url+"/game/getPlayer"



function Start(){
    
    getUser()
    if(sessionStorage.getItem("gameID") == 'undefined' || sessionStorage.getItem("gameID") == undefined){
        connectToRandom();
    }
    else{
        connectToSpecificGame(sessionStorage.getItem("gameID"));
    }
}

function makeMove(from,to){
    if(isGameOver){ return;}

    let xhr = new XMLHttpRequest()
    xhr.open('GET',makeMoveRoute+"/"+sessionStorage.getItem("gameID")+"/"+from+"/"+to,true)
    xhr.onload = function(){
     if(xhr.status == 200){
        console.log("makeMove()");
        console.log("response",this.response);
        getTableUpdate(JSON.parse(this.response))
     }
     else {
         console.log("Problem with makeMove request!")
     }
    }
    xhr.send()
}

 function getTableUpdate(data){

        console.log("getTableUpdate")
        console.log(data);

        document.getElementById("gameStatusDiv").innerText = data.status

        const username = sessionStorage.getItem('username')
        const isWhitePlayer = sessionStorage.getItem('isWhitePlayer') == "true"
        const table = data["table"];

        allSquare.forEach(square => {
            square.replaceChildren();
            square.classList.remove("BLACK")
            square.classList.remove("WHITE")
        })   

        if(isWhitePlayer){
            document.getElementById("chessTable").classList.remove("BlackPlayerTable")

            if(data["blackPlayer"]!=null){
                document.getElementById("playingWith").innerText = data["blackPlayer"]["username"]
            }

        }
        else{

            document.getElementById("chessTable").classList.add("BlackPlayerTable")
            if(data["whitePlayer"] !=null){
                document.getElementById("playingWith").innerText = data["whitePlayer"]["username"]
            }
        }
        document.getElementById("GameID").innerText = sessionStorage.getItem("gameID")


        Array.from(document.getElementsByClassName("validMove")).forEach(square => {
            square.classList.remove("validMove")
        })

        isGameOver = table["isGameOver"]
        activePlayerColor = table["activePlayerColor"]

        if(isGameOver){
            document.getElementById("TurnsH1").innerText = "CheckMate "+activePlayerColor+" Win"
            sessionStorage.setItem("gameID",'undefined')
        }
        else{
            document.getElementById("TurnsH1").innerText = activePlayerColor +" player's turn"
        }

        document.getElementById("gameNotation").innerText = table["gameNotation"]

        const takenFiguresDivWhite = document.getElementById('takenFiguresWhite')
        const takenFiguresDivBlack = document.getElementById('takenFiguresBlack')
        takenFiguresDivWhite.replaceChildren();
        takenFiguresDivBlack.replaceChildren();

        for (let figure in table["takenFigures"]) {
            const img = document.createElement("img");
            img.src = table["takenFigures"][figure]["imageSource"]

            if(table["takenFigures"][figure]["color"] == "WHITE"){
                takenFiguresDivBlack.appendChild(img)
            }
            else{
                takenFiguresDivWhite.appendChild(img)
            }

        }

        for (let figure in table["figures"]) {

            const img = document.createElement("img");

            const posX = table["figures"][figure]["position"]["posX"]
            const posY = table["figures"][figure]["position"]["posY"]
            const notation = PositionToNotation(posX,posY)

            img.src = table["figures"][figure]["imageSource"]
            document.getElementById(notation).replaceChildren(img)
            document.getElementById(notation).classList.add(table["figures"][figure]["color"])

        }
        Whitefigures = Array.from(document.getElementsByClassName("WHITE"));
        Blackfigures = Array.from(document.getElementsByClassName("BLACK"));

        allSquare.forEach(square => {
            square.removeEventListener("click",ClickFigure)
            square.removeEventListener("click",ClickSquare)
        })

        if(activePlayerColor == 'WHITE' && isWhitePlayer){
            Whitefigures.forEach(figure => figure.addEventListener("click",ClickFigure,false))
        }
        else if(activePlayerColor == 'BLACK' && !isWhitePlayer){
            Blackfigures.forEach(figure => figure.addEventListener("click",ClickFigure,false))
        }
}

function handleBlackPlayerTable(){
    console.log("handleBlackPlayerTable")
    if(!(sessionStorage.getItem("isWhitePlayer"))){
        document.getElementById("chessTable").classList.add("BlackPlayerTable")
    }
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


function getValidMoves(pos){
   let xhr = new XMLHttpRequest()
   xhr.open('GET',validMovesRoute+sessionStorage.getItem("gameID")+'/'+pos ,true)
   xhr.onload = function(){
    if(xhr.status == 200){
        console.log("getValidMoves success")

        const validMovesResponse = Array.from(JSON.parse(this.response))

        Array.from(document.getElementsByClassName("validMove")).forEach(square => {
            square.classList.remove("validMove")
        })

        validMovesResponse.forEach(validMove => {
            document.getElementById(validMove).classList.add("validMove")
            document.getElementById(validMove).removeEventListener("click",ClickFigure)
            document.getElementById(validMove).addEventListener("click",ClickSquare,true)

        })
    }
    else {
        console.log("Problem with getValidMoves request !!!")
    }
   }
   xhr.send()
}

function newGame(){
    let xhr = new XMLHttpRequest()
   xhr.open('GET',newGameRoute,true)
   xhr.onload = function(){
        if(xhr.status == 200){
            console.log("NEwGame")
            clearTable()
            getTableUpdate()
        }
        else {
            console.log("Problem with newGame request !!!")
        }
   }
   xhr.send()

}

function ClickFigure(event){
    if(isGameOver){ return;}
    const index = event.target.parentNode.getAttribute('id')
    clickedFigure = index;
    
    allSquare.forEach(square => {
       square.removeEventListener("click",ClickSquare,true)
    })
    
    getValidMoves(clickedFigure)
}


function ClickSquare(event){
    if(isGameOver){ return;}
    if (clickedFigure) {
            let index = undefined
            if (event.target.tagName === 'IMG') {
                 index = event.target.parentNode.getAttribute('id')
            }
            else{
                 index = event.target.getAttribute('id')
            }

            const figure = clickedFigure
            clickedFigure = undefined;
            selectedFigure = null;
            validMoves = null;

            makeMove(figure,index)
    }

}


console.log("LocalGame SCRIPT LOAD")

const localGameRoute = url+"/game/getLocalGame"
let gameID = ""

function getLocalGame(){
    let xhr = new XMLHttpRequest()
    xhr.open('GET',localGameRoute,true)
    xhr.onload = function(){
     if(xhr.status == 200){
        console.log("getLocalGame()");
        const data = JSON.parse(this.response)
        getTableUpdate(data)
        gameID = data.id
        //sessionStorage.setItem("gameID",data.id)
     }
     else {
         console.log("Problem with getLocalGame request !!!")
     }
    }
    xhr.send()
}

function makeMove(from,to){
    if(isGameOver){ return;}

    let xhr = new XMLHttpRequest()
    xhr.open('GET',makeMoveRoute+"/"+gameID+"/"+from+"/"+to,true)
    xhr.onload = function(){
     if(xhr.status == 200){
        console.log("makeMove()");
        getTableUpdate(JSON.parse(this.response))
     }
     else {
         console.log("Problem with getValidMoves request !!!")
     }
    }
    xhr.send()
}

function getTableUpdate(data){

    console.log("data",data)
    console.log("getTableUpdate getPuzzle")
    const table = data["table"];

    const isWhitePlayer = data.table.activePlayerColor == 'WHITE';
    
    console.log("isWhitePlayer",isWhitePlayer)

    console.log("CLEAR ALL")
    allSquare.forEach(square => {
        square.replaceChildren();
        square.classList.remove("BLACK")
        square.classList.remove("WHITE")
    })   

    if(isWhitePlayer){
        document.getElementById("chessTable").classList.remove("BlackPlayerTable")
    }
    else{
        document.getElementById("chessTable").classList.add("BlackPlayerTable")
    }


    Array.from(document.getElementsByClassName("validMove")).forEach(square => {
        square.classList.remove("validMove")
    })



    document.getElementById("gameNotation").innerText = table["gameNotation"]


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

    if(isWhitePlayer){
        Whitefigures.forEach(figure => figure.addEventListener("click",ClickFigure,false))
    }else{
        Blackfigures.forEach(figure => figure.addEventListener("click",ClickFigure,false))
    }

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

function getValidMoves(pos){
    let xhr = new XMLHttpRequest()
    xhr.open('GET',validMovesRoute+gameID+'/'+pos ,true)
    xhr.onload = function(){
     if(xhr.status == 200){
         console.log("validMoves success")
 
         const validMovesResponse = Array.from(JSON.parse(this.response))
 
         console.log(validMovesResponse)
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



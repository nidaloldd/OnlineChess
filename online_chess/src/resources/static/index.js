const allSquare = Array.from(document.getElementsByClassName('whiteSquare')).concat(Array.from(document.getElementsByClassName('blackSquare')));
let clickedFigure = null;
let validMoves = null;
let activePlayerColor = null;
let EmptyOrEnemySquares = []
let Whitefigures = []
let Blackfigures = []
let isGameOver = false
getTableUpdate()



function makeMove(from,to){
    if(isGameOver){ return;}
   let xhr = new XMLHttpRequest()
   xhr.open('GET','http://localhost:8081/updateTable/'+from+'/'+to,true)
   xhr.onload = function(){
        if(xhr.status == 200){
            console.log("NEWPAGE success")
            clickedFigure = null;
            allSquare.forEach(square => {
                document.getElementById(square.id).replaceChildren();
                square.classList.remove("validMove")
            })
            getTableUpdate()
        }
    }
    xhr.send()

}

function clearTable(){

}



 function getTableUpdate(){
    let xhr = new XMLHttpRequest()
    xhr.open('GET','http://localhost:8081/api/fullTable',true)
   xhr.onload  = function(){
    if(xhr.status == 200){
        console.log("api/fullTable success")

        console.log(this.response)
        const table = JSON.parse(this.response)


        isGameOver = table["isGameOver"]
        activePlayerColor = table["activePlayerColor"]

        if(isGameOver){
            document.getElementById("TurnsH1").innerText = "CheckMate "+activePlayerColor+" Win"
        }
        else{
            document.getElementById("TurnsH1").innerText = activePlayerColor
        }

        document.getElementById("gameNotation").innerText = table["gameNotation"]

        const takenFiguresDivWhite = document.getElementById('takenFiguresWhite')
        const takenFiguresDivBlack = document.getElementById('takenFiguresBlack')
        takenFiguresDivWhite.replaceChildren();
        takenFiguresDivBlack.replaceChildren();
        /*
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
        */

        for (let figure in table["figures"]) {

            const img = document.createElement("img");

            const posX = table["figures"][figure]["position"]["posX"]
            const posY = table["figures"][figure]["position"]["posY"]
            const notation = PositionToNotation(posX,posY)

            img.src = table["figures"][figure]["imageSource"]
            document.getElementById(notation).appendChild(img)
            //img.classList.add(table["figures"][figure]["color"])
            document.getElementById(notation).classList.remove("BLACK")
            document.getElementById(notation).classList.remove("WHITE")
            document.getElementById(notation).classList.add(table["figures"][figure]["color"])

        }
        Whitefigures = Array.from(document.getElementsByClassName("WHITE"));
        Blackfigures = Array.from(document.getElementsByClassName("BLACK"));

        allSquare.forEach(square => {
            square.removeEventListener("click",ClickFigure)
        })
        if(activePlayerColor == 'WHITE'){
            Whitefigures.forEach(figure => figure.addEventListener("click",ClickFigure))
        }
        else{
            Blackfigures.forEach(figure => figure.addEventListener("click",ClickFigure))
        }
    }
}
xhr.send()

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
   xhr.open('GET','http://localhost:8081/api/validMoves/'+pos ,true)
   xhr.onload = function(){

    if(xhr.status == 200){
        console.log("validMoves success")

        const validMovesResponse = Array.from(JSON.parse(this.response))

        allSquare.forEach(square => {
            Array.from(document.getElementsByClassName("validMove")).forEach(square => {
                square.classList.remove("validMove")
            })
        })

        validMovesResponse.forEach(validMove => {
            document.getElementById(validMove).classList.add("validMove")
            document.getElementById(validMove).addEventListener("click",ClickSquare,true)

        })
    }
   }
   xhr.send()
}

function newGame(){
    let xhr = new XMLHttpRequest()
   xhr.open('GET','http://localhost:8081/updateTable/newGame',true)
   xhr.onload = function(){
        if(xhr.status == 200){
            console.log("NEwGame")
            clearTable()
            getTableUpdate()
        }
   }
   xhr.send()

}

function ClickFigure(){
    if(isGameOver){ return;}
    const index = event.target.parentNode.getAttribute('id')
    clickedFigure = index;
    getValidMoves(clickedFigure)
}


function ClickSquare(){
    if(isGameOver){ return;}
    if (clickedFigure) {

            let index = undefined
            if (event.target.tagName === 'IMG') {
                 index = event.target.parentNode.getAttribute('id')
            }
            else{
                 index = event.target.getAttribute('id')
            }

            // Move the selected figure to the clicked square
            const figure = clickedFigure
            clickedFigure = undefined;
            selectedFigure = null;
            validMoves = null;

            makeMove(figure,index)

    }

}


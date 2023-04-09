const getRandomPuzzleRoute = url+"/game/getRandomPuzzle"
const getPuzzleRoute = url+"/game/getPuzzle/"


let solution = ""
let enemyMoves = ""
let gameID = ""
let puzzleNumber = ""

document.getElementById("restartButton").classList.add("disabled")

function getRandomPuzzle(){

    let xhr = new XMLHttpRequest()
    xhr.open('GET',getRandomPuzzleRoute,true)
    xhr.onload = function(){
     if(xhr.status == 200){
        console.log("getRandomPuzzle success");

        document.getElementById("solvedPuzzleDiv").replaceChildren();

        let puzzleData = JSON.parse(this.response)

        isGameOver = false;
        getTableUpdatePuzzle(puzzleData.chessParty)
        gameID = puzzleData.chessParty.id
        solution = puzzleData.chessPuzzle.solutionMoves;
        enemyMoves = puzzleData.chessPuzzle.enemyMoves;
        puzzleNumber = puzzleData.chessPuzzle.id;
        document.getElementById("restartButton").classList.remove("disabled")

     }
     else {
         console.log("Problem with getRandomPuzzle request!")
     }
    }
    xhr.send()
}

function getPuzzle(){

    let xhr = new XMLHttpRequest()
    xhr.open('GET',getPuzzleRoute+puzzleNumber,true)
    xhr.onload = function(){
     if(xhr.status == 200){
        console.log("getPuzzle success");

        document.getElementById("solvedPuzzleDiv").replaceChildren();

        puzzleData = JSON.parse(this.response)

        isGameOver = false;
        getTableUpdatePuzzle(puzzleData.chessParty)
        gameID = puzzleData.chessParty.id
        solution = puzzleData.chessPuzzle.solutionMoves;
        enemyMoves = puzzleData.chessPuzzle.enemyMoves;

     }
     else {
         console.log("Problem with getPuzzle request!")
     }
    }
    xhr.send()
}

function makeMovePuzzle(from,to){
    if(isGameOver){ return;}

    let xhr = new XMLHttpRequest()
    xhr.open('GET',makeMoveRoute+"/"+gameID+"/"+from+"/"+to,true)
    xhr.onload = function(){
     if(xhr.status == 200){
        console.log("makeMove success");


        if(document.getElementById(from).classList.contains("WHITE")){

            handlePuzzleSolutions(from,to)
        }
        getTableUpdatePuzzle(JSON.parse(this.response))
     }
     else {
         console.log("Problem with makeMove request!")
     }
    }
    xhr.send()
}

function handlePuzzleSolutions(from,to){
    if(isGameOver){ return;}

    let subSolution = solution.substring(0,solution.indexOf(","));
    if(subSolution==""){subSolution = solution}

    if(subSolution == from+"-"+to){

        solution = solution.substring(6,solution.length);

        if(solution == ""){

            const div = document.createElement("div");
            document.getElementById("solvedPuzzleDiv").appendChild(div);

            div.classList.add("alert") ;
            div.classList.add("alert-success");
            div.innerText = "Congratulation, You solved the Puzzle";


            isGameOver = true;
        }
        else{
            let subenemyMoves = enemyMoves.substring(0,enemyMoves.indexOf(","));
            if(subenemyMoves==""){subenemyMoves = enemyMoves}
            
            makeMovePuzzle(subenemyMoves.substring(0,2),subenemyMoves.substring(3,5))
            enemyMoves = enemyMoves.substring(6,enemyMoves.length);
        }
    }
    else{

        const div = document.createElement("div");
        document.getElementById("solvedPuzzleDiv").appendChild(div);

        div.classList.add("alert") ;
        div.classList.add("alert-danger");
        div.innerText = "Bad Move";

        isGameOver = true;
    }
}


function getTableUpdatePuzzle(data){

    console.log("getTableUpdate")
    const table = data.table;


    const isWhitePlayer = table.activePlayerColor == 'WHITE';

    allSquare.forEach(square => {
        square.replaceChildren();
        square.classList.remove("BLACK")
        square.classList.remove("WHITE")
    })   



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

            makeMovePuzzle(figure,index)

    }

}





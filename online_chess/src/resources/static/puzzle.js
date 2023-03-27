console.log("PUZZLE SCRIPT LOAD")

const getPuzzleRoute = url+"/game/getPuzzle"
let solution = ""
let enemyMoves = ""
let gameID = ""

function getPuzzle(){

    let xhr = new XMLHttpRequest()
    xhr.open('GET',getPuzzleRoute,true)
    xhr.onload = function(){
     if(xhr.status == 200){
        console.log("getPuzzle()");
        console.log(this.response);

        document.getElementById("solvedPuzzleDiv").replaceChildren();


        let data = JSON.parse(this.response)
        console.log(data);
        isGameOver = false;
        getTableUpdatePuzzle(data.chessParty)
        gameID = data.chessParty.id
        solution = data.chessPuzzle.solutionMoves;
        enemyMoves = data.chessPuzzle.enemyMoves;
        console.log("solution",solution);
        console.log("enemyMoves",enemyMoves);
     }
     else {
         console.log("Problem with getPuzzle request !!!")
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
        console.log("makeMove()");
        console.log("solution",solution)
        console.log("from+to",from+"-"+to)

        if(document.getElementById(from).classList.contains("WHITE")){

            handlePuzzleSolutions(from,to)
        }
        getTableUpdatePuzzle(JSON.parse(this.response))
     }
     else {
         console.log("Problem with getValidMoves request !!!")
     }
    }
    xhr.send()
}

function handlePuzzleSolutions(from,to){
    if(isGameOver){ return;}

    let subSolution = solution.substring(0,solution.indexOf(","));
    if(subSolution==""){subSolution = solution}

    if(subSolution == from+"-"+to){
        console.log("HELYES")

        solution = solution.substring(6,solution.length);

        console.log("solution%!!!!!!!!",solution)
        if(solution == ""){
            console.log("GYŐZELEM")

            const div = document.createElement("div");
            document.getElementById("solvedPuzzleDiv").appendChild(div);

            div.classList.add("alert") ;
            div.classList.add("alert-success");
            div.innerText = "Congratulation, You solved the Puzzle";


            isGameOver = true;
        }
        else{
            console.log("ELENFÉL LÉP")
            let subenemyMoves = enemyMoves.substring(0,enemyMoves.indexOf(","));
            if(subenemyMoves==""){subenemyMoves = enemyMoves}
            
            console.log("!!",subenemyMoves.substring(0,2)+":"+subenemyMoves.substring(3,5))
            makeMovePuzzle(subenemyMoves.substring(0,2),subenemyMoves.substring(3,5))
            enemyMoves = enemyMoves.substring(6,enemyMoves.length);
        }
    }
    else{
        console.log("NEM HELYES")

        const div = document.createElement("div");
        document.getElementById("solvedPuzzleDiv").appendChild(div);

        div.classList.add("alert") ;
        div.classList.add("alert-danger");
        div.innerText = "Bad Move";

        isGameOver = true;
    }
}


function getTableUpdatePuzzle(data){

    console.log("data",data)
    console.log("getTableUpdate getPuzzle")
    const table = data.table;


    const isWhitePlayer = table.activePlayerColor == 'WHITE';
    
    console.log("isWhitePlayer",isWhitePlayer)

    console.log("CLEAR ALL")
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


import {getPuzzleRoute, getRandomPuzzleRoute, makeMoveRoute} from "../index.js";
import {getTableUpdateForPuzzle, isGameOver, makeMove} from "./chessTable.js";

let solution = ""
let enemyMoves = ""
let puzzleNumber = ""

export function makeMovePuzzle(from,to){
    if(isGameOver){ return;}
    const isPlayerMove = document.getElementById(from).classList.contains("WHITE")
    if(!isPlayerMove) {
        return;
    }
    console.log("makeMovePuzzle")

    let subSolution = solution.substring(0,solution.indexOf(","));
    if(subSolution===""){subSolution = solution}

    console.log("subSolution",subSolution)
    console.log("from+\"-\"+to",from+"-"+to)

    if(subSolution === from+"-"+to) {
        correctMove()
    }
    else{
        puzzleFailed()
    }
}
function correctMove() {
    solution = solution.substring(6,solution.length);
    if(solution===""){
        puzzleSolved()
    }
    else{
        handleEnemyMove()
    }
}
function handleEnemyMove() {
    let subEnemyMoves = enemyMoves.substring(0,enemyMoves.indexOf(","));
    if(subEnemyMoves===""){subEnemyMoves = enemyMoves}

    makeMove(subEnemyMoves.substring(0,2),subEnemyMoves.substring(3,5))
    enemyMoves = enemyMoves.substring(6,enemyMoves.length);
}
function puzzleSolved() {
    const div = document.createElement("div");
    document.getElementById("solvedPuzzleDiv").appendChild(div);

    div.classList.add("alert") ;
    div.classList.add("alert-success");
    div.innerText = "Congratulation, You solved the Puzzle";
}
function puzzleFailed(){
    const div = document.createElement("div");
    document.getElementById("solvedPuzzleDiv").appendChild(div);

    div.classList.add("alert") ;
    div.classList.add("alert-danger");
    div.innerText = "Bad Move";
}

export function getRandomPuzzle(){
    let xhr = new XMLHttpRequest()
    xhr.open('GET',getRandomPuzzleRoute,true)
    xhr.onload = function(){
        if(xhr.status === 200){
            console.log("getRandomPuzzle success");

            document.getElementById("solvedPuzzleDiv").replaceChildren();

            let puzzleData = JSON.parse(this.response)

            getTableUpdateForPuzzle(puzzleData.chessParty)
            setUpPuzzleVariables(puzzleData.chessPuzzle)

            document.getElementById("restartPuzzleButton").classList.remove("disabled")

        }
        else {
            console.log("Problem with getRandomPuzzle request!")
        }
    }
    xhr.send()
}

export function getPuzzle(){
    let xhr = new XMLHttpRequest()
    xhr.open('GET',getPuzzleRoute+puzzleNumber,true)
    xhr.onload = function(){
        if(xhr.status === 200){
            console.log("getPuzzle success");

            document.getElementById("solvedPuzzleDiv").replaceChildren();

            const puzzleData = JSON.parse(this.response)

            getTableUpdateForPuzzle(puzzleData.chessParty)
            setUpPuzzleVariables(puzzleData.chessPuzzle)

        }
        else {
            console.log("Problem with getPuzzle request!")
        }
    }
    xhr.send()
}

function setUpPuzzleVariables(chessPuzzle) {
    solution = chessPuzzle.solutionMoves;
    enemyMoves = chessPuzzle.enemyMoves;
    puzzleNumber = chessPuzzle.id;
}
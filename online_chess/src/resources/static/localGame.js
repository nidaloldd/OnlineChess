import {getTableUpdateForLocal} from "./library/chessTable.js";
import {localGameRoute} from "./index.js";

const getLocalGameButton = document.getElementById('getLocalGameButton');
getLocalGameButton.addEventListener('click', getLocalGame);

function getLocalGame() {
    document.getElementById("puzzleNav").classList.add("activeNav");
    let xhr = new XMLHttpRequest()
    xhr.open('GET',localGameRoute,true)
    xhr.onload = function(){
        if(xhr.status === 200){
            console.log("getLocalGame()");
            const data = JSON.parse(this.response)
            getTableUpdateForLocal(data)
        }
        else {
            console.log("Problem with getLocalGame request!")
        }
    }
    xhr.send()
}

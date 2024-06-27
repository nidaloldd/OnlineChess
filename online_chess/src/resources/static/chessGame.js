import {connectToRandom, connectToSpecificGame} from "./library/socket.js";
import {getPlayerRoute, newGameRoute, url} from "./index.js";
import {gameId, getTableUpdateForOnline} from "./library/chessTable.js";


(function() {
    if (gameId) {
        connectToSpecificGame(gameId);
    } else {
        connectToRandom();
    }
    const leaveGameButton = document.getElementById('leaveGameButton');
    leaveGameButton.addEventListener('click', leaveGame);
})();

function newGame(){
    let xhr = new XMLHttpRequest()
    xhr.open('GET',newGameRoute,true)
    xhr.onload = function(){
        if(xhr.status === 200){
            console.log("NEwGame")
            //clearTable()
            getTableUpdateForOnline()
        }
        else {
            console.log("Problem with newGame request !!!")
        }
    }
    xhr.send()
}

function leaveGame() {
    sessionStorage.clear();
    window.location.href = url;
}

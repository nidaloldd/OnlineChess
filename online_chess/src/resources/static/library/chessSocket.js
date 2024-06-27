import {getTableUpdateForOnline} from "./chessTable.js";

let gameStompClient

export function connectToChessSocket(){
    console.log("connecting to the Chess Socket");
    let gameSocket = new SockJS("/chessWs");

    gameStompClient = Stomp.over(gameSocket);
    gameStompClient.connect({},onChessSocketConnected)
}

function onChessSocketConnected(frame){
    const gameId = sessionStorage.getItem("gameID");
    if (gameId != null) {
        console.log("connected to the frame: " + frame);
        gameStompClient.subscribe("/topic/game-progress/" + gameId, onChessMessageReceived);
    }
}

function onChessMessageReceived(response){
    let data = JSON.parse(response.body);
    sessionStorage.setItem("gameID",data.id)
    getTableUpdateForOnline(data)
}
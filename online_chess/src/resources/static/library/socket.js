import {connectToChessSocket} from "./chessSocket.js";
import {connectToChatSocket} from "./chatSocket.js";
import {connectRandomRoute, connectRoute, gameStartRoute} from "../index.js";
import {getTableUpdateForOnline} from "./chessTable.js";


function connectToSockets() {
    connectToChessSocket()
    connectToChatSocket()
}

export function create_game() {
    console.log("create_game()")
    $.ajax({
        url: gameStartRoute,
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            sessionStorage.setItem("gameID",data.id)
            connectToSockets();
            getTableUpdateForOnline(data)
        },
        error: function (error) {
            console.log(error);
        }
    })
}

export function connectToRandom() {
    $.ajax({
        url: connectRandomRoute,
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            console.log("connectToRandom()");
            
            if(data.whitePlayer.username === sessionStorage.getItem('username')){
                sessionStorage.setItem('isWhitePlayer',true);
            }
            else{
                sessionStorage.setItem('isWhitePlayer',false);
            }
            
            sessionStorage.setItem("gameID",data.id)
            connectToSockets();
            getTableUpdateForOnline(data)
        },
        error: function (error) {
            console.log(error);
        }
    })
}

export function connectToSpecificGame(gameId='') {

    const gameIdFromInput = gameId
    if(gameId){
        console.log("No GameID to Connect")
    }
    
    $.ajax({
        url: connectRoute,
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "gameId": gameIdFromInput
        }),
        success: function (data) {
            console.log("connectToSpecificGame()");
            sessionStorage.setItem("gameID",gameIdFromInput)
            connectToSockets();
            getTableUpdateForOnline(data)
        },
        error: function (error) {
            console.log(error);
        }
    })
}
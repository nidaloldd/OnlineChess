console.log("SOCKET SCRIPT LOAD")

let gameId;

function displayGameInformation(loginName,playingWith,GameId){
    document.getElementById("loginName").innerHTML = loginName
    document.getElementById("playingWith").innerHTML = playingWith
    document.getElementById("GameID").innerHTML = GameId
}

function connectToSocket(gameId) {
    getUser()

    console.log("connecting to the game");
    let socket = new SockJS("/makeMove");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to the frame: " + frame);
        stompClient.subscribe("/topic/game-progress/" + gameId, function (response) {
            
            let data = JSON.parse(response.body);
            console.log("connectToSocket()");
            sessionStorage.setItem("gameID",data.id)
            getTableUpdate(data)
            
        })
    })

    console.log("connecting to the CHAT")
    socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
}

function create_game() {
    console.log("create_game()")
    $.ajax({
        url: gameStartRoute,
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            sessionStorage.setItem("gameID",data.id)
            connectToSocket(data.id);
            getTableUpdate(data)
        },
        error: function (error) {
            console.log(error);
        }
    })
}

function connectToRandom() {
    $.ajax({
        url: connectRandomRoute,
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            console.log("connectToRandom()");
            
            if(data.whitePlayer.username == sessionStorage.getItem('username')){
                sessionStorage.setItem('isWhitePlayer',true);
            }
            else{
                sessionStorage.setItem('isWhitePlayer',false);
            }
            
            sessionStorage.setItem("gameID",data.id)
            connectToSocket(data.id);
            getTableUpdate(data)
        },
        error: function (error) {
            console.log(error);
        }
    })
}

function connectToSpecificGame(gameId='') {

    gameIdFromInput = gameId
    if(gameId == null || gameId === '' ){
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
            gameId = gameIdFromInput;

            connectToSocket(gameId);
            getTableUpdate(data)
        },
        error: function (error) {
            console.log(error);
        }
    })
    
}

function getUser(){
    $.ajax({
        url: getPlayerRoute,
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            console.log("getUser",data)

            sessionStorage.setItem('username', data.username)
            console.log("username ",sessionStorage.getItem('username') )
        },
        error: function (error) {
            console.log(error);
        }
    })
}
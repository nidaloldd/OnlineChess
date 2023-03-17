let stompClient;
let gameId;
let loginName;

getUser()
console.log(sessionStorage.getItem("gameID"))
if(sessionStorage.getItem("gameID") == 'undefined' || sessionStorage.getItem("gameID") == undefined){
    console.log("GameID is NUll connectToRandom")
    connectToRandom();
}
else{
    console.log("GameID NOT NUll connectToSpecificGame")
    connectToSpecificGame(sessionStorage.getItem("gameID"));
}

function displayGameInformation(loginName,playingWith,GameId){
    console.log(loginName)
    console.log(playingWith)
    console.log(GameId)
    document.getElementById("loginName").innerHTML = loginName
    document.getElementById("playingWith").innerHTML = playingWith
    document.getElementById("GameID").innerHTML = GameId
} 

function connectToSocket(gameId) {
    console.log("connecting to the game");
    let socket = new SockJS("/makeMove");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to the frame: " + frame);
        stompClient.subscribe("/topic/game-progress/" + gameId, function (response) {
            let data = JSON.parse(response.body);
            console.log("connectToSocket()");
            console.log("data",data);
            console.log("isWhitePlayer",localStorage.getItem('isWhitePlayer'));
            getTableUpdate(data)
           sessionStorage.setItem("gameID",data.id)
        })
    })
}

function create_game() {
    console.log("create_game()")
    $.ajax({
        url: gameStartRoute,
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            console.log("create_game()");
            sessionStorage.setItem("gameID",data.id)
            connectToSocket(data.id);
            getTableUpdate(data)
            alert("You created a game. Game id is: " + data.id);
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
            
            if(data.whitePlayer.username == localStorage.getItem('username')){
                localStorage.setItem('isWhitePlayer',true);
            }
            else{
                localStorage.setItem('isWhitePlayer',false);
            }
            
            alert("and playing with: " + data.whitePlayer.name);

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
        alert("No GameID to Connect")
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
            alert("Congrats you're playing with: " + data.player1.login);
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

            localStorage.setItem('username', data.username)
            console.log("username ",localStorage.getItem('username') )
        },
        error: function (error) {
            console.log(error);
        }
    })
}
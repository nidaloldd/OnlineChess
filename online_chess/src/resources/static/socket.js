const url = 'http://localhost:8081';
let stompClient;
let gameId;
let loginName;
let opponentName;


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
    let socket = new SockJS(url + "/makeMove");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to the frame: " + frame);
        stompClient.subscribe("/topic/game-progress/" + gameId, function (response) {
            let data = JSON.parse(response.body);
            console.log("connectToSocket()");
            console.log("data",data);
            getTableUpdate(data)
            if(login == data.whitePlayer.name){
                displayGameInformation(login,data.blackPlayer.name,data.id)
            }
        })
    })
}

function create_game() {
    login = document.getElementById("loginInput").value;
    if (login == null || login === '') {
        alert("Please enter login");
    } else {
        $.ajax({
            url: url + "/api/start",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: login,
            success: function (data) {
                console.log("create_game()");
                gameId = data.id;
                connectToSocket(gameId);
                getTableUpdate(data)
                handleBlackPlayerTable(data.whitePlayer.name);
                displayGameInformation(login," - ",data.id)
                alert("Your created a game. Game id is: " + data.id);
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}

function connectToRandom() {
    let login = document.getElementById("loginInput").value;
    if (login == null || login === '') {
        alert("Please enter login");
    } else {
        document.getElementById("loginName").innerHTML = login
        $.ajax({
            url: url + "/api/connect/random",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: login,
            success: function (data) {
                console.log("connectToRandom()");
                gameId = data.id;
                connectToSocket(data.id);
                getTableUpdate(data)
                handleBlackPlayerTable(data.blackPlayer.name)
                displayGameInformation(login,data.whitePlayer.name,data.id)
                alert("and playing with: " + data.whitePlayer.name);
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}

function connectToSpecificGame() {
    let login = document.getElementById("loginInput").value;
    if (login == null || login === '') {
        alert("Please enter login");
    } else {
        document.getElementById("loginName").innerHTML = login
        let gameId = document.getElementById("game_id").value;
        if (gameId == null || gameId === '') {
            alert("Please enter game id");
        }
        $.ajax({
            url: url + "/api/connect",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify({
                "player": {
                    "login": login
                },
                "gameId": gameId
            }),
            success: function (data) {
                console.log("connectToSpecificGame()");
                gameId = data.gameId;
                connectToSocket(gameId);
                handleBlackPlayerTable(data.blackPlayer.name);
                displayGameInformation(login,data.whitePlayer.name,data.id)
                alert("Congrats you're playing with: " + data.player1.login);
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}
function handleBlackPlayerTable(blackPlayerName){
    const loginName = document.getElementById("loginName").innerHTML
    if(blackPlayerName == loginName){
        document.getElementById("chessTable").classList.add("BlackPlayerTable")

    }

}
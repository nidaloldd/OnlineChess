const url = 'http://localhost:8081';
let stompClient;
let gameId;


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
        })
    })
}

function create_game() {
    let login = document.getElementById("login").value;
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
                console.log(data);
                gameId = data.id;
                connectToSocket(gameId);
                getTableUpdate(data)
                alert("Your created a game. Game id is: " + data.id);
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}

function connectToRandom() {
    let login = document.getElementById("login").value;
    if (login == null || login === '') {
        alert("Please enter login");
    } else {
        $.ajax({
            url: url + "/api/connect/random",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: login,
            success: function (data) {
                console.log("connectToRandom()");
                console.log(data);
                gameId = data.id;
                connectToSocket(data.id);
                getTableUpdate(data)
                alert("and playing with: " + data.whitePlayer.name);
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}

function connectToSpecificGame() {
    let login = document.getElementById("login").value;
    if (login == null || login === '') {
        alert("Please enter login");
    } else {
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
                console.log(data);
                gameId = data.gameId;
                playerType = 'O';
                connectToSocket(gameId);
                alert("Congrats you're playing with: " + data.player1.login);
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}
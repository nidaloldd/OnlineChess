let chatStompClient
let gameStompClient


function displayGameInformation(loginName,playingWith,GameId){
    document.getElementById("loginName").innerHTML = loginName
    document.getElementById("playingWith").innerHTML = playingWith
    document.getElementById("GameID").innerHTML = GameId
}

function connectToSockets() {
    connectToChessSocket()
    connectToChatSocket()
}

function connectToChessSocket(){
    console.log("connecting to the Chess Socket");
    let gameSocket = new SockJS("/chessWs");

    gameStompClient = Stomp.over(gameSocket);
    gameStompClient.connect({},onChessSocketConnected)
}

function onChessSocketConnected(frame){
    gameId = sessionStorage.getItem("gameID");
    if (gameId != null && gameId != undefined) {
        console.log("connected to the frame: " + frame);
        gameStompClient.subscribe("/topic/game-progress/" + gameId, onChessMessageReceived)
    }
}

function onChessMessageReceived(response){
    let data = JSON.parse(response.body);
    sessionStorage.setItem("gameID",data.id)
    getTableUpdate(data)
}

function connectToChatSocket(){
    console.log("connecting to the CHAT Socket")
    let chatSocket = new SockJS('/chatWs');
    chatStompClient = Stomp.over(chatSocket);
    chatStompClient.connect({}, onChatSocketConnected, onChatError);
}

//-------------------------------------------------------------
function onChatSocketConnected() {
    gameId = sessionStorage.getItem("gameID");
    chatStompClient.subscribe("/topic/chat/"+ gameId, onChatMessageReceived);
    chatStompClient.send("/app/chatAddGameid",
        {},
        JSON.stringify({gameId: sessionStorage.getItem('gameID')})
    )
    connectingElement.classList.add('hidden');
}

function onChatError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

function sendChatMessage(event) {
    var messageContent = messageInput.value.trim();

    if (messageContent && chatStompClient) {
        var chatMessage = {
            sender: sessionStorage.getItem('username'),
            content: messageInput.value,
            gameId: sessionStorage.getItem('gameID'),
            type: 'CHAT'
        };

        chatStompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    else {
        console.log("sendMessage FAILD")
        console.log("messageContent ",messageContent)
        console.log("sendMessage ",chatStompClient)
    }
    event.preventDefault();
}
messageForm.addEventListener('submit', sendChatMessage, true)

function onChatMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    console.log("message ",message)

    var messageElement = document.createElement('li');

    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    } else {
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}
//-------------------------------------------------------------


function create_game() {
    console.log("create_game()")
    $.ajax({
        url: gameStartRoute,
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            sessionStorage.setItem("gameID",data.id)
            connectToSockets();
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
            connectToSockets();
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
            sessionStorage.setItem("gameID",gameIdFromInput)
            connectToSockets();
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
        },
        error: function (error) {
            console.log(error);
        }
    })
}


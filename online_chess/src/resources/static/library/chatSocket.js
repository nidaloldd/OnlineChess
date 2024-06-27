import {gameId, username} from "./chessTable.js";

let chatStompClient
const usernamePage = document.querySelector('#username-page');
const chatPage = document.querySelector('#chat-page');
const usernameForm = document.querySelector('#usernameForm');
const messageForm = document.querySelector('#messageForm');
const messageInput = document.querySelector('#message');
const messageArea = document.querySelector('#messageArea');
const connectingElement = document.querySelector('.connecting');
connectingElement.style.display = 'contents';

export function connectToChatSocket(){
    console.log("connecting to the CHAT Socket")
    let chatSocket = new SockJS('/chatWs');
    chatStompClient = Stomp.over(chatSocket);
    chatStompClient.connect({}, onChatSocketConnected, onChatError);
}

function onChatSocketConnected() {
    const gameId = sessionStorage.getItem("gameID");
    chatStompClient.subscribe("/topic/chat/"+ gameId, onChatMessageReceived);
    chatStompClient.send("/app/chatAddGameid",
        {},
        JSON.stringify({gameId: sessionStorage.getItem('gameID')})
    )
    connectingElement.style.display = 'none';
}

function onChatError(error) {
    connectingElement.style.display = 'contents';
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

function sendChatMessage(event) {
    var messageContent = messageInput.value.trim();

    if (messageContent && chatStompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            gameId: gameId,
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

const colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

console.log("username "+sessionStorage.getItem('username'))

function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }

    var index = Math.abs(hash % colors.length);
    return colors[index];
}


function logout(){
    document.getElementById('frmlogout').submit();
    sessionStorage.removeItem("gameID")
    sessionStorage.removeItem("isWhitePlayer")
    sessionStorage.removeItem("username")
}

const logoutButton = document.getElementById('logoutButton');
logoutButton.addEventListener('click', logout);
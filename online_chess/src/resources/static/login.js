(function() {
    document.addEventListener("DOMContentLoaded", function() {
        const loginInfoBox = document.querySelector('.login-info-box');
        const registerInfoBox = document.querySelector('.register-info-box');
        const loginShow = document.querySelector('.login-show');
        const registerShow = document.querySelector('.register-show');
        const whitePanel = document.querySelector('.white-panel');
        const logLoginShow = document.getElementById('log-login-show');
        const logRegShow = document.getElementById('log-reg-show');

        document.querySelectorAll('.login-reg-panel input[type="radio"]').forEach(function(radio) {
            radio.addEventListener('change', function() {
                if (logLoginShow.checked) {
                    showReg()
                } else if (logRegShow.checked) {
                    showLogin()
                }
            });
        });

        function showLogin() {
            loginInfoBox.classList.add("fade")
            registerInfoBox.classList.remove("fade")

            whitePanel.classList.remove('right-log');
            registerShow.classList.remove('show-log-panel');
            loginShow.classList.add('show-log-panel');
        }
        function showReg() {
            registerInfoBox.classList.add("fade")
            loginInfoBox.classList.remove("fade")

            whitePanel.classList.add('right-log');
            loginShow.classList.remove('show-log-panel');
            registerShow.classList.add('show-log-panel');
        }

        loginShow.classList.add('show-log-panel');

    });
})();

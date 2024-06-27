import {scoreTableRoute} from "./index.js";

document.getElementById("userPageNav").classList.add("activeNav");
(function getScoreTable(){
    let xhr = new XMLHttpRequest()
    xhr.open('POST',scoreTableRoute,true)
    xhr.onload = function(){
        if(xhr.status === 200){
            console.log("getScoreTable succsess");

            const data = JSON.parse(this.response);

            for (let index = 0; index < 5; ++index){

                const tr = document.createElement("tr")
                const th = document.createElement("th")
                th.scope= "row";
                th.textContent = index+1;
                tr.appendChild(th)

                const userTd = document.createElement("td")
                userTd.textContent = data[index].username
                tr.appendChild(userTd)

                const emailTd = document.createElement("td")
                emailTd.textContent = data[index].email
                tr.appendChild(emailTd)

                const scoreTd = document.createElement("td")
                scoreTd.textContent = data[index].score
                tr.appendChild(scoreTd)

                document.getElementById("tableBody").appendChild(tr)
            }

        }
        else {
            console.log("Problem with getScoreTable request!")
        }
    }
    xhr.send()
})();
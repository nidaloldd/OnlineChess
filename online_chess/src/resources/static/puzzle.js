import {getPuzzle, getRandomPuzzle} from "./library/chessTablePuzzle.js";

document.getElementById("puzzleNav").classList.add("activeNav");

const getRandomPuzzleButton = document.getElementById('getRandomPuzzleButton');
getRandomPuzzleButton.addEventListener('click', getRandomPuzzle);

const restartPuzzleButton = document.getElementById('restartPuzzleButton');
restartPuzzleButton.classList.add("disabled");
restartPuzzleButton.addEventListener('click', getPuzzle);
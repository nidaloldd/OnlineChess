const template = document.createElement('template');
template.innerHTML = `
<table id="chessTable">
            <tr>
              <td class="mark">8</td>
              <td id="A8" class="blackSquare"></td>
              <td id="B8" class="whiteSquare"></td>
              <td id="C8" class="blackSquare"></td>
              <td id="D8" class="whiteSquare"></td>
              <td id="E8" class="blackSquare"></td>
              <td id="F8" class="whiteSquare"></td>
              <td id="G8" class="blackSquare"></td>
              <td id="H8" class="whiteSquare"></td>
            </tr>
            <tr>
              <td class="mark">7</td>
              <td id="A7" class="whiteSquare"></td>
              <td id="B7" class="blackSquare"></td>
              <td id="C7" class="whiteSquare"></td>
              <td id="D7" class="blackSquare"></td>
              <td id="E7" class="whiteSquare"></td>
              <td id="F7" class="blackSquare"></td>
              <td id="G7" class="whiteSquare"></td>
              <td id="H7" class="blackSquare"></td>
            </tr>
            <tr>
              <td class="mark">6</td>
              <td id="A6" class="blackSquare"></td>
              <td id="B6" class="whiteSquare"></td>
              <td id="C6" class="blackSquare"></td>
              <td id="D6" class="whiteSquare"></td>
              <td id="E6" class="blackSquare"></td>
              <td id="F6" class="whiteSquare"></td>
              <td id="G6" class="blackSquare"></td>
              <td id="H6" class="whiteSquare"></td>
            </tr>
            <tr>
              <td class="mark">5</td>
              <td id="A5" class="whiteSquare"></td>
              <td id="B5" class="blackSquare"></td>
              <td id="C5" class="whiteSquare"></td>
              <td id="D5" class="blackSquare"></td>
              <td id="E5" class="whiteSquare"></td>
              <td id="F5" class="blackSquare"></td>
              <td id="G5" class="whiteSquare"></td>
              <td id="H5" class="blackSquare"></td>
            </tr>
            <tr>
              <td class="mark">4</td>
              <td id="A4" class="blackSquare"></td>
              <td id="B4" class="whiteSquare"></td>
              <td id="C4" class="blackSquare"></td>
              <td id="D4" class="whiteSquare"></td>
              <td id="E4" class="blackSquare"></td>
              <td id="F4" class="whiteSquare"></td>
              <td id="G4" class="blackSquare"></td>
              <td id="H4" class="whiteSquare"></td>
            </tr>
            <tr>
              <td class="mark">3</td>
              <td id="A3" class="whiteSquare"></td>
              <td id="B3" class="blackSquare"></td>
              <td id="C3" class="whiteSquare"></td>
              <td id="D3" class="blackSquare"></td>
              <td id="E3" class="whiteSquare"></td>
              <td id="F3" class="blackSquare"></td>
              <td id="G3" class="whiteSquare"></td>
              <td id="H3" class="blackSquare"></td>
            </tr>
            <tr>
              <td class="mark">2</td>
              <td id="A2" class="blackSquare"></td>
              <td id="B2" class="whiteSquare"></td>
              <td id="C2" class="blackSquare"></td>
              <td id="D2" class="whiteSquare"></td>
              <td id="E2" class="blackSquare"></td>
              <td id="F2" class="whiteSquare"></td>
              <td id="G2" class="blackSquare"></td>
              <td id="H2" class="whiteSquare"></td>
            </tr>
            <tr>
              <td class="mark">1</td>
              <td id="A1" class="whiteSquare"></td>
              <td id="B1" class="blackSquare"></td>
              <td id="C1" class="whiteSquare"></td>
              <td id="D1" class="blackSquare"></td>
              <td id="E1" class="whiteSquare"></td>
              <td id="F1" class="blackSquare"></td>
              <td id="G1" class="whiteSquare"></td>
              <td id="H1" class="blackSquare"></td>
            </tr>
            <tr class="mark">
              <td></td>
              <td>A</td>
              <td>B</td>
              <td>C</td>
              <td>D</td>
              <td>E</td>
              <td>F</td>
              <td>G</td>
              <td>H</td>
            </tr>
          </table>`;

class ChessTable extends HTMLElement{
 constructor(){
     super();
     this.attachShadow({ mode: 'open'});
     this.shadowRoot.appendChild(template.content.cloneNode(true));
 } 
 connectedCallback(){
  this.h3 = this.getAttribute("name")
  this.render();
}

render(){
  this.h3;
}
}
window.customElements.define('chessTable', ChessTable);
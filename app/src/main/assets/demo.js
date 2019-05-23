 function androidCallToJSChangeFontStyle(fontStyle) {
         document.body.classList.add(fontStyle);
  }

  function anddroidCallToJSChangeFontSize(fontSize) {
        var target = query("#body");
        style(target, "fontSize", fontSize + "px");
  }

  function anddroidCallToJSChangeBackgroundColor(colorHex) {
      document.body.style.background = colorHex;
  }

  function anddroidCallToJSChangeTextColor(textColor) {
      var target = query("#body");
      style(target, "color", textColor);
  }

  function jsCallToAndroid() {
    var msg = "Calling from Java Script to android";
    JsHandler.jsCallToAndroid(msg);

  }

  function style (el, property, value) {
     el.style[property] = value;
  }

  function query (selector) {
    return document.querySelector(selector);
  }
  function scrollTo(element){
     document.getElementById(element).scrollIntoView(true);
  }

  function setFontSizeNote(element, fontSize){
    document.getElementById(element).style.fontSize = fontSize;
  }

  function setBottomNote(element, index){
      document.getElementById(element).style.position = 'relative';
      document.getElementById(element).style.bottom = index;
    }

function selectForId(element){
    var text = document.getElementById(element);
    return text
}


function stylizeHighlightedStringHN() {
    var text        = window.getSelection();
    var selectedText    = text.anchorNode.textContent.substr(text.anchorOffset, text.focusOffset - text.anchorOffset);
    var element =  window.getSelection().anchorNode.parentNode;
    element.className = 'uiWebviewHighlight';
    return selectedText;
}


function styRemoveHighlighted() {
    var text        = window.getSelection();
    var selectedText    = text.anchorNode.textContent.substr(text.anchorOffset, text.focusOffset - text.anchorOffset);
    var element =  window.getSelection().anchorNode.parentNode;
    element.className = '';
    return selectedText;
}


function getSelectedText() {
    var text        = window.getSelection().anchorNode.parentNode;
    var selectedText    = text.anchorNode.textContent.substr(text.anchorOffset, text.focusOffset - text.anchorOffset);
    return selectedText;
}
function getTextID() {
     var element =  window.getSelection().anchorNode.parentNode;
     return element.id;
}
function getYPositionVerse(verseId) {
    var elOffSet = document.getElementById(verseId).getBoundingClientRect();
    return elOffSet.top;
}

function getYPos(chapNumber) {
    var elementId = chapNumber;
    var elOffSet = document.getElementById(elementId).getBoundingClientRect();
    return elOffSet.top;
}


function getPosition( element ) {
   var rect = element.getBoundingClientRect();
   return {x:rect.left,y:rect.top};
}

function onMouseOut(event) {
        //this is the original element the event handler was assigned to
        var e = event.toElement || event.relatedTarget;
        if (e.parentNode == this || e == this) {
           return;
        }
    alert('MouseOut');
}

function selectText(containerid) {
        if (document.selection) {
            var range = document.body.createTextRange();
            range.moveToElementText(document.getElementById(containerid));
            range.select();
        } else if (window.getSelection) {
            var range = document.createRange();
            range.selectNode(document.getElementById(containerid));
            window.getSelection().removeAllRanges();
            window.getSelection().addRange(range);
        }
    }


function getElementsStartsWithId( id ) {
  var children = document.body.getElementsByTagName('*');
  var elements = [], child;
  for (var i = 0, length = children.length; i < length; i++) {
    child = children[i];
    if (child.id.substr(0, id.length) == id)
      elements.push(child);
  }
  return elements;
}


function getabc(id){
    var text = document.getElementById(id).textContent;
    return text;
}

function GeTextContenById(idOfElement) {
    return document.getElementById(idOfElement).innerHTML;
}

function setHiglig(e){
    document.getSelection() = e;
}

function getSelectedTexted() {
    var text        = window.getSelection();
    var selectedText    = text.anchorNode.parentNode;
    return selectedText;
}

function addHighlight(idVerse){
    var d = document.getElementById(idVerse);
    d.className += "uiWebviewHighlight";
}

function removeHighlight(idVerse){
    var d = document.getElementById(idVerse);
    d.className = "uiWebviewRemoveHighlight";
}

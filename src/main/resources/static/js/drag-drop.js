'use strict';
//using the sample code from https://www.javascripttutorial.net/web-apis/javascript-drag-and-drop/
    var results;
function onSearch(){
    results = document.querySelectorAll(".search-result");
    console.log(results);
    results.forEach(function (result){
        result.addEventListener('dragstart',dragStart);
    });
}

function dragStart(e) {
    console.log("dragging");
    e.dataTransfer.setData('text/plain', e.target.html);
    setTimeout(() => {
        e.target.classList.add('hide');
    }, 0);
}

/* drop targets */
const topsterImageSlots = document.querySelectorAll('.p-3');

topsterImageSlots.forEach(topsterImageSlot => {
    topsterImageSlot.addEventListener('dragenter', dragEnter)
    topsterImageSlot.addEventListener('dragover', dragOver);
    topsterImageSlot.addEventListener('dragleave', dragLeave);
    topsterImageSlot.addEventListener('drop', drop);
});


function dragEnter(e) {
    e.preventDefault();
    e.target.classList.add('drag-over');
}

function dragOver(e) {
    e.preventDefault();
    e.target.classList.add('drag-over');
}

function dragLeave(e) {
    e.target.classList.remove('drag-over');
}

function drop(e) {
    e.preventDefault();
    e.target.classList.remove('drag-over');

    // get the draggable element
    const id = e.dataTransfer.getData('text/plain'); //this is just the image src address in this case
    console.log(id);
    //having to improvise a way to select the img element with said src attribute
    let theImgTagThatWeWantToCopy = document.querySelector(`.search-result [src=${CSS.escape(id)}]`);
    console.log(theImgTagThatWeWantToCopy.outerHTML);
    let newImgTag= theImgTagThatWeWantToCopy.outerHTML;
    newImgTag = newImgTag.replace("width=\"120\" height=\"120\"","width=\"100%\" height=\"100%\"") //here we're using the portion of the tag that defines the height and width to strip that out. If we change the results from appearing 120x120 we'll have to change or remove this
    // let draggable;
    //
    // results.forEach(function (result){
    //     console.log(result.firstChild.src);
    //     if(result.firstChild.src === id){
    //         draggable = result;
    //     }
    // });
    //
    // console.log(draggable);

    // add it to the drop target
    // e.target.appendChild(draggable);
    e.target.innerHTML=newImgTag;

    // display the draggable element
    // draggable.classList.remove('hide');
}
'use strict';
//using the sample code from https://www.javascripttutorial.net/web-apis/javascript-drag-and-drop/
    var results;
function onSearch(){
    results = document.querySelectorAll(".search-result");
    results.addEventListener('dragstart',dragStart)
    // results.forEach(function (result){
    //     result.addEventListener('dragstart',dragStart);
    // })
}

function dragStart(e) {
    console.log("dragging");
    e.dataTransfer.setData('text/plain', e.target.id);
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
    let theImgTagThatWeWantToCopy = document.querySelectorAll('[src=id]');
    console.log(theImgTagThatWeWantToCopy);
    let draggable;

    results.forEach(function (result){
        console.log(result.firstChild.src);
        if(result.firstChild.src === id){
            draggable = result;
        }
    });

    console.log(draggable);

    // add it to the drop target
    // e.target.appendChild(draggable);
    e.target.innerHTML=draggable;

    // display the draggable element
    draggable.classList.remove('hide');
}
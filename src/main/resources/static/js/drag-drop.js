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
    e.dataTransfer.setData('text/plain', e.target.id);
    e.dataTransfer.items.add(e.target.id,'text/plain')
    console.log(e.target.html);
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


    if(!(navigator.userAgent.toLowerCase().indexOf('firefox') > -1)){
        let theOriginalTag;
        e.dataTransfer.items[0].getAsString(function (s) {
            console.log(s); //In firefox the alt attribute gets included in the string for some reason. In chrome, it's just the img link
            let trimmedAlbumArtLink;
            //Code to strip out the alt portion
            if (s.indexOf(" ") !== -1) {
                trimmedAlbumArtLink = s.substring(0, s.indexOf(" "));
                console.log(trimmedAlbumArtLink);
            } else {
                trimmedAlbumArtLink = s;
            }

            theOriginalTag = document.querySelector(`.search-result [src='` + trimmedAlbumArtLink + `']`).outerHTML;
            let newTag = theOriginalTag.replace("width=\"120\" height=\"120\"", "width=\"100%\" height=\"100%\"");
            e.target.innerHTML = newTag;
        });
    }

    // console.log("Using firefox = " + (navigator.userAgent.toLowerCase().indexOf('firefox') > -1));
    if(navigator.userAgent.toLowerCase().indexOf('firefox') > -1) {


        // get the draggable element
        const id = e.dataTransfer.getData('text/plain'); //this is just the image src address in this case

        //having to improvise a way to select the img element with said src attribute
        let theImgTagThatWeWantToCopy = document.querySelector(`.search-result [src=${CSS.escape(id)}]`); //works on firefox but not chrome
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
}
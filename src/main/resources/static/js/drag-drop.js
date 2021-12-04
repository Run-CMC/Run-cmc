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
            let newImgTag = theOriginalTag.replace("width=\"120\" height=\"120\"", "width=\"100%\" height=\"100%\"");

            let imgPlusHiddenForms = newImgTag + createAlbumInfoFields(newImgTag, e.target.parentNode.getAttribute("value"));

            e.target.innerHTML = imgPlusHiddenForms;
            // console.log(e.target.outerHTML);
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
        //this line logs the position value of the drop area (it's attached to the parent div we're dropping the img tag into)
        console.log(e.target.parentNode.getAttribute("value"));

        let imgPlusHiddenForms = newImgTag + createAlbumInfoFields(newImgTag, e.target.parentNode.getAttribute("value"));


        e.target.innerHTML = imgPlusHiddenForms;
    }

    function createAlbumInfoFields(imgTagHTML, positionValue) {

        let src = imgTagHTML.substring((imgTagHTML.indexOf("src=") + 5),
            imgTagHTML.indexOf("\" alt=", (imgTagHTML.indexOf("src=") + 5)));

        let title = imgTagHTML.substring((imgTagHTML.indexOf("data-title=") + 12),
            imgTagHTML.indexOf("\" data-", (imgTagHTML.indexOf("data-title=") + 12)));

        let artist = imgTagHTML.substring((imgTagHTML.indexOf("data-artist=") + 13),
            imgTagHTML.indexOf("\" data-", (imgTagHTML.indexOf("data-artist=") + 13)));

        let releaseDate = imgTagHTML.substring((imgTagHTML.indexOf("data-releasedate=") + 18),
            imgTagHTML.indexOf("\" data-", (imgTagHTML.indexOf("data-releasedate=") + 18)));

        let spotifyID = imgTagHTML.substring((imgTagHTML.indexOf("data-spotifyid=") + 16),
            imgTagHTML.indexOf("\" width=", (imgTagHTML.indexOf("data-spotifyid=") + 16)));

        // console.log(src);
        // console.log(title);
        // console.log(artist);
        // console.log(releaseDate);
        // console.log(spotifyID);

        return `<div class="form-group hide">
                  <input type="text" value="${src}" name="src[]">
                  <input type="text" value="${title}" name="title[]">
                  <input type="text" value="${artist}" name="artist[]">
                  <input type="text" value="${releaseDate}" name="releaseDate[]">
                  <input type="text" value="${spotifyID}" name="spotifyID[]">
                  <input type="text" value="${positionValue}" name="position[]">
                </div>`

    }
}
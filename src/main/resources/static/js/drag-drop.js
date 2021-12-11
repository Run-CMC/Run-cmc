'use strict';
//using the sample code from https://www.javascripttutorial.net/web-apis/javascript-drag-and-drop/
var results;
function onSearch(){
    results = document.querySelectorAll(".search-result");
    // console.log(results);
    results.forEach(function (result){
        result.addEventListener('dragstart',dragStart);
    });
}

function dragStart(e) {
    e.dataTransfer.setData('text/plain', e.target.id);
    e.dataTransfer.items.add(e.target.id,'text/plain');
    // console.log(e.target.id);
    setTimeout(() => {
        e.target.classList.add('hide');
    }, 0);
}

function dragStartButForAlreadyDroppedAlbums(e){
    if (!(navigator.userAgent.toLowerCase().indexOf('firefox') > -1)){
        e.dataTransfer.items.add(e.target.outerHTML,'text/plain');
    }else{
        e.dataTransfer.setData('text/plain', e.target.outerHTML);
    }

    setTimeout(() => {
        if(e.target.tagName=="IMG"){
            e.target.parentNode.innerHTML = clearFormFields(e.target.nextSibling.outerHTML);

        }else if(e.target.className=="p-3"){
            e.target.innerHTML = clearFormFields(e.target.lastChild.outerHTML);
        }
        e.target.classList.add('hide');
    }, 0);
}

function clearFormFields(form){
    let valuesCleared = 0;
    let clearedForm = form;
    // console.log(form);
    while(valuesCleared<5){
        //all the form groups have 6 inputs. The first 5 need to be cleared out when we move an album away.
        // console.log(clearedForm);
        let portionUntilNextValue = clearedForm.substring(0, clearedForm.indexOf(" value=\""));
        // console.log(portionUntilNextValue);
        let indexOfSubsequentQuote = clearedForm.indexOf("\"", (portionUntilNextValue.length+ (" value=\"".length)));
        // console.log(clearedForm.substring(indexOfSubsequentQuote+1));
        clearedForm = portionUntilNextValue + clearedForm.substring(indexOfSubsequentQuote+1);
        // console.log(clearedForm);
        valuesCleared++;
    }
    return clearedForm;
    // clearedForm = form.substring(0, form.indexOf(" value=\""));
    // console.log(form.substring(clearedForm.length+9,form.indexOf(" value=\"") ))
    // console.log(form);

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
    // console.log(e.target);
    if (!(navigator.userAgent.toLowerCase().indexOf('firefox') > -1)) {
        dropNonFirefoxPath(e);
        //Firefox path
    } else if (navigator.userAgent.toLowerCase().indexOf('firefox') > -1) {
        dropFireFoxPath(e);
    }
    submissionControl();
}

function dropNonFirefoxPath(e){
    //non-firefox path
    let theOriginalTag;
    //wrapping the logic up in the block that starts on the next line has the benefit of making 's' (which ends up
    //being the value we assigned to dataTransfer at dragStart) accessible throughout the block
    e.dataTransfer.items[0].getAsString(function (s){
        if(s.indexOf("<img title=")==-1){//In this case, s is the image link from a search result and the alt tag
            let trimmedAlbumArtLink;
            //Code to strip out the alt portion
            if (s.indexOf(" ") !== -1) {
                trimmedAlbumArtLink = s.substring(0, s.indexOf(" "));
                // console.log(trimmedAlbumArtLink);
            } else {
                trimmedAlbumArtLink = s;
            }
            // console.log(trimmedAlbumArtLink);
            if((document.querySelector(`.search-result [src='` + trimmedAlbumArtLink + `']`))==null){
                theOriginalTag = document.querySelector(`[src='` + trimmedAlbumArtLink + `']`).outerHTML;
            } else{
                theOriginalTag = document.querySelector(`.search-result [src='` + trimmedAlbumArtLink + `']`).outerHTML;
            }
            let newImgTag = theOriginalTag.replace("width=\"120\" height=\"120\"", "width=\"100%\" height=\"100%\"");
            if (e.target.className == "p-3") { //dropped on the p-3 class tag path
                // console.log(s); //In firefox the alt attribute gets included in the string for some reason. In chrome, it's just the img link
                let imgPlusHiddenForms = newImgTag + createAlbumInfoFields(newImgTag, e.target.parentNode.getAttribute("value"));

                e.target.innerHTML = imgPlusHiddenForms;
                e.dataTransfer.items.clear();
                e.target.firstChild.addEventListener('dragstart',dragStartButForAlreadyDroppedAlbums);
            }
            else if (e.target.tagName == "IMG") { //dropped ON an image path
                let imgPlusHiddenForms = newImgTag + createAlbumInfoFields(newImgTag, e.target.parentNode.parentNode.getAttribute("value"));
                e.target.nextSibling.outerHTML = "";
                e.target.outerHTML = imgPlusHiddenForms;
                e.target.addEventListener('dragstart',dragStartButForAlreadyDroppedAlbums);

                // console.log(e.dataTransfer.items[0]);

            } else if(e.target.className == "col"){
                let imgPlusHiddenForms = newImgTag + createAlbumInfoFields(newImgTag, e.target.getAttribute("value"));

                e.target.firstChild.innerHTML = imgPlusHiddenForms;
                e.dataTransfer.items.clear();
                e.target.firstChild.firstChild.addEventListener('dragstart',dragStartButForAlreadyDroppedAlbums);
            }
        } else{
            let imgTag= s;
            if((e.target.className == "col")){
                // console.log("is it reaching this?")
                let imgPlusHiddenForms = imgTag + createAlbumInfoFields(imgTag, e.target.getAttribute("value"));
                // console.log(imgPlusHiddenForms);
                e.target.firstChild.innerHTML = imgPlusHiddenForms;
                // console.log(e.target.firstChild.firstChild);
                e.target.firstChild.addEventListener('dragstart',dragStartButForAlreadyDroppedAlbums);
            }else if((e.target.className == "p-3")){
                // console.log("is it reaching this?")
                let imgPlusHiddenForms = imgTag + createAlbumInfoFields(imgTag, e.target.parentNode.getAttribute("value"));
                // console.log(imgPlusHiddenForms);
                e.target.innerHTML = imgPlusHiddenForms;
                // console.log(e.target.firstChild);
                e.target.firstChild.addEventListener('dragstart',dragStartButForAlreadyDroppedAlbums);
            }else if((e.target.tagName == "IMG")){
                console.log("is it reaching this?")
                let imgPlusHiddenForms = imgTag + createAlbumInfoFields(imgTag, e.target.parentNode.parentNode.getAttribute("value"));
                // console.log(imgPlusHiddenForms);
                e.target.parentNode.innerHTML = imgPlusHiddenForms;
                // console.log(e.target.firstChild);
                e.target.addEventListener('dragstart',dragStartButForAlreadyDroppedAlbums);
            }
        }
    });
}

function dropFireFoxPath(e){
    let transferData = e.dataTransfer.getData('text/plain');
    if(transferData.indexOf("<img ")==-1){//In this case, tData is the image link from a search result
        //having to improvise a way to select the img element with said src attribute
        // console.log(transferData);
        let theImgTagThatWeWantToCopy = document.querySelector(`.search-result [src=${CSS.escape(transferData)}]`);
        if(theImgTagThatWeWantToCopy == null){
            theImgTagThatWeWantToCopy= document.querySelector(`[src=${CSS.escape(transferData)}]`);
        }
        // console.log(theImgTagThatWeWantToCopy);
        let newImgTag = theImgTagThatWeWantToCopy.outerHTML;
        newImgTag = newImgTag.replace("width=\"120\" height=\"120\"", "width=\"100%\" height=\"100%\"") //here we're using the portion of the tag that defines the height and width to strip that out. If we change the results from appearing 120x120 we'll have to change or remove this

        if (e.target.className == "col") {// get the draggable element
            // console.log(transferData);
            let imgPlusHiddenForms = newImgTag + createAlbumInfoFields(newImgTag, e.target.getAttribute("value"));
            //this next line logs the position value of the drop area (it's attached to the parent div we're dropping the img tag into)
            // console.log(e.target.parentNode.getAttribute("value"));
            e.target.firstChild.innerHTML = imgPlusHiddenForms;
            // console.log(e.target.firstChild);
            e.target.firstChild.firstChild.addEventListener('dragstart',dragStartButForAlreadyDroppedAlbums);
            // console.log(id);
        }else if (e.target.className == "p-3") {// get the draggable element
            // console.log(transferData);
            let imgPlusHiddenForms = newImgTag + createAlbumInfoFields(newImgTag, e.target.parentNode.getAttribute("value"));
            //this next line logs the position value of the drop area (it's attached to the parent div we're dropping the img tag into)
            // console.log(e.target.parentNode.getAttribute("value"));
            e.target.innerHTML = imgPlusHiddenForms;
            // console.log(e.target.firstChild);
            e.target.firstChild.addEventListener('dragstart',dragStartButForAlreadyDroppedAlbums);
        } //this defines what to do if user drops over an image already in a tag
        else if (e.target.tagName == "IMG") {
            let imgPlusHiddenForms = newImgTag + createAlbumInfoFields(newImgTag, e.target.parentNode.parentNode.getAttribute("value"));
            //this next line logs the position value of the drop area (it's attached to the parent div we're dropping the img tag into)
            // console.log(e.target.parentNode.getAttribute("value"));
            e.target.nextSibling.outerHTML = "";    //clears out the form affiliated with the former image
            e.target.outerHTML = imgPlusHiddenForms;
            // console.log(e.target);
            // e.target.removeEventListener('dragstart', dragStart);
            e.target.addEventListener('dragstart',dragStartButForAlreadyDroppedAlbums);
        }
    } else{ //in this case tData is the text of an existing tag, grabbed fom inside the topster area we're dropping elsewhere
        if((e.target.className == "p-3")){
            let imgPlusHiddenForms = transferData + createAlbumInfoFields(transferData, e.target.parentNode.getAttribute("value"));
            e.target.innerHTML = imgPlusHiddenForms;
            // console.log(e.target.firstChild);
            e.target.firstChild.addEventListener('dragstart',dragStartButForAlreadyDroppedAlbums);
        } else if((e.target.tagName == "IMG")){
            let imgPlusHiddenForms = transferData + createAlbumInfoFields(transferData, e.target.parentNode.parentNode.getAttribute("value"));
            // console.log(e.target.outerHTML);
            e.target.nextSibling.outerHTML="";
            e.target.outerHTML = imgPlusHiddenForms;
            // console.log(e.target.nextSibling.innerHTML);
            // console.log(e.target);
            e.target.addEventListener('dragstart',dragStartButForAlreadyDroppedAlbums);
        } else if((e.target.className == "col")){
            let imgPlusHiddenForms = transferData + createAlbumInfoFields(transferData, e.target.getAttribute("value"));
            e.target.firstChild.innerHTML = imgPlusHiddenForms;
            // console.log(e.target.firstChild.firstChild);
            e.target.firstChild.firstChild.addEventListener('dragstart',dragStartButForAlreadyDroppedAlbums);
        }
    }




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
        imgTagHTML.indexOf("\"", (imgTagHTML.indexOf("data-spotifyid=") + 16)));
    // console.log(spotifyID);

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

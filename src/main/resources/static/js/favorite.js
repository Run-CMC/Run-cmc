'use strict';

function favoriteClick(e){
    let data = e.target.dataset.topsterid;
    console.log(e.target.parentNode.nextSibling.nextSibling.outerHTML);
    let currentDisplayedNumberOfFavoritesOnTarget = parseInt((e.target.parentNode.nextSibling.nextSibling.innerText).substring(1));
    fetch(`/favorites/topster/${data}`,{
        method: 'POST',
        credentials: "same-origin",
        headers: {
        'Content-Type': 'text/plain',
        'Content-Length': 0,
        'Accept': '*/*'
        }

    }).then(response => {
        console.log(response);

        if (e.target.classList.contains("fas")) {
            e.target.classList.remove("fas");
            e.target.classList.add("far");
            (e.target.parentNode.nextSibling.nextSibling.innerText) = (e.target.parentNode.nextSibling.nextSibling.innerText).substring(0,1) + (currentDisplayedNumberOfFavoritesOnTarget-1);
        } else if (e.target.classList.contains("far")) {
            e.target.classList.remove("far");
            e.target.classList.add("fas");
            (e.target.parentNode.nextSibling.nextSibling.innerText)= (e.target.parentNode.nextSibling.nextSibling.innerText).substring(0,1) + (currentDisplayedNumberOfFavoritesOnTarget+1);
        }
    })
        .catch(error => console.error(error));


}
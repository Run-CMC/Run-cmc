'use strict';

function favoriteClick(e){
    let data = e.target.dataset.topsterid;
    // console.log(data);
    fetch(`/favorites/topster/${data}`,{
        method: 'POST',
        credentials: 'include',
        headers: {
        'Content-Type': 'application/json'
        },
        body: JSON.stringify("")
    }).then(response => console.log(response))
        .catch(error => console.error(error));

    if(e.target.classList.contains("fas")){
        e.target.classList.remove("fas");
        e.target.classList.add("far");
    } else if(e.target.classList.contains("far")){
        e.target.classList.remove("far");
        e.target.classList.add("fas");
    }
}
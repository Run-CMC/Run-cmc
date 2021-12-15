function deleteConfirm(event){
    if(confirm("Are you sure you want to delete this topster?")){
        event.target.parentNode.parentNode.submit();
    }
}
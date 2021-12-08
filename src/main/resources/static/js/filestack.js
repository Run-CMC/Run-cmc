"use strict";




    function openFilePicker() {



    const client = filestack.init(filestack_api_key);
    client.picker().open();
    function onUploadDone(result) {
    	console.log(result)
    	const url = result.filesUploaded[0].url
    	$('#profile-photo').attr({src: url})
    	$('#profile-photo-input').val(url)
    }

    const options = {
    	onUploadDone,
    };

    const picker = client.picker(options);
    picker.open();

};

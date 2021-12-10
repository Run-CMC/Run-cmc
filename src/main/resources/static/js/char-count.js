$(document).ready(function() {
	var text_max = 255;
	$('#char-count').html(text_max + ' characters remaining');

	$('#bio').keyup(function() {
		var text_length = $('#bio').val().length;
		var text_remaining = text_max - text_length;

		$('#char-count').html(text_remaining + ' characters remaining');
	});
	//topster creation
	$('#char-count').html(text_max + ' characters remaining');

	$('#topster-text-body').keyup(function() {
		var text_length = $('#topster-text-body').val().length;
		var text_remaining = text_max - text_length;

		$('#char-count').html(text_remaining + ' characters remaining');
	});
});
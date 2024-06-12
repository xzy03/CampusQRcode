$(document).ready(function() {
    $('.arrow').click(function() {
        var extraDetails = $(this).closest('.booking-item').next('.extra-details');
        extraDetails.toggle();

        // Toggle arrow direction
        if ($(this).text() === '\u25BA') { // '▶' character
            $(this).html('&#9660;'); // '▼' character
        } else {
            $(this).html('&#9654;'); // '▶' character
        }
    });
});




$(document).ready(function() {
    $('.arrow').click(function() {
        var extraDetails = $(this).closest('.booking-item').next('.extra-details');
        extraDetails.toggle();
        // Toggle arrow direction
        var arrow = $(this);
        if (arrow.text() === '▶') { // '▶' character
            arrow.text('▼'); // '▼' character
        } else {
            arrow.text('▶'); // '▶' character
        }
    });
});

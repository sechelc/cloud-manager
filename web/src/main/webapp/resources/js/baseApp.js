var app = app || {};
(function (app) {

    app.map = initialize();
})(app);
function initialize() {
    var mapOptions = {
        center: {lat: 46.771630, lng: 23.591965},
        zoom: 8
    };
    var map = new google.maps.Map(document.getElementById('bigMap'),
            mapOptions);
    return map;
}
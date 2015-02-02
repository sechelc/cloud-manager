var app = app || {};
function initialize() {
    var mapOptions = {
        center: {lat: 46.771630, lng: 23.591965},
        zoom: 8
    };
    var map = new google.maps.Map(document.getElementById('bigMap'),
            mapOptions);
    return map;
}
jQuery(document).ready(function ($) {
    app.map = initialize();
    app.mapPointsCol = new app.mapPoints;
    app.mapPointsViewRef = new app.mapPointsView({
        collection: app.mapPointsCol
    });
    app.mapPointsViewRef.map = app.map;
    app.mapPointsCol.fetch();
});
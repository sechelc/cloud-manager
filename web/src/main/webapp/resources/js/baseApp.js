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
    google.maps.event.addListener(app.map, 'click', function (e) {
        //render another view pop from bottom iooooiii
        if (app.truckInfoViewRef) {
            app.truckInfoViewRef.remove(app.truckInfoViewRef);
        }
    });
    app.tpl.loadTemplates(['truckInfo'], function () {
        app.mapPointsViewRef = new app.mapPointsView({
            collection: app.mapPointsCol
        });
        app.mapPointsViewRef.map = app.map;
    });
    app.mapPointsCol.fetch();

});
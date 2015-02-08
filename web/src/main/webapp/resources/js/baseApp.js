var app = app || {};
function initialize() {
    var mapOptions = {
        center: {lat: 0, lng: 0},
        streetViewControl: false,
        zoom: 0
    };
    var map = new google.maps.Map(document.getElementById('bigMap'),
            mapOptions);
    return map;
}
jQuery(document).ready(function ($) {
    app.map = initialize();
    app.latlngbounds = new google.maps.LatLngBounds();
    app.trucksPointsCol = new app.trucksPoints;
    app.deliverySites = new app.staticPoints('ds', 'test');
    app.batchingPlants = new app.staticPoints('bp', 'test');
    google.maps.event.addListener(app.map, 'click', function (e) {
        //render another view pop from bottom iooooiii
        if (app.truckInfoViewRef) {
            app.truckInfoViewRef.remove(app.truckInfoViewRef);
        }
    });
    app.tpl.loadTemplates(['truckInfo', 'truckInfoUL'], function () {
        app.mapPointsViewRef = new app.mapPointsView({
            collection: app.trucksPointsCol
        });
        app.mapPointsViewRef.map = app.map;
    });

    app.deliverySitesRef = new app.staticPointsView({
        collection: app.deliverySites
    });
    app.deliverySitesRef.map = app.map;

    app.batchingPlantsRef = new app.staticPointsView({
        collection: app.batchingPlants
    });
    app.batchingPlantsRef.map = app.map;


    app.trucksPointsCol.fetch();
    app.batchingPlants.fetch();
    app.deliverySites.fetch();
//    updateTrucksPos();
});

function updateTrucksPos() {
    //animate this map!
    setTimeout(function () {
//        var model = app.trucksPointsCol.models[ Math.floor(Math.random() * app.trucksPointsCol.length)];
//        var newLat = model.get('latitude') + Math.random() / 10 - Math.random() / 10;
//        var newLng = model.get('longitude') + Math.random() / 10 - Math.random() / 10;
//        model.set('latitude', newLat);
//        model.set('longitude', newLng);
//        console.info('moved: ', model.cid)
        app.trucksPointsCol.fetch();
        updateTrucksPos();
    }, 30000);
}
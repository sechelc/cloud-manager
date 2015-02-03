var app = app || {};

app.mapPointModel = Backbone.Model.extend({
    //parse lat log to map objects
    defaults: {
        marker: {}
    },
    parse: function (data) {
        data.marker = new google.maps.Marker({
            position: new google.maps.LatLng(parseInt(data.latitude, 10) + Math.random(0.05, 0.5), parseInt(data.longitude, 10) + Math.random(0.05, 0.5)),
            title: "Truck ID:" + data.truckNo
        });
        return data;
    }
});

app.mapPoints = Backbone.Collection.extend({
    model: app.mapPointModel,
    url: "log",
    parse: function (data) {
        return data._embedded.log;
    }
});
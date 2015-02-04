var app = app || {};

app.mapPointModel = Backbone.Model.extend({
    //parse lat log to map objects
    idAttribute : "truckNo",
    url: function () {
        var _links = this.get('_links');
        return   _links.self.href;
    },
    defaults: {
        marker: {},
        drawn: false
    },
    parse: function (data) {
        data.latitude = parseInt(data.latitude, 10) + Math.random(0.05, 0.5);
        data.longitude = parseInt(data.longitude, 10) + Math.random(0.05, 0.5);
        data.marker = new google.maps.Marker({
            position: new google.maps.LatLng(data.latitude, data.longitude),
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
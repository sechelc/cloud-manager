var app = app || {};

app.truckPointModel = Backbone.Model.extend({
    //parse lat log to map objects
    idAttribute: "truckNo",
    url: function () {
        var _links = this.get('_links');
        return   _links.self.href;
    },
    defaults: {
        marker: {},
        drawn: false
    },
    parse: function (data) {
//        data.latitude = parseInt(data.latitude, 10) + Math.random(0.05, 0.5);
//        data.longitude = parseInt(data.longitude, 10) + Math.random(0.05, 0.5);
        data.marker = new google.maps.Marker({
            position: new google.maps.LatLng(data.latitude, data.longitude),
            title: "Truck ID:" + data.truckNo,
            icon: {
                url: 'resources/images/truck.png',
//                size: new google.maps.Size(20, 32)
            }
        });
        return data;
    }
});

app.trucksPoints = Backbone.Collection.extend({
    model: app.truckPointModel,
    url: "log/search/findLatestByCompany?company=test",
    parse: function (data) {
        return data._embedded.log;
    }
});


app.staticPointModel = Backbone.Model.extend({
    //parse lat log to map objects
//    initialize: function () {
//        this.idAttribute = id;
//    },
    idAttribute: "name",
    url: function () {
        var _links = this.get('_links');
        return   _links.self.href;
    },
    defaults: {
        marker: {},
        drawn: false
    },
    parse: function (data) {
//        data.latitude = parseInt(data.latitude, 10) + Math.random(0.05, 0.5);
//        data.longitude = parseInt(data.longitude, 10) + Math.random(0.05, 0.5);
        data.marker = new google.maps.Marker({
            position: new google.maps.LatLng(data.latitude, data.longitude),
            title: "Truck ID:" + data.truckNo,
            icon: this.collection.image
        });
        return data;
    }
});

app.staticPoints = Backbone.Collection.extend({
    initialize: function (type, company) {
        this.url = type + this.url + company;
        this.type = type;
        this.image = {
            url: 'resources/images/' + this.type + '.png',
            // This marker is 20 pixels wide by 32 pixels tall.
//            size: new google.maps.Size(20, 32)
            // The origin for this image is 0,0.
        };
    },
    model: app.staticPointModel,
    url: "/search/findByCompany?company=",
    parse: function (data) {
        return data._embedded[this.type];
    }
});
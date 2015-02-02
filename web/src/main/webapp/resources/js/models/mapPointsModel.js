var app = app || {};

app.mapPointModel = Backbone.Model.extend({
    //parse lat log to map objects
    defaults: {
        marker: {}
    },
    parse: function (data) {
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
var app = app || {};

app.mapPointsView = Backbone.View.extend({
    template: _.template('<li><b>Name: </b>TruckNo <%=model.get("truckNo")%></li><li><b>Last Updated: </b><%=model.get("timestamp")%></li>'),
    tagName: 'ul',
    events: {
//        "click .toggle": "toggleDone",
//        "dblclick .view": "edit",
//        "click a.destroy": "clear",
//        "keypress .edit": "updateOnEnter",
//        "blur .edit": "close"
    },
    initialize: function () {
        this.listenTo(this.collection, 'change sync', this.render);
    },
    render: function () {
        var self = this;
        _.each(self.collection.models, function (model, index) {
            var marker = new google.maps.Marker({
                position: new google.maps.LatLng(parseInt(model.get('latitude'), 10) + Math.random(0.05, 0.5), parseInt(model.get('longitude'), 10) + Math.random(0.05, 0.5)),
                title: "Truck ID:" + model.get('truckNo'),
                map: self.map
            });
            model.set('marker', marker);
            var popup = new google.maps.InfoWindow({
                content: self.template({model: model})
            });
            google.maps.event.addListener(marker, 'mouseover', function (e) {
                popup.open(self.map, marker);
            });
            google.maps.event.addListener(marker, 'mouseout', function (e) {
                popup.close();
            });
        });
        return this;
    }
});
        
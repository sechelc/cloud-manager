var app = app || {};


app.truckInfoView = Backbone.View.extend({
//    template : _.template($('#truckInfo_template').html()),
//    el: '#truckInfo'
});

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
            var marker = model.get('marker');
            marker.setMap(self.map);
            var popup = new google.maps.InfoWindow({
                content: self.template({model: model})
            });
            google.maps.event.addListener(marker, 'mouseover', function (e) {
                popup.open(self.map, marker);
            });
            google.maps.event.addListener(marker, 'mouseout', function (e) {
                popup.close();
            });
            google.maps.event.addListener(marker, 'click', function (e) {
                //render another view pop from bottom iooooiii
            });
        });
        return this;
    }
});
        
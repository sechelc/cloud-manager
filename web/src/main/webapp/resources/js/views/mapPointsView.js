var app = app || {};

$(function () {

    app.truckInfoView = Backbone.View.extend({
        el: '#truckInfo',
        initialize: function () {
            this.listenTo(this.model, 'change', this.render);
            this.template = _.template(app.tpl.get('truckInfo'));
        },
        render: function () {
            this.$el.html(this.template({model: this.model}));
            this.$el.animate({
                height: '15%'
            }, 500);
            return this;
        },
        remove: function (self) {
            self.$el.animate({
                height: '0'
            }, 300, function () {
                self.$el.html('');
            });
            self.stopListening();
        }
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
                if (!model.get('drawn')) {
                    //draw new marker, attach events
                    marker.setMap(self.map);
                    model.set('drawn', true);
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
                        e.cancelBubble = true;
                        //render another view pop from bottom iooooiii
                        if (app.truckInfoViewRef) {
                            app.truckInfoViewRef.model = model;
                        } else {
                            app.truckInfoViewRef = new app.truckInfoView({
                                model: model
                            });
                        }
                        app.truckInfoViewRef.render();
                    });
                } else {
                    //update position
                    marker.setPosition( new google.maps.LatLng( model.get('latitude'),model.get('longitude') ) );
                }
            });
            return this;
        }
    });
});
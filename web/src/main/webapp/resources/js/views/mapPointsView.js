var app = app || {};

$(function () {

    app.truckInfoView = Backbone.View.extend({
        el: '#truckInfo',
        events: {
            'click #showChart': 'showChart',
            'click #hideChart': 'hideChart'
        },
        initialize: function () {
            this.listenTo(this.model, 'change sync', this.render);
            this.template = _.template(app.tpl.get('truckInfo'));
            this.templateUL = _.template(app.tpl.get('truckInfoUL'));
            this.chartOpened = 1;
        },
        render: function () {
            if (this.chartOpened === 1) {
                //first time
                this.$el.html(this.template({model: this.model}));
                this.$el.find('table').html(this.templateUL({model: this.model}));
                this.$el.animate({
                    height: '15%'
                }, 500);
            } else if (this.chartOpened) {
                this.$el.find('table').html(this.templateUL({model: this.model}));
            } else {
                this.$el.html(this.template({model: this.model}));
                this.$el.animate({
                    height: '15%'
                }, 500);
            }
            return this;
        },
        remove: function (self) {
            self.$el.animate({
                height: '0'
            }, 300, function () {
                self.$el.html('');
                self.$el.find('#chart').empty();
            });
            app.destroyTruckRecentInfoChart(this.$el.find('#truckRecentInfoChart'));
            this.chartOpened = false;
            this.chartOpened = 1;
            self.stopListening();
        },
        showChart: function (e) {
            app.getChartDataAndDraw(this.model.get('truckNo'), this.$el.find('#truckRecentInfoChart'));
            this.$el.animate({
                height: '95%'
            }, 200);
            $(e.currentTarget).addClass('hide').siblings("#hideChart").removeClass('hide');
            this.chartOpened = true;
        },
        hideChart: function (e) {
            this.$el.animate({
                height: '15%'
            }, 200);
            app.destroyTruckRecentInfoChart(this.$el.find('#truckRecentInfoChart'));
            $(e.currentTarget).addClass('hide').siblings("#showChart").removeClass('hide');
            this.chartOpened = false;
        }
    });

    app.mapPointsView = Backbone.View.extend({
        template: _.template('<li><b>Name: </b>TruckNo <%=model.get("truckNo")%></li><li><b>Last Updated: </b><%= app.timeConverter(model.get("timestamp"))%></li>'),
        tagName: 'ul',
        el: '#trucksUl',
        events: {
            "click .truckLi": "highlighTruck",
//        'hover truckLi':'animateHover'
//        "dblclick .view": "edit",
            "click button.navbar-toggle": "toggleList",
            "hover button.navbar-toggle": "toggleSomeOfList"
//        "keypress .edit": "updateOnEnter",
//        "blur .edit": "close"
        },
        initialize: function () {
            this.listenTo(this.collection, 'change sync', this.render);
            this.templateList = _.template(app.tpl.get('trucksList'));
            this.listOpened = false;
        },
        render: function () {
            var self = this,
                    anyNew = false;
            self.$el.html((this.templateList({collection: this.collection})));
            _.each(self.collection.models, function (model, index) {
                var marker = model.get('marker');
                if (!model.get('drawn')) {
                    anyNew = true;
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
                    marker.setPosition(new google.maps.LatLng(model.get('latitude'), model.get('longitude')));
                }
                app.latlngbounds.extend(marker.getPosition());
            });
            if (anyNew)
                self.map.fitBounds(app.latlngbounds);
            return this;
        },
        highlighTruck: function (ev) {
            var model = this.collection.get($(ev.currentTarget).data('cid'));
            var marker = model.get('marker');
            if (app.truckInfoViewRef) {
                app.truckInfoViewRef.model = model;
            } else {
                app.truckInfoViewRef = new app.truckInfoView({
                    model: model
                });
            }
            app.truckInfoViewRef.render();
//            marker.setIcon('resources/images/truckRED.png');
            if (marker.getAnimation() !== null) {
                marker.setAnimation(null);
            } else {
                marker.setAnimation(google.maps.Animation.BOUNCE);
                setTimeout(function () {
                    marker.setAnimation(null);
                }, 5000);
            }
        },
        toggleList: function (ev) {
            $(ev.currentTarget).toggleClass("active");
            this.$el.toggleClass("opened");
            this.listOpened = !this.listOpened;
        },
        toggleSomeOfList: function (ev) {
//            alert('futincur!');
            this.$el.toggleClass("partialOpened");
        }
    });
    app.staticPointsView = Backbone.View.extend({
        template: _.template('<li><b>Name: </b><%=model.get("name")%></li><li><b>Type: </b><%= model.collection.type == "ds" ? "Delivery Site" : model.collection.type == "bp" ? "Batching Plant" : "Unknown" %></li>'),
        tagName: 'ul',
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
//                    google.maps.event.addListener(marker, 'click', function (e) {
//                        e.cancelBubble = true;
//                        //render another view pop from bottom iooooiii
//                        if (app.truckInfoViewRef) {
//                            app.truckInfoViewRef.model = model;
//                        } else {
//                            app.truckInfoViewRef = new app.truckInfoView({
//                                model: model
//                            });
//                        }
//                        app.truckInfoViewRef.render();
//                    });
                } else {
                    //update position
                    marker.setPosition(new google.maps.LatLng(model.get('latitude'), model.get('longitude')));
                }
                app.latlngbounds.extend(marker.getPosition());
            });
            self.map.fitBounds(app.latlngbounds);
            return this;
        }
    });
});
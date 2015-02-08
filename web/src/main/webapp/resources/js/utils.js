var app = app || {};

app.tpl = {
    // Hash of preloaded templates for the app
    templates: {},
    // Recursively pre-load all the templates for the app.
    // This implementation should be changed in a production environment. All the template files should be
    // concatenated in a single file.
    loadTemplates: function (names, callback) {

        var that = this;

        var loadTemplate = function (index) {
            var name = names[index];
            $.get('resources/templates/' + name + '.html', function (data) {
                that.templates[name] = data;
                index++;
                if (index < names.length) {
                    loadTemplate(index);
                } else {
                    callback();
                }
            });
        }

        loadTemplate(0);
    },
    // Get template by name from hash of preloaded templates
    get: function (name) {
        return this.templates[name];
    }

};

app.timeConverter = function (UNIX_timestamp) {
    var a = new Date(UNIX_timestamp);
    var months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
    var year = a.getFullYear();
    var month = months[a.getMonth()];
    var date = a.getDate() < 10 ? '0' + a.getDate() : a.getDate();
    var hour = a.getHours() < 10 ? '0' + a.getHours() : a.getHours();
    var min = a.getMinutes() < 10 ? '0' + a.getMinutes() : a.getMinutes();
    var sec = a.getSeconds() < 10 ? '0' + a.getSeconds() : a.getSeconds();
    var time = date + '-' + month + '-' + year + ' ' + hour + ':' + min + ':' + sec;
    return time;
};

app.getChartDataAndDraw = function (truckNo, container) {
    var data;
    $.ajax({
        type: 'GET',
        url: 'logs/graph?truckNo=' + truckNo,
        success: function (response) {
            if (response.graphData)
                data = response.graphData;
            app.drawTruckRecentInfoChart(data, container, truckNo);
        }
    });
};

app.drawTruckRecentInfoChart = function (data, container, truckNo) {
    //get data for chart...
//    console.warn(data);
    var
            i = 0, data0 = [], data1 = [], data2 = [], data3 = [],data4 = [],
            // set the allowed units for data grouping
            dataLength = data.length, max = 0,
            lastTimestamp = data[dataLength - 1][0];

    for (i; i < dataLength; i += 1) {
        data0.push([
            data[i][0], // the date
            data[i][1] // speed
        ]);
        data1.push([
            data[i][0], // the date
            data[i][2] // Slump
        ]);
        data2.push([
            data[i][0], // the date
            data[i][3] // tempProbe
        ]);
        data3.push([
            data[i][0], // the date
            data[i][4] // Volume
        ]);
        data4.push([
            data[i][0], // the date
            data[i][5] // pressure
        ]);
    }
//    console.warn(data2);
    // create the chart
    $(container).highcharts('StockChart', {
        chart: {
            events: {
                load: function () {
                    var self = this;
                    // set up the updating of the chart each second
                    this.updateInterval = setInterval(function () {
                        $.get('logs/add', function () {
                        });
                        $.getJSON('logs/graphGreaterThan?truckNo=' + truckNo + '&timestamp=' + lastTimestamp, function (data) {
                            if (data.graphData) {
                                data = data.graphData;
                                var dataLength = data.length;
                                if (dataLength > 0) {
                                    lastTimestamp = data[dataLength - 1][0];
                                    var i = 0;
                                    for (i; i < dataLength; i += 1) {
                                        var x = data[i][0];
                                        $.each(self.series, function (j, series) {
                                            if (data[i][j + 1]) {
                                                var y = data[i][j + 1];
                                                series.addPoint([x, y], true, true);
                                            }
                                        });
                                    }
                                }
                            }
                        });
                    }, 30000);
                }
            },
            height: '700',
            backgroundColor: 'rgba(0,0,0,0)'
        },
        tooltip: {
            valueDecimals: 2,
            backgroundColor: 'rgba(0, 0, 0, 0.9)',
            borderColor: 'gray',
            borderWidth: 1
        },
        rangeSelector: {
            enabled: false
        },
        colors: ['#FF0000', '#00FF00', '#0000FF', '#FFFFFF'],
        yAxis: [{
                title: {
                    text: 'Speed'
                },
                height: '20%',
                offset: 0
            }, {
                title: {
                    text: 'Slump'
                },
                top: '20%',
                height: '20%',
                offset: 0

            }, {
                title: {
                    text: 'Temperature'
                },
                top: '40%',
                height: '20%',
                offset: 0
            }, 
            {
                title: {
                    text: 'Volume'
                },
                top: '60%',
                height: '20%',
                offset: 0
            },
            {
                title: {
                    text: 'Pressure'
                },
                top: '80%',
                height: '20%',
                offset: 0
            }
        ],
        series: [{
                type: 'areaspline',
                name: 'Speed',
                id: '111',
                data: data0,
                yAxis: 0
            }
            , {
                type: 'line',
                name: 'Slump',
                data: data1,
                id: '222',
                yAxis: 1
            },
            {
                type: 'areaspline',
                name: 'Temperature',
                data: data2,
                id: '444',
                yAxis: 2

            },
            {
                type: 'area',
                name: 'Volume',
                data: data3,
                id: '333',
                yAxis: 3
            },
            {
                type: 'line',
                name: 'Pressure',
                data: data4,
                id: '444',
                yAxis: 4

            }
        ]
    });
};
app.destroyTruckRecentInfoChart = function (container) {
    if (container.highcharts()) {
        clearInterval(container.highcharts().updateInterval);
        container.highcharts().destroy();
    }
};
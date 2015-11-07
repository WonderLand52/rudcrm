angular.module('priceCalc.templates', [])
    //DeadLine buttons data and template
    .factory('deadLineButtonsDataService', function () {
        return function() {
            //TODO: go to server here
            return [
                {
                    value: 3,
                    name: '3 h'
                },
                {
                    value: 6,
                    name: '6 h'
                },
                {
                    value: 12,
                    name: '12 h'
                },
                {
                    value: 24,
                    name: '24 h'
                },
                {
                    value: 48,
                    name: '2 d'
                },
                {
                    value: 72,
                    name: '3 d'
                },
                {
                    value: 144,
                    name: '6 d'
                },
                {
                    value: 240,
                    name: '10 d'
                }
                //{
                //    value: 336,
                //    name: '14 d'
                //}
            ]
        }
    })
    .factory('deadLineButtonsTplService', function() {
        return function() {
            return '<div class="deadline" class="btn-group" role="group">' +
                '<button type="button" class="btn btn-default" ' +
                'ng-repeat="button in price.buttons" ng-click="price.setDeadline(button.value)">{{button.name}}</button>' +
                '</div>'
        }
    });
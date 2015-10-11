angular.module('partialsManager', [])
    .directive('ttNavBar', function () {
        return {
            restrict: 'E',
            templateUrl: 'resources/public/partials/nav.html'
        }
    });
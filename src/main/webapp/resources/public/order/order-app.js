'use strict';

var orderApp = angular.module('orderApp', ['common',
    'indexApp',
    'orderApp.registerView',
    'orderApp.orderDetailsView',
    'orderApp.priceCalcView',
    'spring-security-csrf-token-interceptor']);

orderApp.controller('OrderProcessingCtrl', ['$scope', function ($scope) {

    $scope.tab = {
        activeTab: 1,
        proceedClicked: false,
        validForm: false
    };


    $scope.selectTab = function (tabNumber) {
        $scope.tab.activeTab = tabNumber;
    };

    $scope.isTabSelected = function (tabNumber) {
        return $scope.tab.activeTab === tabNumber;
    };

    $scope.broadcastProcessUserDetails = function () {
        $scope.$broadcast('processUserDetailsTabClicked')
    }
}]);


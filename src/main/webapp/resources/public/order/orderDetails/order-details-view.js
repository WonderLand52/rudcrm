angular.module('orderApp.orderDetailsView', [])
    .directive('detailsInformation', function() {
        return {
            restrict: 'E',
            controller: 'orderDetailsCtrl',
            templateUrl: 'orderDetails/details.html'
        }
    })
    .controller('orderDetailsCtrl', ['$scope', function ($scope) {

        $scope.details = {
            proceedClicked: false,

            isMessagesVisible: function () {
                return $scope.details.proceedClicked;
            },

            processOrderDetails: function () {
                $scope.details.proceedClicked = true;

                if(true) {
                    //TODO: interact with server here

                    //on success
                    $scope.selectTab(3);
                }
            }
        };
        console.log('orderDetails loaded')
    }]);
angular.module('orderApp.priceCalcView', ['priceCalc.templates'])
    .directive('priceCalcInformation', function () {
        return {
            restrict: 'E',
            controller: 'priceCalcCtrl',
            templateUrl: 'priceCalc/price.html'
        }
    })
    .controller('priceCalcCtrl', ['$scope',
                                'deadLineButtonsDataService',
                                'deadLineButtonsTplService',
                                function($scope,
                                        deadLineButtonsDataService,
                                        deadLineButtonsTplService) {
        $scope.price = {
            proceedClicked: false,
            typesOfService: ['Writing from scratch', 'Editing/proofreading'],
            currentDeadline: 0,
            buttons: deadLineButtonsDataService(),
            buttonsTemplate:  deadLineButtonsTplService(),
            isMessagesVisible: function () {
               return $scope.price.proceedClicked;
            },
            setDeadline: function(currentDeadLine) {
                this.currentDeadline = currentDeadLine;
                console.log("this.currentDeadLine: " + this.currentDeadline)
            }
        }


    }])
    .directive ('deadlineButtons', function ($compile) {
        return {
            scope: true,
            link: function(scope, elem, attrs) {
                var el;

                attrs.$observe('template', function (tpl) {
                    if(angular.isDefined(tpl)) {
                        el = $compile(tpl)(scope);
                        elem.html("");
                        elem.append( el );
                    }
                })
            }
        }
    });
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
                                'academicLevelButtonsDataService',
                                'academicLevelButtonsTplService',
                                function($scope,
                                        deadLineButtonsDataService,
                                        deadLineButtonsTplService,
                                        academicLevelButtonsDataService,
                                        academicLevelButtonsTplService) {
        $scope.price = {
            proceedClicked: false,
            typesOfService: ['Writing from scratch', 'Editing/proofreading'],
            currentDeadline: 0,
            currentAcademicLevel: 0,
            currentSlideNumber: 0,
            deadLineButtons: deadLineButtonsDataService(),
            deadlineButtonsTemplate:  deadLineButtonsTplService(),
            academicLevelButtons: academicLevelButtonsDataService(),
            academicLevelButtonsTemplate:  academicLevelButtonsTplService(),
            isMessagesVisible: function () {
               return $scope.price.proceedClicked;
            },
            setDeadline: function(currentDeadLine) {
                this.currentDeadline = currentDeadLine;
            },
            setAcademicLevel: function(currentAcademicLevel) {
                this.currentAcademicLevel = currentAcademicLevel;
            },
            plusSlide: function () {
                if(this.currentSlideNumber < 100) {
                    this.currentSlideNumber++;
                }
            },
            minusSlide: function () {
                if(this.currentSlideNumber > 0 ) {
                    this.currentSlideNumber--;
                }
            }
        }


    }])
    .directive ('baseTemplate', function ($compile) {
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
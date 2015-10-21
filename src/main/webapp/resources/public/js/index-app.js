angular.module( 'indexApp', ['partialsManager'])
    .controller( 'CalculatePriceCtrl', function ($scope) {
        console.log("calculatePriceCtrl");

        $scope.paperTypes = [
            {
                "group": "",
                "value": "Select type of Paper"
            },
            {
                "group": "Essays",
                "value": "Article"
            },
            {
                "group": "Essays",
                "value": "Book review"
            },
            {
                "group": "Essays",
                "value": "Business plan"
            },
            {
                "group": "Dissertations",
                "value": "Dissertation"
            },
            {
                "group": "Dissertations",
                "value": "Dissertation chapter"
            }
        ];

        $scope.subjects = ["Select...", "Accounting", "Anropology"];

        $scope.calculatePrice = function() {
            if($scope.pagesNumber == undefined) {
                $scope.calculatedPrice = '';
            }
            if($scope.defaultType.value != "Select type of Paper"
                && $scope.levelSelect != undefined
                && $scope.deadlineSelect != undefined
                && typeof $scope.pagesNumber === 'number') {
                $scope.calculatedPrice = $scope.pagesNumber * 10;
            }
        };

    })
    .controller("GetCalcDataCtrl", function () {

    });
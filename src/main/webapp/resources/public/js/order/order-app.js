var orderApp = angular.module('orderApp', ['common', 'spring-security-csrf-token-interceptor']);

orderApp.controller('OrderProcessingCtrl', ['$scope', function ($scope) {

    $scope.tab = {
        activeTab: 1,
        proceedClicked: false,
        validForm: false
    };


    $scope.selectTab = function (tabNumber) {
        console.log('selectTab');
        $scope.tab.activeTab = tabNumber;
    };

    $scope.isTabSelected = function (tabNumber) {
        return $scope.tab.activeTab === tabNumber;
    };

    $scope.broadcastProcessUserDetails = function (tabNumber) {
        $scope.$broadcast('processUserDetailsTabClicked', tabNumber)
    }
}]);

orderApp.controller('RegisterCtrl', ['$scope', '$http', function ($scope, $http) {
    console.log('register');

    var isValidRegisterForm = function () {
        return $scope.form.firstname.$valid &&
            $scope.form.secondname.$valid &&
            $scope.form.email.$valid &&
            $scope.form.retypeemail.$valid;
    };

    $scope.$on('processUserDetailsTabClicked', function(event, tabNumber) {
        $scope.processUserDetails(tabNumber);
    });

    $scope.processUserDetails = function (tabNumber) {
        console.log('tabNumber: ' + tabNumber);
        $scope.tab.proceedClicked = true;

        if(isValidRegisterForm()) {
            console.log('form is valid!');
            createUser(tabNumber);
        }
    };

    var createUser = function (tabNumber) {
        console.log("createUser()");
        var postData = {
            firstName: $scope.vm.firstname,
            secondName: $scope.vm.secondname,
            email: $scope.vm.email,
            phone: $scope.vm.phone,
            plainTextPassword: $scope.vm.password
        };

        $http({
            method: 'POST',
            url: '/user',
            data: postData,
            headers: {
                "Content-Type": "application/json",
                "Accept": "text/plain"
            }
        })
            .then(function (response) {
                if (response.status == 200) {
                    $scope.selectTab(tabNumber);
                }
                //if (response.status == 200) {
                //    $scope.login($scope.vm.username, $scope.vm.password);
                //}
                //else {
                //    $scope.vm.errorMessages = [];
                //    $scope.vm.errorMessages.push({description: response.data});
                //    console.log("failed user creation: " + response.data);
                //}
            });
    }

}]);


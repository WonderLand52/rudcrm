angular.module('registerApp', ['common', 'spring-security-csrf-token-interceptor'])
    .controller('RegisterCtrl', ['$scope', '$http', function($scope, $http) {
        console.log("RegisterCtrl");

        $scope.vm.submitted = true;

        $scope.createUser = function () {
            console.log("createUser()");
            var postData = {
                username: $scope.vm.username,
                plainTextPassword: $scope.vm.password,
                email: $scope.vm.email
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
                    if(response.status == 200) {
                        $scope.login()
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
    }])
    .controller('LoginCtrl', ['$scope', function ($scope) {

        $scope.onLogin = function () {
            console.log('Attempting login with username ' + $scope.vm.email + ' and password ' + $scope.vm.password);

            $scope.vm.submitted = true;

            $scope.login($scope.vm.email, $scope.vm.password);

        };
    }]);
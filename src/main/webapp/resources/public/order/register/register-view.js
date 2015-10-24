'use strict';

angular.module('orderApp.registerView', [])
    .directive('registerInformation', function() {
        return {
            restrict: 'E',
            controller: 'registerCtrl',
            templateUrl: 'register/register.html'
        }
    })
    .controller('registerCtrl', ['$scope', '$http', function ($scope, $http) {
        console.log('register');

        $scope.register = {
            proceedClicked: false,

            isMessagesVisible: function () {
                return $scope.register.proceedClicked;
            },

            isValidRegisterForm: function () {
                return $scope.form.firstname.$valid &&
                    $scope.form.secondname.$valid &&
                    $scope.form.email.$valid &&
                    $scope.form.retypeemail.$valid &&
                    $scope.form.password.$valid &&
                    $scope.form.retypepassword.$valid;
            },

            processUserDetails: function () {
                $scope.register.proceedClicked = true;

                if(true/*isValidRegisterForm()*/) {
                    console.log('form is valid!');
                    //createUser(tabNumber);
                    $scope.selectTab(2);
                }
            }
        };

        $scope.$on('processUserDetailsTabClicked', function() {
            $scope.register.processUserDetails();
        });

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
            }).then(function (response) {
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
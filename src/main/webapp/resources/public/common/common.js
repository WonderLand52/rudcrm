angular.module('common', ['ngMessages', 'ngAnimate'])
    .controller('BaseFormCtrl', ['$scope', '$http', function ($scope, $http) {
        var fieldWithFocus;

        $scope.vm = {
            validForm: false,
            errorMessages: []
        };

        $scope.focus = function (fieldName) {
            fieldWithFocus = fieldName;
        };

        $scope.blur = function () {
            fieldWithFocus = undefined;
        };

        $scope.isMessagesVisible = function () {
            return $scope.tab.proceedClicked;
        };

        $scope.preparePostData = function () {
            console.log('Password: ' + $scope.vm.loginPassword);
            console.log('Email: ' + $scope.vm.loginEmail);
            var email = $scope.vm.loginEmail != undefined ? $scope.vm.loginEmail : '';
            var password = $scope.vm.loginPassword != undefined ? $scope.vm.loginPassword : '';

            return 'email=' + email + '&password=' + password;
        };

        $scope.login = function () {
            var postData = $scope.preparePostData();

            $http({
                method: 'POST',
                url: '/authenticate',
                data: postData,
                headers: {
                    "Content-Type": "application/x-www-form-urlencoded",
                    "X-Login-Ajax-call": 'true'
                }
            })
                .then(function (response) {
                    if (response.data == 'ok') {
                        console.log('Login is success');
                        window.location.replace('/resources/personal_area.html');
                    }
                    else {
                        $scope.vm.errorMessages = [];
                        $scope.vm.errorMessages.push({description: 'Access denied'});
                    }
                });
        }


    }])
    .directive('checkMatches', function ($interpolate, $parse) {
        return {
            require: 'ngModel',
            link: function (scope, elm, attrs, ngModel) {
                var pwdToMatch = $parse(attrs.checkMatches);
                var pwdFn = $interpolate(attrs.checkMatches)(scope);
                scope.$watch(pwdFn, function (newVal) {
                    ngModel.$setValidity('checkMatches', ngModel.$viewValue == newVal);
                });

                ngModel.$validators.checkMatches = function (modelValue, viewValue) {
                    var value = modelValue || viewValue;
                    return value == pwdToMatch(scope);
                };
            }
        };
    });
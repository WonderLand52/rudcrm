///////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Defines the javascript files that need to be loaded and their dependencies.
//
///////////////////////////////////////////////////////////////////////////////////////////////////////////

require.config({
    paths: {
        angular: '../../../bower_components/angular/angular',
        csrfInterceptor: '../../../bower_components/spring-security-csrf-token-interceptor/dist/spring-security-csrf-token-interceptor.min',
        angularMessages: '../../../bower_components/angular-messages/angular-messages',
        angularAnimate: '../../../bower_components/angular-animate/angular-animate',
        common: '../../common/common',
        signInApp: 'signin-app'
    },
    shim: {
        angular: {
            exports: "angular"
        },
        angularMessages: {
            deps: ['angular']
        },
        angularAnimate: {
          deps: ['angular']
        },
        csrfInterceptor: {
            deps: ['angular']
        },
        common: {
            deps: ['angular', 'csrfInterceptor', 'angularMessages', 'angularAnimate']
        },
        signInApp: {
            deps: ['common', 'csrfInterceptor', 'angularAnimate']
        }
    }
});

require(['signInApp'], function () {

    angular.bootstrap(document.getElementById('main'), ['registerApp']);

});

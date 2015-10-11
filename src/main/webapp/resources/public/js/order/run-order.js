require.config({
    paths: {
        angular: '../../../bower_components/angular/angular',
        angularMessages: '../../../bower_components/angular-messages/angular-messages',
        partialsManager: '../partials-manager',
        angularAnimate: '../../../bower_components/angular-animate/angular-animate',
        csrfInterceptor: '../../../bower_components/spring-security-csrf-token-interceptor/dist/spring-security-csrf-token-interceptor.min',
        common: '../common',
        makeOrderApp: 'order-app'
    },
    shim: {
        angular: {
            exports: "angular"
        },
        csrfInterceptor: {
            deps: ['angular']
        },
        partialsManager: {
            deps: ['angular']
        },
        angularMessages: {
            deps: ['angular']
        },
        angularAnimate: {
            deps: ['angular']
        },
        calculatePriceApp: {
            deps: ['angular', 'partialsManager']
        },
        bootstrap: {
            deps: ['jQuery']
        },
        common: {
            deps: ['angular', 'csrfInterceptor', 'angularMessages', 'angularAnimate']
        },
        makeOrderApp: {
            deps: ['common', 'csrfInterceptor', 'angularAnimate']
        }
    }
});

require(['makeOrderApp', 'angular'], function () {
    angular.bootstrap(document.getElementById('top'), ['orderApp']);
});
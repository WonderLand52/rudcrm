require.config({
    paths: {
        angular: '../../bower_components/angular/angular',
        angularMessages: '../../bower_components/angular-messages/angular-messages',
        partialsManager: '../common/partials-manager',
        angularAnimate: '../../bower_components/angular-animate/angular-animate',
        csrfInterceptor: '../../bower_components/spring-security-csrf-token-interceptor/dist/spring-security-csrf-token-interceptor.min',
        common: '../common/common',
        indexApp: '../index/index-app',
        registerView: 'register/register-view',
        orderDetailsView: 'orderDetails/order-details-view',
        priceCalcView: 'priceCalc/price-calc-view',
        priceCalcTemplates: 'priceCalc/templates',
        makeOrderApp: 'order-app'
    },
    shim: {
        angular: {
            exports: "angular"
        },
        angularAnimate: {
            deps: ['angular']
        },
        common: {
            deps: ['angular', 'csrfInterceptor', 'angularMessages', 'angularAnimate']
        },
        indexApp: {
            deps: ['angular', 'partialsManager']
        },
        priceCalcView: {
            deps: ['priceCalcTemplates']
        },
        makeOrderApp: {
            deps: ['registerView', 'orderDetailsView', 'priceCalcView', 'common', 'indexApp', 'csrfInterceptor', 'angularAnimate']
        }
    }
});

require(['angular',
         'makeOrderApp'], function () {
    angular.bootstrap(document.getElementById('top'), ['orderApp']);
});
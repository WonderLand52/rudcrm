
require.config({
    paths: {
        angular: '../../bower_components/angular/angular',
        angularMessages: '../../bower_components/angular-messages/angular-messages',
        partialsManager: '../common/partials-manager',
        csrfInterceptor: '../../bower_components/spring-security-csrf-token-interceptor/dist/spring-security-csrf-token-interceptor.min.js',
        indexApp: 'index-app'
    },
    shim: {
        angular: {
            exports: "angular"
        },
        csrfInterceptor: {
            deps: ['angular']
        },
        indexApp: {
            deps: ['angular', 'partialsManager']
        },
        bootstrap: {
            deps: ['jQuery']
        }
    }
});

require(['partialsManager', 'index-app', 'angular'], function () {
    angular.bootstrap(document.getElementById('top'), ['indexApp']);
});

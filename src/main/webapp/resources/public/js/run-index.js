///////////////////////////////////////////////////////////////////////////////////////////////////////////
//
// Defines the javascript files that need to be loaded and their dependencies.
//
///////////////////////////////////////////////////////////////////////////////////////////////////////////

require.config({
    paths: {
        angular: '../../bower_components/angular/angular',
        angularMessages: '../../bower_components/angular-messages/angular-messages',
        partialsManager: 'partials-manager',
        csrfInterceptor: '../../bower_components/spring-security-csrf-token-interceptor/dist/spring-security-csrf-token-interceptor.min',
        indexApp: 'index-app'
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
        indexApp: {
            deps: ['angular', 'partialsManager']
        },
        bootstrap: {
            deps: ['jQuery']
        }
    }
});

require(['indexApp'], function () {
    angular.bootstrap(document.getElementById('top'), ['indexApp']);
});

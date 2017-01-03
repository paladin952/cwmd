angular.module('cwmd', ['restangular', 'ui.router', 'pascalprecht.translate', 'ngFileSaver', 'file-model'])
    .config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/login');

        $stateProvider
            .state('login', {
                url: '/login',
                template: '<login></login>'
            })
            .state('home', {
                url: '/home',
                template: '<home-layout></home-layout>'
            })
            .state('workingArea', {
                url: '/workingArea',
                template: '<working-area></working-area>'
            })
            .state('openTasks', {
                url: '/openTasks',
                template: '<open-tasks></open-tasks>'
            })
            .state('myTasks', {
                url: '/myTasks',
                template: '<my-tasks></my-tasks>'
            })
            .state('closedTasks', {
                url: '/closedTasks',
                template: '<closed-tasks></closed-tasks>'
            })
            .state('addDr', {
                url: '/addDr',
                template: '<add-dr></add-dr>'
            })
            .state('addRn', {
                url: '/addRn',
                template: '<add-rn></add-rn>'
            })
            .state('downloadDr', {
                url: '/downloadDr',
                template: '<download-dr></download-dr>'
            });
    })
    .run(function ($rootScope, $state) {
        $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams, options) {
            // console.log(arguments);

            console.log("State changed from ");
            console.log(fromState);
            console.log(", to ");
            console.log(toState);
        });
    });

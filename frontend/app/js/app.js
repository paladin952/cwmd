angular.module('cwmd', ['restangular', 'ui.router', 'pascalprecht.translate'])
    .config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/workingArea');

        $stateProvider
            .state('login', {
                url: '/login',
                template: '<login></login>'
            })
            .state('workingArea', {
                url: '/workingArea',
                template: '<working-area></working-area>'
            })
            .state('home', {
                url: '/home',
                template: '<home-layout></home-layout>'
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
            .state('home.addDr', {
                url: '/addDr',
                template: '<add-dr></add-dr>'
            })
            .state('home.downloadDr', {
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

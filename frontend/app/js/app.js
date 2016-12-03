angular.module('cwmd', ['restangular', 'ui.router', 'pascalprecht.translate'])
    .config(function ($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/home');

        // $stateProvider
        //     .state('dashboard', {
        //         url: '/dashboard',
        //         template: '<dashboard></dashboard>'
        //     })
        //     .state('herolist', {
        //         url: '/herolist',
        //         template: '<hero-list></hero-list>'
        //     })
        //     .state('detail', {
        //         url: '/detail/:id',
        //         template: '<hero-details></hero-details>'
        //     })
        //     .state('login', {
        //         url: '/login',
        //         template: '<login></login>'
        //     });
    })
    .run(function ($rootScope, $state) {
        $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams, options) {
            // console.log(arguments);
            // debugger
            console.log("State changed from " + fromState + ", to " + toState);

            // if ($rootScope.loggedInUser == null) {
            //     console.log('user not logged in:');
            //     if (toState.name != 'login') {
            //         console.log('redirecting to login');
            //         $state.go('login');
            //         event.preventDefault();
            //     }
            // } else {
            //     console.log('user logged in:', $rootScope.loggedInUser);
            // }
        });
    });

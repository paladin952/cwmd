(function (ng) {
    'use strict';

    ng.module('login', ['ng', 'ngRoute'])
        .config(['$routeProvider', function ($route) {
            $route.when('/login', { templateUrl: 'app/js/user/auth/login.tmpl.html', controller: 'loginCtrl' });
        }]);

})(angular);

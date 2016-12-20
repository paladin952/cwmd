(function (ng) {
    'use strict';

    ng.module('cwmd')
        .component('login', {
            templateUrl: '/app/js/user/auth/login.tmpl.html',
            controller: 'loginCtrl'
        });

})(angular);

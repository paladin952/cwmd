(function (ng,  console) {
    'use strict';

    ng.module('login')
        .controller('loginCtrl', ['$rootScope', '$scope', '$http', '$location', '$route', 'UserSrv',function ($root, $s, $http, $location, $route, userService) {

            $('#loginModal').modal('show');

            $s.currentWantedPage = '';

            $s.showLoginFailedModal = function () {
                $('#loginFailedModal').modal('show');
            };

            $s.showLoginModal = function () {
                $route.reload();
            };

            $s.password = "";
            $s.username ="";

            $s.login = function () {
                if(!$s.username || !$s.password){
                    return;
                }
                userService.login($s.username, $s.password)
                    .then(function(){
                        $location.path('/home');
                    })
                    .then(function(){
                        showLoginFailedModal();
                    });
            }

        }





])})(this.angular, this.console);
(function (ng,  $, console) {
    'use strict';

    ng.module('cwmd')
        .controller('loginCtrl', ['$rootScope', '$scope', '$state', 'UserSrv',function ($root, $s, $state, userService) {

            $('#loginModal').modal('show');

            $s.showLoginFailedModal = function () {
                $('#loginFailedModal').modal('show');
            };

            $s.showLoginModal = function () {
                $state.go("login");
            };

            $s.password = "";
            $s.username ="";

            $s.login = function () {
                if(!$s.username || !$s.password){
                    $s.showLoginFailedModal();
                }
                userService.login($s.username, $s.password)
                    .then(function (response) {
                        var user = response;
                        if (!user){
                            $s.showLoginFailedModal();
                        } else{
                            $state.go('/workingArea');
                        }
                    });
            }
        }





])})(this.angular, this.jQuery, this.console);
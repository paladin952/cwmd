(function (ng,  $, console) {
    'use strict';

    ng.module('cwmd')
        .controller('loginCtrl', ['$rootScope', '$scope', '$state', '$window','UserSrv',function ($root, $s, $state, $window, userService) {

            $('#loginModal').modal('show');

            $s.showLoginFailedModal = function () {
                $('#loginFailedModal').modal('show');
            };

            $s.showLoginModal = function () {
                $state.go("login");
                $('#loginModal').modal('show');
            };

            $s.password = "";
            $s.username ="";

            $s.login = function () {
                if(!$s.username || !$s.password){
                    $s.showLoginFailedModal();
                    return;
                }

                $window.localStorage.setItem("currentUsername", $s.username);
                $window.localStorage.setItem("currentUserRole", $s.role);
                
                $state.go('workingArea');
                userService.login($s.username, $s.password)
                    .then(function (response) {
                        var user = response;
                        $('#loginModal').modal('hide');
                        if (!user){
                            $s.showLoginFailedModal();
                        } else{
                            $state.go('workingArea');
                        }
                    });
            }
        }





])})(this.angular, this.jQuery, this.console);
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
                //userService.login($s.username, $s.password);
                var user = userService.getCurrentUser();
                console.log("user " + user);  
                if (!user){
                    $s.showLoginFailedModal();
                }
                else{
                    console.log("aaa");
                    console.log($state);
                     $state.go("workingArea");
                }   
            }
        }





])})(this.angular, this.jQuery, this.console);
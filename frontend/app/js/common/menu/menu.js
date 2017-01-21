angular.module('cwmd').component('menu', {
    templateUrl: '/app/js/common/menu/menu.html',
    controller: function (UserSrv, $state) {
        var $ctrl = this;

        $ctrl.$onInit = function () {
            $ctrl.username = UserSrv.getCurrentUser;
            $ctrl.isAdmin = UserSrv.isAdmin;
            $ctrl.isManager = UserSrv.isManager;
        };
        console.log($ctrl.isAdmin);
        $ctrl.logout = function () {
            UserSrv.logout();
            $state.go('login');
        }
    }
});
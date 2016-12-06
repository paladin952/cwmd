angular.module('cwmd').component('menu', {
    templateUrl: 'app/js/common/menu/menu.html',
    controller: function (UserSrv) {
        var $ctrl = this;

        $ctrl.$onInit = function () {
            $ctrl.user = UserSrv.getCurrentUser();
        };

        $ctrl.logout = function () {
            UserSrv.logout();
            location.path('/login');
        }
    }
});
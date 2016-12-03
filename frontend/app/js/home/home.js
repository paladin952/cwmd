angular.module('cwmd').component('homeLayout', {
    templateUrl: 'app/js/home/home.html',
    controller: function (UserSrv) {
        var $ctrl = this;
        $ctrl.projectName = "CWMD";

        $ctrl.getUsers = function () {
            UserSrv.getUsers()
                .then(function (response) {
                    $ctrl.users = response;
                    debugger;
                })
                .catch(function (response) {
                    console.log(response);
                });
        };

        // $ctrl.getUsers();
        UserSrv.login('admin','admin');
    }
});
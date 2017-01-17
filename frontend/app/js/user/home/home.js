angular.module('cwmd').component('homeLayout', {
    templateUrl: 'app/js/user/home/home.html',
    controller: function () {
        var $ctrl = this;
        $ctrl.projectName = "CWMD";
        $ctrl.getUsers = function () {
            UserSrv.getUsers()
                .then(function (response) {
                    $ctrl.users = response;
                })
                .catch(function (response) {
                    console.log(response);
                });
        };
    }
});
angular.module('cwmd').component('openTasks', {
    templateUrl: 'app/js/user/open-tasks/openTasks.html',
    controller: function (FlowSrv) {
        var $ctrl = this;
        $ctrl.flows = [];

        $ctrl.$onInit = function () {
            // var currentUser = $window.localStorage.currentUser;
            FlowSrv.getActiveFlows()
                .then(function (response) {
                    $ctrl.flows = response;
                })
                .catch(function (response) {
                    console.log(response);
                });
        };
    }
});
angular.module('cwmd').component('openTasks', {
    templateUrl: 'app/js/user/open-tasks/openTasks.html',
    controller: function (FlowSrv) {
        var $ctrl = this;
        $ctrl.flows = null;

        $ctrl.$onInit = function () {
            // var currentUser = $window.localStorage.currentUser;
            FlowSrv.getFlowsStartedByCurrentUser()
                .then(function (response) {
                    $ctrl.flows = response;
                })
                .catch(function (response) {
                    console.log(response);
                });
        };
    }
});
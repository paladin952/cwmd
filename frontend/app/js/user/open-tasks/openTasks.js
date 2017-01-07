angular.module('cwmd').component('openTasks', {
    templateUrl: 'app/js/user/open-tasks/openTasks.html',
    controller: function (FlowSrv) {
        var $ctrl = this;
        $ctrl.flows = null;

        $ctrl.$onInit = function () {
            FlowSrv.getFlows()
                .then(function (response) {
                    $ctrl.flows = response;
                })
                .catch(function (response) {
                    console.log(response);
                });    
        };
    }
});
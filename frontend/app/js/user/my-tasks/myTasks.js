angular.module('cwmd').component('myTasks', {
    templateUrl: 'app/js/user/my-tasks/myTasks.html',
    controller: function (FlowSrv) {
        var $ctrl = this;
        $ctrl.flows = null;

        $ctrl.$onInit = function () {
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
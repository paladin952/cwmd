angular.module('cwmd').component('closedTasks', {
    templateUrl: 'app/js/user/closed-tasks/closedTasks.html',
    controller: function (FlowSrv) {
        var $ctrl = this;
        $ctrl.finished = [];

        $ctrl.$onInit = function () {
            FlowSrv.getFinishedFlows()
                .then(function (response) {
                    $ctrl.finished = response;
                })
                .catch(function (response) {
                    console.log(response);
                });    
        };
    }
});
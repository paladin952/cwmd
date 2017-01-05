angular.module('cwmd').service('FlowSrv', function (Restangular) {
    var service = this;

    service.getFlows = function () {
        var flows = null;

        Restangular.all('flow').getList()
            .then(function (response) {
                flows = response;
            });
        return flows;
    };

    service.getFlowById = function (flowId) {
        var flow = null;

        Restangular.one('flow', flowId)
            .then(function (response) {
                flow = response;
            });

        return flow;
    };

    service.goToNextDepartment = function (flowId) {
        if (!flowId) {
            return;
        }

        var newFlow = null;

        Restangular.all('flow').post(flowId)
            .then(function (response) {
                newFlow = response;
            });

        return newFlow;
    };

    service.goToInitialDepartment = function (flowId, remark) {
        if (!flowId) {
            return;
        }

        var newRemark = remark;
        if (!remark) {
            newRemark = "There is no remark regarding this action.";
        }

        var parameters = {
            flowId: flowId,
            remark: newRemark
        };

        var newFlow = null;

        Restangular.all('flow').post(parameters)
            .then(function (response) {
                newFlow = response;
            });

        return newFlow;
    };

    service.isFlowAtEnd = function(flowId){
        // Restangular.all('flow').post(flowId)
        //     .then(function (response) {

        //     });

        // return newFlow;
    }

    service.updateFlow = function(flow){
        var newflow = null;

        Restangular.all('flow').post(flow)
            .then(function (response) {
                newFlow = response;
            });

        return newFlow;
    }

    service.deleteFlow = function(flowId){
        var isDeleted = null;

        Restangular.all('flow').post(flowId)
            .then(function (response) {
                isDeleted = response;
            });

        return isDeleted;
    }

});
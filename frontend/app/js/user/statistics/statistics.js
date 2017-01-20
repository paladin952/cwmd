angular.module('cwmd').component('statistics', {
    templateUrl: '/app/js/user/statistics/statistics.html',
    controller: function (RNDocumentSrv, DRDocumentSrv, FlowSrv) {
        var $ctrl = this;
        $ctrl.rnDocumentCount = "N/A";
        $ctrl.rnPartOfAFlowDocumentCount = "N/A";
        $ctrl.drDocumentCount = "N/A";
        $ctrl.drPartOfAFlowDocumentCount = "N/A";
        $ctrl.flowCount = "N/A";
        $ctrl.activeFlowCount = "N/A";
        $ctrl.finishedFlowCount = "N/A";

        $ctrl.$onInit = function () {
            RNDocumentSrv.getAllDocumentCount()
                .then(function (response) {
                    if (response == -1) {
                        $ctrl.rnDocumentCount = $ctrl.handleZeroValue();
                    } else if (response) {
                        $ctrl.rnDocumentCount = response;
                    }
                })
                .catch(function (response) {
                    console.log(response);
                });

            RNDocumentSrv.getAllPartOfAFlowDocumentCount()
                .then(function (response) {
                    if (response == -1) {
                        $ctrl.rnPartOfAFlowDocumentCount = $ctrl.handleZeroValue();
                    } else if (response) {
                        $ctrl.rnPartOfAFlowDocumentCount = response;
                    }
                })
                .catch(function (response) {
                    console.log(response);
                });

            DRDocumentSrv.getAllDocumentCount()
                .then(function (response) {
                    if (response == -1) {
                        $ctrl.drDocumentCount = $ctrl.handleZeroValue();
                    } else if (response) {
                        $ctrl.drDocumentCount = response;
                    }
                })
                .catch(function (response) {
                    console.log(response);
                });

            DRDocumentSrv.getAllPartOfAFlowDocumentCount()
                .then(function (response) {
                    if (response == -1) {
                        $ctrl.drPartOfAFlowDocumentCount = $ctrl.handleZeroValue();
                    } else if (response) {
                        $ctrl.drPartOfAFlowDocumentCount = response;
                    }
                })
                .catch(function (response) {
                    console.log(response);
                });

            FlowSrv.countAllFlows()
                .then(function (response) {
                    if (response == -1) {
                        $ctrl.flowCount = $ctrl.handleZeroValue();
                    } else if (response) {
                        $ctrl.flowCount = response;
                    }
                })
                .catch(function (response) {
                    console.log(response);
                });

            FlowSrv.countAllActiveFlows()
                .then(function (response) {
                    if (response == -1) {
                        $ctrl.activeFlowCount = $ctrl.handleZeroValue();
                    } else if (response) {
                        $ctrl.activeFlowCount = response;
                    }
                })
                .catch(function (response) {
                    console.log(response);
                });

            FlowSrv.countAllFinishedFlows()
                .then(function (response) {
                    if (response == -1) {
                        $ctrl.finishedFlowCount = $ctrl.handleZeroValue();
                    } else if (response) {
                        $ctrl.finishedFlowCount = response;
                    }
                })
                .catch(function (response) {
                    console.log(response);
                });
        };

        $ctrl.handleZeroValue = function () {
            return 0;
        };
    }
});
angular.module('cwmd').component('workingArea', {
    templateUrl: 'app/js/user/working-area/workingArea.html',
    controller: function (DRDocumentSrv, RNDocumentSrv) {
        var $ctrl = this;

        $ctrl.$onInit = function () {
            DRDocumentSrv.getDocuments()
                .then(function (response) {
                    $ctrl.drDocuments = response;
                })
                .catch(function (response) {
                    console.log(response);
                });
            RNDocumentSrv.getDocuments()
                .then(function (response) {
                    $ctrl.rnDocuments = response;
                })
                .catch(function (response) {
                    console.log(response);
                });
        };
    }
});